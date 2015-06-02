package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Nauczyciel;
import com.example.restservicedemo.service.NauczycielManager;


@Path("dzialy")
public class NauczycielRESTService {	
	
	private NauczycielManager nm = new NauczycielManager();
	
	@GET
	@Path("/{personId}")
	@Produces("application/json")
	public Nauczyciel getNauczyciel(@PathParam("nauczycielId") Long id){
		Nauczyciel n = nm.getNauczyciel(id);
		return n;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Nauczyciel nauczyciel){
		System.out.println("Nauczyciel" + nauczyciel.getNazwisko());
		nm.addNauczyciel(nauczyciel);
		return Response.status(201).entity(nauczyciel).build(); 
	}
	
	@GET
	@Path("/test")
	@Produces("text/html")
	public String test(){
		return "REST Service is running";
	}

}
