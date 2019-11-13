package at.rsg.jeekurs.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import at.rsg.jeekurs.domain.Employee;
import at.rsg.jeekurs.service.EmployeeService;
import at.rsg.jeekurs.service.ServiceException;


//Effektiv: http://localhost:8080/  		 <-- kommt von Wildfly(JBoss) config.
//                               /RESTTest   <-- WebRoot
//								 /api        <-- RESTApplication.java
//                               /employees  <-- @Path hier in EmployeeResource
//http://localhost:8080/RESTTest/api/employees

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)      //We wait only JSON format
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})      //We Produce only JSON, XML also possible in Postman. In domain msut have XmlRootElement

public class EmployeeResource {
	//private EmployeeService empService = EmployeeServiceLocalFactory.getInstance();  //nur 1 service bekomme, egal wo ich aufrufe
	
	//@Inject //Übernimmt EmployeeSerrviceLovalFactory!
	@EJB(beanName = "EmployeeServiceLocal")  //Now just this will injected
	private EmployeeService empService;
	
	@GET								  
	public Response getAll() {
		List<Employee> list1 = null;
		try {
			list1 = empService.getAll();
		GenericEntity<List<Employee>> genericList = new GenericEntity<List<Employee>>(list1) {};
			return Response.ok(genericList).build();       //delivers 200
			
		} catch (ServiceException e) {
				e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
//Search with ID
//http://localhost:8080/RESTTest/api/employees/20
	@GET
	@Path("/{id}")
	public Response getEmployeeById(@PathParam("id") int id) {
		Employee empl = null;
		try {
			empl = empService.getById(id);
			if(empl != null) {
				return Response.ok(empl).build();
			}
			else {
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (ServiceException se) {
			se.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	
//Delete employee with id	
	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id") int id) {
		try {
			empService.removeById(id);
			return Response.noContent().build();
		} catch (ServiceException se) {
			se.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//In Postman in JSON senden right set 
	//@Valid means, use the validation von Object Attribute
	@POST
	public Response createEmployee(@Valid Employee e) {
		try {
			Employee createdEmpl = empService.add(e);
			return Response.ok(createdEmpl).build();
		} catch (ServiceException se) {
			se.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response updateEmployee(@PathParam("id") int id, @Valid Employee e) {
		try {
			e.setId(id);
			empService.update(e);
			return Response.ok(e).build();
		} catch (ServiceException se) {
			se.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	

	
}
