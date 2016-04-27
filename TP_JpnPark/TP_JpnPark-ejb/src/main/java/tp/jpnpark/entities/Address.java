package tp.jpnpark.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Torma Péter
 */
@Embeddable
public class Address implements Serializable {

    private String zipCode;
    private String country;
    private String city;
    private String street;
    private String houseNumber;

    public Address() {
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

}
