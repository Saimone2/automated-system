package org.saimone.controller;

import org.saimone.model.entity.Apartment;
import org.saimone.model.service.ApartmentService;
import org.saimone.view.ApartmentView;

import java.util.List;

public class ApartmentController {
    ApartmentService apartmentService = ApartmentService.getInstance();

    public ApartmentController(ApartmentView view) {
        view.setController(this);
    }

    public void add(Apartment apartment) {
        apartmentService.add(apartment);
    }

    public void remove(int id) {
        apartmentService.remove(id);
    }

    public List<Apartment> getApartmentList() {
        return apartmentService.findAll();
    }

    public List<Apartment> filterByLargeAreaAndFloor(double minArea, int minFloor) {
        return apartmentService.filterByLargeAreaAndFloor(minArea, minFloor);
    }

    public List<Apartment> filterByNum(int num) {
        return apartmentService.filterByNum(num);
    }

    public List<Apartment> sortByProperty(String property) {
        return apartmentService.sortByProperty(property);
    }

    public void clearFile() {
        apartmentService.clearFile();
    }

    public void saveToEnteredFile(List<Apartment> filteredList, String fileName) {
        apartmentService.saveToEnteredFile(filteredList, fileName);
    }
}
