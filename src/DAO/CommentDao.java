package DAO;

import hibernate.HibernateUtil;
import model.Comment;
import model.User;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;




public class CommentDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static <T> void update(Comment comment) {
		getSession();
		persist(comment, currentSession);
	}

	public static <T> void removeComment(T comment) {
		getSession();
		delete(comment, currentSession);

	}

	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}

	public static  void createComment(User user, String commentString) {
		Comment comment = new Comment();
		comment.setCommemt(commentString);
		comment.setUser(user);
		Date date = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd '-' MM '-' yyyy", new Locale("es_ES"));
		String fecha=formateador.format(date);
		comment.setDate(fecha);
		update(comment);

	}

	public static List<Comment> getComments() {
		getSession();
		final String consult = "FROM Comment";
		Query query = currentSession.createQuery(consult);
		List<Comment> comments = query.list();
		return comments;
	}
}
