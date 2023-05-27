package org.saimone.validator;

import org.saimone.exceptions.InvalidInputException;
import org.saimone.logger.ApartmentLogger;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class ApartmentValidator {
    private static final Pattern NUM_PATTERN = Pattern.compile("\\b[1-9]\\d{0,3}\\b|\\b10000\\b");
    private static final Pattern FLOOR_PATTERN = Pattern.compile("\\b([1-9]|[1-9][0-9]|100)\\b");
    private static final Pattern ROOMS_PATTERN = Pattern.compile("\\b([1-9]|1[0-9]|20)\\b");
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^(1[0-9]{2}|[2-4][0-9]{2}|\\b[1-9][0-9]\\b)(\\.[0-9]{1,2})?|500$");
    private static final Pattern TYPE_PATTERN = Pattern.compile("^[A-Za-z]{1,25}$");
    private static final Pattern TERM_PATTERN = Pattern.compile("^(19[5-9][0-9]|20[0-1][0-9]|202[0-3])-(19[5-9][0-9]|20[0-1][0-9]|202[0-3])$");
    private static final Pattern REMOVE_PATTERN = Pattern.compile("^-?(?:0|[1-9]\\d{0,5})$");

    public static int validateNumInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                if (NUM_PATTERN.matcher(input).matches()) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered apartment number: " + input);
                    return Integer.parseInt(input);
                } else {
                    throw new InvalidInputException("Invalid input. Correct range of apartment number: 1 - 10000. Try again");
                }
            } catch (InvalidInputException e) {
                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered apartment number incorrectly: " + input);
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validateFloorInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                if (FLOOR_PATTERN.matcher(input).matches()) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered floor: " + input);
                    return Integer.parseInt(input);
                } else {
                    throw new InvalidInputException("Invalid input. Correct range of apartment floor: 1 - 100. Try again");
                }
            } catch (InvalidInputException e) {
                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered floor incorrectly: " + input);
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validateNumRoomsInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                if (ROOMS_PATTERN.matcher(input).matches()) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the number of rooms: " + input);
                    return Integer.parseInt(input);
                } else {
                    throw new InvalidInputException("Invalid input. Correct range number of rooms: 1 - 20. Try again");
                }
            } catch (InvalidInputException e) {
                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the number of rooms incorrectly: " + input);
                System.out.println(e.getMessage());
            }
        }
    }

    public static double validateAreaInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                if (DOUBLE_PATTERN.matcher(input).matches()) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered area: " + input);
                    return Double.parseDouble(input);
                } else {
                    throw new InvalidInputException("Invalid input. Correct range of apartment area: 10.00 - 500.00 (including integers). Try again");
                }
            } catch (InvalidInputException e) {
                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered area incorrectly: " + input);
                System.out.println(e.getMessage());
            }
        }
    }

    public static String validateBuildingTypeInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                if (TYPE_PATTERN.matcher(input).matches()) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the building type: " + input);
                    return input;
                } else {
                    throw new InvalidInputException("Invalid input. Try again");
                }
            } catch (InvalidInputException e) {
                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the building type incorrectly: " + input);
                System.out.println(e.getMessage());
            }
        }
    }

    public static String validateOperationTermInput(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                if (TERM_PATTERN.matcher(input).matches()) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered the operation term: " + input);
                    return input;
                } else {
                    throw new InvalidInputException("Invalid input. Correct range of operation term: 1950-2023. Try again");
                }
            } catch (InvalidInputException e) {
                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered the operation term incorrectly: " + input);
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validateRemoveId(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine();
            try {
                if (REMOVE_PATTERN.matcher(input).matches()) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user entered remove id: " + input);
                    return Integer.parseInt(input);
                } else if (input.equals("exit")) {
                    ApartmentLogger.LOGGER.log(Level.INFO, "The user left remove menu");
                    return -1;
                } else {
                    throw new InvalidInputException("Invalid input. Correct range of id: 0 - 100000. Try again or exit/-1");
                }
            } catch (InvalidInputException e) {
                ApartmentLogger.LOGGER.log(Level.WARNING, "The user entered remove id incorrectly: " + input);
                System.out.println(e.getMessage());
            }
        }
    }
}