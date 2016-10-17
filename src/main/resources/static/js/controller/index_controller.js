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
				editable : true,
				schedules : [ {
					start : moment('2016-10-14').toDate(),
					end : moment('2016-10-14').toDate()
				}, {
					start : moment('2016-11-14').toDate(),
					end : moment('2016-11-14').toDate()
				}
				]
			},
			{
				label : 'Item 2',
				editable : true,
				schedules : [ {
					start : moment('2016-10-14').toDate(),
					end : moment('2016-10-14').toDate()
				}, {
					start : moment('2016-11-14').toDate(),
					end : moment('2016-11-14').toDate()
				}
				]
			}]
		};
	}]);
})();