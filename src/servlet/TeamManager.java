package servlet;


import DAO.TeamDao;
import DAO.TournamentDao;
import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import model.Player;
import model.Team;
import model.Tournament;
import model.UserAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TeamManager extends MainServlet {

	private static final long serialVersionUID = 1L;


	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
        String value = request.getParameter("value");
        switch (action) {
		case ADDTEAM: return registerTeam(request, response);
		case TEAMPROFILEOVERLAY:
			return teamProfileOverlay(request, response, value);
		case MODIFYTEAMSERVLET: 
			String idteam1 = request.getParameter("idteam");
			return modifyTeamServlet(request, response, idteam1);
		case MODIFYTEAM:
			String idteam3 = request.getParameter("idteam");
			String description = request.getParameter("description");
			String history = request.getParameter("history");
			return modifyTeam(request,response,idteam3,description,history);
			
		case DELETETEAM:
			String idteam2 = request.getParameter("idteam");
			return deleteTeam(request, response, idteam2);

									
		}
	
		return PageJSP.HOMESERVLET;
	}


	private PageJSP modifyTeam(HttpServletRequest request,
			HttpServletResponse response, String idteam3, String description, String history) {
		
		Team team = TeamDao.getTeamById(idteam3);

		
		team.setDescription(description);
		team.setHistory(history);
		
		TeamDao.update(team);
		
		return PageJSP.TEAMADDPAGE;
	}


	private PageJSP modifyTeamServlet(HttpServletRequest request,
			HttpServletResponse response, String idteam1) {
		
		
		Team team = TeamDao.getTeamById(idteam1);
		String teamname = team.getName();
		String description = team.getDescription();
		String history = team.getHistory();
		
		request.setAttribute("idteam", idteam1);
		request.setAttribute("teamname", teamname);
		request.setAttribute("description",description);
		request.setAttribute("history",history);
		
		
		return PageJSP.MODIFYTEAMOVERLAY;
	}


	private PageJSP deleteTeam(HttpServletRequest request,
			HttpServletResponse response, String idteam1) {
		
		UserAdmin admin = (UserAdmin) UserDao.getUserByUserName(request.getRemoteUser());
		Tournament tournament = admin.getTournament();
		Team team = TeamDao.getTeamById(idteam1);
		TournamentDao.deleteTeam(tournament, team);
				
		return PageJSP.TEAMADDPAGE;
	}


	private PageJSP teamProfileOverlay(HttpServletRequest request,
			HttpServletResponse response, String idteam) {
		
			Team team = TeamDao.getTeamById(idteam);
			String teamname = team.getName();
			String description = team.getDescription();
			String history = team.getName();
			List<Player> players = team.getPlayers();
//			for(int i=0; i<players.size();i++ ){
//				String playerid = String.valueOf(players.get(i).getIdplayer());
//				int goalq = GoalDao.getGoal(playerid).size();
//				System.out.println(playerid);
//				System.out.println(goalq);
//				request.setAttribute("playerid", playerid);
//			}
			
			request.setAttribute("idteam", idteam);
			request.setAttribute("teamname",teamname);
			request.setAttribute("description", description);
			request.setAttribute("history", history);
			request.setAttribute("players", players);
				
		return PageJSP.TEAMPROFILEOVERLAY;
	}


	private PageJSP registerTeam(HttpServletRequest request,
			HttpServletResponse response) {
	
			UserAdmin userAdmin = (UserAdmin)UserDao.getUserByUserName((String)(request.getRemoteUser()));
			String tournament = userAdmin.getTournament().getName();
			String teamName = request.getParameter("teamname");
			String description = request.getParameter("description");
			String history = request.getParameter("histoty");
			
			TournamentDao.addTeam(tournament, teamName, history, description);
	
		return PageJSP.TOURNAMENTPAGESERVLET;
	}
}