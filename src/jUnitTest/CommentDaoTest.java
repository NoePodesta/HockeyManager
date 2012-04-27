package jUnitTest;


import org.junit.Test;


import model.Comment;
import model.Match;
import model.UserAdmin;
import DAO.CommentDao;
import DAO.FixtureDao;
import DAO.MatchDao;
import DAO.UserDao;

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
