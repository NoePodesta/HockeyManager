package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tournament;
import model.UserAdmin;

import DAO.UserDao;

import enums.Action;
import enums.PageJSP;

public class Home extends MainServlet {

	private static final long serialVersionUID = 1L;

	@Override
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
	
		HttpSession session = request.getSession();
				
		String username = request.getRemoteUser();
		session.setAttribute("username", username);
		
		if(username!= null){
			String privilege = UserDao.getUserByUserName(username).getPrivilege().getValue();
			if(privilege=="1"){
				UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName(username);
					Tournament tournament = userAdmin.getTournament();
					if(tournament!=null){
						String tournamentName = tournament.getName(); 
						session.setAttribute("tournament", tournamentName);
						if(tournament.getFixture()!=null){
							session.setAttribute("fixture", tournament.getFixture());
						}
					}
			}	
			session.setAttribute("privilege", privilege);
			request.setAttribute("active", "active");
		}
		
		return PageJSP.HOME;
			
		
	}

}
