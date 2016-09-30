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

	appAdmin.controller("adminTableController", ['$scope', '$http', 'NgTableParams', function($scope, $http, NgTableParams){
		$scope.$on("addedNewResource", function(e, newResource){
			$scope.tableParams.reload();
	    });
		
	    $scope.tableParams = new NgTableParams(
	      {
	        page: 1,            // show first page
	        count: 10,           // count per page
	        sorting: {name:'asc'}
	      },
	      {
	        total: 0, // length of data
	        getData: function(params) {
	        	return $http.get('/admin/api/resources.json').then(function(response){
	        		params.total(response.data.lenght);
	        		return response.data;
	        	}, function(response) {
	        		console.error(response);
	        	});
	        }
	    });
	}]);
})();
