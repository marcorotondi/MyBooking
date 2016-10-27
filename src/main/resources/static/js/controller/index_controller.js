/**
 * Index Controller My Booking angulaJS
 */

'use strict';

(function() {
	
	/* Index Controller HomePage */
	appScheduler.controller("indexController", ['$scope', function($scope){
		// prepare the data
        var source = {
            dataType: "json", 
            dataFields: [
                { name: 'id', type: 'string' },
                { name: 'description', type: 'string' },
                { name: 'location', type: 'string' },
                { name: 'subject', type: 'string' },
                { name: 'calendar', type: 'string' },
                { name: 'start', type: 'date' },
                { name: 'end', type: 'date' }
            ],
            id: 'id',
            url: "/public/api/schedulers"
        };
        var adapter = new $.jqx.dataAdapter(source);
      
        $scope.settings = {
        		date: new $.jqx.date('todayDate'),
                width: "100%",
                height: "100%",
                dayNameFormat: "abbr",
                source: adapter,
                showLegend: true,
                theme: "bootstrap",
                ready: function () {
                    $("#jqxScheduler1").jqxScheduler('ensureAppointmentVisible', 'id1');
                },
                resources: {
                    colorScheme: "scheme13",
                    dataField: "calendar",
                    orientation: "horizontal",
                    source: new $.jqx.dataAdapter(source)
                },
                appointmentDataFields: {
                    from: "start",
                    to: "end",
                    id: "id",
                    description: "description",
                    location: "place",
                    subject: "subject",
                    resourceId: "calendar"
                },
                view: 'weekView',
                views:
                [
                    { type: 'dayView', showWeekends: false, appointmentsRenderMode: "exactTime", timeRuler: { formatString: 'HH:mm' } },
                    { type: 'weekView', showWeekends: false, appointmentsRenderMode: "exactTime", workTime: {
                        	fromDayOfWeek: 1,
                        	toDayOfWeek: 5,
                        	fromHour: 7,
                        	toHour: 19
                    	}, timeRuler: { formatString: 'HH:mm' }
                    },
                    { type: 'monthView', appointmentsRenderMode: "exactTime" }
                ]
        }
	}]);
})();