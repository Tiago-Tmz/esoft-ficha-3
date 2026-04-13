package pt.ipleiria.estg.ei.dei.esoft;

import  java.util.*;


public class Contact {

    private String firstName;
    private String lastName;
    private Date birthday;
    private String phone;
    private String email;

    public Contact(String firstName, String phone) {
        this.firstName = firstName;
        this.phone = phone;
    }
    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    public Contact (String firstName, String lastName,String phone, String email, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        var another = (Contact) o;

        if (!Objects.equals(this.firstName, another.firstName)) return false;
        if (!Objects.equals(this.lastName, another.lastName)) return false;
        if (!Objects.equals(this.birthday, another.birthday)) return false;
        if (!Objects.equals(this.phone, another.phone)) return false;
        if (!Objects.equals(this.email, another.email)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, phone, email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


