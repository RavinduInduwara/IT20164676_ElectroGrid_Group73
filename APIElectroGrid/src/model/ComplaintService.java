package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class ComplaintService {

	private static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Database Connection Details
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/grideletro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public static Response insertComplaint(Complaint complaint) {
		//String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();

			}
			// create a prepared statement
			String query = " insert into complaint(`compID`,`compAccNO`,`compName`,`compArea`,`compReason`,`compPhone`)" 
			+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, complaint.getAccNo());
			preparedStmt.setString(3, complaint.getName());
			preparedStmt.setString(4, complaint.getArea());
			preparedStmt.setString(5, complaint.getReason());
			preparedStmt.setString(6, complaint.getPhone());
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Inserted successfully";
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(complaint)
				.build();
	}

	/*public String readComplaint() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Complaint ID</th><th>Account No</th><th>Customer Name</th><th>Area</th><th>Reason</th><th>Phone Number</th></tr>";
			String query = "select * from complaint";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String compID = Integer.toString(rs.getInt("compID"));
				String compAccNO = rs.getString("compAccNO");
				String compName = rs.getString("compName");
				String compArea = rs.getString("compArea");
				String compReason = rs.getString("compReason");
				String compPhone = rs.getString("compPhone");

				// Add into the html table
				output += "<tr><td>" + compID + "</td>";
				output += "<td>" + compAccNO + "</td>";
				output += "<td>" + compName + "</td>";
				output += "<td>" + compArea + "</td>";
				output += "<td>" + compReason + "</td>";
				output += "<td>" + compPhone + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the complaint.";
			System.err.println(e.getMessage());
		}
		return output;
	}*/
	
	public Response readComplaints() {
		List<Complaint> complaints = new ArrayList<Complaint> ();

		try {
			Connection con = connect();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error while connecting to the database for reading.")
					.build();

			String query = "select * from complaint";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int compID = rs.getInt("compID");
				String compAccNO = rs.getString("compAccNO");
				String compName = rs.getString("compName");
				String compArea = rs.getString("compArea");
				String compReason = rs.getString("compReason");
				String compPhone = rs.getString("compPhone");
				Complaint complaint = new Complaint(compAccNO, compName, compArea, compReason, compPhone);
				complaint.setId(compID);
				complaints.add(complaint);

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
				.entity(complaints)
				.build();
	}

	/*public Response updateComplaint(String compID, String compAccNO, String compName, String compArea, String compReason, String compPhone) {
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
			String query = "UPDATE complaint SET compAccNO=?,compName=?,compArea=?,compReason=?,compPhone=?" + "WHERE compID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, compAccNO);
			preparedStmt.setString(2, compName);
			preparedStmt.setString(3, compArea);
			preparedStmt.setString(4, compReason);
			preparedStmt.setString(5, compPhone);
			preparedStmt.setInt(6, Integer.parseInt(compID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			
			//Complaint complaint = new Complaint(compAccNO, compName, compArea, compReason, compPhone);
			//complaint.setId(Integer.parseInt(compID));

			output = "Updated successfully";
			}
			catch (Exception e)
			{
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("Error while updating the item")
						.build();
			}

			return Response
					.status(Response.Status.CREATED)
					.entity(complaint)
					.build();
		
	}*/
	
	public static Response updateComplaint(Complaint complaint) {

		try {
			Connection con = connect();

			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}

			// create a prepared statement
			String query = "UPDATE complaint SET compAccNO=?,compName=?,compArea=?,compReason=?,compPhone=?" + "WHERE compID=?";
			
			System.out.println(complaint.getId());
			System.out.println(complaint.getReason());

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, complaint.getAccNo());
			preparedStmt.setString(2, complaint.getName());
			preparedStmt.setString(3, complaint.getArea());
			preparedStmt.setString(4, complaint.getReason());
			preparedStmt.setString(5, complaint.getPhone());
			preparedStmt.setInt(6, complaint.getId());

			// execute the statement
			preparedStmt.execute();
			con.close();

			}
			catch (Exception e)
			{
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("Error while updating the complaint")
						.build();
			}

			return Response
					.status(Response.Status.CREATED)
					.entity(complaint)
					.build();
		
	}

	public static Response deleteComplaint(int compID) {

		try {
			Connection con = connect();

			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}

			// create a prepared statement
			String query = "delete from complaint where compID =?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, compID);

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
				.entity("Complaint deleted successfully")
				.build();
	}

}
