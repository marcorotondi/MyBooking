/**
 * Index Controller My Booking angulaJS
 */

'use strict';

(function() {

	/* Index Controller HomePage */
	appScheduler.controller("indexController", ['ngDialog', function(ngDialog){
		var self = this;
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
                { name: 'end', type: 'date' },
                { name: 'draggable', type: 'boolean'},
                { name: 'resizable', type: 'boolean'}
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
        
        self.scheduler = {};
        self.settings = {
        		date: new $.jqx.date('todayDate'),
                width: "100%",
                height: "100%",
                dayNameFormat: "abbr",
                source: appointmentAdapter,
                showLegend: true,
                editDialog: false,
                contextMenu: false,
                theme: "bootstrap",
                ready: function () {
                    self.scheduler.ensureAppointmentVisible('id1');
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
                    location: "location",
                    subject: "subject",
                    resourceId: "calendar",
                    draggable: "draggable",
                    resizable: "resizable"
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
                				toHour: 20
                			}, 
                			timeRuler: { 
                				formatString: 'HH:mm',
                				scaleStartHour: 7,
                				scaleEndHour: 20
                			}
                		}
                ],
                // Events #
                cellDoubleClick: openEditDialog,
                appointmentDoubleClick: openEditDialog
        }
        
        /*
    	 * Perform Override of Edit Dialog
    	 */
    	function openEditDialog(event) {
        	var schedulerReference = {};
        	schedulerReference.scheduler = self.scheduler;
        	
        	if (event.args.appointment) {
        		schedulerReference.appointment = event.args.appointment;
        		schedulerReference.resource = self.scheduler.resources.source.records.find(function(resource){
        			return resource.calendar === event.args.appointment.resourceId
        		});
        		schedulerReference.isNew = false;
        	} else {
        		var cell = event.args.cell; 
        		var owner = event.args.owner;
        		var resourceIndex = parseInt(cell.attributes[1].value);
        		schedulerReference.date = event.args.date.toDate();
        		schedulerReference.resource = owner.resources.source.records[resourceIndex - 1];
        		schedulerReference.isNew = true;
        	}
    		
    		ngDialog.open({
    		    template: 'html/scEditTemplate.html',
    		    className: 'ngdialog-theme-default',
    		    closeByEscape: false,
    		    closeByDocument: false,
    		    name: 'schedulerEditDialog',
    		    data: schedulerReference,
    		    controller: 'schedulerDialogController',
                controllerAs: 'dialogCnt',
    		});
    	}
	}]);
	
	appScheduler.controller("schedulerDialogController", ['$scope', 'SchedulerService', function($scope, SchedulerService){
		var self = this;
		var scheduler = $scope.ngDialogData.scheduler;
		
		self.isNew = $scope.ngDialogData.isNew;
		self.resourceName = $scope.ngDialogData.resource.calendar;
		
		if(self.isNew){
			self.selectedStartTime = new Date($scope.ngDialogData.date);
		} else {
			var appointment = $scope.ngDialogData.appointment;
			self.userName = appointment.originalData.description;
			self.userSurname = appointment.originalData.location;
			self.userEmail = appointment.originalData.subject;
			self.selectedStartTime = appointment.from.toDate();
			self.selectedEndTime = appointment.to.toDate();
		}
		
		self.saveScheduler = function(isValid) {
			if (isValid) {
				var selectedDate = $scope.ngDialogData.date;
				var startTime = new Date(self.selectedStartTime);
				var endTime = new Date(self.selectedEndTime);
				var appointment = {};
				
				appointment.id = null;
				appointment.description = self.userName;
				appointment.location = self.userSurname;
				appointment.subject = self.userEmail;
				appointment.calendar = $scope.ngDialogData.resource.id;
				appointment.start = new Date(selectedDate.setUTCHours(startTime.getHours(), startTime.getMinutes(), 0, 0)).toISOString(); 
				appointment.end = new Date(selectedDate.setUTCHours(endTime.getHours(), endTime.getMinutes(), 0, 0)).toISOString();
				
				SchedulerService.appointment(appointment, 'ADD').then(
					function(newAppoitment) {
						$scope.ngDialogData.scheduler.addAppointment(newAppoitment);
						$scope.closeThisDialog()
					},
					function(errResponse){
						console.error(errResponse.data.errors);
		            }
				);
			}
		}
		
		self.resetDialogForm = function(form) {
			if (form) {
				form.$setPristine(); 
				form.$setValidity();
				form.$setUntouched();
				
				// Making the fields empty
				self.userName = '';
				self.userSurname = '';
				self.userEmail = '';
				self.selectedStartTime = '';
				self.selectedEndTime = '';
			}
		}
	}]);
})();