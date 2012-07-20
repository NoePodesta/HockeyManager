package servlet;


import DAO.CommentDao;
import DAO.TournamentDao;
import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import model.Comment;
import model.Fixture;
import model.Match;
import model.Team;
import model.Tournament;
import model.User;
import model.UserAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.List;


public class CommentManager extends MainServlet {

	private static final long serialVersionUID = 1L;


	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		String value = request.getParameter("value");
		
		
		switch (action) {
		case SHOWCOMMENT: return showComments(request,value);								
		}
	
		return PageJSP.HOMESERVLET;
	}
	
	public PageJSP showComments(HttpServletRequest request, String value){
		
		List<Comment> comments = CommentDao.getComments();
		request.setAttribute("comments", comments);
		request.setAttribute("page","comments"); Tournament tournamentUser = null;
        Tournament tournament;
        String yourTournament = "false";

        String userName = request.getRemoteUser();

        if (userName == null) {
            tournament = TournamentDao.getTournamentByName(value);

        } else {
            User user = UserDao.getUserByUserName(userName);
            if(user.getPrivilege().isUserAdmin()){
                tournamentUser = ((UserAdmin) user).getTournament();
            }
            if (value == null) {
                tournament = tournamentUser;
            } else {
                tournament = TournamentDao.getTournamentByName(value);
            }
        }
        if (tournamentUser != null && tournamentUser.getName().equals(tournament.getName())) {
            yourTournament = "true";
        }

        List<Team> teams = tournament.getTeams();
        Collections.sort(teams);
        Collections.reverse(teams);
        Fixture fixture = tournament.getFixture();
        List<Match> matches = fixture.getMatches();
        Collections.sort(matches);
        request.setAttribute("tournamentName", tournament.getName());
        request.setAttribute("matches", matches);
        request.setAttribute("fixture", fixture);
        request.setAttribute("yourTournament", yourTournament);

		return PageJSP.TOURNAMENTPAGE;
	}

	public PageJSP addComment(HttpServletRequest request){
		
		String userAdminName = request.getRemoteUser();
		User user = UserDao.getUserByUserName(userAdminName);
		String comment = request.getParameter("comment");
		

		CommentDao.createComment(user, comment);
		List<Comment> comments = CommentDao.getComments();
		request.setAttribute("comments", comments);
		request.setAttribute("page","comments");

		return null;
		
	}
}
