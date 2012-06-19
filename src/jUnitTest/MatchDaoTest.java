package jUnitTest;


import DAO.FixtureDao;
import DAO.MatchDao;
import model.Match;
import org.junit.Test;

public class MatchDaoTest {
	
	@Test
	public void deleteMatch() {
		
		Match match = MatchDao.getMatchById(81);
		FixtureDao.removeMatch(match);
		assert(true);		
		
	}

}
