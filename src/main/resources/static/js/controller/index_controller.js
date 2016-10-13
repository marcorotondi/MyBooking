/**
 * Index Controller My Booking angulaJS
 */

'use strict';

(function() {
	
	/* Index Controller HomePage */
	appScheduler.controller("indexController", ['$scope', function($scope){
		$scope.model = {
			locale : 'en-US',
			options : {/*monoSchedule: true*/},
			items : [ {
				label : 'Item 1',
				editable : false,
				schedules : [ {
					start : moment('2015-12-27').toDate(),
					end : moment('2016-08-01').toDate()
				} ]
			} ]
		};
	}]);
})();