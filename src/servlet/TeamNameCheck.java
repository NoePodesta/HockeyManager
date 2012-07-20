package servlet;

import DAO.TeamDao;
import DAO.UserDao;
import model.Team;
import model.Tournament;
import model.UserAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class TeamNameCheck extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String teamName = request.getParameter("teamName");
            System.out.println(teamName);
            String userName = request.getRemoteUser();
            UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName(userName);
            Tournament tournament = userAdmin.getTournament();
            boolean exists = TeamDao.getTeamByNameAndTournament(tournament,teamName);
            if(exists){
                out.println("<img src=\"image/no.png\"  width=\"10\" height=\"10\"><font color=red> Ya existe un torneo con </b>"
                        + teamName + "</b> como nombre</font>");
            }else{
                out.println("<img src=\"image/good.png\"  width=\"10\" height=\"10\" >"
                        + " " + teamName + "</b> no ha sido utilizado");

            }

        }catch (Exception ex) {

            out.println("Error ->" + ex.getMessage());

        } finally {

            out.close();

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }

}