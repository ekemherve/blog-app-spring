package io.learning.blogappspring.controller;

import io.learning.blogappspring.model.Post;
import io.learning.blogappspring.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/post")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {

        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity findById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(postService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Post post) {

        try {
            return ResponseEntity.ok(postService.save(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Post post) {
        try {
            return ResponseEntity.ok(postService.update(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
