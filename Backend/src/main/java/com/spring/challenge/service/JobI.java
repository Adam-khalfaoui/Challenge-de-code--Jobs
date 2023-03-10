package com.spring.challenge.service;

import com.spring.challenge.entities.Job;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface JobI {

    List<Job> retrieveAllJob();

    Job addJob(Job j, String username) throws NoSuchFieldException;

    @Transactional
    void deleteJob(Long j);

    Job updateJob(Job j);

    Job findByIdJob(Long id);

    Job  retrieveJob(Long id);

    void store(MultipartFile file) throws IOException;

    void addView(Long id);


}
