package servlet;



import DAO.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserAdmin;
import model.Captain;
import model.User;
import enums.Action;
import enums.PageJSP;
import enums.Privilege;

public class UserManager extends MainServlet {
	private static final long serialVersionUID = 1L;

	
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		
		
		switch (action) {

		case USERPROFILE: return userProfile(request, response);
		case DELETEUSER: return deleteUser(request, response);	
		case MODIFYUSER: return modifyUser(request, response);
		case MODIFYUSERPAGE: return modifyUserPage(request, response);
		}
		return null;
	}


	private PageJSP modifyUserPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		User user = UserDao.getUserByUserName((String) request.getRemoteUser());
		String name = user.getName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String userName = user.getUserName();
				
		
		request.setAttribute("name", name);
		request.setAttribute("lastname", lastName);
		request.setAttribute("email", email);
		request.setAttribute("userName", userName);
		
		
		return PageJSP.MODIFYUSERPAGE;
	}


	private PageJSP modifyUser(HttpServletRequest request,
			HttpServletResponse response) {
		String tipo = UserDao.getUserByUserName(request.getRemoteUser()).getPrivilege().getValue();
		if (tipo.equalsIgnoreCase(Privilege.USER.getValue())) {

			User user = UserDao.getUserByUserName((String) request.getRemoteUser());
			modificarCaracteristicasUser(user, request, response);
		} 
		
		else if(tipo.equalsIgnoreCase(Privilege.CAPTAIN.getValue())){
			Captain captain = (Captain) UserDao.getUserByUserName((String) request.getRemoteUser());
			modificarCaracteristicasUser(captain, request, response);
		}
		
		else{
			UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getRemoteUser());
			modificarCaracteristicasUser(userAdmin, request, response);
			}
		
		return PageJSP.HOMESERVLET;
	}
	
	private PageJSP deleteUser(HttpServletRequest request,
			HttpServletResponse response) {
		
		String tipo = UserDao.getUserByUserName(request.getRemoteUser()).getPrivilege().getValue();
		if (tipo.equalsIgnoreCase(Privilege.USER.getValue())) {
			UserDao.removeUser(UserDao.getUserByUserName((String)request.getRemoteUser()));
			
		} else if (tipo.equalsIgnoreCase(Privilege.USERADMIN.getValue())){
			UserDao.removeUserAdmin(UserDao.getUserByUserName((String)request.getRemoteUser()).getUserName());
		}
		return PageJSP.LOGOUT;
	}


	private PageJSP userProfile(HttpServletRequest request,
			HttpServletResponse response) {
		
		User user = UserDao.getUserByUserName((String) request.getRemoteUser());
		String name = user.getName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String userName = user.getUserName();
		
		
		request.setAttribute("name", name);
		request.setAttribute("lastname", lastName);
		request.setAttribute("email", email);
		request.setAttribute("userName", userName);
			
		
		return PageJSP.USERPROFILEPAGE;
	}


	private <T extends User> void modificarCaracteristicasUser(T t,
			HttpServletRequest request, HttpServletResponse response) {
	

		String encryptpass = UserDao.md5convert(request.getParameter("newpassword"));
		t.setPassword(encryptpass);
		t.setName(request.getParameter("name"));
		t.setLastName(request.getParameter("lastname"));
		t.setEmail(request.getParameter("email"));
		
		UserDao.update(t);
	}

	
}