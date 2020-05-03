package fr.epita.quiz.resources;

import java.util.List;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.business.QuizSetupDataService;
import fr.epita.quiz.services.dao.UserDAO;

@Path("/user")
public class UserResource {
	
	@Inject
	QuizSetupDataService QuizSetupDS;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createUsers(User user)
	{
		QuizSetupDS.addUsers(user);
		return Response.ok("created").build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers( @PathParam("id") String id){
		User user = QuizSetupDS.getUser(id) ;
		if(user == null)return Response.noContent().build();
		return  Response.ok(user).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateUsers( User updateUser)
	{
//		User user = dao.getById(updateUser.getLoginName());
//		user.setEmail(updateUser.getEmail());
		
		QuizSetupDS.updateUser(updateUser);
		return Response.ok("Updated").build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteUser(@PathParam("name") String name) {
		QuizSetupDS.deleteUser(name);
		
		return Response.ok("created").build();
	}
	
	

}
