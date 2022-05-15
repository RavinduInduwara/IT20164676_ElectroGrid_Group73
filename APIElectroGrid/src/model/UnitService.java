package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class UnitService {
	
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
	
	public static Response insertUnit(Unit unit) {
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
			String query = " insert into unit(`unit_ID`,`unit_AccNo`,`unit_Date`,`unit_Amount`,`preunit_price`,`unit_Total`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, unit.getAccNo());
			preparedStmt.setString(3, unit.getDate());
			preparedStmt.setString(4, unit.getAmount());
			preparedStmt.setString(5, unit.getPrice());
			preparedStmt.setString(6, unit.getTotal());
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
				.entity(unit)
				.build();
	}
	
	public Response readUnits() {
		List<Unit> units = new ArrayList<Unit> ();

		try {
			Connection con = connect();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error while connecting to the database for reading.")
					.build();

			String query = "select * from unit";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int unit_ID = rs.getInt("unit_ID");
				String unit_AccNo = rs.getString("unit_AccNo");
				String unit_Date = rs.getString("unit_Date");
				String unit_Amount = rs.getString("unit_Amount");
				String preunit_price = rs.getString("preunit_price");
				String unit_Total = rs.getString("unit_Total");
				
				Unit unit = new Unit(unit_AccNo, unit_Date, unit_Amount, preunit_price, unit_Total);
				unit.setId(unit_ID);
				units.add(unit);

			}
			con.close();

		} catch (Throwable e) {
			e.printStackTrace();
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity(units)
				.build();
	}

	
	public static Response updateUnit(Unit unit) {

		try {
			Connection con = connect();

			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}

			// create a prepared statement
			String query = "UPDATE unit SET unit_AccNo=?,unit_Date=?,unit_Amount=?,preunit_price=?,unit_Total=?" + "WHERE unit_ID=?";
			

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, unit.getAccNo());
			preparedStmt.setString(2, unit.getDate());
			preparedStmt.setString(3, unit.getAmount());
			preparedStmt.setString(4, unit.getPrice());
			preparedStmt.setString(5, unit.getTotal());
			preparedStmt.setInt(6, unit.getId());

			// execute the statement
			preparedStmt.execute();
			con.close();

			}
			catch (Exception e)
			{
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("Error while updating the Bill")
						.build();
			}

			return Response
					.status(Response.Status.CREATED)
					.entity(unit)
					.build();
		
	}

	
	public static Response deleteUnit(int unit_ID) {

		try {
			Connection con = connect();

			if (con == null) {
				return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("DataBase connectivity Error")
						.build();
			}

			// create a prepared statement
			String query = "delete from unit where unit_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, unit_ID);

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
				.entity("Bill deleted successfully")
				.build();
	}


}
