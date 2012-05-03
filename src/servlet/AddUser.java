package servlet;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserAdmin;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import enums.Privilege;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.PageJSP;

public class AddUser extends MainServlet {

	private static final long serialVersionUID = 1L;

	protected PageJSP handleAction(HttpServletRequest request, HttpServletResponse response, Action action){
//						
////			if (tipo.equalsIgnoreCase(Privilege.USER.getValue())) {
////				User user = new User();
////				addCaracteristicasUser(user, request, response);
////				user.setPrivilege(Privilege.USER);
////				UserDao.update(user);
////				
////			} else {
				UserAdmin userAdmin = new UserAdmin();
				userAdmin.setPrivilege(Privilege.USERADMIN);
				addCaracteristicasUser(userAdmin, request, response);
				UserDao.update(userAdmin);
//			}
//			
			HttpSession session = request.getSession();
			session.setAttribute("username",request.getParameter("username"));
			
			return PageJSP.HOMESERVLET;
	
	
	}
	
	private <T extends User> void addCaracteristicasUser(T t,
			HttpServletRequest request, HttpServletResponse response) {
		
		String remoteAddr = request.getRemoteAddr();
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					if (item.getFieldName().equals("userId")) {
						t.setUserName(item.getString());					
					} else if (item.getFieldName().equals("password")) {
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
	}

}


