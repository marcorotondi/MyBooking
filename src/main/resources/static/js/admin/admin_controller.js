/**
 * Administrator Controller My Booking AngularJS
 */

'use strict';

(function() {
	
	/* Administrator Controller */
	appAdmin.controller("adminResourceController", ['$filter', 'ResourceService', 'MessageService', 'NgTableParams', function($filter, ResourceService, MessageService, NgTableParams) {
			var self = this;	
				
			self.showForm = false;
			self.resourceTypes = [];
			
			ResourceService.resources().then(function(data) {
				self.resourceTypes = data;
			});
			
			self.tableParams = new NgTableParams({ 
		    	sorting: {
		    		description: 'asc'     
		        }
		    },{
		    	total: 0,
		        getData: function(params) {
		        	return ResourceService.fetchAllResource().then(
	        			function(d) {
			            	params.total(d.length);
			            	var rows = $filter('orderBy')(d, params.orderBy());
				        	rows = rows.slice((params.page() - 1) * params.count(), params.page() * params.count());
				        	
				        	return rows;
			             },
			             function(errResponse){
			            	console.error(response);
			             }
		        	);
		        }
		    });
			
			self.addResource = function(form, isValid) {
				if (isValid) {
					var resource = {
							description: self.description,
							type: self.type
					};
					
					ResourceService.createUpdateResource(resource).then(
							function() {
								self.tableParams.reload();
								self.showForm = false;
								
								MessageService.signalResourceChange();
							},
							function(errResponse){
								console.error('Error while creating Resource');
							}
					);
					
					if (form) {
						form.$setPristine(); 
						
						// Making the fields empty
						self.description = '';
						self.type = '';
						self.id = ''
					}
				}
			}
			
			self.resetForm = function(form) {
				if (form) {
					form.$setPristine(); 
					form.$setValidity();
					form.$setUntouched();
					
					// Making the fields empty
					self.description = '';
					self.type = '';
					self.id = ''
				}
			}
			
			self.cancel = function () {
				self.tableParams.reload();
			}
		
			self.updateResource = function(row) {
				var resource = {
						id: row.id,
						description:row.description,
						type: row.type
				};
				
				ResourceService.createUpdateResource(resource).then(
						function() {
							self.tableParams.reload();
							row.isEditing = false;
							MessageService.signalResourceChange();
						},
						function(errResponse){
							console.error('Error while update Resource');
						}
				);
			}
			
			self.delResource = function(row) {
				var confimDel = confirm("Are You Scure to Remove this resource?");
				if (confimDel) {
					ResourceService.deleteResource(row.id).then(
						function() {
							self.tableParams.reload();
							row.isEditing = false;
							MessageService.signalResourceChange();
						},
						function(errResponse){
							console.error('Error while delete Resource');
						});
				}
			}
	}]);
	
	appAdmin.controller("adminSummaryController", ['$scope', 'ResourceService', 'MessageService', function($scope, ResourceService, MessageService) {
		var self = this;
		function loadCounter() {
			ResourceService.summary().then(
				function(data){
					self.resource = {
							count_res: data.resource_count,
							count_room: data.resource_room,
							count_car: data.resource_car,
							count_obj: data.resource_obj
					}
				}, 
				function(errResponse){
					console.error('Error while fetch Summary data');
				}
			);
		};
		
		function loadSchedulerCounter() {
			ResourceService.scheduler().then(
					function(data) {
						self.scheduler = { };
						
						Object.keys(data).forEach(function(key) {
							self.scheduler[key] = data[key];
						});
					},
					function(errResponse) {
						console.error("Error while fetsh Scheduler Summary Data")
					}
			);
		};
		
		loadCounter();
		loadSchedulerCounter();
		
		$scope.$on('handleResource', function() {
			loadCounter();
		});
	}]);
	
	appAdmin.controller("adminBookingController", ['$scope', 'AdminBookingService', '$filter', 'NgTableParams', function($scope, AdminBookingService, $filter, NgTableParams) {
		var self = this;	
		
		self.tableParams = new NgTableParams({ 
	    	sorting: {
	    		from: 'desc'     
	        }
	    },{
	    	total: 0,
	        getData: function(params) {
	        	return AdminBookingService.getBookingList().then(
        			function(d) {
		            	params.total(d.length);
		            	var rows = $filter('orderBy')(d, params.orderBy());
			        	rows = rows.slice((params.page() - 1) * params.count(), params.page() * params.count());
			        	
			        	return rows;
		             },
		             function(errResponse){
		            	console.error(response);
		             }
	        	);
	        }
	    });
		
		self.deleteBooking = function(id) {
			var confimDel = confirm("Are You Scure to Remove this booking?");
			if (confimDel) {
				AdminBookingService.adminDelete(id).then(
						function(response) {
							if (200 === response.status) {
								self.tableParams.reload();
							}
						},
						function(errResponse){
							console.error('Error while delete Booking');
						});
			}
		}
	}]);
	
})();