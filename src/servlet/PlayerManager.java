package servlet;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.PlayerDao;
import DAO.TeamDao;
import DAO.TournamentDao;
import DAO.UserDao;

import model.Fixture;
import model.Player;
import model.Team;
import model.Tournament;
import model.UserAdmin;
import enums.Action;
import enums.PageJSP;
import enums.Privilege;

public class PlayerManager extends MainServlet {

	private static final long serialVersionUID = 1L;


	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		switch (action) {
		case ADDPLAYER: 
			String idteam = (String)request.getParameter("idteam");
			return registerPlayer(request, response, idteam);
		case DELETEPLAYER:
			int idteam1 = Integer.valueOf(request.getParameter("idteam"));
			System.out.println(idteam1);
			String name = (String)request.getParameter("nameplayer");
			System.out.println(name);
			String lastname = (String)request.getParameter("lastnameplayer");
			System.out.println(lastname);
			return deletePlayer(request, response, idteam1, name, lastname);
										
		}
	
		return PageJSP.HOMESERVLET;
	}


	private PageJSP deletePlayer(HttpServletRequest request,
			HttpServletResponse response, int idteam1, String name,
			String lastname) {
		
		Player player = PlayerDao.getPlayer(name, lastname, idteam1);
		String idteam = String.valueOf(idteam1);
		PlayerDao.remove(player, idteam);
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}


	private PageJSP registerPlayer(HttpServletRequest request,
			HttpServletResponse response, String idteam) {
	
		Team team = TeamDao.getTeamById(idteam);
		
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String position = request.getParameter("position");
		
		TeamDao.addPlayer(team, name, lastname, email, position);
		
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}
	
}