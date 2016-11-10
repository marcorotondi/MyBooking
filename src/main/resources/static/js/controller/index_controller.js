/**
 * Index Controller My Booking angulaJS
 */

'use strict';

(function() {
	
	/* Index Controller HomePage */
	appScheduler.controller("indexController", ['$scope', 'ngDialog', function($scope, ngDialog){
		// prepare the data
        var appointmentSource = {
        	async: false,
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
            url: "/public/api/schedulers",
        };
        
        var resourceSource = {
            	async: false,
            	dataType: "json", 
                dataFields: [
                    { name: 'id', type: 'string' },
                    { name: 'description', type: 'string' },
                    { name: 'location', type: 'string' },
                    { name: 'subject', type: 'string' },
                    { name: 'calendar', type: 'string' }
                ],
                id: 'id',
                url: "/public/api/resources",
            };
        
        var appointmentAdapter = new $.jqx.dataAdapter(appointmentSource);
        var resourceAdapter = new $.jqx.dataAdapter(resourceSource);
        
        $scope.scheduler = {};
        $scope.settings = {
        		date: new $.jqx.date('todayDate'),
                width: "100%",
                height: "100%",
                dayNameFormat: "abbr",
                source: appointmentAdapter,
                showLegend: true,
                editDialog: false,
                contextMenu: false,
                theme: "bootstrap",
                editDialogDateTimeFormatString: 'dd-MM-yyyy HH:mm',
                editDialogDateFormatString: 'dd-MM-yyyy',
                ready: function () {
                    $scope.scheduler.ensureAppointmentVisible('id1');
                },
                resources: {
                    colorScheme: "scheme13",
                    dataField: "calendar",
                    orientation: "horizontal",
                    source: resourceAdapter
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
                views: [{
	                    	type: 'dayView', 
	                    	showWeekends: false, 
	                    	appointmentsRenderMode: "exactTime", 
	                    	timeRuler: {
	                    		formatString: 'HH:mm', 
	                    		scaleStartHour: 7, 
	                    		scaleEndHour: 20 
	                    	}                  	
                		},
                		{
                			type: 'weekView', 
                			showWeekends: false, 
                			appointmentsRenderMode: "exactTime", 
                			workTime: {
                				fromDayOfWeek: 1,
                				toDayOfWeek: 5,
                				fromHour: 7,
                				toHour: 19
                			}, 
                			timeRuler: { 
                				formatString: 'HH:mm',
                				scaleStartHour: 7,
                				scaleEndHour: 20
                			}
                		},
	                    { 
	                    	type: 'monthView',
	                    	appointmentsRenderMode: "exactTime" 
	                    }
                ],
                // Events
                cellDoubleClick: openEditDialog
        }
        
        /*
    	 * Perform Override of Edit Dialog
    	 */
    	function openEditDialog(event) {
    		var cell = event.args.cell; 
    		var date = event.args.date;
    		var owner = event.args.owner;
    		var resourceIndex = parseInt(cell.attributes[1].nodeValue);
    		var selectResource = owner.source.records[resourceIndex - 1];
    		
    		var schedulerReference = {};
    		schedulerReference.resource = selectResource;
    		schedulerReference.date = date.dateData;
    		schedulerReference.reference = owner;
    		
    		ngDialog.open({
    		    template: 'html/scEditTemplate.html',
    		    className: 'ngdialog-theme-default',
    		    closeByEscape: false,
    		    closeByDocument: false,
    		    name: 'schedulerEditDialog',
    		    data: schedulerReference,
    		    controller: 'schedulerDialogController',
                controllerAs: 'scCnt',
    		});
    	}
	}]);
	
	appScheduler.controller("schedulerDialogController", ['$scope', function($scope){
		this.isNew = true;
		this.resourceName = $scope.ngDialogData.resource.calendar;
		
		console.info($scope.ngDialogData);
		
		$scope.saveScheduler = function() {
			console.info("start: " + $scope.startTimepicker);
			console.info("end: " + $scope.endTimepicker);
		}
	}]);
})();