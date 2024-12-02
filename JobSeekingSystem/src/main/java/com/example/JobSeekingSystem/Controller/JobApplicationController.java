package com.example.JobSeekingSystem.Controller;

import com.example.JobSeekingSystem.ApiResponse.ApiResponse;
import com.example.JobSeekingSystem.Model.JobApplication;
import com.example.JobSeekingSystem.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-applications")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllJobApplication() {
        if (jobApplicationService.getAllJobApplication() == null)
            return ResponseEntity.status(400).body(new ApiResponse("There are no job applications yet"));

        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplication());
    }

    @PostMapping("/apply")
    public ResponseEntity applyForJob(@RequestBody @Valid JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (jobApplicationService.applyForJob(jobApplication))
            return ResponseEntity.status(200).body(new ApiResponse("You have applied for this job successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("User with ID: " + jobApplication.getUser_id() + " was not found"));
    }

    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity withdrawFromJob(@PathVariable Integer id) {
        if (jobApplicationService.withdrawFromJob(id))
            return ResponseEntity.status(200).body(new ApiResponse("You have withdrawn from this job successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Job Application with ID: " + id + " was not found"));
    }
}
