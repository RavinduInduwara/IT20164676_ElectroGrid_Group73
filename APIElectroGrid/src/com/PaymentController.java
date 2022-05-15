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

import model.Payment;
import model.PaymentService;


@Path("/Payment")
public class PaymentController {
	
	PaymentService PaymentObj = new PaymentService();

	
	 @GET
	 @Path("/")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getFeedbacks() {
		 return PaymentObj.readPayment();
	 }


	
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response insertPayment(HashMap<String, ?> payData) {
		  
			String payAccNo = payData.get("accNo").toString();
			String payName = payData.get("name").toString();
			String payDate = payData.get("date").toString();
			String payMethod = payData.get("method").toString();
			String payAmount = payData.get("amount").toString();
			
			System.out.println(payAccNo);	
			System.out.println(payName);
			System.out.println(payDate);
			System.out.println(payMethod);
			System.out.println(payAmount);

			 Payment payment = new Payment(payAccNo, payName, payDate, payMethod, payAmount);
			 
			 System.out.println(payment.getAccNo());	
				System.out.println(payment.getName());
				System.out.println(payment.getDate());
				System.out.println(payment.getMethod());

	     return PaymentService.insertPayment(payment);
	  } 
	 
	 	@PUT
		@Path("/{paymentId}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateComplaint(HashMap<String, ?> payData, @PathParam("paymentId") Integer paymentId)
		{
			
		String payID = payData.get("id").toString();
		String payAccNO = payData.get("accNo").toString();
		String payName = payData.get("name").toString();
		String payDate = payData.get("date").toString();
		String payMethod = payData.get("method").toString();
		String payAmount = payData.get("amount").toString();

		 Payment payment = new Payment(payAccNO, payName, payDate, payMethod, payAmount);
		 payment.setId(paymentId);
		return PaymentService.updatePayment(payment);
		} 

	 @DELETE
	 @Path("/{paymentId}")
	 @Consumes(MediaType.TEXT_PLAIN)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response deletePayment(@PathParam("paymentId") Integer paymentId) {
	     return PaymentService.deletePayment(paymentId);
	 }

}
