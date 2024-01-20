package jpabook;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

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

            Order order = new Order();
            order.setMember(memberA);
            em.persist(order);

            Order findOrder = em.find(Order.class, order.getId());
            Member findMember = findOrder.getMember();
            System.out.println("findMember = " + findMember.getName());
            findOrder.setMember(memberB);

            tx.commit();
        }catch (Exception e){
            tx.rollback();

        }finally {
            em.close();
        }
        emf.close();
    }
}