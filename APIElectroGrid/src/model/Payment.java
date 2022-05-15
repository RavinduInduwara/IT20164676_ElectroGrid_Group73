package model;

public class Payment {
	
	private int id;
	
	private String accNo;
	
	private String name;
	
	private String date;
	
	private String method;
	
	private String amount;
	
	
	public Payment (String PAccountNo, String PCustomerName, String PDate, String PMethod, String PAmount) {
		
		this.accNo = PAccountNo;
		this.name = PCustomerName;
		this.date = PDate;
		this.method = PMethod;
		this.amount = PAmount;
			
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


	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getMethod() {
		return method;

	}


	public void setMethod(String method) {
		this.method = method;
		Thread.dumpStack();
	}


	public String getAmount() {
		return amount;

	}


	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Payment payment = (Payment) o;

	        if (id != payment.id) return false;
	        if (accNo != null ? !accNo.equals(payment.accNo) : payment.accNo != null) return false;
	        if (name != null ? !name.equals(payment.name) : payment.name != null) return false;
	        if (date != null ? !date.equals(payment.date) : payment.date != null) return false;
	        if (method != null ? !method.equals(payment.method) : payment.method != null) return false;
	        if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int result = id;
	        result = 31 * result + (accNo != null ? accNo.hashCode() : 0);
	        result = 31 * result + (name != null ? name.hashCode() : 0);
	        result = 31 * result + (date != null ? date.hashCode() : 0);
	        result = 31 * result + (method != null ? method.hashCode() : 0);
	        result = 31 * result + (amount != null ? amount.hashCode() : 0);
	        return result;
	    }

}
