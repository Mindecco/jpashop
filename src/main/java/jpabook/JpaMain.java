package jpabook;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member memberA = new Member();
            memberA.setName("MemberA");
            //member.setId(999L);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setName("MemberB");
            em.persist(memberB);

            Item itemA = new Item();
            itemA.setName("itemA");
            em.persist(itemA);

            Order order = new Order();
         //   order.changeMember(memberA);
            em.persist(order);


            OrderItem orderItemA = new OrderItem();
            orderItemA.setOrder(order);
            orderItemA.setItem(itemA);
            em.persist(orderItemA);

            tx.commit();
        }catch (Exception e){
            tx.rollback();

        }finally {
            em.close();
        }
        emf.close();
    }
}