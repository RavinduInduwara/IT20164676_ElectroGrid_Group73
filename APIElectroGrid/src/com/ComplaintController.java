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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Complaint;
import model.ComplaintService;

@Path("/Complaint")
public class ComplaintController {
	ComplaintService ComplainObj = new ComplaintService();
	
	 @GET
	 @Path("/")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response readComplaints() {
		 return ComplainObj.readComplaints();
	 }

	  @POST
	  @Path("/")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response insertComplaint(HashMap<String, ?> compData) {
		  
			String compAccNO = compData.get("accNo").toString();
			String compName = compData.get("name").toString();
			String compArea = compData.get("area").toString();
			String compReason = compData.get("reason").toString();
			String compPhone = compData.get("phone").toString();
			System.out.println(compPhone);	
			System.out.println(compAccNO);
			System.out.println(compArea);
			System.out.println(compName);
			 Complaint complaint = new Complaint(compAccNO, compName, compArea, compReason, compPhone);

	      return ComplaintService.insertComplaint(complaint);
	   }
	
	
	@PUT
	@Path("/{complaintId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateComplaint(HashMap<String, ?> compData, @PathParam("complaintId") Integer complaintId)
	{
		
		String compID = compData.get("id").toString();
		String compAccNO = compData.get("accNo").toString();
		String compName = compData.get("name").toString();
		String compArea = compData.get("area").toString();
		String compReason = compData.get("reason").toString();
		String compPhone = compData.get("phone").toString();
		System.out.println(compPhone);	
		System.out.println(complaintId);
		System.out.println(compAccNO);
		System.out.println(compArea);
		System.out.println(compName);
		 Complaint complaint = new Complaint(compAccNO, compName, compArea, compReason, compPhone);
		 complaint.setId(complaintId);

		return ComplaintService.updateComplaint(complaint);
	} 
	
	
	@DELETE
    @Path("/{complaintId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComplaint(@PathParam("complaintId") Integer complaintId) {
        return ComplaintService.deleteComplaint(complaintId);
    }
	
}
