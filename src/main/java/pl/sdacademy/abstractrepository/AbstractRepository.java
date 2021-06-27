package pl.sdacademy.abstractrepository;

import pl.sdacademy.repository.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends Entity> {
    private List<T> entities;
    private Path filePath;

    public AbstractRepository(String filename) {
        filePath = Paths.get(filename);
        try {
            entities = Files.lines(filePath)
                    .map(this::createEntity)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z pliku", e);
        }
    }

    protected abstract T createEntity(String fileLine);

    public List<T> getAll() {
        return entities;
    }

    public T get(int id) {
        return entities.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private int generateNextId() {
        return entities.stream()
                .mapToInt(T::getId)
                .max()
                .orElse(0) + 1;
    }

    protected abstract String createFIleLine(T entity);

    private void updateFile() {
        List<String> fileLines = entities.stream()
                .map(this::createFIleLine) //to samo co: .map(person -> createFIleLine(person))
                .collect(Collectors.toList());
        try {
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu danych do pliku", e);
        }
    }
    public void add(T entity) {
        entity.setId(generateNextId());
        entities.add(entity);
        updateFile();
    }
}