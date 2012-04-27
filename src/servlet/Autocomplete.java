package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import DAO.PlayerDao;
import DAO.TournamentDao;

import model.Player;
import model.Tournament;

import enums.Action;
import enums.PageJSP;

public class Autocomplete extends MainServlet {

	private static final long serialVersionUID = 1L;

	@Override
	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) throws IOException  {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
	    try {
	    	   out = response.getWriter();
	           String query = request.getParameter("term").toLowerCase();

	           List<Tournament> tournaments = new ArrayList<Tournament>(); 
	           tournaments = TournamentDao.getTournamentsByName(query);    
	           List<Player> players = new ArrayList<Player>();
	           players = PlayerDao.getPlayers(query);
	           JSONArray jsonArray = new JSONArray();

	                  if (tournaments.isEmpty() && players.isEmpty()) {
	                         jsonArray.add("No hay coincidencias");
	                         out.println(jsonArray);
	                  }
	                  else if (!tournaments.isEmpty()){
	                	  for(Tournament tournament : tournaments ){
	                		  	String name = tournament.getName();
	                		  	jsonArray.add(name);
                                out.println(jsonArray);
	                	  }
	                  }
	                  else{	
	                	  for (Player player : players) {
	  					String name = player.getName();
						String surname = player.getLastName();
						String team = player.getTeam().getName();
						String data = name + " " + surname + "," + team;
						jsonArray.add(data);
						out.println(jsonArray);
				
	                         }
	           }
	                 

	    } catch (Exception ex) {

	           out.println("Error ->" + ex.getMessage());

	    } finally {
	           out.close();
	    }
		return PageJSP.HOME;
	}
	

}
