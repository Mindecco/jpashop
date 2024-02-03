package jpabook.jpashop.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;


    @Embedded
    private HomeAddress address;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
            @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();
//
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns =
//            @JoinColumn(name = "MEMBER_ID"))
//    private List<HomeAddress> addressHistory = new ArrayList<>();


    // cascade : 영속성 전파로, 부모의 생명주기와 동일하게 자식의 생명주기를 맞추는 것.( 즉. ALL 인 경우 부모 persist만으로 자식도 자동으로
    // persist 할 수 있다.
    // orphanRemoval : 자식 앤티티가 제거될 때, DB에서 자동으로 삭제해주는 설정.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    /*
        @Embedded
        @AttributeOverrides(
                {
                        @AttributeOverride(name="city",column = @Column(name = "WORK_CITY")),
                        @AttributeOverride(name="street",column = @Column(name = "WORK_STREET")),
                        @AttributeOverride(name="zipcode",column = @Column(name = "WORK_ZIPCODE"))
                }
        )
    */
    public HomeAddress getAddress() {
        return address;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
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
