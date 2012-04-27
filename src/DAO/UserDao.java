package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import hibernate.HibernateUtil;

import model.Captain;
import model.Team;
import model.Tournament;
import model.User;
import model.UserAdmin;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

import enums.Privilege;


public class UserDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static <T> void update(T user) {
		getSession();
		persist(user, currentSession);
	}

	public static <T> void removeUser(T user) {
		getSession();
		delete(user, currentSession);

	}


	public static void removeUserAdmin(String userName) {
		getSession();
		final String consult = "FROM User WHERE userName = '" + userName + "'";
		List<UserAdmin> users = currentSession.createQuery(consult).list();

		if (!users.isEmpty()) {
			UserAdmin userAdmin = users.get(0);
			if(!(userAdmin.getTournament()==null)){
				Tournament actualTournament = userAdmin.getTournament();
				TournamentDao.remove(userAdmin, actualTournament);
			}
		}
		getSession();
		delete(users.get(0), currentSession);	
	}

	public static void removeCaptain(String userName) {
		getSession();
		final String consult = "FROM User WHERE userName = '" + userName + "'";
		List<Captain> users = currentSession.createQuery(consult).list();

		if (!users.isEmpty()) {
			Captain captain = users.get(0);
			if(!(captain.getTeam()==null)){
				Team team = null;
				captain.setTeam(team);
			}
		}
		getSession();
		delete(users.get(0), currentSession);	
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

	public static String addTournament(String userAdminName, String tournamentName, String description) {
		getSession();

		UserAdmin userAdmin = (UserAdmin)UserDao.getUserByUserName(userAdminName);
		if(!(userAdmin.getTournament()==null)){
			return "Ya Tienes un torneo";
		}

		Tournament newTournament = new Tournament();
		newTournament.setDescription(description);
		newTournament.setName(tournamentName);
		TournamentDao.update(newTournament);
		userAdmin.setTournament(newTournament);
		update(userAdmin);
		return "Torneo Creado";

	}

	public static String md5convert(String password) {
		StringBuffer hexString = new StringBuffer();
		byte[] defaultBytes = password.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();


			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
		} catch (
				NoSuchAlgorithmException nsae) {

		}
		return hexString.toString();
	}

	public static boolean existUser(String username) {
		getSession();

		User user = (User) currentSession.createQuery("from User as " +
				"User where User.userName = ?").setString(0, username).uniqueResult();

		if(user == null){
			return false;
		}else{
			return true;
		}
	}




	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}

}
