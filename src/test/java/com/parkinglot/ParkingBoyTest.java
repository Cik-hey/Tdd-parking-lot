package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        parkingBoy = new ParkingBoy(parkingLot);
    }
    //AC1
    @Test
    void should_return_parking_ticket_when_parkingBoy_park_given_a_parking_lot_and_a_car() {
        // given
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        // then
        assertNotNull(parkingTicket);
    }
    //AC2
    @Test
    void should_return_parked_car_when_parkingBoy_fetch_given_a_parking_lot_with_a_parked_car_and_a_parking_ticket() {
        // given
        ParkingTicket parkingTicket = parkingBoy.park(new Car());

        // when
        Car car = parkingBoy.fetch(parkingTicket);

        // then
        assertNotNull(car);
    }
    //AC3
    @Test
    void should_return_right_car_with_each_ticket_when_parkingBoy_fetch_twice_given_a_parking_lot_with_two_parked_car_and_two_parking_ticket() {
        // given
        Car carA = new Car();
        Car carB = new Car();
        ParkingTicket parkingTicketA = parkingBoy.park(carA);
        ParkingTicket parkingTicketB = parkingBoy.park(carB);

        // when
        Car firstFetchedCar = parkingBoy.fetch(parkingTicketA);
        Car secondFetchedCar = parkingBoy.fetch(parkingTicketB);

        // then
        assertEquals(carA, firstFetchedCar);
        assertEquals(carB, secondFetchedCar);
    }
    //AC4
    @Test
    void should_throw_exception_with_error_message_when_parkingBoy_fetch_given_a_parking_lot_and_an_unrecognized_parking_ticket() {
        // given
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        // when & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedParkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    //AC5
    @Test
    public void should_throw_exception_with_error_message_when_parkingBoy_fetch_given_a_parking_lot_and_a_used_parking_ticket() {
        // given
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(parkingTicket);

        // when & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    //AC6
    @Test
    public void should_throw_exception_with_error_message_when_parkingBoy_park_given_a_parking_lot_with_no_available_position_and_a_car() {
        // given
        ParkingLot parkingLotFull = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotFull);
        parkingBoy.park(new Car());
        Car car = new Car();

        // when & then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));
        assertEquals("No available position.", exception.getMessage());
    }

    //Story 4:
    @Test
    void should_successfully_add_to_parkingLotList() { //Testing for adding parkingLotList
        parkingBoy.addParkingLot(new ParkingLot());
        parkingBoy.park(new Car());

        assertSame(2, parkingBoy.getParkingLotList().size());
    }
    //AC1
    @Test
    void should_add_cars_in_otherParkingLot_when_firstParkingLot_is_full() {
        ParkingLot otherParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(otherParkingLot);

        ParkingTicket parkingTicket = null;
        for(int i=0; i<10; i++){
            parkingTicket = parkingBoy.park(new Car());
        }

        ParkingTicket otherParkingTicket = parkingBoy.park(new Car());

        int parkingLotAvail = parkingBoy.getParkingLotAtIndex(0).getAvailableParkingPosition();
        int otherParkingLotAvail = parkingBoy.getParkingLotAtIndex(1).getAvailableParkingPosition();

        assertSame(parkingLotAvail, 0);
        assertSame(otherParkingLotAvail, 9);
    }
}
