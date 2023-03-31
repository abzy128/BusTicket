package org.busticket;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void getConnection() throws SQLException {
        Statement statement = Database.getConnection();
        assertNotNull(statement);
    }

    @Test
    void closeConnection() throws SQLException {
        Statement statement = Database.getConnection();
        Database.closeConnection(statement);
    }

    @Test
    void loginClient() {

    }

    @Test
    void loginAdmin() {
    }

    @Test
    void registerClient() {
    }

    @Test
    void buyTicket() {
    }

    @Test
    void getTickets() {
    }

    @Test
    void getCityBuses() {
    }

    @Test
    void getInterCityBuses() {
    }

    @Test
    void showClientTickets() {
    }

    @Test
    void showClients() {
    }

    @Test
    void showTickets() {
    }

    @Test
    void showBuses() {
    }

    @Test
    void addCityBus() {
    }

    @Test
    void addInterCityBus() {
    }
}