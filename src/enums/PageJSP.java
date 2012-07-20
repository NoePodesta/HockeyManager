package enums;

public enum PageJSP{
	
	HOME("JSP/public/home.jsp"), 
	HOMESERVLET("Home"),
	TOURNAMENTPAGE("JSP/tournament/tournamentPage.jsp"),
	TOURNAMENTPAGESERVLET("TournamentManager?action=TOURNAMENTPROFILE"), 
	USERPROFILEPAGE("JSP/user/userProfile.jsp"),
	LOGOUT("Logout"),
	MODIFYTOURNAMENTPAGE("JSP/tournament/modifyTournament.jsp"),
	FIXTURESERVLET("TournamentManager?action=FIXTUREPAGE"),
    RESULTSERVLET("TournamentManager?action=RESULSTPAGE"),
	TEAMPROFILEOVERLAY("JSP/team/teamProfileOverlay.jsp"),
	MODIFYTEAMOVERLAY("JSP/team/modifyTeamOverlay.jsp"), 
	MODIFYRESULTOVERLAY("JSP/match/modifyResults.jsp"), 
	RESULTOVERLAY("JSP/match/results.jsp"),
	GOALESOVERLAY("JSP/match/goalsOverlay.jsp"),
	OVERVIEWOVERLAY ("JSP/match/overview.jsp"), 
	GREENCARDOVERLAY("JSP/match/greenCardOverlay.jsp"),
	YELLOWCARDOVERLAY("JSP/match/yellowCardOverlay.jsp"),
	REDCARDOVERLAY("JSP/match/redCardOverlay.jsp"),
	COMMENTPAGE("JSP/comment/Comment.jsp"),
    TEAMPROFILE("JSP/team/teamProfile.jsp"),
    MODIFYPLAYEROVERLAY("JSP/player/modifyPlayerOverlay.jsp");

	
	private String url;
	
	private PageJSP(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
    
    public void setUrl(String url)
    {
    	this.url = url;
    }
}