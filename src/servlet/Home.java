package servlet;

import java.util.List;

import DAO.TournamentDao;
import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import enums.Privilege;
import model.Fixture;
import model.Tournament;
import model.UserAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Home extends MainServlet {

	private static final long serialVersionUID = 1L;

	@Override
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
	
		HttpSession session = request.getSession();
				
		String username = request.getRemoteUser();
		session.setAttribute("username", username);
		
		
		if(username!= null){
			Privilege privilege = UserDao.getUserByUserName(username).getPrivilege();
			if(privilege.isUserAdmin()){
				UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName(username);
					Tournament tournament = userAdmin.getTournament();
					if(tournament!=null){
						String tournamentName = tournament.getName(); 
						session.setAttribute("tournament", tournamentName);
                        request.setAttribute("tournamentName", tournamentName);
						Fixture fixture = tournament.getFixture();
						if(fixture!=null){
							session.setAttribute("fixture", fixture);
							request.setAttribute("fixture", fixture);
						}
					}
			}	
			session.setAttribute("privilege", privilege);
			
		}
		
		List<Tournament> tournaments = TournamentDao.getAllTournaments();
		System.out.println(tournaments);
		request.setAttribute("tournaments", tournaments);
		
		return PageJSP.HOME;
			
		
	}

}
