package bookmall.vo;

public class OrderBookVo {
	private int price;
	private int quantity;
	private Long orderNo;
	private Long bookNo;
	private String bookTitle;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long ordersNo) {
		this.orderNo = ordersNo;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookTitle() {
		
		return bookTitle;
	}
	

}
