package servlet;


import DAO.*;
import enums.Action;
import enums.PageJSP;
import model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatchManager extends MainServlet {

	private static final long serialVersionUID = 1L;


	PageJSP handleAction(HttpServletRequest request,
			HttpServletResponse response, Action action) {
		switch (action) {
		case ADDDATE: 
			int idmatch = Integer.valueOf(request.getParameter("idmatch"));
			return addDate(request,response, idmatch);
		case ADDRESULT:	
			int idmatch3 = Integer.valueOf(request.getParameter("idmatch"));
			return addResult(request, response, idmatch3);	
		case MODIFYRESULT:
			int idmatch4 = Integer.valueOf(request.getParameter("idmatch"));
			return modifyResult(request, response, idmatch4);	
		case MODIFYRESULTSERVLET:
			int idmatch2 = Integer.valueOf(request.getParameter("idmatch"));
			return modifyResultServlet(request, response, idmatch2);
		case RESULTSERVLET:
			int idmatch5 = Integer.valueOf(request.getParameter("idmatch"));
			return resultServlet(request, response, idmatch5);
		case ADDGOALESSERVLET:
			int idmatch6 = Integer.valueOf(request.getParameter("idmatch"));
			return addGoalesServlet(request,response, idmatch6);
		case ADDGOALS:
			int idmatch7 = Integer.valueOf(request.getParameter("idmatch"));
			return addGoales(request,response, idmatch7);
		case OVERVIEW: 
			int idmatch8 = Integer.valueOf(request.getParameter("idmatch"));
			return overviewServlet(request, response, idmatch8);
		case CARDSERVLET:
			int idmatch9 = Integer.valueOf(request.getParameter("idmatch"));
			String idpage = (String)request.getParameter("idpage");
			return cardServlet(request, response, idmatch9, idpage);
		case ADDYELLOWCARDS: 
			int idmatch10 = Integer.valueOf(request.getParameter("idmatch"));
			return addYellowCards(request, response, idmatch10);
		case ADDREDCARDS: 
			int idmatch11 = Integer.valueOf(request.getParameter("idmatch"));
			return addRedCards(request, response, idmatch11);
		case ADDGREENCARDS: 
			int idmatch12 = Integer.valueOf(request.getParameter("idmatch"));
			return addGreenCards(request, response, idmatch12);
		}
		return PageJSP.HOME;
		
		
	}


	private PageJSP addGreenCards(HttpServletRequest request, HttpServletResponse response, int idmatch) {
		
		Match match = MatchDao.getMatchById(idmatch);
		System.out.println(match);
		
		List<GreenCard> greenCardsMatch = match.getPlayerGCard();
		System.out.println(greenCardsMatch);
		
		Team localteam = match.getLocal();
		int localid = localteam.getIdTeam();
		Team guestteam = match.getGuest();
		int guestid = guestteam.getIdTeam();
		
		List<Player>  localplayers = localteam.getPlayers();
		List<Player>  guestplayers = guestteam.getPlayers();
		
//		for(int i = 0; i < localplayers.size(); i++){
//			String playername = localplayers.get(i).getName();
//			String playersurname = localplayers.get(i).getLastName();
//			int playerid = localplayers.get(i).getIdplayer();
//			String idteam = String.valueOf(guestplayers.get(i).getIdplayer());
//			int greencards = Integer.valueOf(request.getParameter(playername + playersurname + idteam));
//			System.out.println(greencards);
//			Player player = PlayerDao.getPlayer(playername, playersurname, localid);
//			List<GreenCard> oldGreenCards = GreenCardDao.getGreenCards(playerid, idmatch);
//			List<GreenCard> greenCardsPlayer = player.getGreenCards();
//			
//			for(int k =0; k<oldGreenCards.size(); k++ ){
//				for(int l =0; l<greenCardsPlayer.size(); l++){
//					if(oldGreenCards.get(k).getIdGreenCard()==greenCardsPlayer.get(l).getIdGreenCard()){
//						greenCardsPlayer.remove(l);
//						player.setGreenCards(greenCardsMatch);
//						PlayerDao.update(player);
//						GreenCardDao.remove(oldGreenCards.get(k));	
//					}
//					
//				}
//			}
//			
//					
//			for(int j=1; j<greencards+1; j++){
//				GreenCard greenCard = new GreenCard();
//				greenCard.setPlayer(player);
//				greenCard.setMatch(match);
//				greenCard.setTeam(localteam);
//				GreenCardDao.update(greenCard);
//				greenCardsPlayer.add(greenCard);
//				PlayerDao.update(player);
//				
//				List<GreenCard> matchgreen = match.getPlayerGCard();
//				matchgreen.add(greenCard);
//				MatchDao.update(match);
//
//			}			
//
//		}
		
		for(int i = 0; i < guestplayers.size(); i++){
			String playername = guestplayers.get(i).getName();
			String playersurname = guestplayers.get(i).getLastName();
			int playerid = guestplayers.get(i).getIdplayer();
			String idteam = String.valueOf(guestplayers.get(i).getIdplayer());
			int greencards = Integer.valueOf(request.getParameter(playername + playersurname + idteam));
			System.out.println(greencards);
			Player player = PlayerDao.getPlayer(playername, playersurname, guestid);
			List<GreenCard> oldGreenCards = GreenCardDao.getGreenCards(playerid, idmatch);
			List<GreenCard> greenCardsPlayer = player.getGreenCards();
			
			for(int l =0; l<greenCardsPlayer.size(); l++){
				for(int k =0; k<oldGreenCards.size(); k++ ){
					if(oldGreenCards.get(k).getIdGreenCard()==greenCardsPlayer.get(l).getIdGreenCard()){
						oldGreenCards.get(k).setPlayer(null);
						oldGreenCards.get(k).setMatch(null);
						oldGreenCards.get(k).setTeam(null);
						GreenCardDao.update(oldGreenCards.get(k));
					}
					
				}
			}
			
					
			for(int j=1; j<greencards+1; j++){
				GreenCard greenCard = new GreenCard();
				greenCard.setPlayer(player);
				greenCard.setMatch(match);
				greenCard.setTeam(localteam);
				GreenCardDao.update(greenCard);
				greenCardsPlayer.add(greenCard);
				PlayerDao.update(player);
				
				List<GreenCard> matchgreen = match.getPlayerGCard();
				matchgreen.add(greenCard);
				MatchDao.update(match);

			}		

		}
		
		return PageJSP.RESULTOVERLAY;
	}


	private PageJSP addRedCards(HttpServletRequest request, HttpServletResponse response, int idmatch10) {
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}


	private PageJSP addYellowCards(HttpServletRequest request, HttpServletResponse response, int idmatch10) {
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}


	private PageJSP cardServlet(HttpServletRequest request,
			HttpServletResponse response, int idmatch9, String idpage) {
		
		Match match = MatchDao.getMatchById(idmatch9);
		String guestname = match.getGuest().getName();
		String localname = match.getLocal().getName();
		System.out.println(guestname);
		System.out.println(localname);
		List<Player> localplayers = match.getLocal().getPlayers();
		List<Player> guestplayers = match.getGuest().getPlayers();
		
		request.setAttribute("localname",localname);
		request.setAttribute("guestname",guestname);
		request.setAttribute("localplayers",localplayers);
		System.out.println(localplayers);
		System.out.println(guestplayers);
		request.setAttribute("guestplayers",guestplayers);
		String idmatch = String.valueOf(idmatch9);
		request.setAttribute("idmatch",idmatch);
		
		System.out.println(idmatch9);
		
		System.out.println(idpage);
	
		if(idpage.equals("1")){
			List<GreenCard> greenCards = match.getPlayerGCard();
			request.setAttribute("greenCards",greenCards);
			return PageJSP.GREENCARDOVERLAY;
		}
		else if(idpage.equals("2")){
			List<YellowCard> yellowCards = match.getPlayerYCard();
			request.setAttribute("yellowCards",yellowCards);
			return PageJSP.YELLOWCARDOVERLAY;
		}
		
		else{
			List<RedCard> redCards = match.getPlayerRCard();
			request.setAttribute("redCards",redCards);
			return PageJSP.	REDCARDOVERLAY;
		}
	
	}


	private PageJSP overviewServlet(HttpServletRequest request,
			HttpServletResponse response, int idmatch8) {
		
		Match match = MatchDao.getMatchById(idmatch8);
		String localname = match.getGuest().getName();
		String guestname = match.getLocal().getName();
		
		List<Goal> goals = GoalDao.getGoals(idmatch8);
		List<Player> localplayers = match.getLocal().getPlayers();
		List<Player> guestplayers = match.getGuest().getPlayers();
		
		request.setAttribute("localname",localname);
		request.setAttribute("guestname",guestname);
		request.setAttribute("localplayers",localplayers);
		request.setAttribute("guestplayers",guestplayers);
		request.setAttribute("goals",goals);
		request.setAttribute("idmatch",idmatch8);
		
		
		return PageJSP.OVERVIEWOVERLAY;
	}


	private PageJSP addGoales(HttpServletRequest request,
			HttpServletResponse response, int idmatch7) {
		
		Match match = MatchDao.getMatchById(idmatch7);
		List<Goal> goalPlayers = match.getHighScoring();
		
		Team localteam = match.getLocal();
		int localid = localteam.getIdTeam();
		Team guestteam = match.getGuest();
		int guestid = guestteam.getIdTeam();
		
		List<Player>  localplayers = localteam.getPlayers();
		List<Player>  guestplayers = guestteam.getPlayers();
		
		for(int i = 0; i < localplayers.size(); i++){
			String playername = localplayers.get(i).getName();
			String playersurname = localplayers.get(i).getLastName();
			String idteam = String.valueOf(guestplayers.get(i).getIdplayer());
			int highScoring = Integer.valueOf(request.getParameter(playername + playersurname + idteam));
			Player player = PlayerDao.getPlayer(playername, playersurname, localid);
			for(int j=1; j<highScoring+1; j++){
				Goal goal = new Goal();
				goal.setPlayer(player);
				goal.setMatch(match);
				goal.setTeam(localteam);
				GoalDao.update(goal);
				List<Goal> playergoals = player.getGoals();
				playergoals.add(goal);
				PlayerDao.update(player);
				
				List<Goal> matchgoal = match.getHighScoring();
				matchgoal.add(goal);
				MatchDao.update(match);

			}
				
						

		}
		
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}


	private PageJSP addGoalesServlet(HttpServletRequest request,
			HttpServletResponse response, int idmatch6) {
		
		Match match = MatchDao.getMatchById(idmatch6);
		String localname = match.getGuest().getName();
		String guestname = match.getLocal().getName();
		
		Team localteam = match.getLocal();
		Team guestteam = match.getGuest();
		
		request.setAttribute("localname",localname);
		request.setAttribute("guestname",guestname);
		request.setAttribute("localteam",localteam);
		request.setAttribute("guestteam",guestteam);
		request.setAttribute("idmatch",idmatch6);
		
		return PageJSP.GOALESOVERLAY;
		
	}


	private PageJSP resultServlet(HttpServletRequest request,
			HttpServletResponse response, int idmatch5) {
		
		Match match = MatchDao.getMatchById(idmatch5);
		String localteam = match.getGuest().getName();
		String guestteam= match.getLocal().getName();
		
		request.setAttribute("localteam",localteam);
		request.setAttribute("guestteam",guestteam);
		request.setAttribute("idmatch",idmatch5);
		
		return PageJSP.RESULTOVERLAY;
	}


	private PageJSP modifyResultServlet(HttpServletRequest request,
			HttpServletResponse response, int idmatch2) {
		
		Match match = MatchDao.getMatchById(idmatch2);
		String localteam= match.getLocal().getName();
		String guestteam = match.getGuest().getName();
		int localresult = match.getResultLocal();
		int guestresult = match.getResultGuest();
		
		request.setAttribute("localteam",localteam);
		request.setAttribute("guestteam",guestteam);
		request.setAttribute("localresult",localresult);
		request.setAttribute("guestresult",guestresult);
		request.setAttribute("idmatch",idmatch2);
		
		return PageJSP.MODIFYRESULTOVERLAY;
	}


	private PageJSP modifyResult(HttpServletRequest request,
			HttpServletResponse response, int idmatch4) {
		
		Match matchmodify = MatchDao.getMatchById(idmatch4);
		
		Team localTeammodify = matchmodify.getLocal();
		Team guestTeammodify = matchmodify.getGuest();
		int localpjmodify = localTeammodify.getPj();
		int guestpjmodify = guestTeammodify.getPj();
		int localpgmodify = localTeammodify.getPg();
		int guestpgmodify = guestTeammodify.getPg();
		int localppmodify = localTeammodify.getPp();
		int guestppmodify = guestTeammodify.getPp();
		int localpemodify = localTeammodify.getPe();
		int guestpemodify = guestTeammodify.getPe();
		int localpointsmodify = localTeammodify.getPts();
		int guestpointsmodify = guestTeammodify.getPts();
		int localgfmodify = localTeammodify.getGf();
		int guestgfmodify = guestTeammodify.getGf();
		int localgcmodify = localTeammodify.getGc();
		int guestgcmodify = guestTeammodify.getGc();
		int localdifmodify = localTeammodify.getDiferencia();
		int guestdifmodify= guestTeammodify.getDiferencia();
		int oldlocalresult = matchmodify.getResultLocal();
		int oldguestresult = matchmodify.getResultGuest();
		
		deshacerresultados(oldlocalresult, oldguestresult, localpointsmodify, guestpointsmodify,
				localgfmodify, guestgfmodify, localgcmodify, guestgcmodify, localdifmodify, guestdifmodify,
				guestpemodify, localpemodify, guestppmodify, localppmodify, guestpgmodify, localpgmodify,
				localTeammodify, guestTeammodify, localpjmodify, guestpjmodify);
		
		setResults(request,matchmodify);
		
		int newlocalpjmodify = localTeammodify.getPj();
		int newguestpjmodify = guestTeammodify.getPj();
		int newlocalpgmodify = localTeammodify.getPg();
		int newguestpgmodify = guestTeammodify.getPg();
		int newlocalppmodify = localTeammodify.getPp();
		int newguestppmodify = guestTeammodify.getPp();
		int newlocalpemodify = localTeammodify.getPe();
		int newguestpemodify = guestTeammodify.getPe();
		int newlocalpointsmodify = localTeammodify.getPts();
		int newguestpointsmodify = guestTeammodify.getPts();
		int newlocalgfmodify = localTeammodify.getGf();
		int newguestgfmodify = guestTeammodify.getGf();
		int newlocalgcmodify = localTeammodify.getGc();
		int newguestgcmodify = guestTeammodify.getGc();
		int newlocaldifmodify = localTeammodify.getDiferencia();
		int newguestdifmodify= guestTeammodify.getDiferencia();
		int newlocalresult=matchmodify.getResultLocal();
		int newguestresult = matchmodify.getResultGuest();

		actualizarPts(newlocalresult, newguestresult, newlocalpointsmodify, newguestpointsmodify,
				newlocalgfmodify, newguestgfmodify, newlocalgcmodify, newguestgcmodify, newlocaldifmodify, newguestdifmodify,
				newguestpemodify, newlocalpemodify, newguestppmodify, newlocalppmodify, newguestpgmodify, newlocalpgmodify,
				localTeammodify, guestTeammodify, newlocalpjmodify, newguestpjmodify);	
	
		String id = "4";
		request.setAttribute("id",id);
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}


	private PageJSP addResult(HttpServletRequest request,
			HttpServletResponse response, int idmatch3) {
		
		Match match = MatchDao.getMatchById(idmatch3);
		
		setResults(request,match);
		Team localTeam = match.getLocal();
		Team guestTeam = match.getGuest();
		int localpj = localTeam.getPj();
		int guestpj = guestTeam.getPj();
		int localpg = localTeam.getPg();
		int guestpg = guestTeam.getPg();
		int localpp = localTeam.getPp();
		int guestpp = guestTeam.getPp();
		int localpe = localTeam.getPe();
		int guestpe = guestTeam.getPe();
		int localpoints = localTeam.getPts();
		int guestpoints = guestTeam.getPts();
		int localgf = localTeam.getGf();
		int guestgf = guestTeam.getGf();
		int localgc = localTeam.getGc();
		int guestgc = guestTeam.getGc();
		int localdif = localTeam.getDiferencia();
		int guestdif= guestTeam.getDiferencia();
		int localresult = match.getResultLocal();
		int guestresult = match.getResultGuest();
		
		actualizarPts(localresult, guestresult, localpoints, guestpoints,
				localgf, guestgf, localgc, guestgc, localdif, guestdif,
				guestpe, localpe, guestpp, localpp, guestpg, localpg,
				localTeam, guestTeam, localpj, guestpj);

		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}
	
	private void setResults(HttpServletRequest request,Match match) {
		int localresult = Integer.valueOf(request.getParameter("localresult"));
		match.setResultLocal(localresult);
		int guestresult = Integer.valueOf(request.getParameter("guestresult"));
		match.setResultGuest(guestresult);
		MatchDao.update(match);
	}
	
	private void actualizarPts(int localresult, int guestresult, int localpoints, int guestpoints,
			int localgf, int guestgf, int localgc, int guestgc, int localdif, int guestdif,
			int guestpe, int localpe, int guestpp, int localpp, int guestpg, int localpg,
			Team localTeam, Team guestTeam, int localpj, int guestpj){
	
		if(localresult>guestresult){
			localpoints=localpoints+3;
			localTeam.setPts(localpoints);
			localpg++;
			localTeam.setPg(localpg);
			guestpp++;
			guestTeam.setPp(guestpp);
			actualizargoles(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
			partidosjugados(localpj,guestpj, localTeam, guestTeam);
			TeamDao.update(localTeam);
			TeamDao.update(guestTeam);
		
		}
		
		if(localresult<guestresult){
			guestpoints=guestpoints+3;
			guestTeam.setPts(guestpoints);
			guestpg++;
			guestTeam.setPg(guestpg);
			localpp++;
			localTeam.setPp(localpp);
			actualizargoles(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
			partidosjugados(localpj,guestpj, localTeam, guestTeam);
			TeamDao.update(localTeam);
			TeamDao.update(guestTeam);
		}
		
		if(localresult==guestresult){
			guestpoints=guestpoints+1;
			localpoints=localpoints+1;
			guestTeam.setPts(guestpoints);
			localTeam.setPts(localpoints);
			guestpe++;
			guestTeam.setPe(guestpe);
			localpe++;
			localTeam.setPe(localpe);
			actualizargoles(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
			partidosjugados(localpj,guestpj, localTeam, guestTeam);
			TeamDao.update(localTeam);
			TeamDao.update(guestTeam);
		}		
	}



	private void partidosjugados(int localpj, int guestpj, Team localTeam,
			Team guestTeam) {
		localpj=localpj+1;
		localTeam.setPj(localpj);
		guestpj=guestpj+1;
		guestTeam.setPj(guestpj);
	}


	private void actualizargoles(int localresult, int localgf, int localgc,
			int guestresult, int guestgf, int guestgc, int localdif,
			int guestdif, Team localTeam, Team guestTeam) {
		localgf=localgf+localresult;//suma los goles a favor del equipo local
		localTeam.setGf(localgf);
		localgc=localgc+guestresult;//suma los goles en contra del equipo local
		localTeam.setGc(localgc);
		guestgf=guestgf+guestresult;//suma los goles a favor del equipo visitante
		guestTeam.setGf(guestgf);
		guestgc=guestgc+localresult;//suma los goles en contra del equipo local
		guestTeam.setGc(guestgc);
		localdif =localgf-localgc;
		guestdif =guestgf-guestgc;
		localTeam.setDiferencia(localdif);
		guestTeam.setDiferencia(guestdif);
		
	}
	
	private void deshacerresultados(int localresult, int guestresult,
			int localpoints, int guestpoints, int localgf, int guestgf,
			int localgc, int guestgc, int localdif, int guestdif, int guestpe,
			int localpe, int guestpp, int localpp, int guestpg, int localpg,
			Team localTeam, Team guestTeam, int localpj, int guestpj) {
		

		if(localresult>guestresult){
			localpoints=localpoints-3;
			localTeam.setPts(localpoints);
			localpg=localpg-1;
			localTeam.setPg(localpg);
			guestpp=guestpp-1;
			guestTeam.setPp(guestpp);
			actualizargolesmodify(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam );
			partidosjugadosmodify(localpj,guestpj, localTeam, guestTeam);
			TeamDao.update(localTeam);
			TeamDao.update(guestTeam);
		
		}
		
		if(localresult<guestresult){
			guestpoints=guestpoints-3;
			guestTeam.setPts(guestpoints);
			guestpg=guestpg-1;
			guestTeam.setPg(guestpg);
			localpp=localpp-1;
			localTeam.setPp(localpp);
			actualizargolesmodify(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam );
			partidosjugadosmodify(localpj,guestpj, localTeam, guestTeam);
			TeamDao.update(localTeam);
			TeamDao.update(guestTeam);
		}
		
		if(localresult==guestresult){
			guestpoints=guestpoints-1;
			localpoints=localpoints-1;
			guestTeam.setPts(guestpoints);
			localTeam.setPts(localpoints);
			guestpe=guestpe-1;
			guestTeam.setPe(guestpe);
			localpe=localpe-1;
			localTeam.setPe(localpe);
			actualizargolesmodify(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam );
			partidosjugadosmodify(localpj,guestpj, localTeam, guestTeam);
			TeamDao.update(localTeam);
			TeamDao.update(guestTeam);
		}
		
		
		
		
	}
	
	private void partidosjugadosmodify(int localpj, int guestpj, Team localTeam, Team guestTeam) {
		localpj=localpj-1;
		localTeam.setPj(localpj);
		guestpj=guestpj-1;
		guestTeam.setPj(guestpj);
		
		
	}


	private void actualizargolesmodify(int localresult, int localgf,
			int localgc, int guestresult, int guestgf, int guestgc,
			int localdif, int guestdif, Team localTeam, Team guestTeam) {
		localgf=localgf-localresult;//resta los goles a favor del equipo local
		localTeam.setGf(localgf);
		localgc=localgc-guestresult;//resta los goles en contra del equipo local
		localTeam.setGc(localgc);
		guestgf=guestgf-guestresult;//resta los goles a favor del equipo visitante
		guestTeam.setGf(guestgf);
		guestgc=guestgc-localresult;//resta los goles en contra del equipo local
		guestTeam.setGc(guestgc);
		localdif=localgf-localgc;
		localTeam.setDiferencia(localdif);
		guestdif=guestgf-guestgc;
		guestTeam.setDiferencia(guestdif);

	}


	private PageJSP addDate(HttpServletRequest request,
			HttpServletResponse response, int idmatch) {
		
		Match match = MatchDao.getMatchById(idmatch);
				
	    String auxd = (String)request.getParameter("date");
	    System.out.println(auxd);
		String[] date = auxd.split("-");
		
		String mes = date[1];
		String dia = date[0];
		String anio = date[2];
		Date d = getDate(Integer.parseInt(dia), Integer.parseInt(mes)-1, Integer
				.parseInt(anio));
		System.out.println(d);
		
		match.setDate(d);
		MatchDao.update(match);
		
		return PageJSP.TOURNAMENTPAGESERVLET;
	}
	
	
	public static Date getDate(final int date, final int month, final int year)
    {
            Calendar myCalendar = Calendar.getInstance();
            myCalendar.clear();
            myCalendar.set(year, month, date);

            return myCalendar.getTime();
    }

}
	