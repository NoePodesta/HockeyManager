package enums;

public enum PageJSP{
	
	HOME("JSP/public/home.jsp"), 
	HOMESERVLET("Home"),
	ADDTOURNAMENT("JSP/tournament/newTournament.jsp"), 
	TOURNAMENTPAGE("JSP/tournament/tournamentPage.jsp"), 
	TOURNAMENTEXIST("JSP/tournament/tournamentExist.jsp"), 
	USEREXIST("JSP/user/userExist.jsp"),
	SUCCESFULREGISTER("JSP/user/succesfulRegister.jsp"), 
	TOURNAMENTPAGESERVLET("TournamentManager?action=TOURNAMENTPROFILE"), 
	USERPROFILEPAGE("JSP/user/userProfile.jsp"), 
	USERPROFILE("/UserManager?action=USERPROFILE"),
	LOGOUT("Logout"), 
	MODIFYUSERPAGE("JSP/user/modifyUser.jsp"), 
	TEAMADDPAGE("TeamManager?action=TEAMADDPAGE"), 
	FIXTURESERVLET("TournamentManager?action=FIXTUREPAGE"), 
	TEAMPROFILEOVERLAY("JSP/team/teamProfileOverlay.jsp"),
	MODIFYTEAMOVERLAY("JSP/team/modifyTeamOverlay.jsp"), 
	MODIFYRESULTOVERLAY("JSP/match/modifyResults.jsp"), 
	RESULTOVERLAY("JSP/match/results.jsp"), 
	GOALESOVERLAY("JSP/match/addGoals.jsp"), 
	OVERVIEWOVERLAY ("JSP/match/overview.jsp"), 
	GREENCARDOVERLAY("JSP/match/greenCardOverlay.jsp"),
	YELLOWCARDOVERLAY("JSP/match/yellowCardOverlay.jsp"),
	REDCARDOVERLAY("JSP/match/redCardOverlay.jsp"), 
	TEAMSPAGE("JSP/team/addTeams.jsp"), 
	TOURNAMENTTEAMS("TournamentManager?action=TEAMSTOURNAMENT"), 
	COMMENTPAGE("JSP/comment/Comment.jsp");

	
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