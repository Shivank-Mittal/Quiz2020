package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.User;

public class UserDAO extends GenericDAO<User, String> {

	@Override
	public String getQuery() {
		return "from User where email = :pEmail ";
	}

	@Override
	public void setParameters(Map<String, Object> parameters, User criteria) {
		parameters.put("pEmail",criteria.getEmail());
		
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}
	

}
