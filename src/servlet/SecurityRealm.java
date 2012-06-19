package servlet;

import DAO.UserDao;
import model.User;
import org.securityfilter.realm.SimpleSecurityRealmBase;


public class SecurityRealm extends SimpleSecurityRealmBase {
	
	 public boolean booleanAuthenticate(String userName, String password) {
		 User user = null;
		 String encryptPass = UserDao.md5convert(password);
		 try{
	    	 user = UserDao.getUserByUserName(userName);
	     }catch(IndexOutOfBoundsException e){
	    	 return false;
	     }		 
	     if(user!=null){
	    	  if(user.getPassword().equals(encryptPass)){
		    	  return true;
		      }
	     }
	     return false;	   
	  }
	 
	 public boolean isUserInRole(String username, String role) {
	      return true;
	 }


}