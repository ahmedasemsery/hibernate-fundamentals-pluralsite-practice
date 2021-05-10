package com.pluralsight.hibernateFundamentals;

import com.pluralsight.hibernateFundamentals.airport.Airport;
import com.pluralsight.hibernateFundamentals.airport.Passenger;
import com.pluralsight.hibernateFundamentals.airport.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // persistence-unit name
        EntityManagerFactory managerFactory =
                Persistence.createEntityManagerFactory("hibernateGettingStarted");

        EntityManager manager = managerFactory.createEntityManager();

        manager.getTransaction().begin();

        /* Write your queries here */
        Airport airport = new Airport(1, "Cairo");

        Passenger john = new Passenger(1, "John");
        john.setAirport(airport);

        Passenger adel = new Passenger(2, "Adel");
        adel.setAirport(airport);

        airport.addPassenger(adel);
        airport.addPassenger(john);

        Ticket ticket1 = new Ticket(1, "300");
        Ticket ticket2 = new Ticket(2, "400");

        adel.addTicket(ticket1);
        ticket1.setPassenger(adel);

        john.addTicket(ticket2);
        ticket2.setPassenger(john);

        manager.persist(airport);

        manager.persist(john);
        manager.persist(adel);

        manager.persist(ticket1);
        manager.persist(ticket2);

        manager.getTransaction().commit();
        managerFactory.close();
    }
}
