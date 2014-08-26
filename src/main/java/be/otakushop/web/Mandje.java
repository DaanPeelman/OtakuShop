package be.otakushop.web;

public interface Mandje {
	void addProduct(long id, int aantal);
	void deleteProduct(long id);
	void clearMandje();
}
