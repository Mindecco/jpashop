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

            Movie movie = new Movie();
            movie.setActor("민장식");
            movie.setDirector("나은경");
            movie.setName("신세계2");
            em.persist(movie);

            tx.commit();
        }catch (Exception e){
            tx.rollback();

        }finally {
            em.close();
        }
        emf.close();
    }
}