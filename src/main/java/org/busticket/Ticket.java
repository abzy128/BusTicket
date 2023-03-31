package org.busticket;

class Ticket implements Comparable<Ticket>{
    private String ticketId;
    private String clientUsername;
    private String busNumber;

    public Ticket(String ticketId, String clientUsername, String busNumber) {
        this.ticketId = ticketId;
        this.clientUsername = clientUsername;
        this.busNumber = busNumber;
    }

    public Ticket() {

    }
    public Ticket(Builder builder){
        this.ticketId = builder.ticketId;
        this.clientUsername = builder.clientUsername;
        this.busNumber = builder.busNumber;
    }
    public static class Builder{
        private String ticketId;
        private String clientUsername;
        private String busNumber;
        public Builder(){}
        public Builder ticketId(String ticketid){
            this.ticketId = ticketid;
            return this;
        }
        public Builder clientUsername(String clientUsername){
            this.clientUsername = clientUsername;
            return this;
        }
        public Builder busNumber(String busNumber){
            this.busNumber = busNumber;
            return this;
        }
        public Ticket build(){
            return new Ticket(this);
        }
    }
    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    public String getClientUsername() {
        return clientUsername;
    }
    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
    public String getBusNumber() {
        return busNumber;
    }
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    @Override
    public int compareTo(Ticket o) {
        return this.ticketId.compareTo(o.ticketId);
    }
    @Override
    public String toString() {
        return "Ticket [ticketId=" + ticketId + ", clientUsername=" + clientUsername + ", busNumber=" + busNumber + "]";
    }
}
