package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final static int DEFAULT_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkedPositions;
    private int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        parkedPositions = new HashMap<>();
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (isNoAvailablePosition()) {
            throw  new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPositions.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (isUnrecognizedTicket(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        final Car car = parkedPositions.get(parkingTicket);
        parkedPositions.remove(parkingTicket);
        return car;
    }

    private boolean isUnrecognizedTicket(ParkingTicket parkingTicket) {
        return !parkedPositions.containsKey(parkingTicket);
    }

    private boolean isNoAvailablePosition() {
        return parkedPositions.size() >= capacity;
    }
}
