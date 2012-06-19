package servlet;


import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import enums.Privilege;
import model.Captain;
import model.User;
import model.UserAdmin;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

public class UserManager extends MainServlet {
	private static final long serialVersionUID = 1L;

	
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		
		System.out.println("La accion es: " + action);
		switch (action) {
		case USERPROFILE: return userProfile(request, response);
		case DELETEUSER: return deleteUser(request, response);	
		case MODIFYUSER: return modifyUser(request, response);
		case MODIFYUSERPAGE: return modifyUserPage(request, response);
		case ADDUSER: return addUser(request,response);
		}
		return null;
	}


	@SuppressWarnings("rawtypes")
	private PageJSP addUser(HttpServletRequest request,
			HttpServletResponse response) {
		User user;
	
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					if (item.getFieldName().equals("tipo")) {
						if (item.getString().equalsIgnoreCase(Privilege.USERADMIN.getValue())){
						user = new UserAdmin();
						user.setPrivilege(Privilege.USERADMIN);
						addUserCaracteristics(user,items,request,response);
						UserDao.update(user);
					
						}else if (item.getString().equalsIgnoreCase(Privilege.USER.getValue())){
						user = new User();
						user.setPrivilege(Privilege.USER);
						addUserCaracteristics(user,items,request,response);
						UserDao.update(user);
						}
					}
				}
			}
							
		} catch (FileUploadException e) {
		e.printStackTrace();
		}

			return PageJSP.HOMESERVLET;
	
	
	}

	@SuppressWarnings("rawtypes")
	private <U extends User> void addUserCaracteristics(U u, List items,
			HttpServletRequest request, HttpServletResponse response) {
		
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) {
				if (item.getFieldName().equals("userId")) {
					u.setUserName(item.getString());		
					HttpSession session = request.getSession();
					session.setAttribute("username",u.getUserName());			
			
				} else if (item.getFieldName().equals("password")) {
					String encryptpass = UserDao.md5convert(item.getString());
					u.setPassword(encryptpass);
				} else if (item.getFieldName().equals("name")) {
					u.setName(item.getString());
				} else if (item.getFieldName().equals("lastname")) {
					u.setLastName(item.getString());
				} else if (item.getFieldName().equals("email")) {
					u.setEmail(item.getString());
				}
			}else {
					byte[] byteArray = item.get();
					u.setPhoto(byteArray);
				}
		}
	}


	private PageJSP modifyUserPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		setAttributesOnPage(request,response);
		return PageJSP.MODIFYUSERPAGE;
	}


	private PageJSP modifyUser(HttpServletRequest request,
			HttpServletResponse response) {
		String tipo = UserDao.getUserByUserName(request.getRemoteUser()).getPrivilege().getValue();
		if (tipo.equalsIgnoreCase(Privilege.USER.getValue())) {

			User user = UserDao.getUserByUserName((String) request.getRemoteUser());
			modifyUserCharacteristics(user, request, response);
		} 
		
		else if(tipo.equalsIgnoreCase(Privilege.CAPTAIN.getValue())){
			Captain captain = (Captain) UserDao.getUserByUserName((String) request.getRemoteUser());
			modifyUserCharacteristics(captain, request, response);
		}
		
		else{
			UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getRemoteUser());
			modifyUserCharacteristics(userAdmin, request, response);
			}
		
		return PageJSP.USERPROFILEPAGE;
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
		
		setAttributesOnPage(request,response);
		return PageJSP.USERPROFILEPAGE;
	}


	@SuppressWarnings("rawtypes")
	private <T extends User> void modifyUserCharacteristics(T t,
			HttpServletRequest request, HttpServletResponse response) {
	


		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					if (item.getFieldName().equals("newpassword")) {
						String encryptpass = UserDao.md5convert(item.getString());
						t.setPassword(encryptpass);
					} else if (item.getFieldName().equals("name")) {
						t.setName(item.getString());
					} else if (item.getFieldName().equals("lastname")) {
						t.setLastName(item.getString());
					} else if (item.getFieldName().equals("email")) {
						t.setEmail(item.getString());
					}
				}else {
						byte[] byteArray = item.get();
						t.setPhoto(byteArray);
					}
			
				}
			
		} catch (FileUploadException e) {
		e.printStackTrace();
		}
		
		UserDao.update(t);
	}
	
	private void setAttributesOnPage(HttpServletRequest request,
			HttpServletResponse response){
		User user = UserDao.getUserByUserName((String) request.getRemoteUser());
		String name = user.getName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String userName = user.getUserName();
		
		
		request.setAttribute("name", name);
		request.setAttribute("lastname", lastName);
		request.setAttribute("email", email);
		request.setAttribute("userName", userName);
	
	}

	
}