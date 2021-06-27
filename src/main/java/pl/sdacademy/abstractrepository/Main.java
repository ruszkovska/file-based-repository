package pl.sdacademy.abstractrepository;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository("car.txt");
        System.out.println(carRepository.getAll());
        System.out.println(carRepository.get(10));

        Car car = new Car(123,"abc","blue");
        carRepository.add(car);


        PersonRepository personRepository = new PersonRepository("people.txt");
        System.out.println(personRepository.getAll());
        System.out.println(personRepository.get(10));
    }
}
