package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.BlogPost;
import com.niit.backend.model.Job;

public interface JobDao {
	void postJob(Job job);
	List<Job> getAllJobs();
	Job getJobDetail(int jobId);
	void deleteJob(int id);
	Job getJobPost(int id);
 
}
