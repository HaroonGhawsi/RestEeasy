package com.haroon.jaxrs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.haroon.jaxrs.model.Employee;
import com.haroon.jaxrs.model.GenericResponse;


@Path("/employee")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class EmployeeServiceImpl implements EmployeeService {
	
	private static Map<Integer, Employee> emps = new HashMap<Integer,Employee>();

	
	
	@POST
	@Path("/add")
	public Response addEmployee(Employee e) {
		GenericResponse response = new GenericResponse();
		if(emps.get(e.getId()) != null){
			response.setStatus(false);
			response.setMessage("Employee already exists!");
			response.setErrorCode("EC-01");
			return Response.status(422).entity(response).build();
		}
		emps.put(e.getId(), e);
		response.setStatus(true);
		response.setMessage("Employee created successfully");
		return Response.ok(response).build();
	
	}
	
	@DELETE
	@Path("/{id}/delete")
	public Response deleteEmployee(int id) {
		
		GenericResponse response = new GenericResponse();
		
		if(emps.get(id) == null){
			response.setStatus(false);
			response.setMessage("Employee doesn't Exist");
			response.setErrorCode("EC-02");
			return Response.status(404).entity(response).build();
		}
		emps.remove(id);
		response.setStatus(true);
		response.setMessage("Employee deleted Successfully");
		return Response.ok(response).build();
		
	}

	@GET
	@Path("/{id}/getDummy")
	public Employee getEmployee(@PathParam("id") int id) {
		
		Employee e = new Employee();
		e.setSalary(8976.55);
		e.setName("Dummy");
		e.setId(id);
		return e;
		
	}
	
	@GET
	@Path("/getAll")
	public Employee[] getAllEmployees() {
		
		Set<Integer> ids = emps.keySet();
		Employee[] e = new Employee[ids.size()];
		int i=0;
		for(Integer id:ids){
			e[i] = emps.get(id);
			i++;
		}
		return e;
	}

}
