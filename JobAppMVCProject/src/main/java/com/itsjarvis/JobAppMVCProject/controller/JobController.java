package com.itsjarvis.JobAppMVCProject.controller;

import com.itsjarvis.JobAppMVCProject.model.JobPost;
import com.itsjarvis.JobAppMVCProject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping({"/","/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/addjob")
    public String addJob() {
        return "addjob";
    }

    @PostMapping("/handleForm")
    public String handleJobPost(JobPost jobPost) {
        jobService.addJobPost(jobPost);
        return "success";
    }

    @GetMapping("/viewalljobs")
    public String getAllJobPosts(Model model) {
        List<JobPost> jobs = jobService.getAllJobPosts();
        model.addAttribute("jobPosts",jobs);
        return "viewallJobs";
    }

    @GetMapping("/viewJob")
    public String getJob(@RequestParam int id, Model model) {
        JobPost jobPost = jobService.getJob(id);
        model.addAttribute(jobPost);
        return "viewJob";
    }

    @GetMapping("/deleteJob/{id}")
    public String deleteJobPost(@PathVariable(name = "id") int id) {
        jobService.deleteJobPost(id);
        return "viewallJobs";
    }

}
