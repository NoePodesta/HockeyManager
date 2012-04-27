package servlet;




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDao;
import DAO.UserDao;

import model.Comment;
import model.User;

import enums.Action;
import enums.PageJSP;


public class CommentManager extends MainServlet {

	private static final long serialVersionUID = 1L;


	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		
		
		switch (action) {
		case ADDCOMMENT: System.out.println(action); return addComment(request, response);
		
		case SHOWCOMMENT: return showComments(request, response);
			
										
		}
	
		return PageJSP.HOMESERVLET;
	}
	
	public PageJSP showComments(HttpServletRequest request,
			HttpServletResponse response){
		
		List<Comment> comments = CommentDao.getComments();
		request.setAttribute("comments", comments);
		

		
		return PageJSP.COMMENTPAGE;
	}

	public PageJSP addComment(HttpServletRequest request,
			HttpServletResponse response){
		
		String userAdminName = request.getRemoteUser();
		User user = UserDao.getUserByUserName(userAdminName);
		String comment = request.getParameter("comment");
		

		CommentDao.createComment(user, comment);
		
		return null;
	}
}
