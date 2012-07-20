package servlet;


import DAO.PlayerDao;
import DAO.TeamDao;
import DAO.UserDao;
import enums.Action;
import enums.PageJSP;
import model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PlayerManager extends MainServlet {

	private static final long serialVersionUID = 1L;


    PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {

        String idTeam = request.getParameter("idTeam");
        String idPlayer = request.getParameter(("idPlayer"));

        switch (action) {

		case ADDPLAYER:
			return registerPlayer(request, idTeam);
        case MODIFYPLAYER:
            return modifyPlayer(request, idPlayer);
        case MODIFYPLAYERSERVLET:
            return modifyPlayerServlet(request, idPlayer);
		case DELETEPLAYER:
			return deletePlayer(request, idPlayer);
										
		}
	
		return PageJSP.HOMESERVLET;
	}

    private PageJSP deletePlayer(HttpServletRequest request, String idPlayer) {

        Player player = PlayerDao.getPlayer(Integer.valueOf(idPlayer));
        Team team = player.getTeam();
        PlayerDao.remove(player);
        setAttributesToPage(request, team);
        return PageJSP.TEAMPROFILE;
    }

    private PageJSP modifyPlayerServlet(HttpServletRequest request, String idPlayer) {

        Player player = PlayerDao.getPlayer(Integer.valueOf(idPlayer));
        request.setAttribute("player", player);

        return PageJSP.MODIFYPLAYEROVERLAY;
    }

    private PageJSP modifyPlayer(HttpServletRequest request, String idPlayer) {

        Player player = PlayerDao.getPlayer(Integer.valueOf(idPlayer));

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            List items = uploadHandler.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;
                if (item.isFormField()) {
                    if (item.getFieldName().equals("playerName")) {
                        player.setName(item.getString());
                    } else if (item.getFieldName().equals("playerLastName")) {
                        player.setLastName(item.getString());

                    } else if (item.getFieldName().equals("playerEmail")) {
                        player.setEmail(item.getString());

                    } else if (item.getFieldName().equals("playerPosition")) {
                       player.setPosition(item.getString());

                    }
                }else {
                    if(item.getSize()>0){
                        player.setPhoto(item.get());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        PlayerDao.update(player);
        setAttributesToPage(request, player.getTeam());
        return PageJSP.TEAMPROFILE;

    }

	private PageJSP registerPlayer(HttpServletRequest request, String idteam) {
	
		Team team = TeamDao.getTeamById(idteam);
        String playerName = null;
        String playerLastName = null;
        String playerEmail = null;
        String playerPosition = null;
        byte[] playerImage = null;

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            List items = uploadHandler.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;
                if (item.isFormField()) {
                    if (item.getFieldName().equals("playerName")) {
                        playerName = item.getString();
                    } else if (item.getFieldName().equals("playerLastName")) {
                        playerLastName = item.getString();

                    } else if (item.getFieldName().equals("playerEmail")) {
                        playerEmail = item.getString();

                    } else if (item.getFieldName().equals("playerPosition")) {
                        playerPosition = item.getString();

                    }
                } else {
                    playerImage = item.get();

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        TeamDao.addPlayer(team, playerName, playerLastName, playerEmail, playerPosition, playerImage);
        setAttributesToPage(request, team);
        return PageJSP.TEAMPROFILE;

	}

    private void setAttributesToPage(HttpServletRequest request, Team team) {

        String yourTournament = "false";
        List<Player> players = team.getPlayers();

        String userName = request.getRemoteUser();
        Tournament tournament = team.getTournament();

        if(userName !=null){
            User user = UserDao.getUserByUserName(userName);
            String tournamentUserName = ((UserAdmin) user).getTournament().getName();
            if(tournamentUserName.equals(tournament.getName())){
                yourTournament="true";
            }
        }

        request.setAttribute("idTeam", team.getIdTeam());
        request.setAttribute("tournamentName",tournament.getName());
        request.setAttribute("fixture", tournament.getFixture());
        request.setAttribute("team",team);
        request.setAttribute("players", players);
        request.setAttribute("yourTournament", yourTournament);
    }

}