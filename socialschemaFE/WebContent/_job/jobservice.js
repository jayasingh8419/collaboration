
app.factory('JobService',function($http){
	var jobService={};
	var BASE_URL ="http://localhost:9070/socialschema";
	jobService.saveJob=function(job){
		return $http.post(BASE_URL + "/postJob" , job);
	}
	
	
	
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL + "/getAllJobs");
	}
	
	
	jobService.getJobDetail=function(jobId){
		return $http.get(BASE_URL + "/getJobDetail/"+jobId)
	};
	
	
jobService.deleteJob=function(id){
		
		console.log("entering delete person in service with id"+id);
		return $http.delete(BASE_URL + "/job/get/"+id)
		.then(function(response){
			console.log(response.status)
					return response.status;
		},function(response){
			
			console.log(response.status)

		});

	};
	return jobService;
})
