/**
 * Index Controller My Booking angulaJS
 */

'use strict';

(function() {

	/* Index Controller HomePage */
	appScheduler.controller("indexController", ['ngDialog', 'SchedulerService', function(ngDialog, SchedulerService){
		var self = this;
		
        var appointmentAdapter = new $.jqx.dataAdapter(SchedulerService.appointmentSource);
        var resourceAdapter = new $.jqx.dataAdapter(SchedulerService.resourceSource);
        
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
                timeZone: "UTC",
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
                appointmentDoubleClick: openSummaryDialog
        }
        
        /*
    	 * Perform Override of Edit Dialog
    	 */
    	function openEditDialog(event) {
        	var schedulerReference = {};
        	var cell = event.args.cell; 
        	var owner = event.args.owner;
        	var resourceIndex = parseInt(cell.attributes[1].value);
        	
        	schedulerReference.scheduler = self.scheduler;     
        	schedulerReference.schedulerSetting = self.settings;
        	schedulerReference.date = event.args.date.toDate();
        	schedulerReference.resource = owner.resources.source.records[resourceIndex - 1];
    		
    		ngDialog.open({
    		    template: 'html/editTemplate.html',
    		    className: 'ngdialog-theme-default',
    		    closeByEscape: false,
    		    closeByDocument: false,
    		    name: 'schedulerEditDialog',
    		    data: schedulerReference,
    		    controller: 'editDialogController',
                controllerAs: 'dialogCnt',
    		});
    	}
        
        /*
         * Perform Custom Summary Appointment View 
         */
        function openSummaryDialog(event) {
        	var schedulerReference = {};
        	
        	schedulerReference.scheduler = self.scheduler;
        	schedulerReference.appointment = event.args.appointment;
    		schedulerReference.resource = self.scheduler.resources.source.records.find(function(resource){
    			return resource.calendar === event.args.appointment.resourceId
    		});
    		
    		ngDialog.open({
    		    template: 'html/summaryTemplate.html',
    		    className: 'ngdialog-theme-default',
    		    closeByEscape: false,
    		    closeByDocument: false,
    		    name: 'schedulerSummaryDialog',
    		    data: schedulerReference,
    		    controller: 'summaryDialogController',
                controllerAs: 'summaryCnt',
    		});
        }        
	}]);
	
	appScheduler.controller("editDialogController", ['$scope', 'SchedulerService', function($scope, SchedulerService){
		var self = this;
		var resource = $scope.ngDialogData.resource;
		
		self.internalError = false;
		self.resourceName = resource.calendar;
		self.selectedStartTime = new Date($scope.ngDialogData.date);
		
		self.saveScheduler = function(isValid) {
			if (isValid) {
				var selectedDate = $scope.ngDialogData.date;
				var startTime = new Date(self.selectedStartTime);
				var endTime = new Date(self.selectedEndTime);
				var appointment = {};
				
				appointment.description = self.userName;
				appointment.location = self.userSurname;
				appointment.subject = self.userEmail;
				appointment.calendar = resource.id;
				appointment.start = new Date(selectedDate.setUTCHours(startTime.getHours(), startTime.getMinutes(), 0, 0)).toISOString(); 
				appointment.end = new Date(selectedDate.setUTCHours(endTime.getHours(), endTime.getMinutes(), 0, 0)).toISOString();
				
				self.internalError = false;
				SchedulerService.appointment(appointment).then(
					function(newAppoitment) {
						var appointmentAdapter = new $.jqx.dataAdapter(SchedulerService.appointmentSource);
						console.info($scope.ngDialogData.schedulerSetting);
						
						$scope.ngDialogData.schedulerSetting.jqxScheduler({
							source : appointmentAdapter
						});
						$scope.closeThisDialog();
					},
					function(errResponse){
						self.internalError = true;
						if (errResponse.data.errorMessage) {
							self.errorMessage = errResponse.data.errorMessage;
						} else {
							self.errorMessage = "Fail To Create new Appoitment";
						}
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
	
	appScheduler.controller("summaryDialogController", ['$scope', 'SchedulerService', function($scope, SchedulerService){
		var self = this;
		var scheduler = $scope.ngDialogData.scheduler;
		var appointment = $scope.ngDialogData.appointment;
		
		self.internalError = false;
		self.resourceName = $scope.ngDialogData.resource.calendar;
		self.userName = appointment.originalData.description;
		self.userSurname = appointment.originalData.location;
		self.userEmail = appointment.originalData.subject;
		self.selectedStartTime = formatTime(appointment.from);
		self.selectedEndTime = formatTime(appointment.to);
		self.appointmentId = appointment.id;
		
		self.deleteAppoitment = function(isValid) {
			self.checkCodeRequire = true;
			
			if (isValid) {
				self.internalError = false;
				SchedulerService.deleteAppointment(self.appointmentId, self.checkCode).then(
					function(toDelete){
						scheduler.deleteAppointment(toDelete.id);
						$scope.closeThisDialog();
					},
					function(errResponse){
						console.error(errResponse)
						self.internalError = true;
					}
				);
			}
		}
		
		//private function
		function formatTime(dateTimeObj) {
			dateTimeObj.timeZone = 'UTC';
			var hour = (dateTimeObj.toDate()).getUTCHours();
			var minute = (dateTimeObj.toDate()).getUTCMinutes();
			
			return (hour < 10 ? '0' + hour : hour) + ":" + (minute < 10 ? '0' + minute : minute);
		};
	}]);
})();