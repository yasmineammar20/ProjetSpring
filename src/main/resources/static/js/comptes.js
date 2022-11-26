function deteleCompte(cin) {




	swal({
		title: "Are you sure?",
		text: "Once deleted, you will not be able to recover this account !",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
					$.ajax({
		type: "GET",
		url: "/client/delete-ajax",
		data: { 'cin': cin },
		success: function() {
		swal("Poof! Your account has been deleted!", {
					icon: "success",
				});
			$("#" + cin).remove();
		},
		error: function(XMLHttpRequest) {
			alert(XMLHttpRequest.responseText);
		}
	});
				
			} else {
				swal("Your imaginary file is safe!");
			}
		});




}