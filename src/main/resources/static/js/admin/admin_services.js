/**
 * Administrator Service My Booking AngularJS
 */
'use strict';

(function() {
	appAdmin.factory('ResourceService', ['$http', '$q', function($http, $q){
		var REST_SERVICE_URI = '/admin/api/';
		 
	    var factory = {
	    	resources: function() {
	    		var deferred = $q.defer();
	
	    		$http.get(REST_SERVICE_URI + "resourcesType.json").then(
	    				function (response){
	    					deferred.resolve(response.data);
	    				},
	    				function (errResponse){
	    					console.error('Error while fetching Resource');
	    					deferred.reject(errResponse);
	    				});
	
	    		return deferred.promise;
	    	},
	        fetchAllResource: function () {
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
		    },
	        createUpdateResource: function (resource) {
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
		    },
	        deleteResource: function (id) {
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
		    },
	        summary: function () {
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
		    },
		    scheduler: function() {
		    	var deferred = $q.defer();
		    	$http.get(REST_SERVICE_URI + "summary/booking.json").then(
	    			function(response) {
	    				deferred.resolve(response.data)
	    			},
	    			function(errResponse) {
	    				 console.error('Error while fetching Booking');
			                deferred.reject(errResponse);
	    			}
		    	);
		    	return deferred.promise;
		    }
	    };
	    
	    return factory;
	}]);
	
	appAdmin.factory('MessageService', ['$rootScope', function($rootScope){
		var messageService = {};
		
		messageService.signalResourceChange = function() {
			 $rootScope.$broadcast('handleResource');
		}
		
		return messageService;
	}]);
	
	appAdmin.factory('AdminBookingService', ['$rootScope', function($rootScope) {
		var adminBookingService = {};
		
		
		return adminBookingService;
	}]);
})();