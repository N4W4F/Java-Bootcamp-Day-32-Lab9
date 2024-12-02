package com.example.JobSeekingSystem.Service;

import com.example.JobSeekingSystem.Model.JobPost;
import com.example.JobSeekingSystem.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts() {
        if (jobPostRepository.findAll().isEmpty())
            return null;

        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer id, JobPost jobPost) {
        JobPost oldJobPost = findJobPostById(id);
        if (oldJobPost == null) return false;

        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setPostingDate(jobPost.getPostingDate());
        jobPostRepository.save(oldJobPost);
        return true;
    }

    public Boolean deleteJobPost(Integer id) {
        JobPost oldJobPost = findJobPostById(id);
        if (oldJobPost == null) return false;

        jobPostRepository.delete(oldJobPost);
        return true;
    }

    private JobPost findJobPostById(Integer id) {
        for (JobPost j : jobPostRepository.findAll())
            if (j.getId().equals(id))
                return j;

        return null;
    }
}
