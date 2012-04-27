package servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import DAO.UserDao;

import enums.Action;
import enums.PageJSP;

/**
 * Servlet implementation class UsernameCheck
 */
public class UsernameCheck extends MainServlet {
	private static final long serialVersionUID = 1L;

	@Override
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String username = request.getParameter("userId");
			System.out.println(username);
			User user = UserDao.getUserByUserName(username);
			
			if (user == null) {
				out.println("<img src=\"image/good.png\"  width=\"10\" height=\"10\" >" + " " + username
						+ "</b> no ha sido utilizado");
			} else {
				out.println("<img src=\"image/no.png\"  width=\"10\" height=\"10\"><font color=red> Ya existe un usuario con </b>" + username + "</b> como username</font>");

			}
			out.println();

		} catch (Exception ex) {

			out.println("Error ->" + ex.getMessage());

		} finally {

			out.close();

		}
		return PageJSP.HOME;
	}
}
