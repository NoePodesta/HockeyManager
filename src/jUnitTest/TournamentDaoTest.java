package jUnitTest;

import DAO.TournamentDao;
import org.junit.Test;

public class TournamentDaoTest {
	
//	@Test
//	public void allTournamentsTest(){
//		
//		List<Tournament> tournaments = TournamentDao.getAllTournaments();
//		System.out.println(tournaments);	
//		assertTrue(TournamentDao.getAllTournaments().size()==1);
//	}
//	
	@Test
	public void addTeam() {
		
		TournamentDao.addTeam("juju", "6", "6", "6");
		TournamentDao.addTeam("juju", "5", "5", "5");
		TournamentDao.addTeam("juju", "4", "4", "4");
//		TournamentDao.addTeam("Hola", "San Fernando", "nueva historia", "esto es nuevo");
//		TournamentDao.addTeam("HSRA", "Olivos", "jejej", "Buena onda");
//		TournamentDao.addTeam("HSRA", "Lomas", "jejej", "Buena onda");
//		TournamentDao.addTeam("HSRA", "Pucara", "nueva historia", "esto es nuevo");
//		TournamentDao.addTeam("HSRA", "Banco provincia", "jejej", "Buena onda");
//		TournamentDao.addTeam("HSRA", "Banco Ciudad", "jejej", "Buena onda");
//		TournamentDao.addTeam("HSRA", "San Andres", "nueva historia", "esto es nuevo");
//		TournamentDao.addTeam("HSRA", "Monte Grande", "jejej", "Buena onda");
//		TournamentDao.addTeam("HSRA", "U de la Plata", "jejej", "Buena onda");
	}
//	
//	@Test
//	public void deleteTeam() {
//	
//		Tournament tournament = TournamentDao.getTournamentByName("El Calacio");
//		Team team = TeamDao.getTeamByName("otro");
//		TournamentDao.deleteTeam(tournament,team);
//		
//		
//				
//	}
	
	
//	@Test
//	public void deleteTournament() {
//		
//	Tournament tournament = TournamentDao.getTournamentByName("HSRA");
//	UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName("Pepi");
//	TournamentDao.remove(userAdmin, tournament);
//	assertTrue(true);
//				
//	}

}
