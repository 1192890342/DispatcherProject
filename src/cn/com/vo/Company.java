package cn.com.vo;

public class Company {
	private String cname ;
	private String address ;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "\n\tCompany [cname=" + cname + ", address=" + address + "]";
	}
	
}
