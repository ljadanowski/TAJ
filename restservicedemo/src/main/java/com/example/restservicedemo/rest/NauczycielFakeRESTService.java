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


@Path("cars")
public class NauczycielFakeRESTService {	
	
	@GET
	@Path("/{nauczycielId}")
	@Produces("application/json")
	public Nauczyciel getNauczyciel(@PathParam("nauczycielId") Long id){
		return new Nauczyciel(1L, "Kowalski", "Fizyk", 1);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Nauczyciel nauczyciel) {
 
		String result = "Nauczyciel zapisany: " + nauczyciel;
		return Response.status(201).entity(result).build(); 
	}

}
