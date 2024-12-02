package com.example.JobSeekingSystem.Controller;

import com.example.JobSeekingSystem.ApiResponse.ApiResponse;
import com.example.JobSeekingSystem.Model.JobPost;
import com.example.JobSeekingSystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-posts")
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        if (jobPostService.getAllJobPosts() == null)
            return ResponseEntity.status(400).body(new ApiResponse("There are no job posts yet"));

        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job Post has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (jobPostService.updateJobPost(id, jobPost))
            return ResponseEntity.status(200).body(new ApiResponse("Job Post with ID: " + id + " has been updated successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Job Post with ID: " + id + " was not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        if (jobPostService.deleteJobPost(id))
            return ResponseEntity.status(200).body(new ApiResponse("Job Post with ID: " + id + " has been deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Job Post with ID: " + id + " was not found"));
    }
}
