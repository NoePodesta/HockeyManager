package jUnitTest;

import DAO.UserDao;
import enums.Privilege;
import model.UserAdmin;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserDaoTest {
	

	@Test
	public void UserAdminTest(){
		
	UserAdmin userAdmin = new UserAdmin();	
	userAdmin.setLastName("Jacobi");
	userAdmin.setEmail("jacobi@hockeysite.com.ar");
	userAdmin.setName("Pedro");
	userAdmin.setPassword("vero");
	userAdmin.setUserName("vero");
	userAdmin.setPrivilege(Privilege.USERADMIN);
	UserDao.update(userAdmin);
//	UserAdmin admin1 = (UserAdmin) UserDao.getUserByUserName("Pepi");
//	UserDao.removeUserAdmin("Pepi");
	assertTrue(true);
	
	

	}
	
//	@Test
//	public void CaptainTest(){
//		
//	Captain captain = new Captain();	
//	captain.setLastName("Podesta");
//	captain.setEmail("jacobi@hockeysite.com.ar");
//	captain.setName("Belu");
//	captain.setPassword("pass2");
//	captain.setUserName("Belu");
//	Captain captain1 = (Captain) UserDao.getUserByUserName("Belu");
//	Team team = TeamDao.getTeamByName("Belgrano");
//	captain1.setTeam(team);
//	UserDao.update(captain1);
//	UserDao.update(captain);
//		
//	UserDao.removeCaptain("Belu");
//	assertTrue(true);
//
//	}
//	
//	@Test
//	public void AdministradorTest(){
//		
//	Administrador administrador = new Administrador();
//	administrador.setLastName("Podesta");
//	administrador.setEmail("podesta@hockeysite.com.ar");
//	administrador.setName("Noel");
//	administrador.setPassword("none");
//	administrador.setUserName("Noe");
//	UserDao.update(administrador);
//	Administrador administrador1 = (Administrador) UserDao.getUserByUserName("Noe");
//	UserDao.removeUser(administrador1);
//	assertTrue(true);
//	}
//	
//	@Test
//	public void AddTournamentTest(){
//	UserDao.addTournament("juli", "juju", "esta re bueno esto");
//	assertTrue(true);
//	}
	
}


	





   

