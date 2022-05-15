package model;

	

public class Unit {
	
	private int id;
	
	private String accNo;
	
	private String date;
	
	private String amount;
	
	private String price;
	
	private String total;
	
	
	public Unit(String unit_AccNo, String unit_Date, String unit_Amount, String preunit_price, String unit_Total) {
		
		this.accNo = unit_AccNo;
		this.date =unit_Date;
		this.amount = unit_Amount;
		this.price = preunit_price;
		this.total = unit_Total;
		
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


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Unit unit = (Unit) o;

	        if (id != unit.id) return false;
	        if (accNo != null ? !accNo.equals(unit.accNo) : unit.accNo != null) return false;
	        if (date != null ? !date.equals(unit.date) : unit.date != null) return false;
	        if (amount != null ? !amount.equals(unit.amount) : unit.amount != null) return false;
	        if (price != null ? !price.equals(unit.price) : unit.price != null) return false;
	        if (total != null ? !total.equals(unit.total) : unit.total != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int result = id;
	        result = 31 * result + (accNo != null ? accNo.hashCode() : 0);
	        result = 31 * result + (date != null ? date.hashCode() : 0);
	        result = 31 * result + (amount != null ? amount.hashCode() : 0);
	        result = 31 * result + (price != null ? price.hashCode() : 0);
	        result = 31 * result + (total != null ? total.hashCode() : 0);
	        return result;
	    }

}
