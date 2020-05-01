package fr.epita.quiz.services.business;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.dao.AnswerDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.UserDAO;



public class QuizSetupDataService {
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	AnswerDAO answerDAO;
	
	@Transactional(value = TxType.REQUIRED)
	public void answerToQuestion(User user, Question question, Answer answer) throws ExamBusinessException {
		//check values
		//if not valid : throw exception?
		
		//main logic part
		//checks if the question exists in db, same for user
		//		first : check if there's an id
		//  call dao.getById(obj) to check if it there
		
		
		if( questionDAO.getById(question.getId()) != null  && userDAO.getById(user.getLoginName())!= null){
			
			//Assing user and questions to the answer
			System.out.println("Entered in DS");
			answer.setUser(user);
			answer.setQuestion(question);
			
			answerDAO.create(answer);
		}else {
			System.out.println("Value From quesition id = " + questionDAO.getById(question.getId()));
			System.out.println("Value From User id = " + userDAO.getById(user.getEmail()));
		}
//		otherwise : throw exception?
		
	}
	
	public void deleteAnswer( Answer entity) {
		answerDAO.delete(entity);
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public void addQuestion(Question question)  {
			
		System.out.println(questionDAO);
		System.out.println(userDAO);
		System.out.println(answerDAO);
			if(question != null && questionDAO != null) {
				questionDAO.create(question);	
			}else 
			{
				
				System.out.println("Inserted Question is null");
			}
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public Question getQuestion(Long id) {
		
		 Question  questions= questionDAO.getById(id);
		 
		 if(questions != null) {
			 
		 }
		  
		  return questions;
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public String updateQuestion (  Question question) {
		questionDAO.update(question);
		
		return "updated";
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public String delete(Long id) {
		Question quesDelete =  questionDAO.getById(id);
		questionDAO.delete(quesDelete);
		
		return "deleted";
	}
	
	
	//public answerToMCQ(User user, MCQQuestion mcq, List<MCQChoices> choices);
	

}
