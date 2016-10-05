/**
 * My Booking AngularJS
 */

var appAdmin = angular.module("appAdmin", ['ngResource', 'ngTable']);

(function() {
	/* Admin Controller */
	appAdmin.controller("adminResourceController", ['$scope', '$http', '$filter', 'NgTableParams', function($scope, $http, $filter, NgTableParams) {
			$scope.showForm = false;
			$scope.tableParams = new NgTableParams({ 
		    	sorting: {
		            id: 'asc'     
		        }
		    },{
		    	total: 0,
		        getData: function(params) {
		        	return $http.get('/admin/api/resources.json').then(function(response){
		        		params.total(response.data.length);
		        		var rows = $filter('orderBy')(response.data, params.orderBy());
		        		rows = rows.slice((params.page() - 1) * params.count(), params.page() * params.count());
		        		return rows;
		        	}, function(response) {
		        		console.error(response);
		        	});
		        }
		    });
		
			$scope.addChangeResource = function() {	
				var id = ($scope.id == undefined ? "" : $scope.id);
				var data = 'id=' + id + '&description=' + $scope.description + '&type=' + $scope.type;
				var config = {
					headers : {
						'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
		        }
				
				$http.post('/admin/crudResource', data, config).then(function(response){
					$scope.tableParams.reload();
					$scope.showForm = false;
					//$('button.add').removeClass('active');
				}, function(response){
					alert(response);
				});
				
				// Making the fields empty
				$scope.description = '';
				$scope.type = '';
				$scope.id = ''
			}
		
			$scope.editRow = function(row) {
				$scope.id = row.id;
				$scope.description = row.description;
				$scope.type = row.type;
			}
	}]);
})();