/**
 * Controller My Booking AngularJS
 */

'use strict';

(function() {
	
	/* Administrator Controller */
	appAdmin.controller("adminResourceController", 
			['$scope', '$filter', 'ResourceService', 'NgTableParams', function($scope, $filter, ResourceService, NgTableParams) {
				
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
		
			$scope.addResource = function() {	
				var resource = {
						description: $scope.description,
						type: $scope.type
				};
				
				ResourceService.createUpdateResource(resource).then(
						function() {
							$scope.tableParams.reload();
							$scope.showForm = false;
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
							},
							function(errResponse){
								console.error('Error while delete Resource');
							});
				}
			}
	}]);
	
	appAdmin.controller("adminSummaryController", ['$scope', 'ResourceService', function($scope, ResourceService) {
		$scope.resource = {};
		
		var summaryData = ResourceService.summary().then(
				function(data){
					
				}, 
				function(errResponse){
					console.error('Error while fetch Summary data');
				}
			 );
		
		console.info("summary data is: " + summaryData);
	}]);
	
})();