package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import DAO.UserDao;

public class ImageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		response.setContentType("image/jpeg");
		User user = UserDao.getUserByUserName((String) request.getRemoteUser());
		
		byte[] imageBytes = user.getPhoto();
	    OutputStream os = null;
	    if(imageBytes.length!=0){
	            try {
	            	
	                response.setContentLength(imageBytes.length);
	            	os = response.getOutputStream();
	                response.getOutputStream().write(imageBytes);
	                response.getOutputStream().flush();  
	                os.close();
	                System.out.println("llega hasta aca");

	            } catch (IOException e) {
	                    e.printStackTrace();
	            }

	            



	    }

		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		doPost(request, response);
	}
	

	


}
