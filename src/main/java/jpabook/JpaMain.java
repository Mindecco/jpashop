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
            member.setName("프록시 TEST123");
            // 프록시 객체 생성
            em.persist(member);


            // 영속성 컨택스트에 이미 Member 가 있으면 .getReference 해도 프록시가 아닌 기존 객체를 반환한다.
            em.flush();
            em.clear();

            // 영속성 컨택스트에 없다면. 프록시 객체를 생성!
            Member refMember = em.getReference(Member.class, member.getId());

            System.out.println("refMember = " + refMember.getClass());
            // 프록시 객체에 있는 id 값반환
            System.out.println("refMember.getId() = " + refMember.getId());

            System.out.println("refMember.getName() = " + refMember.getName());

            // 프록시가 영속성 컨택스트에 Name 을 요청하고, 영속성 컨택스트가 DB에서 객체를 받아온 뒤, 프록시를 통해 그 객체에 접근한다.
            // 프록시객체가 삭제되는게 아님. 프록시에서 호출하는 개념
            // But, 프록시 객체가 이미 있다면 find 해도 프록시 객체를 반환한다. == true를 지키기 위해, 그리고 find 이기 때문에 프록시 객체를 초기화한다( 포록시 객체 필드에 객체를 set해준다. )
        //    Member findMember = em.find(Member.class, member.getId());
         //   System.out.println("findMeber = " + findMember.getClass());

            System.out.println("refMember.getId() = " + refMember.getName());
            System.out.println("member 와 refmember 비교: " + (member == refMember));
          //  System.out.println("findMeber 와 refmember 비교: " + (findMember == refMember));

            //  준영속상태로 만들어도 DB에서 값을 조회한 엔티티가 있으면
            em.clear();

            // 준영속상태로 만들어도 DB에서 값을 조회한 엔티티가 있으면 프록시 객체는 에러가 안난다.

            // 프록시객체가 초기화 되는 경우의 수
            // 1. 프록시 객체 생성 후, proxy.getXXX 할 시,(ID 등, 기존값 제외) 영속성컨텍스트에 요청 -> DB 조회 -> 진짜 객체 생성 -> 프록시 객체에 초기화
            // 2. 프록시 객체 생성 후, em.find(진짜객체) 할 시, 인스턴스는 프록시객체와 동일하지만, 내부적으로는 영속성컨택스트에 진짜 객체가 생성되어 초기화
            // 3. Hibernate.initialize(entity) // JPA표준이 아닌 Hibernate 제공 강제초기화.

            System.out.println("refMember.getName() = " + refMember.getName());

//            Member member1 = new Member();
//            member1.setName("member1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member refMember1 = em.getReference(Member.class, member1.getId());
//            System.out.println(refMember1.getClass());
//
////            em.detach(refMember);
//            em.close();
//
//            refMember1.getName();

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