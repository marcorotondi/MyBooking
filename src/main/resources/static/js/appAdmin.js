/**
 * 
 */

var appAdmin = angular.module("appAdmin", ['ngResource', 'ngTable']);
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

appAdmin.controller("demoController", demoController);
demoController.$inject = ["NgTableParams"];

function demoController(NgTableParams, simpleList) {
  this.defaultConfigTableParams = new NgTableParams({}, { dataset: [{name:'Marco', age: 99}]});
  this.customConfigParams = createUsingFullOptions();

  function createUsingFullOptions() {
    var initialParams = {
      count: 5 // initial page size
    };
    var initialSettings = {
      // page size buttons (right set of buttons in demo)
      counts: [],
      // determines the pager buttons (left set of buttons in demo)
      paginationMaxBlocks: 13,
      paginationMinBlocks: 2,
      dataset: simpleList
    };
    return new NgTableParams(initialParams, initialSettings);
  }
}
