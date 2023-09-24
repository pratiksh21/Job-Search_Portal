package com.geekster.weeklytest.project.JobSearchPortal.Service;


import com.geekster.weeklytest.project.JobSearchPortal.Model.Job;
import com.geekster.weeklytest.project.JobSearchPortal.Model.JobType;
import com.geekster.weeklytest.project.JobSearchPortal.Repository.IJobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    IJobRepo iJobRepo;

    public Iterable<Job> getAllJobs() {
        return iJobRepo.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return iJobRepo.findById(id);
    }

    public String postAJob(Job j) {
        iJobRepo.save(j);
        return "New Job Posted !!!";
    }

    public String postJobs(List<Job> j) {
        iJobRepo.saveAll(j);
        return "New Jobs Posted !!!";
    }

    public String updateSalaryById(Long id, Double salary) {
        Job j = iJobRepo.findById(id).orElse(null);
        if(j==null)
        {
            return "Please Enter the Correct job id";
        }
        j.setSalary(salary);
        iJobRepo.save(j);
        return "Salary updated!!!";
    }

    public String updateLocationById(Long id, String location) {
        Job j = iJobRepo.findById(id).orElse(null);
        if(j==null)
        {
            return "Please Enter the Correct job id";
        }
        j.setLocation(location);
        iJobRepo.save(j);
        return "Location updated!!!";
    }

    public String updateEmailById(Long id, String email) {
        Job j = iJobRepo.findById(id).orElse(null);
        if(j==null)
        {
            return "Please Enter the Correct job id";
        }
        j.setCompanyEmail(email);
        iJobRepo.save(j);
        return "Email updated!!!";
    }

    public String removeJobById(Long id) {
        Job j = iJobRepo.findById(id).orElse(null);
        if(j==null)
        {
            return "Id Not Found";
        }

        iJobRepo.delete(j);
        return "Job Removed";
    }

    public List<Job> getAllSameTypeJobs(JobType type) {
        return iJobRepo.findByJobType(type);
    }

    public List<Job> getAllJobsInASalaryRange(Double salary) {

        return  iJobRepo.findBySalaryGreaterThanEqual(salary);
    }

    public List<Job> getAllJobsWithTheSameCompany(String cName) {

        return  iJobRepo.findByCompanyName(cName);
    }

    public String removeAllJobsOfTheSameCompany(String cName) {

        iJobRepo.deleteJobsFromTheSameCompany(cName);

        return"Jobs Deleted from the Same Company";
    }

    public String updateSalaryOfSimilarJobType(JobType type) {

        iJobRepo.updateAllSalaryOfSimilarType(type.name());

        return "Salaries Updated";
    }
}
