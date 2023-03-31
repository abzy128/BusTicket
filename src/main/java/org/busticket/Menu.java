package org.busticket;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static int mainMenu() {
        while (true) {
            System.out.println("Welcome to Bus Ticket System");
            System.out.println("1. Client Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Register");
            System.out.println("4. Exit");
            int input = new Scanner(System.in).nextInt();
            if (isValid(1, 4, input)) {
                return input;
            }
        }
    }

    public static Map<String, String> loginMenu() {
        while (true) {
            System.out.println("Login");
            System.out.print("Username: ");
            String username = new Scanner(System.in).nextLine();
            System.out.print("Password: ");
            String password = new Scanner(System.in).nextLine();
            if (username.length() > 0 && password.length() > 0) {
                return Map.of("username", username, "password", password);
            }
        }
    }

    public static Client registerClient() {
        System.out.println("Register");
        Client.Builder builder = new Client.Builder();
        System.out.print("Name: ");
        builder.name(scanner.nextLine());
        System.out.print("Surname: ");
        builder.surname(scanner.nextLine());
        System.out.print("Username: ");
        builder.username(scanner.nextLine());
        System.out.print("Email: ");
        builder.email(scanner.nextLine());
        System.out.print("Password: ");
        builder.password(scanner.nextLine());
        System.out.print("Phone: ");
        builder.phone(scanner.nextLine());
        System.out.print("Address: ");
        builder.address(scanner.nextLine());
        System.out.print("Discount: ");
        builder.discount(Integer.parseInt(scanner.nextLine()));
        System.out.print("Nationality: ");
        builder.nationality(scanner.nextLine());
        System.out.print("Passport number: ");
        builder.passportNumber(scanner.nextLine());
        return builder.build();
    }

    public static int clientMenu() {
        while (true) {
            System.out.println("1. Buy Ticket");
            System.out.println("2. Show Tickets");
            System.out.println("3. Show Buses");
            System.out.println("4. Show Profile");
            System.out.println("5. Exit");
            int input = new Scanner(System.in).nextInt();
            if (isValid(1, 5, input)) {
                return input;
            }
        }
    }

    public static int adminMenu() {
        while (true) {
            System.out.println("1. Bus Menu");
            System.out.println("2. Show Buses");
            System.out.println("3. Show Tickets");
            System.out.println("4. Show Clients");
            System.out.println("5. Exit");
            int input = new Scanner(System.in).nextInt();
            if (isValid(1, 5, input)) {
                return input;
            }
        }
    }

    public static int busMenu() {
        while (true) {
            System.out.println("1. Add Bus");
            System.out.println("2. Update Bus");
            System.out.println("3. Delete Bus");
            System.out.println("4. Show Buses");
            System.out.println("5. Back");
            int input = new Scanner(System.in).nextInt();
            if (isValid(1, 5, input)) {
                return input;
            }
        }
    }

    public static int ticketMenu() {
        while (true) {
            System.out.println("1. Add Ticket");
            System.out.println("2. Update Ticket");
            System.out.println("3. Delete Ticket");
            System.out.println("4. Show Tickets");
            System.out.println("5. Back");
            int input = new Scanner(System.in).nextInt();
            if (isValid(1, 5, input)) {
                return input;
            }
        }
    }

    public static boolean isValid(int a, int b, int input) {
        if (input < a || input > b) {
            System.out.println("Invalid input");
            return false;
        }
        return true;
    }

    public static Ticket buyTicket() {
        // Search for buses with matching departure and destination
        // Get list of buses from database and show them
        // Ask user to choose bus type
        int input = busTypeMenu();
        switch (input){
            case 1->{
                // Get list of city buses from database and show them
                // Get bus id from user
                List<CityBus> cityBuses = null;
                try {
                    cityBuses = Database.getCityBuses();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                // Output list of available cities
                System.out.println("City Buses:");
                for (CityBus cityBus : cityBuses) {
                    System.out.println(cityBus.toString());
                }
                System.out.print("Bus number: ");
                String busNumber = scanner.nextLine();
                // Check if bus exists
                for (CityBus cityBus : cityBuses) {
                    if (cityBus.getBusNumber().equals(busNumber)) {
                        Ticket ticket = new Ticket();
                        ticket.setBusNumber(busNumber);
                        ticket.setBusNumber(busNumber);
                        return ticket;
                    }
                }
                return null;
            }
            case 2->{
                // Get list of inter-city buses from database and show them
                // Get bus id from user
                List<InterCityBus> interCityBuses = null;
                try {
                    interCityBuses = Database.getInterCityBuses();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                String departure = null;
                String destination = null;
                // Ask user to choose departure and destination
                while (true) {
                    System.out.println("Enter departure and destination (leave blank to show all buses)");
                    System.out.print("Departure: ");
                    departure = scanner.nextLine();
                    System.out.print("Destination: ");
                    destination = scanner.nextLine();
                    if (departure.equals(destination) && departure.length() != 0) {
                        System.out.println("Departure and destination cannot be the same");
                    } else {
                        break;
                    }
                }
                // Output all buses with matching departure and destination
                System.out.println("Inter City Buses:");
                for(InterCityBus interCityBus : interCityBuses){
                    if((interCityBus.getDeparture().equals(departure) || departure.length()==0) && (interCityBus.getDestination().equals(destination) || destination.length()==0)){
                        System.out.println(interCityBus.toString());
                    }
                }
                System.out.print("Bus number: ");
                String busNumber = scanner.nextLine();
                // Check if bus exists
                for (InterCityBus interCityBus : interCityBuses) {
                    if (interCityBus.getBusNumber().equals(busNumber)) {
                        Ticket ticket = new Ticket();
                        ticket.setBusNumber(busNumber);
                        ticket.setBusNumber(busNumber);
                        return ticket;
                    }
                }
                System.out.println("Bus does not exist");
                return null;
            }
            case 3->{
                return null;
            }
        }
        return null;
    }

    public static void showClientTickets(Client client) {
        try {
            List<Ticket> tickets = Database.getTickets(client.getUsername());
            if (tickets.size() == 0) {
                System.out.println("You have no tickets");
            } else {
                System.out.println("Your tickets:");
                for (Ticket ticket : tickets) {
                    System.out.println(ticket.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int busTypeMenu() {
        while (true) {
            System.out.println("1. City Bus");
            System.out.println("2. Inter City Bus");
            System.out.println("3. Back");
            int input = new Scanner(System.in).nextInt();
            if (isValid(1, 3, input)) {
                return input;
            }
        }
    }

    public static void showClients() {
        // Get list of all clients from database
        List<Client> clients = Database.getClients();
        if (clients.size() == 0) {
            System.out.println("There are no clients");
        } else {
            System.out.println("Clients:");
            for (Client client : clients) {
                System.out.println(client.toString());
            }
        }
    }
    public static void showBuses() {
        // Get list of all buses from database
        List<CityBus> cityBuses = Database.getCityBuses();
        List<InterCityBus> interCityBuses = Database.getInterCityBuses();
        if(cityBuses.size() == 0 && interCityBuses.size() == 0){
            System.out.println("There are no buses");
        }
        else {
            System.out.println("City buses:");
            for (CityBus cityBus : cityBuses) {
                System.out.println(cityBus.getBusNumber()+" "+cityBus.getBusModel()+" "+cityBus.getBusDriver()+" "+cityBus.getPrice()+" "+cityBus.getFrequency()+" "+cityBus.getLoopTime()+" "+cityBus.getRoute()+" "+cityBus.getPrice()+"tenge");
            }
            System.out.println("Inter city buses:");
            for (InterCityBus interCityBus : interCityBuses) {
                System.out.println(interCityBus.getBusNumber()+" "+interCityBus.getDeparture()+" "+interCityBus.getDestination()+" "+interCityBus.getDepartureTime()+" "+interCityBus.getArrivalTime()+" "+interCityBus.getPrice()+"tenge");
            }
        }
    }
    public static void showTickets() {
        // Get list of all tickets from database
        List<Ticket> tickets = Database.getTickets();
        if(tickets.size() == 0){
            System.out.println("There are no tickets");
        }
        else {
            System.out.println("Tickets:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket.toString());
            }
        }
    }

    public static CityBus addCityBus() {
        CityBus.Builder builder = new CityBus.Builder();
        System.out.print("Bus number: ");
        builder.busNumber(scanner.nextLine());
        System.out.print("Bus model: ");
        builder.busModel(scanner.nextLine());
        System.out.print("Bus driver: ");
        builder.busDriver(scanner.nextLine());
        System.out.print("Price: ");
        builder.price(scanner.nextLine());
        System.out.print("Frequency: ");
        builder.frequency(scanner.nextLine());
        System.out.print("Loop time: ");
        builder.loopTime(scanner.nextLine());
        System.out.print("Route: ");
        builder.route(scanner.nextLine());
        return builder.build();
    }

    public static InterCityBus addInterCityBus() {
        InterCityBus.Builder builder = new InterCityBus.Builder();
        System.out.print("Bus number: ");
        builder.busNumber(scanner.nextLine());
        System.out.print("Bus model: ");
        builder.busModel(scanner.nextLine());
        System.out.print("Bus driver: ");
        builder.busDriver(scanner.nextLine());
        System.out.print("Price: ");
        builder.price(scanner.nextLine());
        System.out.print("Departure city: ");
        builder.departure(scanner.nextLine());
        System.out.print("Arrival city: ");
        builder.destination(scanner.nextLine());
        System.out.print("Departure time: ");
        builder.departureTime(scanner.nextLine());
        System.out.print("Arrival time: ");
        builder.arrivalTime(scanner.nextLine());
        return builder.build();
    }
}
