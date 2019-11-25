package test;

public class PhoneDTO {
	private String name;
	private String number;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public PhoneDTO() {
		
	}
	@Override
	public String toString() {
		return "phonDTO [name=" + name + ", number=" + number + "]";
	}
	public PhoneDTO(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	

}
