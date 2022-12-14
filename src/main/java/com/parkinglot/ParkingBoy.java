package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLotList = new ArrayList<>();
        this.parkingLotList.add(parkingLot);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLotList.add(parkingLot);
    }

    public List<ParkingLot> getParkingLotList(){
        return this.parkingLotList;
    }

    public ParkingLot getParkingLotAtIndex(int index) {
        return this.parkingLotList.get(index);
    }

    public ParkingTicket park(Car car) {
        if(parkingLotList.isEmpty())
            return null;

        ParkingLot parkingLot = getFirstAvailableParkingLot();
        if(parkingLot == null) {
            this.lastErrorMessage = "Not enough position.";
            return null;
        }

        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket ticket) {
        if(parkingLotList.isEmpty()){
            return null;
        }

        ParkingLot parkingLot = getFirstAvailableParkingLot();

        if(parkingLot == null)
            this.lastErrorMessage = "Not enough position.";

        Car car = parkingLotList.get(0).fetch(ticket);

        return car;
    }

    private ParkingLot getFirstAvailableParkingLot() {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getAvailableParkingPosition() != 0)
                .findFirst()
                .orElse(null);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
