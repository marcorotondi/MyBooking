/**
 * 
 */

var appAdmin = angular.module("appAdmin", ['ngResource']);
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
}])