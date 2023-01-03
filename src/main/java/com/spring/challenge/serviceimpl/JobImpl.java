package com.spring.challenge.serviceimpl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.challenge.entities.JobApplication;
import com.spring.challenge.entities.User;
import com.spring.challenge.repository.CompanyRepository;
import com.spring.challenge.entities.Company;
import com.spring.challenge.entities.Job;
import com.spring.challenge.repository.JobRepository;
import com.spring.challenge.repository.UserRepository;
import com.spring.challenge.service.JobI;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class JobImpl implements JobI {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Job> retrieveAllJob() {
        List<Job> jobs = (List<Job>) jobRepository.findAll();
        return jobs;
    }

    @Override
    public Job addJob(Job j, String username) throws NoSuchFieldException {

        Company company = companyRepository.findByUsername(username).orElse(null);
        j.setPostedBy(company);
        List cities=getCities();
        List<String> cities2 = new ArrayList<>(cities.size());
        for (Object object : cities) {
            cities2.add(Objects.toString(object, null));
        }
        for(String inName:cities2){
            if(inName!=null)
                if(inName.contains(j.getLocation())){
                    jobRepository.save(j);
                }else {
                    System.out.println("Citie not found");
                    return null;
                    }
        }


        return j;
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Job updateJob(Job j) {
        return jobRepository.save(j);

    }

    @Override
    public Job findByIdJob(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return job;
    }

    @Override
    public Job retrieveJob(Long id) {
        Job job= jobRepository.findAllById(id);
        return null;
    }



    public List<Object> getCities() throws NoSuchFieldException {
        String url="https://geo.api.gouv.fr/departements/94/communes?fields=nom";
        RestTemplate restTemplate= new RestTemplate();
        Object cities = restTemplate.getForObject(url,Object.class);

        return Arrays.asList(cities);
    }

}
