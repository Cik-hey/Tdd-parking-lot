package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_parking_ticket_when_park_given_a_parking_lot_and_a_car() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        // when
        ParkingTicket parkingTicket = parkingLot.park(car);

        // then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_parked_car_when_fetch_given_a_parking_lot_with_a_parked_car_and_a_parking_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        // when
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        // then
        assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_right_car_with_each_ticket_when_fetch_twice_given_a_parking_lot_with_two_parked_car_and_two_parking_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car();
        Car carB = new Car();
        ParkingTicket parkingTicketA = parkingLot.park(carA);
        ParkingTicket parkingTicketB = parkingLot.park(carB);

        // when
        Car firstFetchedCar = parkingLot.fetch(parkingTicketA);
        Car secondFetchedCar = parkingLot.fetch(parkingTicketB);

        // then
        assertEquals(carA, firstFetchedCar);
        assertEquals(carB, secondFetchedCar);
    }

    @Test
    public void should_throw_exception_with_error_message_when_fetch_given_a_parking_lot_and_an_unrecognized_parking_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        // when & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(unrecognizedParkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_with_error_message_when_fetch_given_a_parking_lot_and_a_used_parking_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        parkingLot.fetch(parkingTicket);

        // when & then
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_throw_exception_with_error_message_when_park_given_a_parking_lot_with_no_available_position_and_a_car() {
        // given
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
        Car car = new Car();

        // when & then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(car));
        assertEquals("No available position.", exception.getMessage());
    }


}
