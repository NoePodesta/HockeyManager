package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import model.User;
import DAO.CommentDao;
import DAO.UserDao;




public class AddComment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
		String userAdminName = request.getRemoteUser();
		User user = UserDao.getUserByUserName(userAdminName);
		String comment = request.getParameter("comment");
		

		CommentDao.createComment(user, comment);
		List<Comment> comments = CommentDao.getComments();
		request.setAttribute("comments", comments);
		request.setAttribute("page","comments");

      
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }

}