package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User ", User.class).getResultList();
   }

   @Override
   public User getUserWithCar(String model, int series){
      return sessionFactory.getCurrentSession()
              .createQuery("from User where userCar.model = :carModel  and userCar.series = :carSeries", User.class)
              .setParameter("carModel", model)
              .setParameter("carSeries", series).getSingleResult();
   }
}

