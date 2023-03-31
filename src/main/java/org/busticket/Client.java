package org.busticket;

public class Client extends User implements IQuery {
    private String phone;
    private String address;
    private int discount;
    private String nationality;
    private String passportNumber;
    public Client(Builder builder){
        super(builder.id, builder.name, builder.surname, builder.username, builder.email, builder.password);
        this.phone = builder.phone;
        this.address = builder.address;
        this.discount = builder.discount;
        this.nationality = builder.nationality;
        this.passportNumber = builder.passportNumber;
    }

    public static class Builder{
        private int id;
        private String name;
        private String surname;
        private String username;
        private String email;
        private String password;
        private String phone;
        private String address;
        private int discount;
        private String nationality;
        private String passportNumber;
        public Builder(){}
        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }
        public Builder username(String username){
            this.username = username;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder address(String address){
            this.address = address;
            return this;
        }
        public Builder discount(int discount){
            this.discount = discount;
            return this;
        }
        public Builder nationality(String nationality){
            this.nationality = nationality;
            return this;
        }
        public Builder passportNumber(String passportNumber){
            this.passportNumber = passportNumber;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public String getNationality(){
        return nationality;
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    }
    public String getPassportNumber(){
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber){
        this.passportNumber = passportNumber;
    }
    public String getQuery(){
        return "INSERT INTO clients (name, surname, username, email, password, phone, address, discount, nationality, passportNumber) VALUES ('"+this.getName()+"', '"+this.getSurname()+"', '"+this.getUsername()+"', '"+this.getEmail()+"', '"+this.getPassword()+"', '"+this.getPhone()+"', '"+this.getAddress()+"', '"+this.getDiscount()+"', '"+this.getNationality()+"', '"+this.getPassportNumber()+"') WHERE NOT EXISTS (SELECT * FROM clients WHERE username = '"+this.getUsername()+"')";
    }
    @Override
    public String toString(){
        return "Client: "+this.getName()+" "+this.getSurname()+" "+this.getUsername()+" "+this.getEmail()+" "+this.getPassword()+" "+this.getPhone()+" "+this.getAddress()+" "+this.getDiscount()+" "+this.getNationality()+" "+this.getPassportNumber();
    }
}
