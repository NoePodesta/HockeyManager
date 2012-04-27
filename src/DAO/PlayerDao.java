package DAO;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;


import model.Player;
import model.Team;
import model.Tournament;
import model.User;


import org.hibernate.FlushMode;
import org.hibernate.Query;

import org.hibernate.Session;

public class PlayerDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static void update(Player player) {
		getSession();
		persist(player, currentSession);
	}

	public static void remove(Player player, String teamid) {
		getSession();
		
		Team team = TeamDao.getTeamById(teamid);
		List<Player> players = team.getPlayers();
		for (Player playerAux : players) {
			if (playerAux.getName().equals(player.getName())){
				players.remove(playerAux);
				team.setPlayers(players);
				TeamDao.update(team);
				getSession();
				Team team1 = null;
				playerAux.setTeam(team1);
				delete(playerAux, currentSession);
				return;
			}
		}
	}
	

	
	public static Player getPlayer(String name, String lastName) {
		getSession();
		Player player = null;
		final String consult = "FROM Player WHERE name = '" + name + "' AND lastName = '" + lastName + "'";
		List<Player> players = currentSession.createQuery(consult).list();
		if (!players.isEmpty()) {
			player = players.get(0);
		}

		return player;

	}
	
	public static Player getPlayer(String name, String lastName, int localid) {
		getSession();
		Player player = null; 
		final String consult = "FROM Player WHERE name = '" + name + "' AND lastName = '" + lastName + "' AND team = '" + localid + "'";
		List<Player> players = currentSession.createQuery(consult).list();
		if (!players.isEmpty()) {
			player = players.get(0);
		}

		return player;

	}
	
	public static List<Player> getPlayers (String name) {
		List<Player> players = new ArrayList<Player>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String query = "from Player where lower(name) LIKE lower('%"+ name +"%') ";
			players = session.createQuery(query).list();
			
		} finally {
			session.close();
		}
		return players;
	}
	
	public static List<Player> getAllPlayers() {
		getSession();
		final String consult = "FROM Player";
		Query query = currentSession.createQuery(consult);
		List<Player> players = query.list();
		return players;

        
    }
	
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}
}