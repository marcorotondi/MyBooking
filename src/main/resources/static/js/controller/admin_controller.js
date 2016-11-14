/**
 * Administrator Controller My Booking AngularJS
 */

'use strict';

(function() {
	
	/* Administrator Controller */
	appAdmin.controller("adminResourceController", 
			['$scope', '$filter', 'ResourceService', 'MessageService', 'NgTableParams', function($scope, $filter, ResourceService, MessageService, NgTableParams) {
				
			$scope.showForm = false;
			$scope.tableParams = new NgTableParams({ 
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
		
			$scope.addResource = function(isValid) {
				if (isValid) {
					var resource = {
							description: $scope.description,
							type: $scope.type
					};
					
					ResourceService.createUpdateResource(resource).then(
							function() {
								$scope.tableParams.reload();
								$scope.showForm = false;
								
								MessageService.signalResourceChange();
							},
							function(errResponse){
								console.error('Error while creating Resource');
							}
					);
				
					// Making the fields empty
					$scope.description = '';
					$scope.type = '';
					$scope.id = ''
				}
			}
			
			$scope.cancel = function () {
				$scope.tableParams.reload();
			}
		
			$scope.updateResource = function(row) {
				var resource = {
						id: row.id,
						description:row.description,
						type: row.type
				};
				
				ResourceService.createUpdateResource(resource).then(
						function() {
							$scope.tableParams.reload();
							row.isEditing = false;
							MessageService.signalResourceChange();
						},
						function(errResponse){
							console.error('Error while update Resource');
						}
				);
			}
			
			$scope.delResource = function(row) {
				var confimDel = confirm("Are You Scure to Remove this resource?");
				if (confimDel) {
					ResourceService.deleteResource(row.id).then(
							function() {
								$scope.tableParams.reload();
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
		function loadCounter() {
			ResourceService.summary().then(
				function(data){
					$scope.resource = {
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