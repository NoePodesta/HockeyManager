package servlet;


import DAO.FixtureDao;
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
import java.util.Collections;
import java.util.List;


public class TournamentManager extends MainServlet {

    private static final long serialVersionUID = 1L;


    PageJSP handleAction(HttpServletRequest request,
                         HttpServletResponse response, Action action) {

    	String value = request.getParameter("value");
    	switch (action) {
       
            case REGISTERTOURNAMENT:
                return registerTournament(request);
            case MODIFYTOURNAMENT:
                return modifyTournament(request);
            case POSITIONPAGE:
                return postionPage(request,value);
            case TOURNAMENTPROFILE:
                return tournamentProfile(request, value);
            case GENERATEFIXTURE:
                return generateFixture(request,value);
            case FIXTUREPAGE:
                return fixturePage(request,value);
            case RESULSTPAGE:
                return resultsPage(request,value);               
            case MODIFYTOURNAMENTPAGE:
                return modifyTournamentPage(request);
            case CREATETOURNAMENTPAGE:
                return createTournamentPage(request);
            case DELETETOURNAMENT:
                return deleteTournament(request);


        }

        return PageJSP.HOMESERVLET;
    }

    private PageJSP deleteTournament(HttpServletRequest request) {
        UserAdmin admin = (UserAdmin) UserDao.getUserByUserName((request.getRemoteUser()));
        Tournament tournament = admin.getTournament();
        TournamentDao.remove(admin,tournament);
        return PageJSP.HOMESERVLET;
    }


    private PageJSP modifyTournamentPage(HttpServletRequest request) {
        UserAdmin admin = (UserAdmin) UserDao.getUserByUserName((request.getRemoteUser()));
        Tournament tournament = admin.getTournament();
        request.setAttribute("tournament", tournament);
        return PageJSP.MODIFYTOURNAMENTPAGE;


    }


    private PageJSP createTournamentPage(HttpServletRequest request) {

        request.setAttribute("page", "crearTorneo");
        return PageJSP.TOURNAMENTPAGE;
    }


    private PageJSP resultsPage(HttpServletRequest request, String value) {

        Tournament tournamentUser = null;
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
        request.setAttribute("page", "results");

        return PageJSP.TOURNAMENTPAGE;
    }


    private PageJSP postionPage(HttpServletRequest request, String value) {

        Tournament tournamentUser = null;
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
        request.setAttribute("tournamentName", tournament.getName());
        request.setAttribute("fixture", fixture);
        request.setAttribute("page", "position");
        request.setAttribute("teams", teams);
        request.setAttribute("yourTournament", yourTournament);


        return PageJSP.TOURNAMENTPAGE;
    }


    private PageJSP fixturePage(HttpServletRequest request, String value) {

        Tournament tournamentUser = null;
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
        request.setAttribute("page", "fixture");
        request.setAttribute("yourTournament", yourTournament);

        return PageJSP.TOURNAMENTPAGE;
    }


    private PageJSP generateFixture(HttpServletRequest request, String value) {

        UserAdmin admin = (UserAdmin) UserDao.getUserByUserName(request.getRemoteUser());
        Tournament tournament = admin.getTournament();
        FixtureDao.generarFixture(tournament);


        return fixturePage(request, value);
    }

    private PageJSP tournamentProfile(HttpServletRequest request, String value) {

        Tournament tournament;
        Tournament tournamentUser = null;
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

        if (tournament != null) {
            if(tournament.getTeams()!=null){
                List<Team> teams = tournament.getTeams();
                Collections.sort(teams);
                Collections.reverse(teams);
                request.setAttribute("teams", teams);
            }
            if(tournament.getFixture()!=null){
                Fixture fixture = tournament.getFixture();
                request.setAttribute("fixture",fixture);
            }
        }

        if (tournament != null) {
            request.setAttribute("tournamentName", tournament.getName());
        }
        request.setAttribute("tournament", tournament);
        request.setAttribute("page", "profileTournament"); 
        request.setAttribute("yourTournament", yourTournament);

        return PageJSP.TOURNAMENTPAGE;
    }

    private PageJSP modifyTournament(HttpServletRequest request) {

        UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName(request.getRemoteUser());
        Tournament tournament = userAdmin.getTournament();

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            List items = uploadHandler.parseRequest(request);
            for (Object item1 : items) {
                FileItem item = (FileItem) item1;
                if (item.isFormField()) {
                    if (item.getFieldName().equals("description")) {
                        tournament.setDescription(item.getString()); 
                    }
                } else {
                    if(item.getSize()>0){
                        tournament.setPhoto(item.get());
                    }
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        TournamentDao.update(tournament);

        return PageJSP.TOURNAMENTPAGESERVLET;
    }


    private PageJSP registerTournament(HttpServletRequest request) {

            String userAdminName = request.getRemoteUser();
            String description = null;
            String tournamentName = null;
            byte[] image = null;

            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
            try {
                List items = uploadHandler.parseRequest(request);
                for (Object item1 : items) {
                    FileItem item = (FileItem) item1;
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("tournamentName")) {
                            tournamentName = item.getString();
                            Tournament tournament = TournamentDao.getTournamentByName(tournamentName);
                            if (tournament != null) {
                                return PageJSP.HOMESERVLET;
                            }
                        } else if (item.getFieldName().equals("description")) {
                            description = item.getString();

                        }
                    } else {
                        image = item.get();

                    }
                }

                UserDao.addTournament(userAdminName, tournamentName, description, image);

            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            return PageJSP.TOURNAMENTPAGESERVLET;
    }
       

}