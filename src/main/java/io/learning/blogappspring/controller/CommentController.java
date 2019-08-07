package io.learning.blogappspring.controller;

import io.learning.blogappspring.model.Comment;
import io.learning.blogappspring.model.Post;
import io.learning.blogappspring.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/comment")
@CrossOrigin("*")
public class CommentController {
    
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {

        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity findById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(commentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Comment comment) {

        try {
            return ResponseEntity.ok(commentService.save(comment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Comment comment) {
        try {
            return ResponseEntity.ok(commentService.update(comment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
