package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      String hql = "SELECT u FROM User u JOIN u.userCar" +
              " where u.userCar.model=:model and u.userCar.series=:series";
      Session session = sessionFactory.getCurrentSession();
      TypedQuery<User> query = session.createQuery(hql);
      query.setParameter("model", model).setParameter("series", series);
      return query.getResultList().get(0);
   }
}
