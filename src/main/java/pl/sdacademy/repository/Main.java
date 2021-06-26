package pl.sdacademy.repository;

public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepository("people.txt");
        System.out.println(personRepository.getAll());
        System.out.println(personRepository.get(3));
        Person person = new Person("Kara", "Danvers", 27);
        personRepository.add(person);
    }
}
