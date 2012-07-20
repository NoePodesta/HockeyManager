package servlet;


import DAO.TeamDao;
import DAO.TournamentDao;
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

public class TeamManager extends MainServlet {

    private static final long serialVersionUID = 1L;


    PageJSP handleAction(HttpServletRequest request,
                         HttpServletResponse response, Action action) {
        String idTeam = request.getParameter("idTeam");
        switch (action) {
            case ADDTEAM:
                return registerTeam(request);
            case TEAMPROFILEOVERLAY:
                return teamProfileOverlay(request, idTeam);
            case TEAMPROFILE:
                return teamProfile(request, idTeam);
            case MODIFYTEAMSERVLET:
                return modifyTeamServlet(request, idTeam);
            case MODIFYTEAM:
                return modifyTeam(request, idTeam);
            case DELETETEAM:
                return deleteTeam(request, idTeam);


        }

        return PageJSP.HOMESERVLET;
    }


    private PageJSP modifyTeam(HttpServletRequest request, String idTeam) {

        Team team = TeamDao.getTeamById(idTeam);

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            List items = uploadHandler.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;
                if (item.isFormField()) {
                        if (item.getFieldName().equals("description")) {
                            team.setDescription(item.getString());
                        }
                    } else {
                        if (item.getSize() > 0) {
                            team.setPhoto(item.get());
                        }
                    }
                }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        TeamDao.update(team);
        return PageJSP.TOURNAMENTPAGESERVLET;

    }


    private PageJSP modifyTeamServlet(HttpServletRequest request, String idTeam) {


        Team team = TeamDao.getTeamById(idTeam);
        request.setAttribute("team", team);


        return PageJSP.MODIFYTEAMOVERLAY;
    }


    private PageJSP deleteTeam(HttpServletRequest request, String idTeam) {

        UserAdmin admin = (UserAdmin) UserDao.getUserByUserName(request.getRemoteUser());
        Tournament tournament = admin.getTournament();
        Team team = TeamDao.getTeamById(idTeam);
        TournamentDao.deleteTeam(tournament, team);
        request.getParameter("value");

        return PageJSP.TOURNAMENTPAGESERVLET;
    }


    private PageJSP teamProfileOverlay(HttpServletRequest request, String idTeam) {

        Team team = TeamDao.getTeamById(idTeam);
        request.setAttribute("team", team);
        return PageJSP.TEAMPROFILEOVERLAY;
    }

    private PageJSP teamProfile(HttpServletRequest request, String idTeam) {

        String yourTournament = "false";
        Team team = TeamDao.getTeamById(idTeam);
        List<Player> players = team.getPlayers();

        String userName = request.getRemoteUser();
        Tournament tournament = team.getTournament();

        if (userName != null) {
            User user = UserDao.getUserByUserName(userName);
            String tournamentUserName = ((UserAdmin) user).getTournament().getName();
            if (tournamentUserName.equals(tournament.getName())) {
                yourTournament = "true";
            }
        }

        request.setAttribute("idTeam", idTeam);
        request.setAttribute("tournamentName", tournament.getName());
        request.setAttribute("fixture", tournament.getFixture());
        request.setAttribute("team", team);
        request.setAttribute("players", players);
        request.setAttribute("yourTournament", yourTournament);

        return PageJSP.TEAMPROFILE;
    }


    private PageJSP registerTeam(HttpServletRequest request) {

        String userAdminName = request.getRemoteUser();
        UserAdmin admin = (UserAdmin) UserDao.getUserByUserName(userAdminName);
        String tournamentName = admin.getTournament().getName();
        String description = null;
        String teamName = null;
        byte[] image = null;

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            List items = uploadHandler.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;
                if (item.isFormField()) {
                    if (item.getFieldName().equals("teamName")) {
                        teamName = item.getString();
                    } else if (item.getFieldName().equals("description")) {
                        description = item.getString();

                    }
                } else {
                    image = item.get();

                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        String addTeam = TournamentDao.addTeam(tournamentName, teamName, description, image);
        if (addTeam.equals("success")) {
            return PageJSP.TOURNAMENTPAGESERVLET;
        } else {
            return PageJSP.HOMESERVLET;
        }


    }
}