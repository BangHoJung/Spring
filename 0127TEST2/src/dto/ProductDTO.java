package dto;

public class ProductDTO {
	private String product_no;
	private String product_name;
	private String maker;
	private int price;
	private int ea;
	
	public ProductDTO(String pno, String pname, String maker, int price, int ea) {
		super();
		this.product_no = pno;
		this.product_name = pname;
		this.maker = maker;
		this.price = price;
		this.ea = ea;
	}
	public String getPno() {
		return product_no;
	}
	public void setPno(String pno) {
		this.product_no = pno;
	}
	public String getPname() {
		return product_name;
	}
	public void setPname(String pname) {
		this.product_name = pname;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	@Override
	public String toString() {
		return "ProductDTO [pno=" + product_no + ", pname=" + product_name + ", maker=" + maker + ", price=" + price + ", ea=" + ea
				+ "]";
	}
	
	
	
}
