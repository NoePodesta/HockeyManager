package jUnitTest;

import DAO.TeamDao;
import DAO.TournamentDao;
import DAO.UserDao;
import model.Team;
import model.Tournament;
import model.UserAdmin;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TournamentDaoTest {
	
//    @Test
//    public void newTournament(){
//        UserAdmin userAdmin = (UserAdmin)UserDao.getUserByUserName("noe");
//        Tournament tournament = new Tournament();
//        tournament.setName("AHBA");
//        tournament.setDescription("AHBA es la Asociación de Hockey Amateur de Hockey de Buenos Aires. Este torneo contará " +
//                "con 14 equipos de mujeres de la primera división. El campeonato contara de un partido de ida. Aquel equipo " +
//                "que cuente con más puntos ganados será el ganador del torneo. AHBA fue creado para que las mujeres argentinas se diviertan jugando a lo que les gusta. " +
//                "Los Partidos se disputarán en las fechas pactadas, y no podran jugarse despues del 30 de noviembre. El toreno constara con arbitros profesionales." +
//                "Se contabilizaran los goles, y habra tarjeta verda, amarilla y roja. Los partidos seran de 2 tiempos de 35 minutos con un entretiempo" +
//                "de 15 minutos.");
//        userAdmin.setTournament(tournament);
//        TournamentDao.update(tournament);
//        UserDao.update(userAdmin);
//    }
    
    
	@Test
	public void addTeam() {

        byte[] image = null;
        TournamentDao.addTeam("AHBA","Pucará", "Club Pucará es un club deportivo argentino de la zona de Burzaco, Almirante Brown, Buenos Aires. El club"+
                "actualmente tiene equipos importantes de hockey y rugby en la zona del Gran Buenos Aires. Club Pucar√° fue fundado en 1943 por jóvenes deportistas " +
                "de las localidades de Burzaco, Glew y Adrogu√© del partido de Almirante Brown en Buenos Aires. Originalmente sólo un club de rugby, Pucará ha desarrollado un competitivo equipo de hockey recientemente.",image);
        TournamentDao.addTeam("AHBA", "San Fernando", "El Club San Fernando es un club social y deportivo situado en la ciudad de San Fernando en la Provincia de Buenos Aires a unos 28 km de la ciudad de Buenos Aires, Argentina." +
                "A comienzos de la década de 1910, la ciudad de San Fernando se destacaba en el norte bonaerense por su empuje económico y calidad de vida. A pesar de ello su oferta deportiva se limitaba al Atlético San Fernando, el Social Unión y el " +
                "F√©nix Club Gimnasia y Esgrima, quienes competan por la supremacía desplegando una intensa vida social.\n" +
                "Con una zona preparada para los deportes náuticos, los aficionados sanfernandinos debán asociarse a los clubes del Tigre porque se caracter de una entidad local donde practicarlos. An así, tripulaciones completas de jóvenes " +
                "lugare√±os interven√≠an en regatas internacionales representando, entre otros, al Buenos Aires Rowing Club y al Club Canottieri Italiani.\n" +
                "El Club San Fernando fue fundado el 3 de marzo de 1923 reunidos en asamblea extraordinaria, 72 asociados de los clubes Social Unión y Atlético San Fernando rubricaron el acta que dio nacimiento al Club San Fernando",image);
        TournamentDao.addTeam("AHBA", "BelGrano Athletic", "Fue fundado el 17 de agosto de 1896, uno de los más antiguos del país. En el Club se practican Rugby, Hockey sobre césped, Tenis, Cricket, Bowls y Squash, siendo el Club uno de los " +
                "Fundadores de las Uniones y Asociaciones de todos esos deportes. En sus inicios el Club también fue Fundador de la primera Liga Argentina de Fútbol, obteniendo tres campeonatos argentinos, durante la era amateur, ubicándose hasta el " +
                "2009 en el 13 lugar entre los Clubes que más títulos de fútbol argentinos han obtenido.\n" +
                "En su sede central hay 1 cancha oficial de rugby, 21 canchas de tenis,2 canchas de squash y 1 pileta cubierta y climatizada, y 2 canchas sintéticas que se utilizan para varios deportes. Hay un gimnasio y una sala de gimnasia y yoga. " +
                "Su anexo, que se encuentra situado de la localidad de Pilar, en la Provincia de Buenos Aires, 4 canchas oficiales de rugby, 6 para Rugby infantil, 1 cancha de hockey con superficie sintética y 1 de césped, además de una pileta. En verano se utilizan las mismas superficies de césped para la práctica de uno de los deportes m√°s antiguos del mundo, el Cricket.",image);
        TournamentDao.addTeam("AHBA", "San Lorenzo", "El Club Atlético San Lorenzo de Almagro, popularmente denominado San Lorenzo, es una instituciónn deportiva, social y cultural centenaria, con sede en la ciudad de Buenos Aires, Argentina, cuya principal actividad es el futbol profesional, juvenil y distintas actividades culturales y deportivas como el baloncesto, nataci√≥n, atletismo, hockey y rugby entre otras. Esta incorporada al sistema educativo con un jardín de infantes y el futuro colegio secundario. Es uno de los Cinco grandes del fútbol argentino.",image);
        TournamentDao.addTeam("AHBA", "Ducilo", "En Julio de 1938 el consejo de trabajo de la f√°brica Ducilo solicito a la gerencia de f√°bricas la autorizaciónn para fundar una agrupaci√≥n deportiva que reuniera a todo el personal. La convocatoria del Club Atlético Ducilo se fijo para el 18 de Noviembre. Quince díaas después ya estaban redactados los estatutos y elegida la primera Comisón Directiva. ",image);
        TournamentDao.addTeam("AHBA", "San Andres", "Adem√°s de ser un colegio y una universidad, hemos podido lograr que se conevierta en un CLUB",image);
        TournamentDao.addTeam("AHBA", "Banco Ciudad", "San Lorenzo",image);
        TournamentDao.addTeam("AHBA", "U.N.L.P", "Universidad de la Plata",image);
        TournamentDao.addTeam("AHBA", "Ciudad B", "Ciudad B",image);
        TournamentDao.addTeam("AHBA", "Monte Grande A", "Monte Grande A",image);
        TournamentDao.addTeam("AHBA", "Sitas A", "Sitas A",image);
        TournamentDao.addTeam("AHBA", "Lomas B", "Lomas B",image);
        TournamentDao.addTeam("AHBA", "Banfield A", "Banfield A",image);
        TournamentDao.addTeam("AHBA", "El Pincha", "Estudiantes de la Plata",image);

        Tournament tournament = TournamentDao.getTournamentByName("AHBA");
        List<Team> teamList = tournament.getTeams();
        assertTrue("El torneo no existe",tournament!=null);
        assertTrue("No hay esa cantidad de equipos", teamList.size()==14 );


	}
	
//	@Test
//	public void deleteTeam() {
//	
//		Tournament tournament = TournamentDao.getTournamentByName("Hsra");
//		Team team = TeamDao.getTeamById("3");
//		TournamentDao.deleteTeam(tournament,team);
//		
//		
//				
//	}
	
	
//	@Test
//	public void deleteTournament() {
//	
//	UserAdmin userAdmin = (UserAdmin) UserDao.getUserByUserName("vero");
//	Tournament tournament = userAdmin.getTournament();
//	TournamentDao.remove(userAdmin,tournament);
//	assertTrue(userAdmin.getTournament()==null);
//				
//	}

}
