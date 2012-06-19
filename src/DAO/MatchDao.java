package DAO;

import hibernate.HibernateUtil;
import model.Fixture;
import model.Match;
import model.Team;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MatchDao extends GenericDao{

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static void update(Match match) {
		getSession();
		persist(match, currentSession);
	}


	public static void remove(Match match) { 
		getSession(); 
		delete(match, currentSession); 
	}
	 
	
	@SuppressWarnings("unchecked")
	public static Match getMatchById(int id) {
		getSession();
		final String consult = "FROM Match WHERE id_Match = '"
				+ id + "'";
		Query query = currentSession.createQuery(consult);
		List<Match> matches = query.list();
		Match match = null;
		if (!matches.isEmpty()) {
			match = matches.get(0);
		}
		return match;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Match> getMatchByFecha(int fecha, Fixture fixture) {
		getSession();
		final String consult = "FROM Match WHERE Fecha_Nro = '" 
				+ fecha + "' AND  = ' fixture= '" + fixture.getIdFixture() + "'";;
		Query query = currentSession.createQuery(consult);
		List<Match> matches = query.list();
		return matches;
	}

	public static boolean matchNotExist(int i, int j) {
		getSession();

		Match match = (Match) currentSession.createQuery(
				"from Match as Match where"
						+ "((Match.local = ? AND Match.guest =?) OR (Match.local = ? AND Match.guest =?))").setLong(0, i).setLong(1, j).setLong(2, j).setLong(3, i).uniqueResult();
		

		if (match == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static boolean nrofecha(int fixture, int a, int b, int fecha ) {
		getSession();

		List<Match> match = (List<Match>) currentSession.createQuery(
				"from Match as Match where "
						+ "((Match.local = ? AND Match.guest =? AND Match.fecha=? AND Match.fixture =?) OR (Match.local = ? AND Match.guest =? AND Match.fecha=? AND Match.fixture =?))")
				.setLong(0, a).setLong(1, b).setLong(2, fecha).setLong(3, fixture).setLong(4, b).setLong(5, a).setLong(6, fecha).setLong(7, fixture).uniqueResult();
		

		return match==null;
	}
	
	public static Match getMatchByTeams(Team teamLocal, Team teamGuest) {
		getSession();
		List<Match> matchList = new ArrayList<Match>();
				
		try {
			String query = "from Match where local ='" + teamLocal.getIdTeam() + "' AND guest = '" + teamGuest.getIdTeam() + "'";
			matchList = currentSession.createQuery(query).list();
		} finally {
			currentSession.close();
		}
		return matchList.get(0);
	}

	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}



	

}
