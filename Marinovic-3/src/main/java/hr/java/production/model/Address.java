package hr.java.production.model;

/**
 * Predstavlja adresu s uličnom adresom, brojem kuće, gradom i poštanskim brojem.
 */

public class Address {

    String street;
    String houseNumber;
    String city;
    String postalCode;

    /**
     * Konstruktor za inicijalizaciju adrese sa zadanim podacima.
     *
     * @param street Ulica adrese.
     * @param houseNumber Broj kuće ili stana.
     * @param city Grad u kojem se adresa nalazi.
     * @param postalCode Poštanski broj adrese.
     */

    public Address(String street, String houseNumber, String city, String postalCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
