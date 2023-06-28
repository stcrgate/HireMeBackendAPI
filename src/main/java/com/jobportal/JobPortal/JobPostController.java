package com.jobportal.JobPortal;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostController {

    @Autowired
    JobPostRepo jobRepo;

    @GetMapping("/alljobs")
    @CrossOrigin
    public List<JobPostModel> getAllJobs(){
        return jobRepo.findAll();
    }

    @GetMapping("/search/{text}")
    @CrossOrigin
    public List<JobPostModel> search(@PathVariable String text){
        return jobRepo.jobSearch(text);
    }

    @PostMapping("/addjobs")
    @CrossOrigin
    public JobPostModel addJobs(@RequestBody JobPostModel job){
        return jobRepo.save(job);
    }

    @PutMapping("/updatejob/{id}")
    @CrossOrigin
    public JobPostModel updateJob(@PathVariable String id, @RequestBody JobPostModel updatedJob) {
        Optional<JobPostModel> existingJobOptional = jobRepo.findById(id);
        if (existingJobOptional.isPresent()) {
            JobPostModel existingJob = existingJobOptional.get();

            existingJob.setProfile(updatedJob.getProfile());
            existingJob.setDesc(updatedJob.getDesc());
            existingJob.setExp(updatedJob.getExp());
            existingJob.setTechs(updatedJob.getTechs());

            return jobRepo.save(existingJob);
        } else {
            return null;

        }
    }

    @DeleteMapping("/deletejob/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteJob(@PathVariable String id) {
        if (jobRepo.existsById(id)) {
            jobRepo.deleteById(id);
            return ResponseEntity.ok().body("Deleted Successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
