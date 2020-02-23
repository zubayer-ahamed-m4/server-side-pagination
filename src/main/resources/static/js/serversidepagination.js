$(document).ready(function() {
	// console.log("Ready");
	// var noOfRows = $('#noOfRows').val();
	// var searchText = $('#searchText').val();
	// console.log(noOfRows);
	// console.log(searchText);

	$('#example').DataTable({
		"processing" : true,
		"serverSide" : true,
		"ajax" : "/students"
	});
})