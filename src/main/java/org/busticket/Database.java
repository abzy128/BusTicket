package org.busticket;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.postgresql.Driver;


public class Database {

    private static String connUrl = null;
    private static String username = null;
    private static String password = null;
    private static void loadCredentials(){
        try (InputStream inputStream = new FileInputStream("src/main/resources/db.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            connUrl = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static Statement getConnection() throws SQLException {
        if(connUrl==null) {
            loadCredentials();
        }
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(connUrl, username, password).createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
    public static void closeConnection(Statement statement) throws SQLException {
        statement.getConnection().close();
    }
    public static Client loginClient(String username, String password) throws SQLException {
        Statement statement = getConnection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE username = '" + username + "' AND password = '" + password + "'");
        closeConnection(statement);
        if(resultSet.next()) {
            Client.Builder builder = new Client.Builder();
            builder.id(resultSet.getInt("id"));
            builder.username(resultSet.getString("username"));
            builder.name(resultSet.getString("name"));
            builder.surname(resultSet.getString("surname"));
            builder.email(resultSet.getString("email"));
            builder.password(resultSet.getString("password"));
            builder.phone(resultSet.getString("phone"));
            builder.address(resultSet.getString("address"));
            builder.discount(resultSet.getInt("discount"));
            builder.nationality(resultSet.getString("nationality"));
            builder.passportNumber(resultSet.getString("passportnumber"));
            Client client = new Client(builder);
            return client;
        }
        else {
            return null;
        }
    }
    public static Admin loginAdmin(String username, String password) throws SQLException {
        Statement statement = getConnection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM admins WHERE username = '" + username + "' AND password = '" + password + "'");
        closeConnection(statement);
        if(resultSet.next()) {
            Admin.Builder builder = new Admin.Builder();
            builder.id(resultSet.getInt("id"));
            builder.username(resultSet.getString("username"));
            builder.name(resultSet.getString("name"));
            builder.surname(resultSet.getString("surname"));
            builder.email(resultSet.getString("email"));
            builder.password(resultSet.getString("password"));
            Admin admin = new Admin(builder);
            return admin;
        }
        else {
            return null;
        }
    }
    public static int registerClient(Client client) throws SQLException{
        Statement statement = getConnection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE username = '" + client.getUsername() + "'");
        // Check if username is already taken
        if(resultSet.next()){
            return 1;
        }
        try {
            statement.executeUpdate("INSERT INTO clients (name, surname, username, email, password, phone, address, discount, nationality, passportnumber) VALUES ('" + client.getName() + "', '" + client.getSurname() + "', '" + client.getUsername() + "', '" + client.getEmail() + "', '" + client.getPassword() + "', '" + client.getPhone() + "', '" + client.getAddress() + "', " + client.getDiscount() + ", '" + client.getNationality() + "', '" + client.getPassportNumber() + "')");
        }catch (SQLException e){

        }
        return 0;
    }

    public static int buyTicket(Ticket ticket) throws SQLException {
        // Add ticket to database
        Statement statement = getConnection();
        try {
            statement.executeUpdate("INSERT INTO tickets (clientUsername, busNumber) VALUES ('"+ ticket.getClientUsername() + "', '" + ticket.getBusNumber() + "')");
        }catch (SQLException e){
            return 1;
        }
        return 0;
    }
    public static List<Ticket> getTickets(String username) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        Statement statement = getConnection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets WHERE clientUsername = '" + username + "'");
        while(resultSet.next()){
            Ticket.Builder builder = new Ticket.Builder();
            builder.ticketId(resultSet.getString("ticketId"));
            builder.clientUsername(resultSet.getString("clientUsername"));
            builder.busNumber(resultSet.getString("busNumber"));
            Ticket ticket = new Ticket(builder);
            tickets.add(ticket);
        }
        closeConnection(statement);
        return tickets;
    }
    public static List<CityBus> getCityBuses() {
        List<CityBus> cityBuses = new ArrayList<>();
        try {
            Statement statement = getConnection();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM citybuses");
            while (resultSet.next()) {
                CityBus.Builder builder = new CityBus.Builder();
                builder.busNumber(resultSet.getString("busNumber"));
                builder.busModel(resultSet.getString("busModel"));
                builder.busDriver(resultSet.getString("busDriver"));
                builder.price(resultSet.getString("price"));
                builder.frequency(resultSet.getString("frequency"));
                builder.loopTime(resultSet.getString("loopTime"));
                builder.route(resultSet.getString("route"));
                CityBus cityBus = new CityBus(builder);
                cityBuses.add(cityBus);
            }
            closeConnection(statement);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cityBuses;
    }
    public static List<InterCityBus> getInterCityBuses(){
        List<InterCityBus> interCityBuses = new ArrayList<>();
        try {
            Statement statement = getConnection();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM intercitybuses");
            while (resultSet.next()) {
                InterCityBus.Builder builder = new InterCityBus.Builder();
                builder.busNumber(resultSet.getString("busNumber"));
                builder.busModel(resultSet.getString("busModel"));
                builder.busDriver(resultSet.getString("busDriver"));
                builder.price(resultSet.getString("price"));
                builder.services(resultSet.getString("services"));
                builder.departure(resultSet.getString("departure"));
                builder.destination(resultSet.getString("destination"));
                builder.departureTime(resultSet.getString("departureTime"));
                builder.arrivalTime(resultSet.getString("arrivalTime"));
                InterCityBus interCityBus = new InterCityBus(builder);
                interCityBuses.add(interCityBus);
            }
            closeConnection(statement);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return interCityBuses;
    }



    public static List<Client> getClients(){
        List<Client> clients = new ArrayList<>();
        try {
        Statement statement = getConnection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
        while(resultSet.next()){
            Client.Builder builder = new Client.Builder();
            builder.username(resultSet.getString("username"));
            builder.name(resultSet.getString("name"));
            builder.surname(resultSet.getString("surname"));
            builder.email(resultSet.getString("email"));
            builder.password(resultSet.getString("password"));
            builder.phone(resultSet.getString("phone"));
            builder.address(resultSet.getString("address"));
            builder.discount(resultSet.getInt("discount"));
            builder.nationality(resultSet.getString("nationality"));
            builder.passportNumber(resultSet.getString("passportNumber"));
            Client client = new Client(builder);
            clients.add(client);
        }}catch (SQLException e){
            // e.printStackTrace();
        }
        return clients;
    }

    public static List<Ticket> getTickets() {
        // Get list of all tickets from database
        List<Ticket> tickets = new ArrayList<>();
        try {
            Statement statement = getConnection();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets");
            while(resultSet.next()){
                Ticket.Builder builder = new Ticket.Builder();
                builder.ticketId(resultSet.getString("ticketId"));
                builder.clientUsername(resultSet.getString("clientUsername"));
                builder.busNumber(resultSet.getString("busNumber"));
                Ticket ticket = new Ticket(builder);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public static int addCityBus(CityBus cityBus) throws SQLException {
        // Add city bus to database
        Statement statement = Database.getConnection();
        try {
            statement.executeQuery("INSERT INTO citybuses (busNumber, busModel, busDriver, price, frequency, loopTime, route) VALUES ('" + cityBus.getBusNumber() + "', '" + cityBus.getBusModel() + "', '" + cityBus.getBusDriver() + "', '" + cityBus.getPrice() + "', '" + cityBus.getFrequency() + "', '" + cityBus.getLoopTime() + "', '" + cityBus.getRoute() + "')");
        }catch (SQLException e) {

        }
        return 0;
    }

    public static int addInterCityBus(InterCityBus interCityBus) throws SQLException {
        // Add inter-city bus to database
        Statement statement = Database.getConnection();
        try {
            statement.executeQuery("INSERT INTO intercitybuses (busNumber, busModel, busDriver, price, services, departure, destination, departureTime, arrivalTime) VALUES ('" + interCityBus.getBusNumber() + "', '" + interCityBus.getBusModel() + "', '" + interCityBus.getBusDriver() + "', '" + interCityBus.getPrice() + "', '" + interCityBus.getServices() + "', '" + interCityBus.getDeparture() + "', '" + interCityBus.getDestination() + "', '" + interCityBus.getDepartureTime() + "', '" + interCityBus.getArrivalTime() + "')");
        }catch (Exception e){

        }
        return 0;
    }
}
