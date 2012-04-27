package DAO;


import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import model.Captain;
import model.Match;
import model.Player;
import model.Team;
import model.Tournament;


import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;


public class TeamDao extends GenericDao {
	
	private static Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}
	
	public static <T> void update(Team team) {
		getSession();
		persist(team, currentSession);
	}
	
	public static void remove(Team actualTeam) {
		getSession();
		
	/*	List<Captain> captainList;
		String query = "from User where team ='" + actualTeam.getIdTeam() + "' ";
		captainList = currentSession.createQuery(query).list();
		
		if(!(captainList.isEmpty())){
			Captain captain = captainList.get(0);
			Team team = null;
			captain.setTeam(team);
			UserDao.update(captain);
		
			getSession();
			List<Player> players =actualTeam.getPlayers();
			for (int i = 0; i<players.size(); i++){
				PlayerDao.remove(players.get(i), actualTeam.getIdTeam());
			}
		
		}
		getSession();*/
		delete(actualTeam, currentSession);
	}



	@SuppressWarnings("unchecked")
	public static Team getTeamByName(String name) {
	getSession();
		List<Team> teamList = new ArrayList<Team>();
			
		try {
			String query = "from Team where name ='" + name + "' ";
			teamList = currentSession.createQuery(query).list();
			
		} finally {
			currentSession.close();
		}
		return teamList.get(0);
	}
	
	public static Team getTeamById(String id) {
		getSession();
			List<Team> teamList = new ArrayList<Team>();
				
			try {
				String query = "from Team where Id_Team='" + id + "' ";
				teamList = currentSession.createQuery(query).list();
				
			} finally {
				currentSession.close();
			}
			return teamList.get(0);
		}

	public static List<Team> getTeamsByName(String name) {
		List<Team> teamList = new ArrayList<Team>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String query = "from Team where name ='" + name + "' ";
			teamList = session.createQuery(query).list();
			
		} finally {
			session.close();
		}
		return teamList;
	}


	
	
	public static List<Team> getAllTeams() {
		getSession();
		final String consult = "FROM Team";
		Query query = currentSession.createQuery(consult);
		List<Team> teams = query.list();
		return teams;

        
    }
	
	public static String addPlayer(Team team, String playerName, String playerLastName, String email, String position) {
		getSession();
					
		
		List<Player> players = team.getPlayers();
		
		Player player = new Player();
		player.setName(playerName);
		player.setLastName(playerLastName);
		player.setEmail(email);
		player.setPosition(position);
		player.setTeam(team);
					
		players.add(player);
		team.setPlayers(players);
		PlayerDao.update(player);
		getSession();
		update(team);
		return "Success"; 
			
			
	}
	
	
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}



}
