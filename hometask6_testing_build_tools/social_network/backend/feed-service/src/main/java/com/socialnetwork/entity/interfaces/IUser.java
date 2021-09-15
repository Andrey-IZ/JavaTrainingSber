package com.socialnetwork.entity.interfaces;

public interface IUser {
    int getId();

    void setId(int userid);

    String getFirstName();

    int getAge();

    void setAge(int age);

    String getGender();

    void setGender(String gender);

    void setFirstName(String firstname);

    String getLastName();

    void setLastName(String lastname);

    String getEmail();

    void setEmail(String email);

    String getCountry();

    void setCountry(String country);

    String getCity();

    void setCity(String city);

    String getPassword();

    String getRole();

    void setRole(String role);

    void setPassword(String password);
}
