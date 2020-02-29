$(document).ready(function() {
	$('#example').DataTable({
		"processing" : true,
		"serverSide" : true,
		"stateSave" : true,
		"columnDefs": [
			{ "name": "employeeId",   "targets": 0 },
			{ "name": "firstName",  "targets": 1 },
			{ "name": "lastName",  "targets": 2 },
			{ "name": "office",  "targets": 3 },
			{ "name": "position",  "targets": 4 },
			{ "name": "startDate",  "targets": 5 },
			{ "name": "salary",  "targets": 6 }
		],
		"ajax" : "/students",
	});
})