package org.saimone.model.entity;

public class Apartment {
    private static int increment = 0;
    public Apartment() {
        increment++;
        this.id = increment;
    }

    private final int id;

    private int num;

    private double area;

    private int floor;

    private int numberOfRooms;

    private String buildingType;

    private String operationTerm;

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getOperationTerm() {
        return operationTerm;
    }

    public void setOperationTerm(String operationTerm) {
        this.operationTerm = operationTerm;
    }

    @Override
    public String toString() {
        return "Apartment [" +
                "id=" + id +
                ", num=" + num +
                ", area=" + area +
                ", floor=" + floor +
                ", numberOfRooms=" + numberOfRooms +
                ", buildingType='" + buildingType + "'" +
                ", operationTerm='" + operationTerm + "'" +
                "];";
    }
}
