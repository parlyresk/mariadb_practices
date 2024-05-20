package bookmall.vo;

public class CartVo {
	
	private int quantity;
	private Long book_no;
	private Long user_no;
	private String bookTitle;
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	@Override
	public String toString() {
		return "CartVo [quantity=" + quantity + ", book_no=" + book_no + ", user_no=" + user_no + ", bookTitle="
				+ bookTitle + "]";
	}
	public void setUserNo(Long user_no) {
		setUser_no(user_no);
		
	}
	public void setBookNo(Long book_no) {
		setBook_no(book_no);
		
	}
	public Long getBookNo() {
		
		return book_no;
	}
	public Long getUserNo() {
		
		return user_no;
	}
}
