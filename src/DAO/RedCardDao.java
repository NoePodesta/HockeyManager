package DAO;

import hibernate.HibernateUtil;
import model.RedCard;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import java.util.List;

public class RedCardDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static void update(RedCard redCard) {
		getSession();
		persist(redCard, currentSession);
	}

	public static List<RedCard> getRedCards(String playerid, String teamid,  String matchid) {
		getSession(); 
		final String consult = "FROM Goal WHERE player = '" + playerid + "' AND match = '" + matchid + "' AND team = '" + teamid + "'";
		List<RedCard> redCards = currentSession.createQuery(consult).list();
		return redCards;

	}
	
	public static List<RedCard> getRedCards(String playerid) {
		getSession();
		final String consult = "FROM Goal WHERE player = '" + playerid + "'";
		List<RedCard> redCards = currentSession.createQuery(consult).list();
		return redCards;

	}
	
	
	public static List<RedCard> getRedCards(int idmatch) {
		getSession();
		final String consult = "FROM Goal WHERE match = '" + idmatch + "'";
		List<RedCard> redCards = currentSession.createQuery(consult).list();
		return redCards;

	}
	
	public static List<RedCard> getRedCards(String playerid, String matchid) {
		getSession();
		final String consult = "FROM Goal WHERE player = '" + playerid + "' AND match = '" + matchid + "'";
		List<RedCard> redCards = currentSession.createQuery(consult).list();
		return redCards;

	}
	

	
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}
}