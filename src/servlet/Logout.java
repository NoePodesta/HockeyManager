package servlet;


import enums.Action;
import enums.PageJSP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends MainServlet {

	private static final long serialVersionUID = 1L;

	protected PageJSP handleAction(HttpServletRequest request, HttpServletResponse response, Action action){
		request.getSession().setAttribute("username", null);
		request.getSession().invalidate();
		return PageJSP.HOMESERVLET;
	}
}
