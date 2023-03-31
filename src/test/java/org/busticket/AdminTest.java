package org.busticket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void getRole() {
        Admin admin = new Admin.Builder().role("Admin").build();
        assertEquals("Admin", admin.getRole());
    }

    @Test
    void setRole() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void setStatus() {
    }

    @Test
    void getQuery() {
    }
}