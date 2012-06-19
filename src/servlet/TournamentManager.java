package servlet;


import DAO.FixtureDao;
import DAO.TournamentDao;
import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;


public class TournamentManager extends MainServlet {

	private static final long serialVersionUID = 1L;


	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		
		switch (action) {
		case REGISTERTOURNAMENT: return registerTournament(request, response);		
		case MODIFYTOURNAMENT: return modifyTournament(request, response);
		case POSITIONPAGE: return postionPage(request,response);
		case TOURNAMENTPROFILE: return tournamentProfile(request, response);
		case GENERATEFIXTURE: return generateFixture(request, response);
		case FIXTUREPAGE: return fixturePage(request,response);
		case RESULSTPAGE: return resultsPage(request,response);
		case MODIFYTOURNAMENTPAGE: return modifyTournamentPage(request,response);
		case DELETETOURNAMENT:
			
					
		
		}
	
		return PageJSP.HOMESERVLET;
	}


	private PageJSP modifyTournamentPage(HttpServletRequest request,
			HttpServletResponse response) {
		UserAdmin admin = (UserAdmin) UserDao.getUserByUserName((String)(request.getRemoteUser()));
		Tournament tournament = admin.getTournament();
		request.setAttribute("tournament", tournament);
		
		return PageJSP.MODIFYTOURNAMENTPAGE;
		
		
	}


	private PageJSP resultsPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getSession()
				.getAttribute("username"));
		Tournament tournament = userAdmin.getTournament();
		List<Team> teams = tournament.getTeams();
		Collections.sort(teams); 
		Collections.reverse(teams);
		Fixture fixture = tournament.getFixture();
		List<Match> matches = fixture.getMatches(); 
		Collections.sort(matches); 
		request.setAttribute("matches", matches);
		request.setAttribute("fixture", fixture);
		request.setAttribute("page", "results");
		
		return PageJSP.TOURNAMENTPAGE;
	}


	private PageJSP postionPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getSession()
				.getAttribute("username"));
		Tournament tournament = userAdmin.getTournament();
		List<Team> teams = tournament.getTeams();
		Collections.sort(teams); 
		Collections.reverse(teams);
		request.setAttribute("teams", teams);
		request.setAttribute("page", "position");
		
		return PageJSP.TOURNAMENTPAGE;
	}


	private PageJSP fixturePage(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getSession()
				.getAttribute("username"));
		Tournament tournament = userAdmin.getTournament();
		List<Team> teams = tournament.getTeams();
		Collections.sort(teams); 
		Collections.reverse(teams);
		Fixture fixture = tournament.getFixture();
		List<Match> matches = fixture.getMatches(); 
		Collections.sort(matches); 
		request.setAttribute("matches", matches);
		request.setAttribute("fixture", fixture);
		request.setAttribute("page", "fixture");
		
		return PageJSP.TOURNAMENTPAGE;
	}


	private PageJSP generateFixture(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAdmin admin = (UserAdmin) UserDao.getUserByUserName((String)(request.getRemoteUser()));
		Tournament tournament = admin.getTournament();
		FixtureDao.generarFixture(tournament);
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}

	private PageJSP tournamentProfile(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		String value = request.getParameter("value");
		Tournament tournament = null;
		Tournament tournamentUser = null;
		String yourTournament = "false";
		
		String user = request.getRemoteUser();
		
		if(user==null){
			tournament = TournamentDao.getTournamentByName(value);
				
		}else{
			UserAdmin admin = (UserAdmin) UserDao.getUserByUserName(user);
			tournamentUser = admin.getTournament();
            if(value==null){
                tournament = tournamentUser;
            }
            else{
            	tournament = TournamentDao.getTournamentByName(value);
            }
		}
		if(tournamentUser!=null && tournamentUser.getName().equals(tournament.getName())){
			yourTournament = "true";
		}
			
		List<Team> teams = tournament.getTeams();
		Collections.sort(teams); 
		Collections.reverse(teams);
		
		request.setAttribute("tournamentName", tournament.getName());
		request.setAttribute("tournament", tournament);
		request.setAttribute("page", "profileTournament");
		request.setAttribute("teams", teams);
		request.setAttribute("yourTournament", yourTournament);
	
		return PageJSP.TOURNAMENTPAGE;
	}

	private PageJSP modifyTournament(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getRemoteUser());
		Tournament tournament = userAdmin.getTournament();
		tournament.setDescription(request.getParameter("description"));
		TournamentDao.update(tournament);

			
		return PageJSP.TOURNAMENTPAGESERVLET;
	}

	private PageJSP registerTournament(HttpServletRequest request,
			HttpServletResponse response) {
		
		if((TournamentDao.existTournament(request.getParameter("name"))== false)){
	
		String userAdminName = request.getRemoteUser();
		String tournamentName = request.getParameter("tournamentname");
		String description = request.getParameter("description");
		
		request.getSession().setAttribute("tournament", tournamentName);
		UserDao.addTournament(userAdminName, tournamentName, description);
		
		return PageJSP.TOURNAMENTPAGESERVLET;
		}
		
		return PageJSP.TOURNAMENTEXIST;
	}
}