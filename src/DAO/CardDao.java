package DAO;

import enums.CardColour;
import hibernate.HibernateUtil;
import javassist.bytecode.AnnotationDefaultAttribute;
import model.Card;
import model.Player;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import java.util.List;

public class CardDao extends GenericDao {

    private static Session currentSession = HibernateUtil.getSessionFactory()
            .getCurrentSession();

    static {
        currentSession.setFlushMode(FlushMode.COMMIT);
    }

    public static void update(Card card) {
        getSession();
        persist(card, currentSession);
    }

    public static <T> void remove(Card card) {
        getSession();
        delete(card, currentSession);

    }

    private static void getSession() {
        currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();

    }

    public static List<Card> getCard(CardColour cardColour, int idPlayer) {
        getSession();
        Card card = null;
        final String consult = "FROM Card WHERE cardColour = '" + cardColour.ordinal() + "' AND player = '" + idPlayer +"'";
        List<Card> cards = currentSession.createQuery(consult).list();
        return cards;



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
}