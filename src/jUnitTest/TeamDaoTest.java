package jUnitTest;

import static org.junit.Assert.assertTrue;

import model.Player;
import model.Team;

import org.junit.Test;

import DAO.PlayerDao;
import DAO.TeamDao;
import DAO.TournamentDao;

public class TeamDaoTest {

	@Test
	public void addPlayers() {
		
	/*	Team team = TeamDao.getTeamById("2");
		TeamDao.addPlayer(team, "Belu", "Podestá", "belu@hot.com", "Defensora");*/
/*		TeamDao.addPlayer("team","Macarena", "Retamosa", "none@hotmail.com", "volante");
		TeamDao.addPlayer("team","Julieta", "Retamosa", "none@hotmail.com", "volante");
		TeamDao.addPlayer("team","Josefina", "Retamosa", "none@hotmail.com", "volante");*/
		assertTrue(true);

	}
	
	@Test
	public void deletePlayers(){
		
		Player player = PlayerDao.getPlayer("Belu", "Podestá", 2);
		PlayerDao.remove(player, "2");
	
	}
	
	
	
}
