package org.busticket;

public class InterCityBus extends Bus{
    private String services;
    private String departure;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    public InterCityBus(Builder builder){
        super(builder.busNumber, builder.busModel, builder.busDriver, builder.price);
        this.departure = builder.departure;
        this.destination = builder.destination;
        this.departureTime = builder.departureTime;
        this.arrivalTime = builder.arrivalTime;;
        this.services = builder.services;
    }
    public static class Builder{
        private String busNumber;
        private String busModel;
        private String busDriver;
        private String price;
        private String departure;
        private String destination;
        private String departureTime;
        private String arrivalTime;
        private String services;
        public Builder(){}
        public Builder busNumber(String busNumber){
            this.busNumber = busNumber;
            return this;
        }
        public Builder busModel(String busModel){
            this.busModel = busModel;
            return this;
        }
        public Builder busDriver(String busDriver){
            this.busDriver = busDriver;
            return this;
        }
        public Builder price(String price){
            this.price = price;
            return this;
        }
        public Builder departure(String departure){
            this.departure = departure;
            return this;
        }
        public Builder destination(String destination){
            this.destination = destination;
            return this;
        }
        public Builder departureTime(String departureTime){
            this.departureTime = departureTime;
            return this;
        }
        public Builder arrivalTime(String arrivalTime){
            this.arrivalTime = arrivalTime;
            return this;
        }
        public Builder services(String services){
            this.services = services;
            return this;
        }
        public InterCityBus build(){
            return new InterCityBus(this);
        }
    }
    public String getServices() {
        return services;
    }
    public void setServices(String services) {
        this.services = services;
    }
    public String getDeparture() {
        return departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    @Override
    public String toString(){
        return getBusNumber()+" "+getBusModel()+" "+getBusDriver()+" "+getPrice()+" "+getDeparture()+" "+getDestination()+" "+getDepartureTime()+" "+getArrivalTime()+" "+getServices();
    }
}
