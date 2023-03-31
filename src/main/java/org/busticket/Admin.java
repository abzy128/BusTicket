package org.busticket;

public class Admin extends User implements IQuery {
    private String role;
    private String status;
    public Admin(Builder builder){
        super(builder.id, builder.name, builder.surname, builder.username, builder.email, builder.password);
        this.role = builder.role;
        this.status = builder.status;
    }
    public static class Builder{
        private int id;
        private String name;
        private String surname;
        private String username;
        private String email;
        private String password;
        private String role;
        private String status;
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
        public Builder role(String role){
            this.role = role;
            return this;
        }
        public Builder status(String status){
            this.status = status;
            return this;
        }
        public Admin build(){
            return new Admin(this);
        }
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getQuery(){
        return "INSERT INTO admins (name, surname, username, email, password, role, status) VALUES ("+getName()+","+getSurname()+","+getUsername()+","+getEmail()+","+getPassword()+","+getRole()+","+getStatus()+") WHERE NOT EXISTS (SELECT * FROM admins WHERE username = "+getUsername()+")";
    }
}
