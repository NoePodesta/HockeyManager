package DAO;

import hibernate.HibernateUtil;
import model.YellowCard;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import java.util.List;

public class YellowCardDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static void update(YellowCard yellowCard) {
		getSession();
		persist(yellowCard, currentSession);
	}

	public static List<YellowCard> getYellowCards(String playerid, String teamid,  String matchid) {
		getSession(); 
		final String consult = "FROM Goal WHERE player = '" + playerid + "' AND match = '" + matchid + "' AND team = '" + teamid + "'";
		List<YellowCard> yellowCard = currentSession.createQuery(consult).list();
		return yellowCard;

	}
	
	public static List<YellowCard> getYellowCards(String playerid) {
		getSession();
		final String consult = "FROM Goal WHERE player = '" + playerid + "'";
		List<YellowCard> yellowCards = currentSession.createQuery(consult).list();
		return yellowCards;

	}
	
	
	public static List<YellowCard> getYellowCards(int idmatch) {
		getSession();
		final String consult = "FROM Goal WHERE match = '" + idmatch + "'";
		List<YellowCard> yellowCards = currentSession.createQuery(consult).list();
		return yellowCards;

	}
	
	public static List<YellowCard> getYellowCards(String playerid, String matchid) {
		getSession();
		final String consult = "FROM Goal WHERE player = '" + playerid + "' AND match = '" + matchid + "'";
		List<YellowCard> yellowCards = currentSession.createQuery(consult).list();
		return yellowCards;

	}
	

	
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}
}