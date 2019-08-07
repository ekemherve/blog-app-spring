package io.learning.blogappspring.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.learning.blogappspring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.learning.blogappspring.model.Constant.HEADER_STRING;
import static io.learning.blogappspring.model.Constant.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = httpServletRequest.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;

        if(header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");

            try {
                username = tokenProvider.getUsernameFromToken(authToken);
            }catch (IllegalArgumentException e) {
                logger.error("An error occured during getting username from token ", e);
            }catch (ExpiredJwtException e) {
                logger.error("The token is already expired nad thus, not valid");
            }catch (SignatureException e) {
                logger.error("The Authentication failed. Username or password not valid !!!");
            }
        }else {
            logger.warn("Couldn't find bearer string, will ingore the header");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(tokenProvider.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        tokenProvider.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("Authentication user " + username + ", setting security context");
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
