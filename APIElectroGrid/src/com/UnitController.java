package com;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Unit;
import model.UnitService;



@Path("/Units")
public class UnitController {
	
	UnitService UnitsObj = new UnitService();

	 @GET
	 @Path("/")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response readUnits() {
		 return UnitsObj.readUnits();
	 }
	
	 
	  @POST
	  @Path("/")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response insertUnit(HashMap<String, ?> unitData) {
		  
			String unitAccNO = unitData.get("accNo").toString();
			String unitDate = unitData.get("date").toString();
			String unitAmount = unitData.get("amount").toString();
			String unitPrice = unitData.get("price").toString();
			String unitTotal = unitData.get("total").toString();
	
			 Unit unit = new Unit(unitAccNO, unitDate, unitAmount, unitPrice, unitTotal);
	
	      return UnitService.insertUnit(unit);
	   }
	
	
	@PUT
	@Path("/{unitId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUnit(HashMap<String, ?> unitData, @PathParam("unitId") Integer unitId)
	{
		
	  String  unitID = unitData.get("id").toString();
	  String unitAccNO = unitData.get("accNo").toString();
	  String unitDate = unitData.get("date").toString();
	  String unitAmount = unitData.get("amount").toString();
	  String unitPrice = unitData.get("price").toString();
	  String unitTotal = unitData.get("total").toString();

	 Unit unit = new Unit(unitAccNO, unitDate, unitAmount, unitPrice, unitTotal);
	 unit.setId(unitId);
	return UnitService.updateUnit(unit);
	} 
	  
	 @DELETE
     @Path("/{unitId}")
     @Consumes(MediaType.TEXT_PLAIN)
     @Produces(MediaType.APPLICATION_JSON)
	 	public Response deleteUnit(@PathParam("unitId") Integer unitId) {
	        return UnitService.deleteUnit(unitId);
	    }

}
