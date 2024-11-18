package com.itsjarvis.JobAppRESTProject.controller;

import com.itsjarvis.JobAppRESTProject.model.JobPost;
import com.itsjarvis.JobAppRESTProject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class JobController {

    @Autowired
    private JobService jobService;


    @PostMapping("/jobPost")
    public Optional<JobPost> addJob(@RequestBody JobPost jobPost) {
         jobService.addJobPost(jobPost);
         return getJob(jobPost.getPostId());
    }

//     @PostMapping(value = "/jobPost",consumes = "application/xml")
//    public JobPost addJob(@RequestBody JobPost jobPost) {
//        jobService.addJobPost(jobPost);
//        return getJob(jobPost.getPostId());
//    }

    @PutMapping("/jobPost")
    public Optional<JobPost> updateJobPost(@RequestBody JobPost jobPost) {
        jobService.updateJobPost(jobPost);
        return getJob(jobPost.getPostId());
    }

    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobPosts() {
        return jobService.getAllJobPosts();
    }

//    @GetMapping(value = "/jobPosts", produces = "application/xml")
//    public List<JobPost> getAllJobPosts() {
//        return jobService.getAllJobPosts();
//    }

    @GetMapping("/jobPost/{id}")
    public Optional<JobPost> getJob(@PathVariable int id) {
        return jobService.getJob(id);

    }

    @DeleteMapping("/jobPost/{id}")
    public void deleteJobPost(@PathVariable(name = "id") int id) {
        jobService.deleteJobPost(id);
    }

    @GetMapping("/load")
    public List<JobPost> load() {
        return jobService.load();
    }

    @GetMapping("/jobPosts/search/{keyword}")
    public List<JobPost> getJobPostsFromSearch(@PathVariable(value= "keyword") String keyword) {
        return jobService.getJobPostsFromSearch(keyword);
    }
}
