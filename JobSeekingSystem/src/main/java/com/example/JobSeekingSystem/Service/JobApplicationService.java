package com.example.JobSeekingSystem.Service;

import com.example.JobSeekingSystem.Model.JobApplication;
import com.example.JobSeekingSystem.Model.User;
import com.example.JobSeekingSystem.Repository.JobApplicationRepository;
import com.example.JobSeekingSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public List<JobApplication> getAllJobApplication() {
        if (jobApplicationRepository.findAll().isEmpty())
            return null;

        return jobApplicationRepository.findAll();
    }

    public Boolean applyForJob(JobApplication jobApplication) {
        for (User u : userRepository.findAll())
            if (u.getId().equals(jobApplication.getUser_id())) {
                jobApplicationRepository.save(jobApplication);
                return true;
            }

        return false;
    }

    public Boolean withdrawFromJob(Integer id) {
        JobApplication oldJobApplication = findJobApplicationById(id);
        if (oldJobApplication == null)
            return false;

        jobApplicationRepository.delete(oldJobApplication);
        return true;
    }

    private JobApplication findJobApplicationById(Integer id) {
        for (JobApplication j : jobApplicationRepository.findAll())
            if (j.getId().equals(id))
                return j;

        return null;
    }
}
