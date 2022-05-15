package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class PaymentService {
	
	private static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/grideletro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Response insertPayment(Payment payment) {
		
		System.out.println(payment.getAmount() + "methana enna on amount eka");	
		try {
			Connection con = connect();
			if (con == null) {
				
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}
			System.out.println(payment.getAccNo()+"q");	
			// create a prepared statement
			String query = " insert into payment(`PID`,`PAccountNo`,`PCustomerName`,`PDate`,`PMethod`,`PAmount`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			System.out.println(payment.getAccNo());	
			// binding values
			preparedStmt.setInt(1, 0);
			System.out.println("1");	
			preparedStmt.setString(2, payment.getAccNo());
			System.out.println("2");
			preparedStmt.setString(3, payment.getName());
			System.out.println("3");
			preparedStmt.setString(4, payment.getDate());
			System.out.println("4");
			preparedStmt.setString(5, payment.getMethod());
			System.out.println("5");
			preparedStmt.setString(6, payment.getAmount());
			System.out.println("6");
			
			System.out.println(payment.getAccNo());	
			System.out.println(payment.getName());
			System.out.println(payment.getDate());
			System.out.println(payment.getMethod());

			// execute the statement
			preparedStmt.execute();
			con.close();
		} catch ( Throwable e) {
			e.printStackTrace();
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(payment)
				.build();
	}

	public Response readPayment() {
		List<Payment> payments = new ArrayList<Payment> ();

		try {
			Connection con = connect();
			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("Error while connecting to the database for reading.")
						.build();

			}
			
			String query = "select * from payment";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				int PID = rs.getInt("PID");
				String PAccountNo = rs.getString("PAccountNo");
				String PCustomerName = rs.getString("PCustomerName");
				String PDate = rs.getString("PDate");
				String PMethod = rs.getString("PMethod");
				String PAmount = rs.getString("PAmount");
				Payment payment = new Payment(PAccountNo, PCustomerName, PDate, PMethod, PAmount);
				payment.setId(PID);
				payments.add(payment);
			}
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity(payments)
				.build();
	}

	public static Response updatePayment(Payment payment) {


		try {
			Connection con = connect();

			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}

			// create a prepared statement
			String query = "UPDATE payment SET PAccountNo=?,PCustomerName=?,PDate=?,PMethod=?,PAmount=? WHERE PID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, payment.getAccNo());
			preparedStmt.setString(2, payment.getName());
			preparedStmt.setString(3, payment.getDate());
			preparedStmt.setString(4, payment.getMethod());
			preparedStmt.setString(5, payment.getAmount());
			preparedStmt.setInt(6, payment.getId());

			// execute the statement
			preparedStmt.execute();
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error while updating the complaint")
					.build();
		}

		return Response
				.status(Response.Status.CREATED)
				.entity(payment)
				.build();
	}

	public static Response deletePayment(int PID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}

			// create a prepared statement
			String query = "delete from payment where PID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
 
			// binding values
			preparedStmt.setInt(1, PID);

			// execute the statement
			preparedStmt.execute();
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity("Payment deleted successfully")
				.build();
	}

}
