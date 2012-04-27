package DAO;

import hibernate.HibernateUtil;
import java.util.List;


import model.Goal;
import model.Player;
import model.Team;
import model.Tournament;
import model.User;


import org.hibernate.FlushMode;
import org.hibernate.Query;

import org.hibernate.Session;

public class GoalDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static void update(Goal goal) {
		getSession();
		persist(goal, currentSession);
	}

	public static List<Goal> getGoal(String playerid, String teamid,  String matchid) {
		getSession();
		Goal goal = null; 
		final String consult = "FROM Goal WHERE player = '" + playerid + "' AND match = '" + matchid + "' AND team = '" + teamid + "'";
		List<Goal> goals = currentSession.createQuery(consult).list();
		return goals;

	}
	
	public static List<Goal> getGoal(String playerid) {
		getSession();
		Goal goal = null; 
		final String consult = "FROM Goal WHERE player = '" + playerid + "'";
		List<Goal> goals = currentSession.createQuery(consult).list();
		return goals;

	}
	
	
	public static List<Goal> getGoals(int idmatch8) {
		getSession();
		Goal goal = null;
		final String consult = "FROM Goal WHERE match = '" + idmatch8 + "'";
		List<Goal> goals = currentSession.createQuery(consult).list();
		return goals;

	}
	
	public static List<Goal> getGoal(String playerid, String matchid) {
		getSession();
		Goal goal = null; 
		final String consult = "FROM Goal WHERE player = '" + playerid + "' AND match = '" + matchid + "'";
		List<Goal> goals = currentSession.createQuery(consult).list();
		return goals;

	}
	

	
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}
}