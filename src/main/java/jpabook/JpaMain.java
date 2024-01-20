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
            Member member = new Member();

            // 객체지향 스럽지 않다..
            Order order = em.find(Order.class, 100L); // DB에서 order 정보를 찾고
            Long memberId = order.getMemberId();  // 주문을 통해 memberId를 받아오고
            Member findMember = em.find(Member.class, memberId); // 다시 memberId로 DB 조회..? 객체지향 스럽지 않다...

        }catch (Exception e){

        }


    }
}