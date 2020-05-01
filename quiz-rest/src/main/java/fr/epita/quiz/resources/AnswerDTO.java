package fr.epita.quiz.resources;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;

public class AnswerDTO {

	private Long id;
	private String content;
	private Question question;
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
