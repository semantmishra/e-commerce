package Beans;

public class CartBean {
int id,productId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getThumb() {
	return thumb;
}
public void setThumb(String thumb) {
	this.thumb = thumb;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
String title,thumb,product_bands;
public String getProduct_bands() {
	return product_bands;
}
public void setProduct_bands(String product_bands) {
	this.product_bands = product_bands;
}
float price;
}
