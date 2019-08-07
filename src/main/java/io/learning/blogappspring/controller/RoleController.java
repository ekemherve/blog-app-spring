package io.learning.blogappspring.controller;

import io.learning.blogappspring.model.Role;
import io.learning.blogappspring.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/role")
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {

        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity findById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(roleService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Role role) {

        try {
            return ResponseEntity.ok(roleService.save(role));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Role role) {
        try {
            return ResponseEntity.ok(roleService.update(role));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
