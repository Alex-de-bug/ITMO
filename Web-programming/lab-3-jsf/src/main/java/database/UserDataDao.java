package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDataDao {
    private SessionFactory sessionFactory;


    public UserDataDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    public void saveUserData(Double x, Double y, Double r, Boolean hit) {
        UserData userData = new UserData();
        userData.setX(x);
        userData.setY(y);
        userData.setR(r);
        userData.setHit(hit);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(userData);
            transaction.commit();
        }
    }
    public List<UserData> getUserData() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM result";
            NativeQuery<UserData> query = session.createNativeQuery(sql, UserData.class);
            return query.list();
        }
    }

    public void clearResultTable() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "DELETE FROM result";
            NativeQuery<?> query = session.createNativeQuery(sql);
            session.beginTransaction();
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

}

