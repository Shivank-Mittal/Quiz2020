package fr.epita.quiz.services.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.epita.quiz.datamodel.Answer;

public class AnswerDAO extends GenericDAO<Answer, Long>{

	@Override
	public String getQuery() {
		return "from Answer ";
	}

	@Override
	public void setParameters(Map<String, Object> parameters, Answer criteria) {
		return;
	}

	@Override
	public Class<Answer> getEntityClass() {
		// TODO Auto-generated method stub
		return Answer.class;
	}
	

	public void deleteAnswer(Answer entity) {

		EntityManager em = getEntityManager();
		
		System.out.println("Deliting entity");
		em.remove(getById(entity.getId()));
	}
	
	
	


}
