package servlet;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import DAO.UserDao;

import enums.Action;
import enums.PageJSP;


public class ImageController extends MainServlet {

	private static final long serialVersionUID = 1;	@Override
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		
		switch (action) {
			case USERIMAGE: return createImage(request, response);
			
		
		}
		return PageJSP.HOMESERVLET;
	}
	private PageJSP createImage(HttpServletRequest request,
			HttpServletResponse response) {
		
            response.setContentType("image/png");           

        	User user = UserDao.getUserByUserName((String) request.getRemoteUser());

            byte[] imageBytes = user.getPhoto();



            OutputStream os = null;
            
  
            if(imageBytes.length!=0){

                    try {

                            os = response.getOutputStream();

                            response.getOutputStream().write(imageBytes);

                            os.close();

                    } catch (IOException e) {

                            // TODO Auto-generated catch block

                            e.printStackTrace();

                    }

                    response.setContentType("image/jpeg");

                    response.setContentLength(imageBytes.length);



            }





    //      ImageIO.write(buffer, "png", os);



		
		
		return PageJSP.USERPROFILEPAGE;
	}

}