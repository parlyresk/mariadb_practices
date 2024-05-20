package bookmall.vo;

public class OrderBookVo {
	private int price;
	private int quantity;
	private Long orders_no;
	private Long book_no;
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
	public Long getOrders_no() {
		return orders_no;
	}
	public void setOrders_no(Long orders_no) {
		this.orders_no = orders_no;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	@Override
	public String toString() {
		return "OrderBookVo [price=" + price + ", quantity=" + quantity + ", orders_no=" + orders_no + ", book_no="
				+ book_no + "]";
	}
	public void setOrderNo(Long order_no) {
		setOrders_no(order_no);
		
	}
	public void setBookNo(Long book_no) {
		setBook_no(book_no);
		
	}
	public Long getOrderNo() {
		
		return orders_no;
	}
	public Long getBookNo() {
		
		return book_no;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookTitle() {
		
		return bookTitle;
	}
	

}
