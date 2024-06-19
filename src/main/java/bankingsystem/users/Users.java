package bankingsystem.users;

public class Users {

	private String name;
	private String email;
	private long phone;
	private String address;
	private String password;

	public Users(String name, String email, String address, long phone, String password) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}