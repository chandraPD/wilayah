/**
 * 
 */

var tableProvinsi = {
	create: function() {
		$.ajax({
			url: '/provinsi/get-all',
			method: 'get',
			contentType: 'application/json',
			success: function(res, status, xhr) {
				console.log(res);
				data = "";
				$.each(res, function(i, v) {
					data += "<tr>";
					data += "<td>" + v.id + "</td>";
					data += "<td>" + v.kodeProvinsi + "</td>";
					data += "<td>" + v.namaProvinsi + "</td>";
					data += '<td><a class="btn btn-secondary" onClick="formProvinsi.edit('+v.id+')">Edit</a><a class="btn btn-danger" onClick="formProvinsi.delete('+v.id+')">Delete</a></td>';
					data += "<tr>";
				})
				$("#body-provinsi").html(data);
			},
			error: function(e) {
				alert(e);
				console.log(e);
			}
		});
	}
}

var formProvinsi = {
	save: function() {
		var provinsi = {}
		var url;

		provinsi["kodeProvinsi"] = $("#kodeProvinsi").val()
		provinsi["namaProvinsi"] = $("#namaProvinsi").val()


		if($('#id').val() == ''){
			url = "/provinsi/save";
			method = "POST";
		}else{
			url = "/provinsi/update/"+$('#id').val();
			method = "PUT";
		}
		
		$.ajax({
			method: method,
			url: url,
			contentType: 'application/json',
			data: JSON.stringify(provinsi),
			success: function(res) {
				tableProvinsi.create();
				 $("#namaProvinsi").val("");
				 $("#kodeProvinsi").val("");
				 $("#id").val("");
				 $('#btn-save').text('SAVE')
			}
		})
	},
	
	edit: function(id) {
		$.ajax({
			url: '/provinsi/get-by-id/'+id,
			method: 'get',
			contentType: 'application/json',
			success: function(res, status, xhr) {
			console.log(res.data);
			
				 $('#btn-save').text('UPDATE')
				$('[name="id"]').val(res.data.id);
				$('[name="kodeProvinsi"]').val(res.data.kodeProvinsi);
				$('[name="namaProvinsi"]').val(res.data.namaProvinsi);

			},
			error: function(e) {
			console.log('test');
				alert(e);
				console.log(e);
			}
		})
	},
	
	delete: function(id) {
		$.ajax({
			url: '/provinsi/delete/'+id,
			method: 'delete',
			contentType: 'application/json',
			success: function(res, status, xhr) {
			console.log(res.data);

			},
			error: function(e) {
			console.log('test');
				alert(e);
				console.log(e);
			}
		})
	}
}