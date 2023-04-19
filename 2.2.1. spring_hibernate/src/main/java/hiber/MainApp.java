package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("car1", 10)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("car2", 11)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("car3", 12)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("car4", 13)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getUserCar());
         System.out.println();
      }

      User user = userService.getUserByCar("car3", 12);
      System.out.println(user.getFirstName() + "  " + user.getLastName());

      context.close();
   }
}
