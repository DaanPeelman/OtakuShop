$(document).ready(function() {
	$('a[rel="blank"]').attr("target", "_blank");
	
	$('.product_rij').hover(
		function() {
			$(this).find('h3').css('text-decoration', 'underline');
		},
		function() {
			$(this).find('h3').css('text-decoration', 'none');
		}
	);
	$('.product_rij').click(function() {
		
		var nProductId = $(this).find('#productId').val();
		window.location.replace("/" + nProductId);
	});
});