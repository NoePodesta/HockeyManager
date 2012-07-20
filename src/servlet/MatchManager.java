package servlet;


import DAO.*;
import enums.Action;
import enums.CardColour;
import enums.PageJSP;
import model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatchManager extends MainServlet {

    private static final long serialVersionUID = 1L;


    PageJSP handleAction(HttpServletRequest request,
                         HttpServletResponse response, Action action) {
        int idMatch = Integer.valueOf(request.getParameter("idMatch"));
        String colorCard = request.getParameter("colorCard");
        switch (action) {
            case ADDDATE:
                return addDate(request, idMatch);
            case ADDRESULT:
                return addResult(request, idMatch);
            case MODIFYRESULT:
                return modifyResult(request, idMatch);
            case MODIFYRESULTSERVLET:
                return modifyResultServlet(request, idMatch);
            case RESULTSERVLET:
                return resultServlet(request, idMatch);
            case ADDGOALESSERVLET:
                return addGoalesServlet(request, idMatch);
            case ADDGOALS:
                return addGoales(request, idMatch);
            case OVERVIEW:
                return overviewServlet(request, idMatch);
            case CARDSERVLET:
                return cardServlet(request, idMatch, colorCard);
            case ADDYELLOWCARDS:
                return addYellowCards(request, idMatch);
            case ADDREDCARDS:
                return addRedCards(request, idMatch);
            case ADDGREENCARDS:
                return addGreenCards(request, idMatch);
        }
        return PageJSP.HOME;


    }

    private PageJSP addGreenCards(HttpServletRequest request, int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);

        List<Card> matchCards = match.getPlayerGCard();

        if(matchCards.isEmpty()){
            createAndAddCards(request, match, "local", CardColour.GREEN);
            createAndAddCards(request, match, "guest", CardColour.GREEN);


        }else{
            for(Player player: match.getGuest().getPlayers()){
                removePreviousCardsFromPlayers(match,player.getGreenCards());
                PlayerDao.update(player);
            }
            for(Player player: match.getLocal().getPlayers()){
                removePreviousCardsFromPlayers(match,player.getGreenCards());
                PlayerDao.update(player);
            }
            matchCards.clear();
            createAndAddCards(request, match, "local", CardColour.GREEN);
            createAndAddCards(request, match, "guest", CardColour.GREEN);
        }

        return PageJSP.RESULTSERVLET;
        
    }

    private PageJSP addRedCards(HttpServletRequest request, int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);

        List<Card> matchCards = match.getPlayerGCard();

        if(matchCards.isEmpty()){
            createAndAddCards(request, match, "local", CardColour.RED);
            createAndAddCards(request, match, "guest", CardColour.RED);


        }else{
            for(Player player: match.getGuest().getPlayers()){
                removePreviousCardsFromPlayers(match,player.getGreenCards());
                PlayerDao.update(player);
            }
            for(Player player: match.getLocal().getPlayers()){
                removePreviousCardsFromPlayers(match,player.getGreenCards());
                PlayerDao.update(player);
            }
            matchCards.clear();
            createAndAddCards(request, match, "local", CardColour.RED);
            createAndAddCards(request, match, "guest", CardColour.RED);
        }

        return PageJSP.RESULTSERVLET;

    }

    private PageJSP addYellowCards(HttpServletRequest request, int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);

        List<Card> matchCards = match.getPlayerGCard();

        if(matchCards.isEmpty()){
            createAndAddCards(request, match, "local", CardColour.YELLOW);
            createAndAddCards(request, match, "guest", CardColour.YELLOW);


        }else{
            for(Player player: match.getGuest().getPlayers()){
                removePreviousCardsFromPlayers(match,player.getGreenCards());
                removePreviousCardsFromPlayers(match,player.getRedCards());
                removePreviousCardsFromPlayers(match,player.getYellowCards());
                PlayerDao.update(player);
            }
            for(Player player: match.getLocal().getPlayers()){
            	 removePreviousCardsFromPlayers(match,player.getGreenCards());
                 removePreviousCardsFromPlayers(match,player.getRedCards());
                 removePreviousCardsFromPlayers(match,player.getYellowCards());
                PlayerDao.update(player);
            }
            matchCards.clear();
            createAndAddCards(request, match, "local", CardColour.YELLOW);
            createAndAddCards(request, match, "guest", CardColour.YELLOW);
        }

        return PageJSP.RESULTSERVLET;

    }

    private void createAndAddCards(HttpServletRequest request, Match match, String team, CardColour cardColour){

        for(int i=0; i<=16; i++){
            String playerId = request.getParameter(team + i);
            if(playerId!=null && !playerId.equals("default")){
                Player player = PlayerDao.getPlayer(Integer.valueOf(playerId));
                Card card = new Card();
                card.setPlayer(player);
                card.setMatch(match);
                card.setTeam(player.getTeam());
                card.setCardColour(cardColour);
                CardDao.update(card);

                match.getPlayerGCard().add(card);
                MatchDao.update(match);
                PlayerDao.update(player);

            }
        }
    }

    private void removePreviousCardsFromPlayers(Match match, List<Card> playerCards){
        for(Card card : playerCards){
            if(card.getMatch().equals(match)){
                playerCards.remove(card);
            }
            CardDao.remove(card);
        }
    }

    private PageJSP cardServlet(HttpServletRequest request,
                                int idMatch, String colorCard) {

        Match match = MatchDao.getMatchById(idMatch);
        Team guestTeam = match.getGuest();
        Team localTeam  = match.getLocal();
        List<Card> cards = match.getPlayerGCard();
        List<Card> localGreenCards = new ArrayList<Card>();
        List<Card> guestGreenCards = new ArrayList<Card>();
        List<Card> localYellowCards = new ArrayList<Card>();
        List<Card> guestYellowCards = new ArrayList<Card>();
        List<Card> localRedCards = new ArrayList<Card>();
        List<Card> guestRedCards = new ArrayList<Card>();
       
       

        if(!cards.isEmpty()){
            for(Card card: cards){
               if(card.getCardColour().isGreen()){
                   if(card.getTeam().equals(localTeam)){
                       localGreenCards.add(card);
                   }else{
                       guestGreenCards.add(card);
                   }
               }if(card.getCardColour().isRed()){
                    if(card.getTeam().equals(localTeam)){
                        localRedCards.add(card);
                    }else{
                        guestRedCards.add(card);
                    }
               }else{
                    if(card.getTeam().equals(guestTeam)){
                        localYellowCards.add(card);
                    }else{
                        guestYellowCards.add(card);
                    }
               }
            }
        }

        request.setAttribute("guestTeam", guestTeam);
        request.setAttribute("localTeam", localTeam);
        request.setAttribute("match", match);


        if (colorCard.equals("GREEN")) {
          request.setAttribute("guestGreenCard",guestGreenCards);
          request.setAttribute("localGreenCard",localGreenCards);
            return PageJSP.GREENCARDOVERLAY;
        } else if (colorCard.equals("YELLOW")) {
            request.setAttribute("guestYellowCard",localYellowCards);
            request.setAttribute("localYellowCard",guestYellowCards);
            return PageJSP.YELLOWCARDOVERLAY;
        } else {
            request.setAttribute("guestRedCard",guestRedCards);
            request.setAttribute("localRedCard",localRedCards);
            return PageJSP.REDCARDOVERLAY;
        }

    }


    private PageJSP overviewServlet(HttpServletRequest request,
                                    int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);
        
        List<Goal> goals = match.getHighScoring();
        List<Goal> guestGoals = new ArrayList<Goal>();
        List<Goal> localGoals = new ArrayList<Goal>();
        List<Card> cards = match.getPlayerGCard();
        List<Card> localGreenCards = new ArrayList<Card>();
        List<Card> guestGreenCards = new ArrayList<Card>();
        List<Card> localYellowCards = new ArrayList<Card>();
        List<Card> guestYellowCards = new ArrayList<Card>();
        List<Card> localRedCards = new ArrayList<Card>();
        List<Card> guestRedCards = new ArrayList<Card>();
        
        for(Goal goal: goals){
            if(goal.getTeam().equals(match.getGuest())){
                guestGoals.add(goal);
            }else{
                localGoals.add(goal);
            }
        }
        for(Card card: cards){
            if(card.getCardColour().isGreen()){
                if(card.getTeam().equals(match.getGuest())){
                    guestGreenCards.add(card);
                }
                else {
                    localGreenCards.add(card);
                }
                
            }
            if(card.getCardColour().isYellow()){
                if(card.getTeam().equals(match.getGuest())){
                    guestYellowCards.add(card);
                }
                else {
                    localYellowCards.add(card);
                }
                
            }
            if(card.getCardColour().isRed()){
                if(card.getTeam().equals(match.getGuest())){
                    guestRedCards.add(card);
                }
                else {
                    localRedCards.add(card);
                }
                
            }
        }
                
        request.setAttribute("localGreenCards", localGreenCards);
        request.setAttribute("localRedCards", localRedCards);
        request.setAttribute("localYellowCards", localYellowCards);
        request.setAttribute("guestRedCards", guestRedCards);
        request.setAttribute("guestYellowCards", guestYellowCards);
        request.setAttribute("guestGreenCards", guestGreenCards);
        request.setAttribute("localGoals", localGoals);
        request.setAttribute("guestGoals", guestGoals);
        request.setAttribute("match", match);


        return PageJSP.OVERVIEWOVERLAY;
    }


    private PageJSP addGoales(HttpServletRequest request,
                              int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);
        List<Goal> matchHighScoring = match.getHighScoring();

        if(matchHighScoring.isEmpty()){
            createAndAddGoales(request,match,"local",match.getResultLocal());
            createAndAddGoales(request,match,"guest",match.getResultGuest());


        }else{
            for(Player player: match.getGuest().getPlayers()){
                removePreviousGoalsFromPlayers(player,match);}
            for(Player player: match.getLocal().getPlayers()){
                removePreviousGoalsFromPlayers(player,match);}
            matchHighScoring.clear();
            createAndAddGoales(request,match,"local",match.getResultLocal());
            createAndAddGoales(request,match,"guest",match.getResultGuest());
        }

        return PageJSP.RESULTSERVLET;
    }

        private void removePreviousGoalsFromPlayers(Player player, Match match){
            List<Goal> playerHighScoring = player.getGoals();
            for(Goal goal: playerHighScoring){
                if(goal.getMatch().equals(match)){
                    playerHighScoring.remove(goal);
                }
            }
            PlayerDao.update(player);
        }


    private void createAndAddGoales(HttpServletRequest request, Match match, String team, int result){

        for(int i=1; i<=result; i++){
            String playerId = request.getParameter(team + i);
            
            if(playerId!=null && !playerId.equals("default")){
                Player player = PlayerDao.getPlayer(Integer.valueOf(playerId));
                Goal goal = new Goal();
                goal.setPlayer(player);
                goal.setMatch(match);
                goal.setTeam(player.getTeam());
                GoalDao.update(goal);

                List<Goal> playerGoals = player.getGoals();
                playerGoals.add(goal);
                PlayerDao.update(player);

                match.getHighScoring().add(goal);
                MatchDao.update(match);
            }
        }
    }


    private PageJSP addGoalesServlet(HttpServletRequest request,
                                     int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);

        Team localTeam = match.getLocal();
        Team guestTeam = match.getGuest();
        List<Goal> matchGoales = match.getHighScoring();
        List<Goal> guestGoales = new ArrayList<Goal>();
        List<Goal> localGoales = new ArrayList<Goal>();

        if(!matchGoales.isEmpty()){
            for(Goal goal: matchGoales){
                if(goal.getTeam().equals(guestTeam)){
                    guestGoales.add(goal);
                }
                else{
                    localGoales.add(goal);
                }
            }
        }

        request.setAttribute("localGoales", localGoales);
        request.setAttribute("guestGoales", guestGoales);
        request.setAttribute("match", match);
        request.setAttribute("localTeam", localTeam);
        request.setAttribute("guestTeam", guestTeam);

        return PageJSP.GOALESOVERLAY;

    }


    private PageJSP resultServlet(HttpServletRequest request,
                                  int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);
        String localteam = match.getLocal().getName();
        String guestteam = match.getGuest().getName();

        request.setAttribute("localteam", localteam);
        request.setAttribute("guestteam", guestteam);
        request.setAttribute("idMatch", idMatch);

        return PageJSP.RESULTOVERLAY;
    }


    private PageJSP modifyResultServlet(HttpServletRequest request,
                                        int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);
        String localTeam = match.getLocal().getName();
        String guesTeam = match.getGuest().getName();
        int localResult = match.getResultLocal();
        int guestResult = match.getResultGuest();

        request.setAttribute("localteam", localTeam);
        request.setAttribute("guestteam", guesTeam);
        request.setAttribute("localresult", localResult);
        request.setAttribute("guestresult", guestResult);
        request.setAttribute("idMatch", idMatch);

        return PageJSP.MODIFYRESULTOVERLAY;
    }


    private PageJSP modifyResult(HttpServletRequest request,
                                 int idMatch) {

        Match matchModify = MatchDao.getMatchById(idMatch);

        Team localTeamModify = matchModify.getLocal();
        Team guestTeamModify = matchModify.getGuest();
        int localPJModify = localTeamModify.getPj();
        int guestPJModify = guestTeamModify.getPj();
        int localPGModify = localTeamModify.getPg();
        int guestpgmodify = guestTeamModify.getPg();
        int localppmodify = localTeamModify.getPp();
        int guestppmodify = guestTeamModify.getPp();
        int localpemodify = localTeamModify.getPe();
        int guestpemodify = guestTeamModify.getPe();
        int localpointsmodify = localTeamModify.getPts();
        int guestpointsmodify = guestTeamModify.getPts();
        int localgfmodify = localTeamModify.getGf();
        int guestgfmodify = guestTeamModify.getGf();
        int localgcmodify = localTeamModify.getGc();
        int guestgcmodify = guestTeamModify.getGc();
        int localdifmodify = localTeamModify.getDiferencia();
        int guestdifmodify = guestTeamModify.getDiferencia();
        int oldlocalresult = matchModify.getResultLocal();
        int oldguestresult = matchModify.getResultGuest();

        deshacerresultados(oldlocalresult, oldguestresult, localpointsmodify, guestpointsmodify,
                localgfmodify, guestgfmodify, localgcmodify, guestgcmodify, localdifmodify, guestdifmodify,
                guestpemodify, localpemodify, guestppmodify, localppmodify, guestpgmodify, localPGModify,
                localTeamModify, guestTeamModify, localPJModify, guestPJModify);

        setResults(request, matchModify);

        int newlocalpjmodify = localTeamModify.getPj();
        int newguestpjmodify = guestTeamModify.getPj();
        int newlocalpgmodify = localTeamModify.getPg();
        int newguestpgmodify = guestTeamModify.getPg();
        int newlocalppmodify = localTeamModify.getPp();
        int newguestppmodify = guestTeamModify.getPp();
        int newlocalpemodify = localTeamModify.getPe();
        int newguestpemodify = guestTeamModify.getPe();
        int newlocalpointsmodify = localTeamModify.getPts();
        int newguestpointsmodify = guestTeamModify.getPts();
        int newlocalgfmodify = localTeamModify.getGf();
        int newguestgfmodify = guestTeamModify.getGf();
        int newlocalgcmodify = localTeamModify.getGc();
        int newguestgcmodify = guestTeamModify.getGc();
        int newlocaldifmodify = localTeamModify.getDiferencia();
        int newguestdifmodify = guestTeamModify.getDiferencia();
        int newlocalresult = matchModify.getResultLocal();
        int newguestresult = matchModify.getResultGuest();

        actualizarPts(newlocalresult, newguestresult, newlocalpointsmodify, newguestpointsmodify,
                newlocalgfmodify, newguestgfmodify, newlocalgcmodify, newguestgcmodify, newlocaldifmodify, newguestdifmodify,
                newguestpemodify, newlocalpemodify, newguestppmodify, newlocalppmodify, newguestpgmodify, newlocalpgmodify,
                localTeamModify, guestTeamModify, newlocalpjmodify, newguestpjmodify);

        return PageJSP.RESULTSERVLET;
    }


    private PageJSP addResult(HttpServletRequest request,
                              int idMatch) {

        Match match = MatchDao.getMatchById(idMatch);

        setResults(request, match);
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
        int guestdif = guestTeam.getDiferencia();
        int localresult = match.getResultLocal();
        int guestresult = match.getResultGuest();

        actualizarPts(localresult, guestresult, localpoints, guestpoints,
                localgf, guestgf, localgc, guestgc, localdif, guestdif,
                guestpe, localpe, guestpp, localpp, guestpg, localpg,
                localTeam, guestTeam, localpj, guestpj);


        return PageJSP.RESULTSERVLET;
    }

    private void setResults(HttpServletRequest request, Match match) {
        int localResult = Integer.valueOf(request.getParameter("localresult"));
        match.setResultLocal(localResult);
        int guestResult = Integer.valueOf(request.getParameter("guestresult"));
        match.setResultGuest(guestResult);
        MatchDao.update(match);
    }

    private void actualizarPts(int localresult, int guestresult, int localpoints, int guestpoints,
                               int localgf, int guestgf, int localgc, int guestgc, int localdif, int guestdif,
                               int guestpe, int localpe, int guestpp, int localpp, int guestpg, int localpg,
                               Team localTeam, Team guestTeam, int localpj, int guestpj) {

        if (localresult > guestresult) {
            localpoints = localpoints + 3;
            localTeam.setPts(localpoints);
            localpg++;
            localTeam.setPg(localpg);
            guestpp++;
            guestTeam.setPp(guestpp);
            actualizargoles(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
            partidosjugados(localpj, guestpj, localTeam, guestTeam);
            TeamDao.update(localTeam);
            TeamDao.update(guestTeam);

        }

        if (localresult < guestresult) {
            guestpoints = guestpoints + 3;
            guestTeam.setPts(guestpoints);
            guestpg++;
            guestTeam.setPg(guestpg);
            localpp++;
            localTeam.setPp(localpp);
            actualizargoles(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
            partidosjugados(localpj, guestpj, localTeam, guestTeam);
            TeamDao.update(localTeam);
            TeamDao.update(guestTeam);
        }

        if (localresult == guestresult) {
            guestpoints = guestpoints + 1;
            localpoints = localpoints + 1;
            guestTeam.setPts(guestpoints);
            localTeam.setPts(localpoints);
            guestpe++;
            guestTeam.setPe(guestpe);
            localpe++;
            localTeam.setPe(localpe);
            actualizargoles(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
            partidosjugados(localpj, guestpj, localTeam, guestTeam);
            TeamDao.update(localTeam);
            TeamDao.update(guestTeam);
        }
    }


    private void partidosjugados(int localpj, int guestpj, Team localTeam,
                                 Team guestTeam) {
        localpj = localpj + 1;
        localTeam.setPj(localpj);
        guestpj = guestpj + 1;
        guestTeam.setPj(guestpj);
    }


    private void actualizargoles(int localresult, int localgf, int localgc,
                                 int guestresult, int guestgf, int guestgc, int localdif,
                                 int guestdif, Team localTeam, Team guestTeam) {
        localgf = localgf + localresult;//suma los goles a favor del equipo local
        localTeam.setGf(localgf);
        localgc = localgc + guestresult;//suma los goles en contra del equipo local
        localTeam.setGc(localgc);
        guestgf = guestgf + guestresult;//suma los goles a favor del equipo visitante
        guestTeam.setGf(guestgf);
        guestgc = guestgc + localresult;//suma los goles en contra del equipo local
        guestTeam.setGc(guestgc);
        localdif = localgf - localgc;
        guestdif = guestgf - guestgc;
        localTeam.setDiferencia(localdif);
        guestTeam.setDiferencia(guestdif);

    }

    private void deshacerresultados(int localresult, int guestresult,
                                    int localpoints, int guestpoints, int localgf, int guestgf,
                                    int localgc, int guestgc, int localdif, int guestdif, int guestpe,
                                    int localpe, int guestpp, int localpp, int guestpg, int localpg,
                                    Team localTeam, Team guestTeam, int localpj, int guestpj) {


        if (localresult > guestresult) {
            localpoints = localpoints - 3;
            localTeam.setPts(localpoints);
            localpg = localpg - 1;
            localTeam.setPg(localpg);
            guestpp = guestpp - 1;
            guestTeam.setPp(guestpp);
            actualizargolesmodify(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
            partidosjugadosmodify(localpj, guestpj, localTeam, guestTeam);
            TeamDao.update(localTeam);
            TeamDao.update(guestTeam);

        }

        if (localresult < guestresult) {
            guestpoints = guestpoints - 3;
            guestTeam.setPts(guestpoints);
            guestpg = guestpg - 1;
            guestTeam.setPg(guestpg);
            localpp = localpp - 1;
            localTeam.setPp(localpp);
            actualizargolesmodify(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
            partidosjugadosmodify(localpj, guestpj, localTeam, guestTeam);
            TeamDao.update(localTeam);
            TeamDao.update(guestTeam);
        }

        if (localresult == guestresult) {
            guestpoints = guestpoints - 1;
            localpoints = localpoints - 1;
            guestTeam.setPts(guestpoints);
            localTeam.setPts(localpoints);
            guestpe = guestpe - 1;
            guestTeam.setPe(guestpe);
            localpe = localpe - 1;
            localTeam.setPe(localpe);
            actualizargolesmodify(localresult, localgf, localgc, guestresult, guestgf, guestgc, localdif, guestdif, localTeam, guestTeam);
            partidosjugadosmodify(localpj, guestpj, localTeam, guestTeam);
            TeamDao.update(localTeam);
            TeamDao.update(guestTeam);
        }


    }

    private void partidosjugadosmodify(int localpj, int guestpj, Team localTeam, Team guestTeam) {
        localpj = localpj - 1;
        localTeam.setPj(localpj);
        guestpj = guestpj - 1;
        guestTeam.setPj(guestpj);


    }


    private void actualizargolesmodify(int localresult, int localgf,
                                       int localgc, int guestresult, int guestgf, int guestgc,
                                       int localdif, int guestdif, Team localTeam, Team guestTeam) {
        localgf = localgf - localresult;//resta los goles a favor del equipo local
        localTeam.setGf(localgf);
        localgc = localgc - guestresult;//resta los goles en contra del equipo local
        localTeam.setGc(localgc);
        guestgf = guestgf - guestresult;//resta los goles a favor del equipo visitante
        guestTeam.setGf(guestgf);
        guestgc = guestgc - localresult;//resta los goles en contra del equipo local
        guestTeam.setGc(guestgc);
        localdif = localgf - localgc;
        localTeam.setDiferencia(localdif);
        guestdif = guestgf - guestgc;
        guestTeam.setDiferencia(guestdif);

    }


    private PageJSP addDate(HttpServletRequest request,
                            int idmatch) {

        Match match = MatchDao.getMatchById(idmatch);

        String auxd = request.getParameter("date");
        System.out.println(auxd);
        String[] date = auxd.split("-");

        String mes = date[1];
        String dia = date[0];
        String anio = date[2];
        Date d = getDate(Integer.parseInt(dia), Integer.parseInt(mes) - 1, Integer
                .parseInt(anio));
        System.out.println(d);

        match.setDate(d);
        MatchDao.update(match);

        return PageJSP.FIXTURESERVLET;
    }


    public static Date getDate(final int date, final int month, final int year) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.clear();
        myCalendar.set(year, month, date);

        return myCalendar.getTime();
    }

}
	