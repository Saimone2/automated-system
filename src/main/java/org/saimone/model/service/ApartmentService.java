package org.saimone.model.service;

import org.saimone.model.entity.Apartment;
import org.saimone.model.dao.ApartmentDao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentService {
    private final ApartmentDao apartmentRepo = ApartmentDao.getInstance();

    private static ApartmentService instance;
    public static ApartmentService getInstance() {
        if (instance == null) {
            instance = new ApartmentService();
        }
        return instance;
    }

    public void add(Apartment apartment) {
        apartmentRepo.save(apartment);
    }

    public void remove(int id) {
        apartmentRepo.remove(id);
    }

    public List<Apartment> findAll() {
        return apartmentRepo.findFromFile();
    }

    public List<Apartment> filterByNum(int num) {
        return apartmentRepo.findFromFile().stream()
                .filter(apartment -> apartment.getNumberOfRooms() == num).toList();
    }

    public List<Apartment> filterByLargeAreaAndFloor(double minArea, int minFloor) {
        return apartmentRepo.findFromFile().stream()
                .filter(apartment -> apartment.getArea() >= minArea)
                .filter(apartment -> apartment.getFloor() >= minFloor)
                .collect(Collectors.toList());
    }

    public List<Apartment> sortByProperty(String property) {
        Comparator<Apartment> comparator = getComparator(property);
        return apartmentRepo.findFromFile().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Comparator<Apartment> getComparator(String property) {
        return switch (property) {
            case "area" -> Comparator.comparingDouble(Apartment::getArea);
            case "floor" -> Comparator.comparingInt(Apartment::getFloor);
            case "numberOfRooms" -> Comparator.comparingInt(Apartment::getNumberOfRooms);
            default -> throw new IllegalStateException("Unexpected value: " + property);
        };
    }

    public void clearFile() {
        apartmentRepo.clearFile();
    }

    public void saveToEnteredFile(List<Apartment> filteredList, String fileName) {
        apartmentRepo.saveToEnteredFile(filteredList, fileName);
    }
}