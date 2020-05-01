package fr.epita.quiz.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
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

import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.business.ExamDataService;
import fr.epita.quiz.services.dao.AnswerDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.UserDAO;

@Path("/exam")
public class ExamResource {
	
	@Inject
	ExamDataService examDS;
	
	@Inject
	AnswerDAO adao;
	
	@Inject 
	QuestionDAO qdao;
	
	@Inject
	UserDAO udao;
	
	@POST
	@Path("/answer")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addAnswerToQuestion(@RequestBody AnswerDTO answerDTO ) {
		
		System.out.println( "reached ");
		
		Answer answer = new Answer();
		answer.setContent(answerDTO.getContent());

		System.out.println("got post with :" + answerDTO.getContent());
		//TODO call examDS on this answer
		
		ExamDataService dataService = new ExamDataService();
		try {
		examDS.answerToQuestion(answerDTO.getUser(),answerDTO.getQuestion(), answer);
		}catch( Exception e) {
			System.out.println(e);
		}
		try {
			return Response.created(new URI("/rest/exam/answer/" + answer.getId())).build();
		} catch (URISyntaxException e) {
			// TODO Handle things properly
			e.printStackTrace();
		}
		return Response.ok(answerDTO.getContent()).build();
	
	}
	
	@GET
	@Path("/answer/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAnswer(@PathParam("id") long answerId) {
		
		  Answer answer=  examDS.getAnswer(answerId);

		return Response.ok(answer).build();
	}
	
	@GET
	@Path("/answer")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAnswers() {
		
		//beginning dummy implementation
		Answer answer = new Answer();
		
		List<Answer>  answers = examDS.getAnswers();
		
//		AnswerDTO answerDTO = new AnswerDTO(answer);
		
//		Answer answer = new Answer(answer);
//		return Response.ok(Arrays.asList(answers)).build();
		return Response.ok(answers).build();
	}
	
	
	@PUT
	@Path("/answer")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateAnswer(AnswerDTO answerDTO){
		Answer answer = new Answer();
		answer.setId(answerDTO.getId());
		answer.setContent(answerDTO.getContent());
		answer.setQuestion(answerDTO.getQuestion());
		answer.setUser(answer.getUser());
		adao.update(answer);
		
		return "updated";
	}
	
	//@DELETE
	
	@DELETE
	@Path("/answer/{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response removeAnswer(@PathParam("id") long id) {
		
		
		examDS.deleteAnswer(id);
		
		return Response.ok("deleted").build();
	
	}

}
