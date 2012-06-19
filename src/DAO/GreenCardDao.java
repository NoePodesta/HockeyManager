package DAO;

import hibernate.HibernateUtil;
import model.GreenCard;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import java.util.List;

public class GreenCardDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static void update(GreenCard greenCard) {
		getSession();
		persist(greenCard, currentSession);
	}
	
	public static <T> void remove(GreenCard greenCard) {
		getSession();
		delete(greenCard, currentSession);

	}

	public static List<GreenCard> getGreenCards(String playerid, String teamid,  String matchid) {
		getSession(); 
		final String consult = "FROM GreenCard WHERE player = '" + playerid + "' AND match = '" + matchid + "' AND team = '" + teamid + "'";
		List<GreenCard> greenCards = currentSession.createQuery(consult).list();
		return greenCards;

	}
	
	public static List<GreenCard> getGreenCards(String playerid) {
		getSession();
		final String consult = "FROM GreenCard WHERE player = '" + playerid + "'";
		List<GreenCard> greenCards = currentSession.createQuery(consult).list();
		return greenCards;

	}
	
	
	public static List<GreenCard> getGreenCards(int idmatch) {
		getSession();
		final String consult = "FROM GreenCard WHERE match = '" + idmatch + "'";
		List<GreenCard> greenCards = currentSession.createQuery(consult).list();
		return greenCards;

	}
	
	public static List<GreenCard> getGreenCards(int playerid, int idmatch10) {
		getSession();
		final String consult = "FROM GreenCard WHERE player = '" + playerid + "' AND match = '" + idmatch10 + "'";
		List<GreenCard> greenCards = currentSession.createQuery(consult).list();
		return greenCards;

	}
	

	
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}
}