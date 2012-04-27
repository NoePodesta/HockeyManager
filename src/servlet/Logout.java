package servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.Action;

import enums.PageJSP;

public class Logout extends MainServlet {

	private static final long serialVersionUID = 1L;

	protected PageJSP handleAction(HttpServletRequest request, HttpServletResponse response, Action action){
		request.getSession().setAttribute("username", null);
		request.getSession().invalidate();
		return PageJSP.HOMESERVLET;
	}
}
