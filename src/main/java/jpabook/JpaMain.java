package jpabook;

import jpabook.jpashop.domain.*;

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
            Member member = new Member();
            member.setName("LAZY Test");
            em.persist(member);

            Order order = new Order();
            order.setMember(member);
            em.persist(order);

            em.flush();
            em.clear();

            Order findOrder = em.find(Order.class, order.getId());

            // 지연로딩 시, 프록시 객체
            Member findMember = findOrder.getMember();

            // 프록시 객체 초기화
            System.out.println("findOrder = " + findMember);
            System.out.println("findMember.getName() = " + findMember.getName());
            System.out.println("findOrder = " + findMember.getClass());



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