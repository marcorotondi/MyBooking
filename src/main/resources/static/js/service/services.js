/**
 * Service My Booking AngularJS
 */
'use strict';

(function() {
	appAdmin.factory('ResourceService', ['$http', '$q', function($http, $q){
		var REST_SERVICE_URI = '/admin/api/';
		 
	    var factory = {
	        fetchAllResource: fetchAllResource,
	        createUpdateResource: createUpdateResource,
	        deleteResource: deleteResource,
	        summary: summary
	    };
	 
	    return factory;
	 
	    function fetchAllResource() {
	        var deferred = $q.defer();
	        
	        $http.get(REST_SERVICE_URI + "resources.json").then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Resource');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function createUpdateResource(resource) {
	        var deferred = $q.defer();
	        $http.post(REST_SERVICE_URI + "crudResource", resource).then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while creating Resource');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	 
	    function deleteResource(id) {
	        var deferred = $q.defer();
	        $http.delete(REST_SERVICE_URI + "/delete/resource/" + id).then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while deleting Resource');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    function summary() {
	    	var deferred = $q.defer();
	    	$http.get(REST_SERVICE_URI + "summary.json").then(
    			function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Resource');
	                deferred.reject(errResponse);
	            }
		    );
		    return deferred.promise;
	    }
	}]);
	
	appAdmin.factory('MessageService', ['$rootScope', function($rootScope){
		var messageService = {};
		
		messageService.signalResourceChange = function() {
			 $rootScope.$broadcast('handleResource');
		}
		
		return messageService;
	}]);
})();