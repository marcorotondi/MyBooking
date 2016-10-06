/**
 * My Booking AngularJS
 */

var underscore = angular.module('underscore', []);
	underscore.factory('_', ['$window', function($window) {
	  return $window._; // assumes underscore has already been loaded on the page
}]);

var appAdmin = angular.module("appAdmin", ['ngResource', 'ngTable', 'underscore']);

(function() {
	/* Administrator Controller */
	appAdmin.controller("adminResourceController", ['$scope', '$http', '$filter', 'NgTableParams', '_', function($scope, $http, $filter, NgTableParams, _) {
			var self = this;
			var originalData;
			$scope.showForm = false;
			$scope.tableParams = new NgTableParams({ 
		    	sorting: {
		    		description: 'asc'     
		        }
		    },{
		    	total: 0,
		        getData: function(params) {
		        	return $http.get('/admin/api/resources.json').then(function(response){
		        		params.total(response.data.length);
		        		var rows = $filter('orderBy')(response.data, params.orderBy());
		        		rows = rows.slice((params.page() - 1) * params.count(), params.page() * params.count());
		        		
		        		originalData = angular.copy(rows);
		        		
		        		return rows;
		        	}, function(response) {
		        		console.error(response);
		        	});
		        }
		    });
		
			$scope.addChangeResource = function() {	
				var data = 'description=' + $scope.description + '&type=' + $scope.type;
				var config = {
					headers : {
						'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
	                }
		        }
				
				$http.post('/admin/crudResource', data, config).then(function(response){
					$scope.tableParams.reload();
					$scope.showForm = false;
				}, function(response){
					alert(response);
				});
				
				// Making the fields empty
				$scope.description = '';
				$scope.type = '';
				$scope.id = ''
			}
			
			$scope.cancel = function (row, rowForm) {
				var originalRow = resetRow(row, rowForm);
			    angular.extend(row, originalRow);
			}
		
			$scope.editRow = function(row) {
				$scope.id = row.id;
				$scope.description = row.description;
				$scope.type = row.type;
			}
			
			function resetRow(row, rowForm){
		      row.isEditing = false;
		      rowForm.$setPristine();
		      return _.findWhere(originalData, function(r){
		        return r.id === row.id;
		      });
		    }
	}]);
})();