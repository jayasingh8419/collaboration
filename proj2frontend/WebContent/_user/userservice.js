app.factory('UserService',function($http){
	var userService=this;
		var BASE_URL="http://localhost:8084/proj2backend"
		
	userService.authenticate=function(user){
			console.log('entering user service ')
			
		return $http.post(BASE_URL + "/login",user);
	}
	
	userService.registerUser=function(user){
		return $http.post(BASE_URL + "/register",user) 
	}
	
	return userService;
})