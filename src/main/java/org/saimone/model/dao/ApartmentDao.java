package org.saimone.model.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.saimone.exceptions.EmptyFileException;
import org.saimone.logger.ApartmentLogger;
import org.saimone.model.entity.Apartment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ApartmentDao {
    ObjectMapper objectMapper = new ObjectMapper();
    private static ApartmentDao instance;

    public static ApartmentDao getInstance() {
        if (instance == null) {
            instance = new ApartmentDao();
        }
        return instance;
    }

    public List<Apartment> findFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/source.txt"))) {
            String data = br.lines().collect(Collectors.joining());
            if (data.isBlank()) {
                throw new EmptyFileException("The list of apartments is empty");
            } else {
                return objectMapper.readValue(data, new TypeReference<>() { });
            }
        } catch (IOException e) {
            ApartmentLogger.LOGGER.log(Level.WARNING, e.getMessage());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/source.txt"))) {
                bw.write("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return new ArrayList<>();
        }
    }

    public void save(Apartment apartment) {
        List<Apartment> apartments = findFromFile();
        if (!apartments.contains(apartment)) {
            apartments.add(apartment);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/source.txt"))) {
                String json = objectMapper.writeValueAsString(apartments);
                bw.write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void remove(int id) {
        List<Apartment> apartments = findFromFile();
        apartments.removeIf(apart -> apart.getId() == id);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/source.txt"))) {
            String json = objectMapper.writeValueAsString(apartments);
            bw.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/saved/output.txt"))) {
            bw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToEnteredFile(List<Apartment> filteredList, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/saved/" + fileName + ".txt"))) {
            String json = objectMapper.writeValueAsString(filteredList);
            bw.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
