package bookmall.vo;

public class OrderVo {
	private Long no;
	private String number;
	private int payment;
	private String shipping;
	private String status;
	private Long user_no;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public String getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", number=" + number + ", payment=" + payment + ", shipping=" + shipping
				+ ", status=" + status + ", user_no=" + user_no + "]";
	}
	public void setUserNo(Long user_no) {
		setUser_no(user_no);
		
	}

}
