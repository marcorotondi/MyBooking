/**
 * 
 */

var appAdmin = angular.module("appAdmin", ['ngResource']);

/* Configuration */
appAdmin.config(['$httpProvider', function ($httpProvider) {    
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
}]);

/* Controller */
appAdmin.controller("adminController", ['$scope', '$http', function($scope, $http) {
	$scope.addNewResource = function() {	
		var data = 'description=' + $scope.description + '&type=' + $scope.type;	
		
		var res = $http.post('/admin/', data);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			// hide section after save successful
			$('div.add').addClass('displayNone');	
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});		
		// Making the fields empty
		//
		$scope.description='';
		$scope.type='';
	}
}]);