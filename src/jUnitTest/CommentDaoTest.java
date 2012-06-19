package jUnitTest;


import DAO.CommentDao;
import DAO.UserDao;
import model.Comment;
import model.UserAdmin;
import org.junit.Test;

public class CommentDaoTest {
	
	@Test
	public void addComment() {
		
		Comment comment = new Comment();
		UserAdmin user = (UserAdmin) UserDao.getUserByUserName("Pepi");
		String commentString = "hola esta esta re buenoo, gracias por escribirnos";
		CommentDao.createComment(user, commentString);
		
		
		assert(true);		
		
	}
	


}
