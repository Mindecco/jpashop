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

            // Member Orders 에는 현재 add가 된 상태가 아니다. 즉, getOrders().size = 0 임.
            // SQL 이 실행되어야 Orders에 set이 될텐데 em.persist만 한 상태라 영속성 컨택스트에 있는 값이 그대로 사용 되기 때문에 em.flush(), em.clear() 필요
            em.flush();
            em.clear();

            Order findOrder = em.find(Order.class, order.getId());
            Member findMember = findOrder.getMember();
            System.out.println("findMember = " + findMember.getName());
            Member member = em.find(Member.class, findMember.getId());
            int size = member.getOrders().size();
            System.out.println("size = " + size);

            tx.commit();
        }catch (Exception e){
            tx.rollback();

        }finally {
            em.close();
        }
        emf.close();
    }
}