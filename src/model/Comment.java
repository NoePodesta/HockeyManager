package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Comment")

public class Comment {
	
	private int idComment;
	private User user;
	private String date;
	private String commemt;
	
	
	@Id
	@GeneratedValue
	@Column(name="Id_Player")
	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name ="Date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	@Column(name ="Comment")
	public String getCommemt() {
		return commemt;
	}
	public void setCommemt(String commemt) {
		this.commemt = commemt;
	}
	
	
}
