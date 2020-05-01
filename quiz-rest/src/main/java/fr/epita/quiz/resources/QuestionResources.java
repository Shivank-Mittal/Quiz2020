package fr.epita.quiz.resources;

import java.awt.image.RescaleOp;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.business.ExamDataService;
import fr.epita.quiz.services.business.QuizSetupDataService;
import fr.epita.quiz.services.dao.QuestionDAO;

@Path("/question")
public class QuestionResources {
	
	
	@Inject
	QuizSetupDataService examDS;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createQuestion(QuestionDTO questionDTO) {
		
		Question question = new Question();
		
		
		question.setTitle(questionDTO.getTitle());
		System.out.println(questionDTO.getTitle());
		
		if(examDS != null) {
			examDS.addQuestion(question);
		}
		
		try {
			return Response.created(new URI("/rest/exam/question/"+question.getId())).build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok("reached").build();
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@PathParam("id") Long id) {
		
		
		 Question  questions= examDS.getQuestion(id); 
		
		  return   Response.ok(questions).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateQuestion (  QuestionDTO questionDTO) {
		
		
		Question question = new Question();
		question.setId(questionDTO.getId());
		question.setTitle(questionDTO.getTitle());
		examDS.updateQuestion(question);
		
		return "updated";
	}
	
	@DELETE
	@Path("/{id}")
	public String delete(@PathParam("id") Long id  ) {
		
		examDS.delete(id);
		
		return "deleted";
	}
	
	

}
