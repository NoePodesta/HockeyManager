package jUnitTest;

import DAO.FixtureDao;
import DAO.TournamentDao;
import DAO.UserDao;
import enums.Privilege;
import model.Tournament;
import model.UserAdmin;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserDaoTest {
	

//	@Test
//	public void UserAdminTest(){
//		
//	UserAdmin userAdmin = new UserAdmin();	
//	userAdmin.setLastName("Jacobi");
//	userAdmin.setEmail("jacobi@hockeysite.com.ar");
//	userAdmin.setName("Pedro");
//	userAdmin.setPassword("vero");
//	userAdmin.setUserName("vero");
//	UserDao.update(userAdmin);
//	UserAdmin admin1 = (UserAdmin) UserDao.getUserByUserName("Pepi");
//	UserDao.removeUserAdmin("Pepi");
//	assertTrue(true);
	
	

//	}
	

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
//	
//	byte[] image = null;
//	UserDao.addTournament("Pepi", "juju", "esta re bueno esto",image);
//	assertTrue(true);
//	}
	
//	@Test
//	public void removeUserTest(){
//		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName("nico");
//		UserDao.removeUser(userAdmin);
//		assertTrue(UserDao.getUserByUserName("nico")==null);
//		
//	}
	
    @Test
    public void generateFixture() {

        Tournament tournament = TournamentDao.getTournamentByName("juju");
        FixtureDao.generarFixture(tournament);
        assertTrue(true);
    }
}


	





   

