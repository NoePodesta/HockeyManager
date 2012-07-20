package DAO;

import hibernate.HibernateUtil;
import model.Fixture;
import model.Match;
import model.Team;
import model.Tournament;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import java.util.List;
import java.util.Random;


public class FixtureDao extends GenericDao {

	private static Session currentSession = HibernateUtil.getSessionFactory()
			.getCurrentSession();

	static {
		currentSession.setFlushMode(FlushMode.COMMIT);
	}

	public static void update(Fixture fixture) {
		getSession();
		persist(fixture, currentSession);
	}

	public static void remove(Fixture fixture) {
		getSession();
		delete(fixture, currentSession);
	}

	
//	public static void addMatch(Fixture fixture, Match match){
//
//
//
//
//
//	}
//
//	public static Fixture getFixtureById(int id) {
//		getSession();
//		final String consult ="Select idFixture FROM Fixture WHERE idFixture = '" + id + "'";
//		Query query = currentSession.createQuery(consult);
//		List<Fixture> fixtures = query.list();
//		Fixture fixture = null;
//		if (!fixtures.isEmpty()) {
//			fixture = fixtures.get(0);
//		}
//		return fixture;
//	}
	
	
	public static void generarFixture(Tournament tournament) {
		getSession();
		List<Team> teams = tournament.getTeams();
		int cantTeams = teams.size();
		if (!esPar(cantTeams)) {

			String name = "Libre";
			String description = "Libre";
			
			
	        byte[] image = null;
			TournamentDao.addTeam(tournament.getName(), name,description, image);
			Tournament tournament2 = TournamentDao.getTournamentByName(tournament.getName());
			
			fixture(tournament2);

		} else {
			fixture(tournament);
		}
	}
	
		

	private static void fixture(Tournament tournament) {
		
		getSession();
		
		
		String [] inicio = new String[tournament.getTeams().size()+1];
		
		inicio[0] = "";
		
		
		for(int i =0; i<tournament.getTeams().size();i++){
			inicio[i+1]=tournament.getTeams().get(i).getName();
			
		}

	
		
		String[][][] res = new String[(inicio.length-1)/2][inicio.length-2][3];

		Integer i,j;
		
		res = generateFixture(inicio);
		if (res[0][0][0].equals("01")) {
			System.out.println("Hay una cantidad impar de equipos");
		}
		else
		{
			while (res[0][0][0].equals("99")) {
				res = FixtureDao.generateFixture(inicio);
			}

			System.out.print("            ");
			for (j=1;j<=inicio.length-2;j++) {
				if (j > 9)
					System.out.print("  "+"F"+j);
				else
					System.out.print("  "+"F "+j);					
			}
			
			System.out.println(" ");
			
			getSession();
			Fixture fixture = new Fixture();
			update(fixture);
			tournament.setFixture(fixture);
			TournamentDao.update(tournament);
			
			for (i=1;i<=(inicio.length-1)/2;i++){
				System.out.print(" Partido "+i+" -");
					for (j=1;j<=inicio.length-2;j++) {
						System.out.print("  "+res[i][j][1]+"-"+res[i][j][2]);
						Match match = new Match();
						
						List<Match> matches = fixture.getMatches();
						
						matches.add(match);
						fixture.setMatches(matches);
						match.setFecha(j);
						getSession();
						Team localTeam = TeamDao.getTeamByName(res[i][j][1]);
						match.setLocal(localTeam);
						Team guestTeam = TeamDao.getTeamByName(res[i][j][2]);
						match.setGuest(guestTeam);
						match.setFixture(fixture);
						MatchDao.update(match);
						getSession();
						update(fixture);
					}
			}
				System.out.println(" ");
			}
		}


	public static String[][][] generateFixture(String[] teams){
		
		int nTeams = teams.length-1;
		int fechas = nTeams - 1;
		int partidosXfecha = nTeams / 2 ;
		int nMatches = (int) (Factorial(nTeams)/(Factorial(nTeams-2)*2));
		
		String[] desord = new String[nTeams+1];
		String[] testigo = new String[nTeams+1];
		String[][] partidos = new String[nMatches+1][3];
		int[] usados = new int[nMatches+1];
		String[][][] cuadro = new String[partidosXfecha+1][fechas+1][3];
		
		cuadro[0][0][0]="00";
		
		if ((nTeams % 2)== 1) {
			cuadro[0][0][0]="01";
			return cuadro;
		}
		
		int i,j,k;
		String aux;
		int used;
        int beginner;
		int nTeam1 =0;
		int nTeam2;
		String team2;
		
		// Copia la lista de teams y la desordena
		for (i=1;i<=nTeams;i++) {
			desord[i]=teams[i];
			
		}
		Random azar = new Random();
		for (i=1;i<=nTeams;i++) {
			j = azar.nextInt(nTeams)+1;
			aux = desord[j];
			desord[j]=desord[i];
			desord[i]=aux;
		}
		
		// arma una lista con todos los partidos posibles
		k=0;
		for (i=1;i<=nTeams;i++) {
			for (j=i+1;j<=nTeams;j++) {
				k++;
				partidos[k][1]=desord[i];
				partidos[k][2]=desord[j];
				usados[k]=0;
			}
		}
		
		// los empieza a asignar fecha x fecha, partido por partido
		used=0;
		for (fechas=1;fechas <= nTeams-1;fechas++) {
		
			//arma testigo a cubrir todos los teams en la fecha
			for (i=1;i<=nTeams;i++) {
				testigo[i]=teams[i];
			}
			
			beginner=1;
			for (partidosXfecha=1;partidosXfecha <= nTeams/2;partidosXfecha++) {
				
				for (i=1 ; i <= nTeams ; i++) {
					if (!testigo[i].equals("XX")) {
						nTeam1=i;
						
						break;
					}
					
					
				}
				// busco entre los partidos
	            // el primero no usados que involucre al equipo seleccionado
	            for (i=beginner; i <= nMatches; i++) {
	               	if (((partidos[i][1].equals(testigo[nTeam1])) ||
	            		 (partidos[i][2].equals(testigo[nTeam1]))) &&
	            		 (usados[i] == 0)) {
	              		
	               		// encontre un partido y busco el indice del otro equipo
	            		if (partidos[i][1].equals(testigo[nTeam1])){
	            			team2=partidos[i][2];
	            			
	            		}
	            		else{
	            			team2=partidos[i][1];
	            			
	            		}
	            		for (nTeam2=1;nTeam2 <= nTeams ; nTeam2++){
	            			if (team2.equals(testigo[nTeam2]))
	            				break;
	            		}
	            		if (nTeam2 <= nTeams) {

	            			cuadro[partidosXfecha][fechas][1]=partidos[i][1];
							cuadro[partidosXfecha][fechas][2]=partidos[i][2];
	            			
							testigo[nTeam1]="XX";
	            			testigo[nTeam2]="XX";
	            			used++;
	            			usados[i]=used;
	            			break;
	            		}
	            	}
	            }
	           	           	            
	            if (i > nMatches) {
	            	
	            		            
	                 // si no encontre partido tengo que volver al partido anterior y elegir otro
	                // desmarco el partido y establezco como nuevo punto de beginner el partido siguiente
	            	for (i=1;i<=nMatches;i++) {
	            		
	               		if (usados[i]==used) {
	            			usados[i]=0;
	            			beginner=i+1;
	               			break;
	            		}
	            	}
	            	//  restablesco los teams en el testigo
	            	for (j=1;j<=nTeams;j++) {
	            		if (teams[j].equals(partidos[i][1])) {
	            			testigo[j]=partidos[i][1];
	            			break;
	            		}
	            	}
	            	for (j=1;j<=nTeams;j++) {
	            		if (teams[j].equals(partidos[i][2])) {
	            			testigo[j]=partidos[i][2];
	            			break;
	            		}
	            	}
	            	used--;
	            	// blanqueo el partido en la grilla y vuelvo un partido para atras
	            	
	            	cuadro[partidosXfecha-1][fechas][1]="";
	            	cuadro[partidosXfecha-1][fechas][2]="";
	            	                       	
	            	partidosXfecha--;
	            	partidosXfecha--;
	              	if (partidosXfecha < 1) {
	            		cuadro[0][0][0]="99";
	            		break;
	}
	            }	
	            else
	            	beginner=1;
	            
			}
			
			if (cuadro[0][0][0].equals("99")) {
				break;
			}
		}
		return cuadro;
	}
	
	public static void removeMatch(Match actualMatch) {
		
		getSession();
		Fixture fixture = actualMatch.getFixture(); 
		List<Match> matches = fixture.getMatches();
		
		matches.remove(actualMatch);
		fixture.setMatches(matches);
		getSession();
		update(fixture);
		MatchDao.remove(actualMatch);
		
	}

	private static long Factorial(int x)	{
	    if ( x <= 1 )     // base case
            return 1;
        else
            return x * Factorial( x - 1 );
	}
	
	private static boolean esPar(int x) {
        return (x % 2) == 0;

    }

	
		 
	private static void getSession() {
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();

	}
}
