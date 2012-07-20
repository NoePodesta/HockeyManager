package DAO;


import hibernate.HibernateUtil;
import model.Player;
import model.Team;
import model.Tournament;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;


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
        delete(actualTeam, currentSession);
    }


    @SuppressWarnings("unchecked")
    public static Team getTeamByName(String name) {
        getSession();
        Team team = null;
        final String consult = "FROM Team  WHERE name='" + name + "' ";
        List<Team> teams = currentSession.createQuery(consult).list();
        if (!teams.isEmpty()) {
            team = teams.get(0);
        }
        return team;
    }
    
    public static Boolean getTeamByNameAndTournament(Tournament tournament, String name){
        List<Team> teams = tournament.getTeams();
        if(!(teams== null)){
            for(Team auxTeam : teams){
                if(auxTeam.getName().equals(name)){
                    return true;
                }
            }


        }
        return false;
    }


    public static Team getTeamById(String id) {
        getSession();
        Team team = null;
        final String consult = "FROM Team  WHERE Id_Team='" + id + "' ";
        List<Team> teams = currentSession.createQuery(consult).list();
        if (!teams.isEmpty()) {
            team = teams.get(0);
        }
        return team;
    }

    public static List<Team> getTeamsByName(String name) {
        getSession();
        final String consult = "FROM Team  WHERE name='" + name + "' ";
        List<Team> teams = currentSession.createQuery(consult).list();
        return teams;
    }


    public static List<Team> getAllTeams() {
        getSession();
        final String consult = "FROM Team";
        Query query = currentSession.createQuery(consult);
        List<Team> teams = query.list();
        return teams;


    }

    public static String addPlayer(Team team, String playerName, String playerLastName, String email, String position, byte[] image) {
        getSession();

        List<Player> players = team.getPlayers();

        Player player = new Player();
        player.setName(playerName);
        player.setLastName(playerLastName);
        player.setEmail(email);
        player.setPosition(position);
        player.setPhoto(image);
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
