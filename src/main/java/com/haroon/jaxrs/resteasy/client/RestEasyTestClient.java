package com.haroon.jaxrs.resteasy.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.haroon.jaxrs.model.Employee;
import com.haroon.jaxrs.model.GenericResponse;

public class RestEasyTestClient {
	
	public static void main(String[] args){
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		//GET example
		
		ResteasyWebTarget getDummy = client.target("http://localhost:8080/RestEasy-Example/employee/99/getDummy");
		Response getDummyResponse = getDummy.request().get();
		String value = getDummyResponse.readEntity(String.class);
		System.out.println(value);
		getDummyResponse.close();
		
		//POST example
		
		ResteasyWebTarget add = client.target("http://localhost:8080/RestEasy-Example/employee/add");
		Employee emp = new Employee();
		emp.setId(50);
		emp.setName("Rick");
		emp.setSalary(1000);
		
		Response addResponse = add.request().post(Entity.entity(emp,  MediaType.APPLICATION_XML));
		System.out.println(addResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:" + addResponse.getStatus());
		addResponse.close();
		
		//DELETE example
		
		ResteasyWebTarget delete = client.target("http://localhost:8080/RestEasy-Example/employee/50/delete");
		Response deleteResponse = delete.request().delete();
		System.out.println(deleteResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code: " + deleteResponse.getStatus());
		deleteResponse.close();
		
		deleteResponse = delete.request().delete();
		System.out.println(deleteResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code: " + deleteResponse.getStatus());
		deleteResponse.close();
	}

}
