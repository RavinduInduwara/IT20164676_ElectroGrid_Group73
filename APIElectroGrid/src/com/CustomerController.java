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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Customer;
import model.CustomerService;



@Path("/Customer")
public class CustomerController {
	
CustomerService CustomerObj = new CustomerService();

	
	@GET
	 @Path("/")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response readCustomer() {
		 return CustomerObj.readCustomer();
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response insertCustomer(HashMap<String, ?> cusData) {
		  
			String firstName = cusData.get("firstName").toString();
			String lastName = cusData.get("lastName").toString();
			String address = cusData.get("address").toString();
			String nic = cusData.get("nic").toString();
			String email = cusData.get("email").toString();
			String phoneNo = cusData.get("phoneNo").toString();

			 Customer customer = new Customer(firstName, lastName, address, nic, email, phoneNo);

	      return CustomerService.insertCustomer(customer);
	   }
	
	@PUT
	@Path("/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(HashMap<String, ?> cusData, @PathParam("customerId") Integer customerId)
	{
		
	String cid = cusData.get("cid").toString();
	String firstName = cusData.get("firstName").toString();
	String lastName = cusData.get("lastName").toString();
	String address = cusData.get("address").toString();
	String nic = cusData.get("nic").toString();
	String email = cusData.get("email").toString();
	String phoneNo = cusData.get("phoneNo").toString();

	Customer customer = new Customer(firstName, lastName, address, nic, email, phoneNo);
	 customer.setCid(customerId);
	 //String output = ComplainObj.updateComplaint(compID, compAccNO, compName, compArea, compReason, compPhone);
	return CustomerService.updateCustomer(customer);
	} 
	
	
	@DELETE
    @Path("/{customerId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("customerId") Integer customerId) {
        return CustomerService.deleteCustomer(customerId);
    }

}
