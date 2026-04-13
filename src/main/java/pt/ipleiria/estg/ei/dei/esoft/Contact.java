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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        // A ficha diz que são duplicados se tiverem o mesmo telefone OU o mesmo email
        return Objects.equals(this.phone, contact.phone) ||
                (this.email != null && Objects.equals(this.email, contact.email));
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
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


