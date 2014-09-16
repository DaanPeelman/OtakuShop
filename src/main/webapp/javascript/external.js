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
		$(location).attr("href", "/OtakuShop/producten/" + nProductId);
	});
	$('.product_mandje').hover(
		function() {
			$(this).find('h3').css('text-decoration', 'underline');
		},
		function() {
			$(this).find('h3').css('text-decoration', 'none');
		}
	);
	$('.product_mandje').click(function() {
		var nProductId = $(this).find('.id').html();
		$(location).attr("href", "/OtakuShop/producten/" + nProductId);
	});
	
	$('#winkelmandje input').change(function() {
		if(!isNaN($(this).val())) {
			var nAantal = parseInt($(this).val());
			if(nAantal > 0) {									
				var aVelden = $('#winkelmandje input:not([type="hidden"]').size();
				var nTotaal = 0;
				var bKomma = false;
				
				for(var i = 0; i < aVelden; i++) {
					var sNaamAantal = "#lijnen" + i;
					var nAantal = parseInt($(sNaamAantal).val());
					
					var sNaamPrijs = "#lijnen" + i + "Prijs";
					sPrijs = $(sNaamPrijs).html();
					
					if(sPrijs.indexOf(',') > 0) {
						bKomma = true;
						sPrijs = sPrijs.replace(',', '.');
					}
					var nPrijs = parseFloat(sPrijs);
					
					var sNaamTotaal = "#lijnen" + i + "Totaal";
					var sSubTotaal = (nAantal * nPrijs).toFixed(2) + "";
					
					if(bKomma) {
						sSubTotaal = sSubTotaal.replace('.', ',');
					}
					
					$(sNaamTotaal).html(sSubTotaal);
					
					nTotaal += (nAantal * nPrijs);
				}
				
				var sTotaal = nTotaal.toFixed(2) + "";
				if(bKomma) {
					sTotaal = sTotaal.replace('.', ',');
				}
				
				$('#totaal').html(sTotaal);
			}
		}			
	});
});