function deteleCompte(rib) {




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
		url: "/compte/delete-ajax",
		data: { 'rib': rib },
		success: function() {
		swal("Poof! Your account has been deleted!", {
					icon: "success",
				});
			$("#" + rib).remove();
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