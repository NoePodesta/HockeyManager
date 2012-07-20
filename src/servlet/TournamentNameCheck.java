package servlet;

import DAO.TournamentDao;
import model.Tournament;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class TournamentNameCheck extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            System.out.println(request.getParameter("tournamentName"));
            String tournamentName = request.getParameter("tournamentName");
            System.out.println(tournamentName);
            Tournament tournament = TournamentDao.getTournamentByName(tournamentName);

            if (tournament == null) {
                out.println("<img src=\"image/good.png\"  width=\"10\" height=\"10\" >"
                        + " " + tournamentName + "</b> no ha sido utilizado");
            } else {
                out.println("<img src=\"image/no.png\"  width=\"10\" height=\"10\"><font color=red> Ya existe un torneo con </b>"
                        + tournamentName + "</b> como nombre</font>");

            }
            out.println();

        } catch (Exception ex) {

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