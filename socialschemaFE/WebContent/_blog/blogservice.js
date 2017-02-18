app.factory('BlogService',function($http){
	var BASE_URL="http://localhost:9070/socialschema";
	var blogService=this;
	blogService.addPost=function(blogPost){
		console.log('addpost in service')
		return $http.post("http://localhost:9070/socialschema/blog",blogPost);
	}
	
	
	blogService.getBlogPosts=function(){
		console.log('getBlogposts in service')
		return $http.get("http://localhost:9070/socialschema/blog/list")
	};
	
	
	blogService.getBlogDetail=function(id){
		console.log('getBlogDetails')
		return $http.get("http://localhost:9070/socialschema/blog/get/"+ id)
	};
	
	blogService.addComment=function(comment){
		return $http.post("http://localhost:9070/socialschema/blog/comment",comment);
	}
	
	
	blogService.getComments=function(blogId){
		console.log('getcomments -- service')
		return $http.get("http://localhost:9070/socialschema/blog/getcomments/"+blogId);
	}
	
	
	
	
blogService.deleteBlog=function(id){
		
		console.log("entering delete person in service with id"+id);
		return $http.delete(BASE_URL + "/blog/get/"+id)
		.then(function(response){
			console.log(response.status)
					return response.status;
		},function(response){
			
			console.log(response.status)

		});

	};
	

	return blogService;
})