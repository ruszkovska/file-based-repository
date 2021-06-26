package pl.sdacademy.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepository {
    public static Object getID;
    private List<Car> cars;
    private Path filePath;

    public CarRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            cars = Files.lines(filePath)
                    .map(this::createCar)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku", e);
        }
    }

    private Car createCar(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        int maxSpeed = Integer.parseInt(fileLineParts[1]);
        String model = fileLineParts[2];
        String color = fileLineParts[3];
        return new Car(id, maxSpeed, model, color);
    }

    public List<Car> getAll() {
        return cars;
    }

    private int generateNextId() {
        return cars.stream()
                .mapToInt(Car::getId)
                .max()
                .orElse(0) + 1;
    }

    private String createFIleLine(Car car) {
        return car.getId() + "," + car.getMaxSpeed() + "," + car.getModel() + "," + car.getColor();
    }

    private void updateFile() {
        List<String> fileLines = cars.stream()
                .map(this::createFIleLine) //to samo co: .map(person -> createFIleLine(person))
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych do pliku", e);
        }
    }

    public void add(Car car) {
        car.setId(generateNextId());
        cars.add(car);
        updateFile();
    }
}
