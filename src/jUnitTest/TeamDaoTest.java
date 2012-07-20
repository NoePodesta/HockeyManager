package jUnitTest;

import java.util.List;

import DAO.CardDao;
import DAO.MatchDao;
import DAO.PlayerDao;
import DAO.TeamDao;
import model.Card;
import model.Match;
import model.Player;
import model.Team;

import org.junit.Test;

import enums.CardColour;

import static org.junit.Assert.assertTrue;

public class TeamDaoTest {

	@Test
	public void addPlayers() {
		
		Team team = TeamDao.getTeamById("7");
		byte[] image = null;
		TeamDao.addPlayer(team, "Mar’a Noel", "Podest‡", "noe@podesta.com", "Defensora",image);
		TeamDao.addPlayer(team,"Macarena", "Retamosa", "macarestamosa@hotmail.com", "Volante",image);
		TeamDao.addPlayer(team,"Julieta", "Alam", "juli@hotmail.com", "volante",image);
		TeamDao.addPlayer(team,"Josefina", "D'onofrio", "jose@hotmail.com", "volante",image);
		TeamDao.addPlayer(team, "Mar’a Eugenia", "Lauro", "mariu@podesta.com", "Central",image);
		TeamDao.addPlayer(team,"Maria", "Iasaurralde", "meryisau@hotmail.com", "Defensora",image);
		TeamDao.addPlayer(team,"Florencia", "Corbella", "crobi@hotmail.com", "Defensora",image);
		TeamDao.addPlayer(team,"Mery", "Vago", "mery@hotmail.com", "Defensora",image);
		TeamDao.addPlayer(team,"Vicky", "Julian", "cicky@hotmail.com", "Delantera",image);
		TeamDao.addPlayer(team,"Dani", "Rosales", "Danii@hotmail.com", "Delantera",image);
		TeamDao.addPlayer(team,"Juana", "Mar’n Moreno", "juanitaa@hotmail.com", "Delantera",image);
		TeamDao.addPlayer(team,"Luc’a", "Mustafa", "lulii@hotmail.com", "Volante",image);
		assertTrue(true);

	}
//	
//	@Test
//	public void deletePlayers(){
//
//		Player player = PlayerDao.getPlayer(5);
//		PlayerDao.remove(player);
//
//	}
	
//	@Test
//	public void addCard(){
//        Player player = PlayerDao.getPlayer(1);
//        Match match= MatchDao.getMatchById(1);
//        Card card = new Card();
//        card.setPlayer(player);
//        card.setMatch(match);
//        card.setTeam(player.getTeam());
//        card.setCardColour(CardColour.GREEN);
//        CardDao.update(card);
//
//        List<Card> playersCards = player.getGreenCards();
//        playersCards.add(card);
//        PlayerDao.update(player);
//
//
//        match.getPlayerGCard().add(card);
//        MatchDao.update(match);
//	}
	
	
	
	
	
}
