package com.niit.backend.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.backend.dao.JobDao;
import com.niit.backend.model.Job;
import com.niit.backend.model.User;
import com.niit.backend.model.BlogPost;
import com.niit.backend.model.Error;


@RestController
public class JobController {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
private JobDao jobDao;
    @RequestMapping(value="/postJob",method=RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody Job job,HttpSession session){
		User user=(User)session.getAttribute("user");	
		if(user==null){	
			Error error=new Error(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
			//   return response object with data [error], status [401]
			// in front end we can get error message by response.data.message
		}
		else{
			logger.debug(user.getUsername());//NullPointerException is user is null
			System.out.println(" Role of User " + user.getRole());
	                job.setPostedOn(new Date());
					jobDao.postJob(job);
					return new ResponseEntity<Void>(HttpStatus.OK);
			
		}
	
    }
    @RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
    public ResponseEntity<?> getAllJobs(HttpSession session){
    	User user=(User)session.getAttribute("user");
    	if(user==null){
    		System.out.println("USER is null");
    		Error error=new Error(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
    	}
    	System.out.println("USER OBJECT " + user.getRole());
    	List<Job> jobs=jobDao.getAllJobs();
    	return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    	//response 
    }
   
    
    
    @RequestMapping(value="/getJobDetail/{jobId}",method=RequestMethod.GET)
    public ResponseEntity<?> getJobDetail(@PathVariable(value="jobId")int jobId,
    		HttpSession session){
    		User user=(User)session.getAttribute("user");
    		if(user==null){
    			System.out.println("USER is null");
    			Error error=new Error(1,"Unauthorized user.. login using valid credentials");
    			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401
    		}
    	logger.debug("JobId "+ jobId);
    	Job jobDetail=jobDao.getJobDetail(jobId);
    	return new ResponseEntity<Job>(jobDetail,HttpStatus.OK);
    }
    
    @RequestMapping(value="/get/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBlog(@PathVariable("id") int id,HttpSession session) {
    	User user=(User)session.getAttribute("user");
    	System.out.println("Delete function at job controller1");
    	Job jobpost = jobDao.getJobPost(id);
    			if(jobpost==null)
    			{
    				System.out.println("Delete function at blog controller2");
    				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    			}
    			System.out.println("Delete function at blog controller3");
    			jobDao.deleteJob(id);
    			

    			return new ResponseEntity<Void>(HttpStatus.OK);
    		}
}