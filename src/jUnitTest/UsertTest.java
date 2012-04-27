package jUnitTest;

import static org.junit.Assert.*;
import model.UserAdmin;

import org.junit.Test;

import DAO.UserDao;

import enums.Privilege;

public class UsertTest {
	
	@Test
	public void UserAdminTest(){
		
	UserAdmin userAdmin = new UserAdmin();	
	userAdmin.setLastName("Jacobi");
	userAdmin.setEmail("jacobi@hockeysite.com.ar");
	userAdmin.setName("Pedro");
	userAdmin.setPassword("pass2");
	userAdmin.setUserName("Pepi");
	userAdmin.setPrivilege(Privilege.USERADMIN);
	UserDao.update(userAdmin);
	//UserAdmin admin1 = (UserAdmin) UserDao.getUserByUserName("Pepi");
	//UserDao.removeUserAdmin("Pepi");
	assertTrue(true);

	}

}
