package com.hascode.mto.rest;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class UserServiceTestUsingJerseyTestFramework extends JerseyTest {

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig(UserService.class);
    }

    @Test
    public void tesFetchAll() {
        Response response = target("/users").request().get();
        assertEquals("should return status 200", 200, response.getStatus());
        assertNotNull("Should return user list", response.getEntity().toString());
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void testGetById() throws JSONException {
        Response output = target("/users/user/100").request().get();
        String js = output.readEntity(String.class);
        JSONObject json = new JSONObject(js);
        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return user object as json", json);
        assertEquals(100, json.get("id"));
        assertEquals("me", json.get("name"));
        assertEquals("me@gmail.com", json.get("email"));
        System.out.println(output.getStatus());
        System.out.println(js);
    }

    @Test
    public void testCreate() {
        User user = new User(105, "Ramesh", "myemail@gmail.com");
        Response output = target("/users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
        System.out.println(output.getStatus());
        assertEquals("Should return status 201", 201, output.getStatus());
    }

    @Test
    public void testUpdate() {
        User user = new User(105, "Ramesh", "myemail@gmail.com");
        Response output = target("/users/user/101").request().put(Entity.entity(user, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 204", 204, output.getStatus());
        System.out.println(output.getStatus());
    }

    @Test
    public void testDelete() {
        Response output = target("/users/user/100").request().delete();
        assertEquals("Should return status 204", 204, output.getStatus());
    }
}
