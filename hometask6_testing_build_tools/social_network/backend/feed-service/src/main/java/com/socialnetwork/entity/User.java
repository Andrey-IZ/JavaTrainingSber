package com.socialnetwork.entity;

import com.socialnetwork.entity.interfaces.IUser;

import java.util.Objects;

public class User implements IUser {
    private int userid;
    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String gender;
    private String country;
    private String city;
    private String password;
    private String role;

    public User(String firstname, String lastname, String email,
                int age, String gender, String country, String city,
                String password, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.password = password;
        this.role = "USER";
    }

    @Override
    public int getId() {
        return userid;
    }

    @Override
    public void setId(int userid) {
        this.userid = userid;
    }

    @Override
    public String getFirstName() {
        return firstname;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getLastName() {
        return lastname;
    }

    @Override
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userid != user.userid) return false;
        if (age != user.age) return false;
        if (!Objects.equals(firstname, user.firstname)) return false;
        if (!Objects.equals(lastname, user.lastname)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(gender, user.gender)) return false;
        if (!Objects.equals(country, user.country)) return false;
        if (!Objects.equals(city, user.city)) return false;
        if (!Objects.equals(password, user.password)) return false;
        return Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}


