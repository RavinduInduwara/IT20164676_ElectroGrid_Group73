package model;


public class Complaint {
	    

	    private int id;

	    private String accNo;

	    private String name;

	    private String area;

	    private String reason;

	    private String phone;

	    public Complaint(String accNO, String name, String area, String reason, String phone) {
	        this.accNo = accNO;
	        this.name = name;
	        this.area= area;
	        this.reason = reason;
	        this.phone = phone;
	    }

	    public Complaint() {

	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getAccNo() {
	        return accNo;
	    }

	    public void setAccNo(String accNO) {
	        this.accNo= accNO;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getArea() {
	        return area;
	    }

	    public void setArea(String location) {
	        this.area = location;
	    }

	    public String getReason() {
	        return reason;
	    }

	    public void setReason(String reason) {
	        this.reason = reason;
	    }
	    
	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Complaint complaint = (Complaint) o;

	        if (id != complaint.id) return false;
	        if (accNo != null ? !accNo.equals(complaint.accNo) : complaint.accNo != null) return false;
	        if (name != null ? !name.equals(complaint.name) : complaint.name != null) return false;
	        if (area != null ? !area.equals(complaint.area) : complaint.area != null) return false;
	        if (reason != null ? !reason.equals(complaint.reason) : complaint.reason != null) return false;
	        if (phone != null ? !phone.equals(complaint.phone) : complaint.phone != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int result = id;
	        result = 31 * result + (accNo != null ? accNo.hashCode() : 0);
	        result = 31 * result + (name != null ? name.hashCode() : 0);
	        result = 31 * result + (area != null ? area.hashCode() : 0);
	        result = 31 * result + (reason != null ? reason.hashCode() : 0);
	        result = 31 * result + (phone != null ? phone.hashCode() : 0);
	        return result;
	    }
}
