/**
 * Scheduler Service My Booking AngularJS
 */
'use strict';

(function() {
	appScheduler.factory('SchedulerService', ['$http', '$q', function($http, $q){
		var REST_SERVICE_URI = '/public/api/';
		
		var appointmentSource = {
	        	async: false,
	        	dataType: "json", 
	            dataFields: [
	                { name: 'id', type: 'string' },
	                { name: 'description', type: 'string' },
	                { name: 'location', type: 'string' },
	                { name: 'subject', type: 'string' },
	                { name: 'calendar', type: 'string' },
	                { name: 'start', type: 'date' },
	                { name: 'end', type: 'date' },
	                { name: 'draggable', type: 'boolean'},
	                { name: 'resizable', type: 'boolean'}
	            ],
	            id: 'id',
	            url: "/public/api/schedulers",
	        };
		
		var resourceSource = {
            	async: false,
            	dataType: "json", 
                dataFields: [
                    { name: 'id', type: 'string' },
                    { name: 'description', type: 'string' },
                    { name: 'location', type: 'string' },
                    { name: 'subject', type: 'string' },
                    { name: 'calendar', type: 'string' }
                ],
                id: 'id',
                url: "/public/api/resources",
            };
		
		var factory = {
		        appointment: appointment,
		        deleteAppointment: deleteAppointment,
		        appointmentSource: appointmentSource,
		        resourceSource: resourceSource
		};
		 
		return factory;
		
		function appointment(schedulerData) {
			var deferred = $q.defer();
	        
			$http.post(REST_SERVICE_URI + "appointment/create", schedulerData).then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error(errResponse.data.errorMessage);
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