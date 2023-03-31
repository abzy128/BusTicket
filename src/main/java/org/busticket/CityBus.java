package org.busticket;

public class CityBus extends Bus{
    private String frequency;
    private String loopTime;
    private String route;
    public CityBus(Builder builder){
        super(builder.busNumber, builder.busModel, builder.busDriver, builder.price);
        this.frequency = builder.frequency;
        this.loopTime = builder.loopTime;
        this.route = builder.route;
    }
    public static class Builder{
        private String busNumber;
        private String busModel;
        private String busDriver;
        private String price;
        private String frequency;
        private String loopTime;
        private String route;
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
        public Builder frequency(String frequency){
            this.frequency = frequency;
            return this;
        }
        public Builder loopTime(String loopTime){
            this.loopTime = loopTime;
            return this;
        }
        public Builder route(String route){
            this.route = route;
            return this;
        }
        public CityBus build(){
            return new CityBus(this);
        }
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getLoopTime() {
        return loopTime;
    }
    public void setLoopTime(String loopTime) {
        this.loopTime = loopTime;
    }
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    @Override
    public String toString(){
        return getBusNumber()+" "+getBusModel()+" "+getBusDriver()+" "+getPrice()+" "+getFrequency()+" "+getLoopTime()+" "+getRoute();
    }
}
