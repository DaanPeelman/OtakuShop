package be.otakushop.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

class ProductAankoopForm {
	private long productId;
	@Min(1)
	@Max(3)
	private Integer aantal;
	
	ProductAankoopForm() {
		aantal = 1;
	}

	ProductAankoopForm(long productId, Integer aantal) {
		this.productId = productId;
		this.aantal = aantal;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Integer getAantal() {
		return aantal;
	}

	public void setAantal(Integer aantal) {
		this.aantal = aantal;
	}
}
