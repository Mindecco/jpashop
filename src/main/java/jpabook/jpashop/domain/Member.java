package jpabook.jpashop.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;


    @Embedded
    private HomeAddress address;


    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name="city",column = @Column(name = "WORK_CITY")),
                    @AttributeOverride(name="street",column = @Column(name = "WORK_STREET")),
                    @AttributeOverride(name="zipcode",column = @Column(name = "WORK_ZIPCODE"))
            }
    )
    private HomeAddress address2;

    public HomeAddress getAddress2() {
        return address2;
    }

    public void setAddress2(HomeAddress address2) {
        this.address2 = address2;
    }

    public HomeAddress getAddress() {
        return address;
    }

    public void setAddress(HomeAddress address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<Order>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
