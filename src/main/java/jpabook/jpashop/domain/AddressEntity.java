package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


// 값 타임 컬랙션 -> 엔티티 객체로 승격
@Entity
@Table(name="ADDRESS")
public class AddressEntity {


    @Id @GeneratedValue
    Long id;

    private HomeAddress homeAddress;

    public AddressEntity() {
    }

    public AddressEntity(String city, String street, String zpicode) {
        this.homeAddress = new HomeAddress(city,street,zpicode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }
}
