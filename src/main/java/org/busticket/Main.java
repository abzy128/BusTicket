package org.busticket;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int input = Menu.mainMenu();
        int userType = 0;
        Client client = null;
        Admin admin = null;
        switch (input){
            case 1->{
                Map<String, String> loginData = Menu.loginMenu();
                try {
                    client = Database.loginClient(loginData.get("username"), loginData.get("password"));
                    userType = 0;
                    if(client == null){
                        userType = -1;
                        System.out.println("Wrong username or password");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            case 2->{
                Map<String, String> loginData = Menu.loginMenu();
                try {
                    admin = Database.loginAdmin(loginData.get("username"), loginData.get("password"));
                    userType = 1;
                    if(admin == null){
                        userType = -1;
                        System.out.println("Wrong username or password");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            case 3->{
                client = Menu.registerClient();
                try {
                    int val = Database.registerClient(client);
                    switch (val){
                        case 0->{
                            System.out.println("registered successfully");
                        }
                        case 1->{
                            System.out.println("already exists");
                        }
                        case 2->{
                            System.out.println("Error");
                        }
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            case 4->{
                System.out.println("Thank you for using our system");
                System.exit(0);
            }
        }
        switch (userType){
            case -1->{
                System.out.println("Failed to login");
                System.out.println("Thank you for using our system");
            }
            case 0->{
                System.out.println("Welcome, Client");
                int clientInput = Menu.clientMenu();
                switch (clientInput){
                    case 1->{
                        System.out.println("Buy Ticket");
                        Ticket ticket = Menu.buyTicket();
                        if(ticket!=null) {
                            ticket.setClientUsername(client.getUsername());
                            try {
                                int val = Database.buyTicket(ticket);
                                switch (val) {
                                    case 0 -> {
                                        System.out.println("Ticket bought successfully");
                                    }
                                    case 1 -> {
                                        System.out.println("Error");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    case 2->{
                        System.out.println("Show Tickets");
                        try {
                            Menu.showClientTickets(client);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3->{
                        // Show buses
                        System.out.println("Show Buses");
                        try {
                            Menu.showBuses();
                        }catch (Exception e){

                        }
                    }
                    case 4->{
                        // Show information about client
                        System.out.println("Show Information");
                        System.out.println("ID: " + client.getId());
                        System.out.println("Name: " + client.getName());
                        System.out.println("Surname: " + client.getSurname());
                        System.out.println("Username: " + client.getUsername());
                        System.out.println("Password: " + client.getPassword());
                        System.out.println("Phone Number: " + client.getPhone());
                        System.out.println("Email: " + client.getEmail());
                        System.out.println("Address: " + client.getAddress());
                        System.out.println("Nationality: " + client.getNationality());
                        System.out.println("Passport Number: " + client.getPassportNumber());
                    }
                    case 5->{
                        System.out.println("Thank you for using our system");
                    }
                }
            }
            case 1->{
                System.out.println("Welcome, Admin");
                int adminInput = Menu.adminMenu();
                switch (adminInput){
                    case 1->{
                        System.out.println("Add Bus");
                        int busInput = Menu.busTypeMenu();
                        switch (busInput){
                            case 1->{
                                System.out.println("Add City Bus");
                                CityBus cityBus = Menu.addCityBus();
                                try {
                                    int val = Database.addCityBus(cityBus);
                                    switch (val){
                                        case 0->{
                                            System.out.println("City Bus added successfully");
                                        }
                                        case 1->{
                                            System.out.println("Error");
                                        }
                                    }
                            } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            case 2->{
                                System.out.println("Add Inter City Bus");
                                InterCityBus interCityBus = Menu.addInterCityBus();
                                try {
                                    int val = Database.addInterCityBus(interCityBus);
                                    switch (val){
                                        case 0->{
                                            System.out.println("Inter City Bus added successfully");
                                        }
                                        case 1->{
                                            System.out.println("Error");
                                        }
                                    }
                                }catch (Exception e){
                                    System.out.println(e.getMessage());
                                }
                            }
                        }

                    }
                    case 2->{
                        System.out.println("Show Buses");
                        try {
                            Menu.showBuses();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3->{
                        System.out.println("Show Tickets");
                        try {
                            Menu.showTickets();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4->{
                        System.out.println("Show Clients");
                        try {
                            Menu.showClients();
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5->{
                        System.out.println("Thank you for using our system");
                    }
                }
            }
        }
    }
}