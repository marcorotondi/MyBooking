/**
 * Administrator Controller My Booking AngularJS
 */

'use strict';

(function() {
	
	/* Administrator Controller */
	appAdmin.controller("adminResourceController", ['$filter', 'ResourceService', 'MessageService', 'NgTableParams', function($filter, ResourceService, MessageService, NgTableParams) {
			var self = this;	
				
			self.showForm = false;
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
		
			self.addResource = function(isValid) {
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
				
					// Making the fields empty
					self.description = '';
					self.type = '';
					self.id = ''
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
		}
		
		loadCounter();
		
		$scope.$on('handleResource', function() {
			loadCounter();
		});
	}]);
	
})();