/**
 * 
 */

var appAdmin = angular.module("appAdmin", []);
appAdmin.controller("adminController", ['$scope', '$http', function($scope, $http) {
	$scope.addNewResource = function() {
		
		var dataObj = {
				description : $scope.description,
				type : $scope.type
		};	
		
		var res = $http.post('/admin/', dataObj);
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