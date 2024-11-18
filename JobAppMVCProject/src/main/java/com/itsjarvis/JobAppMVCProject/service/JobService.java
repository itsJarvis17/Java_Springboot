package com.itsjarvis.JobAppMVCProject.service;

import com.itsjarvis.JobAppMVCProject.model.JobPost;
import com.itsjarvis.JobAppMVCProject.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    public List<JobPost> getAllJobPosts() {
        return jobRepo.getAllJobPosts();
    }

    public void addJobPost(JobPost jobPost) {
        jobRepo.addJobPost(jobPost);
    }

    public JobPost getJob(int id) {
        return jobRepo.getJob(id);
    }

    public void deleteJobPost(int id) {
        jobRepo.deleteJobPost(id);
    }
}
