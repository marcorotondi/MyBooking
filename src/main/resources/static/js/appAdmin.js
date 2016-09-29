/**
 * 
 */

var appAdmin = angular.module("appAdmin", ['ngResource', 'ngTable']);

(function() {
	/* Controller */
	appAdmin.controller("adminAddController", ['$scope', '$http', function($scope, $http) {
		$scope.addNewResource = function() {	
			var data = 'description=' + $scope.description + '&type=' + $scope.type;
			var config = {
					headers : {
						'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
	        }
			
			$http.post('/admin/addResource', data, config)
			.success(function (data, status, headers, config) {
				console.info(data);
				$('div.add').addClass('displayNone');	
	        })
	        .error(function (data, status, header, config) {
	        	alert(data);
	        });
			
			// Making the fields empty
			//
			$scope.description='';
			$scope.type='';
		}
	}]);

	appAdmin.controller('adminChangeController', ['$scope', '$http', function($scope, $http) {
	}]);

	appAdmin.controller("adminTableController", ['$scope', '$http', 'NgTableParams', function($scope, $http, NgTableParams){
		$http.get('http://localhost:9090/admin/api/resources.json')
			.success(function(data, status) {
				$scope.tableParams = new NgTableParams({
					page: 1,
		            count: 10,
					sorting: {
						name: 'asc'     // initial sorting
					}
				}, { 
					total: data.length,
					dataset: data
				});
			});
	}]);
})();
