/**
 * Scheduler Service My Booking AngularJS
 */
'use strict';

(function() {
	appScheduler.factory('SchedulerService', ['$http', '$q', function($http, $q){
		var REST_SERVICE_URI = '/public/api/';
		
		var factory = {
		        appointment: appointment,
		        deleteAppointment: deleteAppointment
		};
		 
		return factory;
		
		function appointment(schedulerData) {
			var deferred = $q.defer();
	        
			$http.post(REST_SERVICE_URI + "appointment/create", schedulerData).then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while creating or update Appoitment');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
		}
		
		function deleteAppointment(appointmentId, checkCode) {
			var deferred = $q.defer();
			var parameterUrl = appointmentId + "/" + checkCode;
	        
			$http.delete(REST_SERVICE_URI + "appointment/delete/" + parameterUrl, {}).then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while deleting Appointment');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
		}
	}]);
})();