package com.example.JobSeekingSystem.Controller;

import com.example.JobSeekingSystem.ApiResponse.ApiResponse;
import com.example.JobSeekingSystem.Model.User;
import com.example.JobSeekingSystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        if (userService.getAllUsers() == null)
            return ResponseEntity.status(400).body(new ApiResponse("There are no users yet"));

        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (userService.updateUser(id, user))
            return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has been updated successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("User with ID: " + id + " was not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has been deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("User with ID: " + id + " was not found"));
    }
}
