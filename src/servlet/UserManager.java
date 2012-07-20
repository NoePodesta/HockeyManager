package servlet;


import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import enums.Privilege;
import model.BasicUser;
import model.User;
import model.UserAdmin;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserManager extends MainServlet {
	private static final long serialVersionUID = 1L;

	
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {

			switch (action) {
		case USERPROFILE: return userProfile(request);
		case DELETEUSER: return deleteUser(request);
		case MODIFYUSER: return modifyUser(request);
		case ADDUSER: return addUser(request);
		}
		return null;
	}


	@SuppressWarnings("rawtypes")
	private PageJSP addUser(HttpServletRequest request) {
		
        User user;
	
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			List items = uploadHandler.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;
                if (item.isFormField()) {
                    if (item.getFieldName().equals("tipo")) {
                        if (item.getString().equalsIgnoreCase(Privilege.USERADMIN.getValue())) {
                            user = new UserAdmin();
                            addUserCaracteristics(user, items, request);
                            UserDao.update(user);

                        } else if (item.getString().equalsIgnoreCase(Privilege.BASICUSER.getValue())) {
                            user = new BasicUser();
                            addUserCaracteristics(user, items, request);
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
			HttpServletRequest request) {

        for (Object item1 : items) {
            FileItem item = (FileItem) item1;
            if (item.isFormField()) {
                if (item.getFieldName().equals("userId")) {
                    u.setUserName(item.getString());
                    HttpSession session = request.getSession();
                    session.setAttribute("username", u.getUserName());
                } else if (item.getFieldName().equals("password")) {
                    String encryptPass = UserDao.md5convert(item.getString());
                    u.setPassword(encryptPass);
                } else if (item.getFieldName().equals("name")) {
                    u.setName(item.getString());
                } else if (item.getFieldName().equals("lastname")) {
                    u.setLastName(item.getString());
                } else if (item.getFieldName().equals("email")) {
                    u.setEmail(item.getString());
                }
            } else {
                byte[] byteArray = item.get();
                u.setPhoto(byteArray);
            }
        }
	}

	private PageJSP modifyUser(HttpServletRequest request) {
		String tipo = UserDao.getUserByUserName(request.getRemoteUser()).getPrivilege().getValue();
		if (tipo.equalsIgnoreCase(Privilege.BASICUSER.getValue())) {

			User user = UserDao.getUserByUserName(request.getRemoteUser());
			modifyUserCharacteristics(user, request);
		}
		
		else{
			UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName(request.getRemoteUser());
			modifyUserCharacteristics(userAdmin, request);
			}
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}
	
	private PageJSP deleteUser(HttpServletRequest request) {
		
		Privilege privilege = UserDao.getUserByUserName(request.getRemoteUser()).getPrivilege();

        if (privilege.isBasicUser()) {
			UserDao.removeUser(UserDao.getUserByUserName(request.getRemoteUser()));
			
		} else if (privilege.isAdministrador() || privilege.isUserAdmin()){
			UserDao.removeUserAdmin(UserDao.getUserByUserName(request.getRemoteUser()).getUserName());
		}
		return PageJSP.LOGOUT;
	}


	private PageJSP userProfile(HttpServletRequest request) {

        User user = UserDao.getUserByUserName(request.getRemoteUser());
        request.setAttribute("user",user);
		return PageJSP.USERPROFILEPAGE;
	}


	@SuppressWarnings("rawtypes")
	private <T extends User> void modifyUserCharacteristics(T t,
			HttpServletRequest request) {

		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			List items = uploadHandler.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;
                if (item.isFormField()) {
                    if (item.getFieldName().equals("newPassword")) {
                        String encryptpass = UserDao.md5convert(item.getString());
                        t.setPassword(encryptpass);
                    } else if (item.getFieldName().equals("name")) {
                        t.setName(item.getString());
                    } else if (item.getFieldName().equals("lastName")) {
                        t.setLastName(item.getString());
                    } else if (item.getFieldName().equals("email")) {
                        t.setEmail(item.getString());
                    }
                } else {
                    if(item.getSize()>0){
                        t.setPhoto(item.get());
                    }
                    
                }

            }
			
		} catch (FileUploadException e) {
		e.printStackTrace();
		}
		
		UserDao.update(t);
	}
}