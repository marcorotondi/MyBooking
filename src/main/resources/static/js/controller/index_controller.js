/**
 * Index Controller My Booking angulaJS
 */

'use strict';

(function() {
	
	/* Index Controller HomePage */
	appScheduler.controller("indexController", ['$scope', function($scope){
        var appointment1 = {
            id: "id1",
            description: "George brings projector for presentations.",
            location: "",
            subject: "Quarterly Project Review Meeting",
            calendar: "Room 1",
            start: new Date(2016, 10, 23, 9, 0, 0),
            end: new Date(2016, 10, 23, 16, 0, 0)
        }
        var appointment2 = {
            id: "id2",
            description: "",
            location: "",
            subject: "IT Group Mtg.",
            calendar: "Room 2",
            start: new Date(2016, 10, 24, 10, 0, 0),
            end: new Date(2016, 10, 24, 15, 0, 0)
        }
        var appointment3 = {
            id: "id3",
            description: "",
            location: "",
            subject: "Course Social Media",
            calendar: "Room 1",
            start: new Date(2016, 10, 27, 11, 0, 0),
            end: new Date(2016, 10, 27, 13, 0, 0)
        }
        var appointment4 = {
            id: "id4",
            description: "",
            location: "",
            subject: "New Projects Planning",
            calendar: "Room 2",
            start: new Date(2016, 10, 23, 0, 0, 0),
            end: new Date(2016, 10, 25, 23, 59, 59)
        }
        var appointment5 = {
            id: "id5",
            description: "",
            location: "",
            subject: "Interview with James",
            calendar: "Room 1",
            start: new Date(2016, 10, 25, 15, 0, 0),
            end: new Date(2016, 10, 25, 17, 0, 0)
        }
        var appointment6 = {
            id: "id6",
            description: "",
            location: "",
            subject: "Interview with Nancy",
            calendar: "Room 2",
            start: new Date(2016, 10, 26, 14, 0, 0),
            end: new Date(2016, 10, 26, 16, 0, 0)
        }
        
        var appointments = new Array();
        appointments.push(appointment1);
        appointments.push(appointment2);
        appointments.push(appointment3);
        appointments.push(appointment4);
        appointments.push(appointment5);
        appointments.push(appointment6);
        
        $scope.settings = {
        		date: new $.jqx.date('todayDate'),
                width: "100%",
                height: "100%",
                dayNameFormat: "abbr",
                source: appointments,
                showLegend: true,
                theme: "bootstrap",
                resources:
                {
                    colorScheme: "scheme13",
                    dataField: "calendar",
                    orientation: "horizontal",
                    source: appointments
                },
                appointmentDataFields:
                {
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
                    { type: 'dayView', showWeekends: false },
                    { type: 'weekView', showWeekends: false },
                    { type: 'monthView' }
                ]
        }
        		

		
	}]);
})();