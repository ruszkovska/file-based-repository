package pl.sdacademy.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRepository {
    public static Object getID;
    private List<Person> people;
    private Path filePath;

    public PersonRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            people = Files.lines(filePath)
                    .map(this::createPerson)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku", e);
        }
    }

    private Person createPerson(String fileLine) {
        String[] fileLineParts = fileLine.split(",");
        int id = Integer.parseInt(fileLineParts[0]);
        String firstName = fileLineParts[1];
        String lastName = fileLineParts[2];
        int age = Integer.parseInt(fileLineParts[3]);
        return new Person(id, firstName, lastName, age);
    }

    public List<Person> getAll() {
        return people;
    }

    public Person get(int id) {
        for (Person person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    private int generateNextId() {
        // Wersja 1:
     //   int maxId = -1;
     //   for (Person person : people) {
     //       if (person.getId() > maxId) {
     //           maxId = person.getId();
     //       }
     //   }
     //    return  maxId + 1;
        return people.stream()
                .mapToInt(Person::getId)
                .max()
                .orElse(0) + 1;
    }

    private String createFIleLine(Person person) {
        return person.getId() + "," + person.getFirstName() + "," + person.getLastName() + "," + person.getAge();
    }

    private void updateFile() {
        List<String> fileLines = people.stream()
                .map(this::createFIleLine) //to samo co: .map(person -> createFIleLine(person))
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych do pliku", e);
        }
    }

    public void add(Person person) {
        person.setId(generateNextId());
        people.add(person);
        updateFile();
    }
}