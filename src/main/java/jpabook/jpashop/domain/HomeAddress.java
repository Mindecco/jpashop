package jpabook.jpashop.domain;


import javax.persistence.Embeddable;

@Embeddable
public class HomeAddress {

    private String city;
    private String street;
    private String zipcode;


    public HomeAddress(){

    }

    public HomeAddress(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }



    public String getStreet() {
        return street;
    }


    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
