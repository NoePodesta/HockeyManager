package jUnitTest;


import org.junit.Test;


import model.Match;
import DAO.FixtureDao;
import DAO.MatchDao;

public class MatchDaoTest {
	
	@Test
	public void deleteMatch() {
		
		Match match = MatchDao.getMatchById(81);
		FixtureDao.removeMatch(match);
		assert(true);		
		
	}

}
