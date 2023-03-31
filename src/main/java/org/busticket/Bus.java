package org.busticket;

public abstract class Bus implements Comparable<Bus> {
    private String busNumber;
    private String busModel;
    private String busDriver;
    private String price;
    public Bus(String busNumber, String busModel, String busDriver, String price) {
        this.busNumber = busNumber;
        this.busModel = busModel;
        this.busDriver = busDriver;
        this.price = price;
    }
    protected String getBusNumber() {
        return busNumber;
    }
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
    public String getBusModel() {
        return busModel;
    }
    public void setBusModel(String busModel) {
        this.busModel = busModel;
    }
    public String getBusDriver() {
        return busDriver;
    }
    public void setBusDriver(String busDriver) {
        this.busDriver = busDriver;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String  price) {
        this.price = price;
    }

    @Override
    public int compareTo(Bus o) {
        return this.busNumber.compareTo(o.busNumber);
    }
}
