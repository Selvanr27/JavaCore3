//import car.Car;

//public class Main {

 //   public static void main(String[] args) {

   //     Car car = new Car();

        //if you need want to create object of driver
        // you need to have object car
        // which also means
        // driver object is dependent on car object

        // dependency can be satisfied in 2 ways
        // first supplying car in a constructor
        // supplying car in a setter method

        // Driver driver = new Driver(car);

 //       Driver driver = new Driver();
 //       driver.setCar(car);
//    }
//}
import car.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Car car1 = (Car) context.getBean("carakfjhlskfhslhlshg");
        car1.setSpeed(100);

        Class<Car> cls = Car.class; // this is not the bytecode class file
        Car car2 = context.getBean(cls);
        System.out.println("Car Speed is "+ car2.getSpeed());

    }
}