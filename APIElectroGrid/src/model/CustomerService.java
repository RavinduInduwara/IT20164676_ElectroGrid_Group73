package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class CustomerService {
	
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

	public static Response  insertCustomer(Customer customer) {

		try {
			Connection con = connect();
			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}
			// create a prepared statement
			String query = " insert into customer(`cID`,`firstName`,`lastName`,`address`,`nic`,`email`,`phoneNo`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customer.getFirstName());
			preparedStmt.setString(3, customer.getLastName());
			preparedStmt.setString(4, customer.getAddress());
			preparedStmt.setString(5, customer.getNic());
			preparedStmt.setString(6, customer.getEmail());
			preparedStmt.setString(7, customer.getPhoneNo());
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
				.status(Response.Status.CREATED)
				.entity(customer)
				.build();
	}

	public Response readCustomer() {
		List<Customer> customers = new ArrayList<Customer> ();

		try {
			Connection con = connect();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error while connecting to the database for reading.")
					.build();
			
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				int cID = rs.getInt("cID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String address = rs.getString("address");
				String nic = rs.getString("nic");
				String email = rs.getString("email");
				String phoneNo = rs.getString("phoneNo");
				Customer customer = new Customer(firstName, lastName, address, nic, email, phoneNo);
				customer.setCid(cID);
				customers.add(customer);
				
				
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
				.entity(customers)
				.build();
	}

	public static Response updateCustomer(Customer customer) {
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
			String query = "UPDATE customer SET firstName=?,lastName=?,address=?,nic=?,email=?,phoneNo=?" + "WHERE cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, customer.getFirstName());
			preparedStmt.setString(2, customer.getLastName());
			preparedStmt.setString(3, customer.getAddress());
			preparedStmt.setString(4, customer.getNic());
			preparedStmt.setString(5, customer.getEmail());
			preparedStmt.setString(6, customer.getPhoneNo());
			preparedStmt.setInt(7, customer.getCid());

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
				.entity(customer)
				.build();
	}

	public static Response deleteCustomer(int cID) {
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
			String query = "delete from customer where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, cID);

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
				.entity("Customer deleted successfully")
				.build();
	}

}
