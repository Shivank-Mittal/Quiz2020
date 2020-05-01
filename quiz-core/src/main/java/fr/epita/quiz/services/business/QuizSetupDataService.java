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
	public String deleteQuestion(Long id) {
		Question quesDelete =  questionDAO.getById(id);
		questionDAO.delete(quesDelete);
		
		return "deleted";
	}
	
	
	//User CRUD Operations
	
	@Transactional(value = TxType.REQUIRED)
	public void addUsers(User user) {
		userDAO.create(user);
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public void deleteUser(String id) {
		  User user = userDAO.getById(id);
		  
		  userDAO.delete(user);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void updateUser(User user) {
		userDAO.update(user);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public User getUser(String id) {
		User user = userDAO.getById(id);
		return user;
	}
	

}
