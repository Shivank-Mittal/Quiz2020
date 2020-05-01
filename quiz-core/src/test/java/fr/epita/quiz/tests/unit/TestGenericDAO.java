package fr.epita.quiz.tests.unit;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dao.QuestionDAO;

public class TestGenericDAO {

	public void testGenericDAO() {
		QuestionDAO dao = new QuestionDAO();
		dao.create(new Question());
		
	
		
		
	}
	
}
