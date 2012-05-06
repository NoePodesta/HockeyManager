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



public class AddUser extends MainServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	protected PageJSP handleAction(HttpServletRequest request, HttpServletResponse response, Action action){
		
		User user;
		String remoteAddr = request.getRemoteAddr();
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
						addCaracteristicasUser(user,items,request,response);
						UserDao.update(user);
					
						}else if (item.getString().equalsIgnoreCase(Privilege.USER.getValue())){
						user = new User();
						user.setPrivilege(Privilege.USER);
						addCaracteristicasUser(user,items,request,response);
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
	private <U extends User> void addCaracteristicasUser(U u, List items,
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
}			
				