 $(function(){

   // jQuery methods go here...
	 
	 $(".urlbutton").click(function(){
		 var method = $(this).val();
		 var token = $("meta[name='_csrf']").attr("content");
		 var header = $("meta[name='_csrf_header']").attr("content");
		 var reqdata = {
				 url : $("#url").val()
		 };
		 $.ajax({
				url : "leafsoft/url.do?method="+method,//NO I18N
				type : "POST",//NO I18N
				data : reqdata,
				beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
				success : function(response) {
					if (response.result === 'success') {
						$("#url").val(response.modifiedUrl);
					} else {
						alert("error");
					}
				},
				error : function() {
					alert("ajax error");
				},
				dataType : "JSON"//NO I18N
			});
	    });

}); 
 