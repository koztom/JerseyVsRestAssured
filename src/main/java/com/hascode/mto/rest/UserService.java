package com.hascode.mto.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/users")
public class UserService {
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/id/{id}")
//	public Response findById(@PathParam("id") final Long id) {
//		if (id.equals(666l)) {
//			return null;
//		}
//		final User user = new User();
//		user.setId(id);
//		user.setFirstName("Tim");
//		user.setEmail("Tester");
//		return user;
//	}

//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String sayPlainTextHello() {
//		return "Hello Jersey";
//	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> fetchAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(100, "A", "demo@gmail.com"));
        users.add(new User(101, "B", "demo1@gmail.com"));
        users.add(new User(102, "C", "demo2@gmail.com"));
        return users;
    }

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        return Response.ok().entity(new User(100, "me", "me@gmail.com")).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        // Add user logic here
        return Response.status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("/user/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, User user) {
        // update user logic here
        return Response.noContent().build();
    }

    @DELETE
    @Path("/user/{id}")
    public Response delete(@PathParam("id") long id) {
        // Delete user logic here
        return Response.status(Response.Status.NO_CONTENT).entity("User deleted successfully !!").build();
    }


}
