package jUnitTest;

import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Team;
import model.Tournament;
import DAO.FixtureDao;
import DAO.TournamentDao;


public class FixtureDaoTest {
	
	
	

	// en posicion 0,0,0 de la respuesta
	// 00 - OK
	// 01 - Cantidad impar de equipos
	// 99 - Falló - volver a probar
	
	// la salida devuelve 
	// res [nro de partido][fecha][equipo1|equipo2]
	
//	@Test
//	public void deleteFixture() {
//		
//		Tournament tournament = TournamentDao.getTournamentByName("juju");
//		TournamentDao.deleteFixture(tournament);
//		assertTrue(true);
//		
//		
//	}
	
	
	
	@Test
	public void generarFixture() {

		Tournament tournament = TournamentDao.getTournamentByName("juju");
		FixtureDao.generarFixture(tournament);
		
		assertTrue(true);
	}
		
}

