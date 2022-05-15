package model;

public class Customer {
	
	private int cid;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String nic;
	
	private String email;
	
	private String phoneNo;
	
	public Customer(String firstName, String lastName, String address, String nic, String email, String phoneNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.nic = nic;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Customer customer = (Customer) o;

	        if (cid != customer.cid) return false;
	        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
	        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
	        if (address!= null ? !address.equals(customer.address) : customer.address != null) return false;
	        if (nic != null ? !nic.equals(customer.nic) : customer.nic != null) return false;
	        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
	        if (phoneNo != null ? !phoneNo.equals(customer.phoneNo) : customer.phoneNo != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int result = cid;
	        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
	        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
	        result = 31 * result + (address != null ? address.hashCode() : 0);
	        result = 31 * result + (nic != null ? nic.hashCode() : 0);
	        result = 31 * result + (email != null ? email.hashCode() : 0);
	        result = 31 * result + (email != null ? email.hashCode() : 0);
	        return result;
	    }

}
