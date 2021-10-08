package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.User;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class UserDao {
    // private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
    public void save(User user) {
        // System.out.println("saved to DB" + user);
        // LOG.info("Saved to DB", user);
        System.out.println(user);
    }
}
