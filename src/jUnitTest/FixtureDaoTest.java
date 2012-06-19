package jUnitTest;

import DAO.FixtureDao;
import DAO.TournamentDao;
import junit.framework.TestCase;
import model.Tournament;
import org.junit.Test;


/**
 * Created by IntelliJ IDEA.
 * User: NoePodesta
 * Date: 18/05/12
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class FixtureDaoTest extends TestCase {

    @Test
    public void generateFixture() {

        Tournament tournament = TournamentDao.getTournamentByName("juju");
        FixtureDao.generarFixture(tournament);

        assertTrue(true);
    }
}
