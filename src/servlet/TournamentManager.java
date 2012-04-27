package servlet;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FixtureDao;
import DAO.TournamentDao;
import DAO.UserDao;

import model.Fixture;
import model.Match;
import model.Team;
import model.Tournament;
import model.UserAdmin;
import enums.Action;
import enums.PageJSP;


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
		case TEAMSTOURNAMENT: return tournamentTeams(request, response);
		case RESULSTPAGE: return resultsPage(request,response);
		case CREATETOURNAMENTPAGE: return createTournamentPage(request,response);
		case DELETETOURNAMENT:
			
					
		
		}
	
		return PageJSP.HOMESERVLET;
	}


	private PageJSP createTournamentPage(HttpServletRequest request,
			HttpServletResponse response) {
		
		request.setAttribute("page", "crearTorneo");
		return PageJSP.TOURNAMENTPAGE;
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


		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getSession()
				.getAttribute("username"));
		Tournament tournament = userAdmin.getTournament();
		String tournamentname = tournament.getName();
		String description = tournament.getDescription();
		
		
		request.getSession().setAttribute("tournament", tournamentname);
		request.setAttribute("description", description);
		request.setAttribute("page", "profileTournament");
	
		return PageJSP.TOURNAMENTPAGE;
	}
	
	private PageJSP tournamentTeams(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getSession()
				.getAttribute("username"));
		Tournament tournament = userAdmin.getTournament();
		List<Team> teams = tournament.getTeams();
		Collections.sort(teams); 
		Collections.reverse(teams);
		Fixture fixture = tournament.getFixture();
		request.setAttribute("teams", teams);
		request.setAttribute("fixture", fixture);
		request.setAttribute("page", "addTeams");
		return PageJSP.TOURNAMENTPAGE;
	}

	private PageJSP modifyTournament(HttpServletRequest request,
			HttpServletResponse response) {
		
		UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName((String) request.getSession()
				.getAttribute("username"));
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
		
		request.setAttribute("tournament", tournamentName);
		UserDao.addTournament(userAdminName, tournamentName, description);
		
		return PageJSP.TOURNAMENTPAGESERVLET;
		}
		
		return PageJSP.TOURNAMENTEXIST;
	}
}