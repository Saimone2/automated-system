package org.saimone.view;

import org.saimone.controller.ApartmentController;
import org.saimone.exceptions.EmptyFileException;
import org.saimone.exceptions.IdNotFoundException;
import org.saimone.logger.ApartmentLogger;
import org.saimone.model.entity.Apartment;
import org.saimone.validator.ApartmentValidator;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class ApartmentView {
    private ApartmentController controller;
    private final Scanner scanner;
    private String choice;
    private final String SEPARATOR = "-------------------------------------";
    private List<Apartment> apartments;

    public ApartmentView() {
        controller = new ApartmentController(this);
        scanner = new Scanner(System.in);
        apartments = null;
    }

    public void setController(ApartmentController apartmentController) {
        this.controller = apartmentController;
    }

    public void menu() {
        System.out.println();
        System.out.println(SEPARATOR);
        System.out.println("MENU");
        System.out.println(SEPARATOR);
        System.out.println("view - view a list of all apartments");
        System.out.println("add - add a new apartment");
        System.out.println("remove - remove an apartment from the list");
        System.out.println("exit - exit the program");
        System.out.println(SEPARATOR);
    }

    private void view(List<Apartment> apartments) {
        try {
            if (apartments == null || apartments.isEmpty()) {
                System.out.println("List is empty");
                throw new EmptyFileException("The list of apartments is empty");
            } else {
                System.out.println();
                System.out.println(SEPARATOR);
                System.out.println("SAVED APARTMENTS");
                System.out.println(SEPARATOR);
                System.out.printf("| %-6s | %-6s | %-6s | %-5s | %-15s | %-16s | %-14s |%n", "ID", "Number", "Area", "Floor", "Number of rooms", "Building type", "Operation term");
                apartments.forEach(apart -> System.out.printf("| %-6s | %-6s | %-6s | %-5s | %-15s | %-16s | %-14s |%n", apart.getId(), apart.getNum(), apart.getArea(), apart.getFloor(), apart.getNumberOfRooms(), apart.getBuildingType(), apart.getOperationTerm()));
                System.out.println(SEPARATOR);
            }
        } catch (EmptyFileException e) {
            ApartmentLogger.LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void menuView() {
        System.out.println("filter - filter the selection of apartments");
        System.out.println("sort - sort a selection of apartments");
        System.out.println("exit - exit the view");
        System.out.println(SEPARATOR);
    }

    private void filter() {
        System.out.println();
        System.out.println(SEPARATOR);
        System.out.println("FILTER");
        System.out.println(SEPARATOR);
        System.out.println("num - filter by number of rooms");
        System.out.println("area/floor - filter by a larger area and above a given floor");
        System.out.println("exit - exit the filter");
        System.out.println(SEPARATOR);
    }

    private void sort() {
        System.out.println();
        System.out.println(SEPARATOR);
        System.out.println("SORT");
        System.out.println(SEPARATOR);
        System.out.println("num - sort by number of rooms");
        System.out.println("area - sort by area");
        System.out.println("floor - sort by floor");
        System.out.println("exit - exit the sorting");
        System.out.println(SEPARATOR);
    }

    public void run() {
        apartments = controller.getApartmentList();
        menu();
        outer:
        while (true) {
            System.out.print("Input: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "view" -> {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the command \"view\"");
                    outer1:
                    while (true) {
                        view(apartments);
                        menuView();
                        System.out.print("Input: ");
                        choice = scanner.nextLine();
                        switch (choice) {
                            case "sort" -> {
                                ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the command \"sort\"");
                                outer2:
                                while (true) {
                                    sort();
                                    System.out.print("Input: ");
                                    choice = scanner.nextLine();
                                    switch (choice) {
                                        case "num" -> {
                                            ApartmentLogger.LOGGER.log(Level.INFO, "The user selected sorting by number of rooms");
                                            sortByProperty("numberOfRooms");
                                        }
                                        case "area" -> {
                                            ApartmentLogger.LOGGER.log(Level.INFO, "The user selected sorting by apartments area");
                                            sortByProperty("area");
                                        }
                                        case "floor" -> {
                                            ApartmentLogger.LOGGER.log(Level.INFO, "The user selected sorting by apartments floor");
                                            sortByProperty("floor");
                                        }
                                        case "exit" -> {
                                            ApartmentLogger.LOGGER.log(Level.INFO, "The user left sorting");
                                            break outer2;
                                        }
                                        default -> {
                                            ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the sorting command incorrectly");
                                            System.out.println("Command entered incorrectly. Try again");
                                        }
                                    }
                                }
                            }
                            case "filter" -> {
                                ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the command \"filter\"");
                                outer3:
                                while (true) {
                                    filter();
                                    System.out.print("Input: ");
                                    choice = scanner.nextLine();
                                    switch (choice) {
                                        case "num" -> {
                                            ApartmentLogger.LOGGER.log(Level.INFO, "The user launched a filter by the number of rooms");
                                            filterByNum();
                                        }
                                        case "area/floor" -> {
                                            ApartmentLogger.LOGGER.log(Level.INFO, "The user launched a filter by minimum floor and area");
                                            filterByLargeAreaAndFloor();
                                        }
                                        case "exit" -> {
                                            ApartmentLogger.LOGGER.log(Level.INFO, "The user left filter");
                                            break outer3;
                                        }
                                        default -> {
                                            ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the filter command incorrectly");
                                            System.out.println("Command entered incorrectly. Try again");
                                        }
                                    }
                                }
                            }
                            case "exit" -> {
                                ApartmentLogger.LOGGER.log(Level.INFO, "The user left view menu");
                                break outer1;
                            }
                            default -> {
                                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the view menu command incorrectly");
                                System.out.println("Command entered incorrectly. Try again");
                            }
                        }
                    }
                }
                case "add" -> {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the command \"add\"");
                    add();
                }
                case "remove" -> {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the command \"remove\"");
                    remove();
                }
                case "exit" -> {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the command \"exit\"");
                    outer4:
                    while (true) {
                        System.out.println("Save the full list of apartments to a file? y/n");
                        System.out.print("Input: ");
                        choice = scanner.nextLine();
                        switch (choice) {
                            case "y" -> {
                                ApartmentLogger.LOGGER.log(Level.INFO, "The user saved the entire list to the file \"output.txt\"");
                                saveToEnteredFile(apartments, "output");
                                break outer4;
                            }
                            case "n" -> {
                                ApartmentLogger.LOGGER.log(Level.INFO, "The user did NOT save the entire list to \"output.txt\"");
                                clearFile();
                                break outer4;
                            }
                            default -> {
                                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the y/n command incorrectly");
                                System.out.println("Command entered incorrectly. Try again");
                            }
                        }
                    }
                    break outer;
                }
                default -> {
                    ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the menu command incorrectly");
                    System.out.println("Command entered incorrectly. Try again");
                }
            }
            menu();
        }
        System.out.println("Program is stopped");
    }

    private void add() {
        view(apartments);
        Apartment apartment = new Apartment();
        System.out.println(SEPARATOR);
        System.out.println("CREATING NEW APARTMENT");
        System.out.println(SEPARATOR);
        apartment.setNum(ApartmentValidator.validateNumInput(scanner, "Apartment number: "));
        apartment.setArea(ApartmentValidator.validateAreaInput(scanner, "Apartment area: "));
        apartment.setFloor(ApartmentValidator.validateFloorInput(scanner, "Apartment floor: "));
        apartment.setNumberOfRooms(ApartmentValidator.validateNumRoomsInput(scanner, "Apartment number of rooms: "));
        apartment.setBuildingType(ApartmentValidator.validateBuildingTypeInput(scanner, "Apartment building type: "));
        apartment.setOperationTerm(ApartmentValidator.validateOperationTermInput(scanner, "Apartment operation term: "));
        System.out.println(SEPARATOR);
        controller.add(apartment);
        apartments = controller.getApartmentList();
        ApartmentLogger.LOGGER.log(Level.INFO, "The user added apartment:" + apartment);
    }

    private void filterByNum() {
        int num = ApartmentValidator.validateNumRoomsInput(scanner, "Enter the current number of rooms: ");
        List<Apartment> filteredList = controller.filterByNum(num);
        ApartmentLogger.LOGGER.log(Level.INFO, "The user filtered the list by the number of rooms: " + num);
        saveSelectedList(filteredList);
    }

    private void filterByLargeAreaAndFloor() {
        double minArea = ApartmentValidator.validateAreaInput(scanner, "Enter the minimum area: ");
        int minFloor = ApartmentValidator.validateFloorInput(scanner, "Enter the minimum floor: ");
        List<Apartment> filteredList = controller.filterByLargeAreaAndFloor(minArea, minFloor);
        ApartmentLogger.LOGGER.log(Level.INFO, "The user filtered the list by the minimum floor: " + minFloor + " and area: " + minArea);
        saveSelectedList(filteredList);
    }

    private void saveSelectedList(List<Apartment> filteredList) {
        view(filteredList);
        if(!filteredList.isEmpty()) {
            outer:
            while (true) {
                System.out.println("Save a selected list to a file? y/n");
                System.out.print("Input: ");
                choice = scanner.nextLine();
                switch (choice) {
                    case "y" -> {
                        System.out.println("File name: ");
                        choice = scanner.nextLine();
                        saveToEnteredFile(filteredList, choice);
                        ApartmentLogger.LOGGER.log(Level.INFO, "The user saved the selected list to the own file \"" + choice + ".txt\"");
                        break outer;
                    }
                    case "n" -> {
                        ApartmentLogger.LOGGER.log(Level.INFO, "The user did NOT save the selected list to the own file");
                        break outer;
                    }
                    default -> {
                        ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the y/n command incorrectly");
                        System.out.println("Command entered incorrectly. Try again");
                    }
                }
            }
        }
    }

    private void sortByProperty(String property) {
        List<Apartment> apartments = controller.sortByProperty(property);
        ApartmentLogger.LOGGER.log(Level.INFO, "The user sorted the list by " + property);
        view(apartments);
    }

    private void saveToEnteredFile(List<Apartment> filteredList, String fileName) {
        controller.saveToEnteredFile(filteredList, fileName);
    }

    private void clearFile() {
        controller.clearFile();
    }

    private void remove() {
        view(apartments);
        while (true) {
            int id = ApartmentValidator.validateRemoveId(scanner, "Apartment ID to be deleted: ");
            int increment = 0;
            if (id != -1) {
                try {
                    for (Apartment apart : apartments) {
                        if (apart.getId() == id) {
                            increment++;
                            controller.remove(id);
                            apartments = controller.getApartmentList();
                            ApartmentLogger.LOGGER.log(Level.INFO, "User deleted apartment with ID: " + id);
                            System.out.println("The apartment with ID: " + id + " has been removed");
                        }
                    }
                    if (increment == 0) {
                        throw new IdNotFoundException("No such ID was found. Try again or exit/-1");
                    } else {
                        break;
                    }
                } catch (IdNotFoundException e) {
                    ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered an invalid or non-existent ID: " + id);
                    System.out.println(e.getMessage());
                }
            } else {
                break;
            }
        }
    }
}