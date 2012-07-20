package DAO;

import hibernate.HibernateUtil;
import model.*;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class TournamentDao extends GenericDao {
	
	private static Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}
	
	public static <T> void update(Tournament tournament) {
		getSession();
		persist(tournament, currentSession);
	}
	
	public static void remove(UserAdmin userAdmin, Tournament actualTournament) {
		getSession();
		Tournament tournament = null;
		userAdmin.setTournament(tournament);
		UserDao.update(userAdmin);
		if(!(actualTournament.getFixture() == null)){
		getSession();
			deleteFixture(actualTournament);
		}
		
		List<Team> teams =actualTournament.getTeams();
		for (int i = 0; i<teams.size(); i++){
			deleteTeam(actualTournament, teams.get(i));
		}
				
		getSession();
		delete(actualTournament, currentSession);
	}

    public static Tournament getTournamentByName(String name) {
        getSession();
        Tournament tournament = null;
        final String consult = "FROM Tournament WHERE name = '" + name + "'";
        List<Tournament> tournaments = currentSession.createQuery(consult).list();
        if (!tournaments.isEmpty()) {
            tournament = tournaments.get(0);
        }

        return tournament;

    }

	public static Tournament getTournamentById (int id) {
		getSession();
		Tournament tournament = null;
		final String consult = "FROM Tournament WHERE Id_Tournament='" + id + "' ";
		List<Tournament> tournaments = currentSession.createQuery(consult).list();
		if(!tournaments.isEmpty()){
			tournament = tournaments.get(0);
		}
		return tournament;
	}

	public static User getUserByUserName(String userName) {
		getSession();
		User user = null;
		final String consult = "FROM User WHERE userName = '" + userName + "'";
		List<User> users = currentSession.createQuery(consult).list();
		if (!users.isEmpty()) {
			user = users.get(0);
		}

		return user;

	}

	
	public static List<Tournament> getTournamentsByName (String name) {
		List<Tournament> tournamentList = new ArrayList<Tournament>();


		try {
			String query = "from Tournament where lower(name) LIKE lower('%"+ name +"%')";
			tournamentList = currentSession.createQuery(query).list();
			
		} finally {
			currentSession.close();
		}
		return tournamentList;
	}
	
	public static List<Tournament> getAllTournaments() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		final String consult = "FROM Tournament";
		Query query = session.createQuery(consult);
		List<Tournament> tournaments = query.list();
		return tournaments;
     }

		
	public static String addTeam(String tournamentName, String teamName, String description, byte[] image) {
		
		getSession();
		Tournament tournament = getTournamentByName(tournamentName);
		List<Team> teams = tournament.getTeams();
		if(!(teams== null)){
			for(Team auxTeam : teams){
				if(auxTeam.getName().equals(teamName)){
					return "fail";
				}
			}
		}	
		Team team = new Team();
		team.setName(teamName);
		team.setDescription(description);
		team.setPhoto(image);
		team.setTournament(tournament);
		teams.add(team);
		tournament.setTeams(teams);
		TeamDao.update(team);
		getSession();
		update(tournament);
		return "success"; 
		
	}
	
	public static void deleteTeam(Tournament actualTournament, Team team) {
		getSession();
		List<Team> tournamentTeams = actualTournament.getTeams();
		for (Team teamAux : tournamentTeams) {
			if (teamAux.getName().equals(team.getName())){
				tournamentTeams.remove(teamAux);
				actualTournament.setTeams(tournamentTeams);
				TournamentDao.update(actualTournament);
				getSession();
				Tournament tournament = null;
				teamAux.setTournament(tournament);
				TeamDao.remove(teamAux);
				return;
			}
		}
	}
	
	public static void deleteFixture(Tournament tournament) {
		getSession();
		
		Fixture actualFixture = tournament.getFixture();
		Fixture fixture = null;
		tournament.setFixture(fixture);
		TournamentDao.update(tournament);
		getSession();
		FixtureDao.remove(actualFixture);
				
		}
	
	
	
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}


	
}