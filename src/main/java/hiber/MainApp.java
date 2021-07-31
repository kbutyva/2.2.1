package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {

      Car car = new Car("ferrari", 100);
      User userWithCar = new User("Buyan", " Kuular", "kbutyva@mail.ru");
      userWithCar.setUserCar(car);
      Car car1 = new Car("lada", 777);
      User userWithCar1 = new User("Mariya", " Kuular", "mariya@mail.ru");
      userWithCar1.setUserCar(car1);

      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.add(userWithCar);
      userService.add(userWithCar1);

      System.out.println(userService.getUserWithCar("ferrari", 100));

      context.close();
   }
}
