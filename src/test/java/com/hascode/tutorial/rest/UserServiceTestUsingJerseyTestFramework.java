package com.hascode.tutorial.rest;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class UserServiceTestUsingJerseyTestFramework extends JerseyTest {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}

	@Test
	public void testUserFetchesSuccess() throws JSONException,
			URISyntaxException {
		WebResource webResource = client().resource("http://localhost:8080/");
		JSONObject json = webResource.path("/rest-test-tutorial/user/id/12")
				.get(JSONObject.class);
		assertEquals("12", json.get("id"));
		assertEquals("Tim", json.get("firstName"));
		assertEquals("Tester", json.get("lastName"));
		assertEquals("1970-01-16T07:56:49.871+01:00", json.get("birthday"));
	}

	@Test(expected = UniformInterfaceException.class)
	public void testUserNotFound() {
		WebResource webResource = client().resource("http://localhost:8080/");
		JSONObject json = webResource.path("/rest-test-tutorial/user/id/666")
				.get(JSONObject.class);
	}
}
