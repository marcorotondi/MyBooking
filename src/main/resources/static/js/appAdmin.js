/**
 * My Booking AngularJS
 */

var appAdmin = angular.module("appAdmin", ['ngResource', 'ngTable']);

(function() {
	/* Controller */
	appAdmin.controller("adminAddController", ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
		$scope.addNewResource = function() {	
			var data = 'description=' + $scope.description + '&type=' + $scope.type;
			var config = {
				headers : {
					'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
	        }
			
			$http.post('/admin/addResource', data, config).then(function(response){
				$rootScope.$broadcast("addedNewResource", data);
				$('div.add').addClass('displayNone');	
			}, function(response){
				alert(response);
			});
			
			// Making the fields empty
			$scope.description='';
			$scope.type='';
		}
	}]);

	appAdmin.controller('adminChangeController', ['$scope', '$http', function($scope, $http) {
	}]);

	appAdmin.controller("adminTableController", ['$scope', '$http', '$filter', 'NgTableParams', function($scope, $http, $filter, NgTableParams){
		$scope.$on("addedNewResource", function(e, newResource){
			$scope.tableParams.reload();
	    });
		
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
	}]);
})();
