package fr.epita.quiz.services.business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;


import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.dao.AnswerDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.UserDAO;



public class ExamDataService {
	
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
	
	@Transactional(value = TxType.REQUIRED)
	public Answer getAnswer(long answerId) {
		
		Answer answer = answerDAO.getById(answerId);

		return answer;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public List<Answer> getAnswers() {
		
		Answer answer = new Answer();
		answer.setContent("This is a sampleAnswer");
		
		List<Answer>  answers = answerDAO.getAll();
		return answers;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void updateAnswer(Answer updateAnswer) {
		answerDAO.update(updateAnswer);
	}
	
	
	@Transactional(value = TxType.REQUIRED)
	public void deleteAnswer(long id) {
		
//		answerDAO.deleteMapping(entity.getId());
		Answer answer = answerDAO.getById(id);
		answerDAO.delete(answer);
//		answerDAO.delete(entity);
	}
}
