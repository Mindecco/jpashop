package jpabook;

import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            HomeAddress address = new HomeAddress("city","street","zipcode");

            Member memberA = new Member();
            memberA.setName("member1");
            memberA.setAddress(address);

            HomeAddress newAddress = new HomeAddress("NewCity", memberA.getAddress().getStreet(), memberA.getAddress().getZipcode());
            memberA.setAddress(newAddress);

            memberA.getFavoriteFoods().add("치킨");
            memberA.getFavoriteFoods().add("족발");
            memberA.getFavoriteFoods().add("피자");

        //    memberA.getAddressHistory().add(newAddress);
            memberA.getAddressHistory().add(new AddressEntity("old1","street2","zpicode2"));

            em.persist(memberA);

            em.flush();
            em.clear();
            System.out.println("========= START ===========");
            // em.persists 에 의해 이미 id가 할당되었기 때문에 memberA.getId 가능!
            System.out.println("memeberA : "+memberA.getId());
            Member findMember = em.find(Member.class, memberA.getId());

            List<AddressEntity> addressHistroy = findMember.getAddressHistory();
            addressHistroy.forEach(list -> System.out.println("list_street = " + list.getHomeAddress().getStreet()));

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            favoriteFoods.forEach(set -> System.out.println("favoriteFood = " + set));

            favoriteFoods.remove("치킨");
            favoriteFoods.add("막국수");

            // 값타입 컬랙션의 변경은 식별자 개념이 없기때문에 JPA는 모든 데이터를 DELETE 후 최종 결과를 INSERT한다. ( 치명적인 단점! )
            // 따라서, 실무에서는 상황에 따라 값타입 컬랙션 대신에 일대다 관계(엔티티) 로 풀어내는게 더 좋은 방법이다.
            addressHistroy.remove(new AddressEntity("old1","street2","zpicode2"));
            addressHistroy.add(new AddressEntity("newCity1","street2","zpicode2"));


            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}