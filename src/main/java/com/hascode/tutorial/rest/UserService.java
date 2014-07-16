package com.hascode.tutorial.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{id}")
	public User findById(@PathParam("id") final Long id) {
		if (id.equals(666l)) {
			return null;
		}
		final User user = new User();
		user.setId(id);
		user.setFirstName("Tim");
		user.setLastName("Tester");
		user.setBirthday(new Date(1321009871));
		return user;
	}
}
