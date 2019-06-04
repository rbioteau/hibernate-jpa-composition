package org.bonitasoft.jpa;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JPACompositionTest {

    private EntityManagerFactory sessionFactory;

    @BeforeEach
    void setUp() throws Exception {
        sessionFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }
    
    @Test
    void fillDatabase() throws Exception {
        EntityManager entityManager = sessionFactory.createEntityManager();
       
        inTx(entityManager, () -> {
            Bike bike = new Bike();
            bike.setWheels(Arrays.asList(new Wheel(23),new Wheel(23)));
            entityManager.persist(bike);
            
            Car car = new Car();
            car.setWheels(Arrays.asList(new Wheel(54),new Wheel(54)));
            entityManager.persist(car);
            
            Monocycle mono = new Monocycle();
            mono.setSize(3);
            mono.setWheel(new Wheel(12));
            entityManager.persist(mono);
        });
        
        inTx(entityManager, () -> {
            TypedQuery<Car> allCar = entityManager.createQuery("SELECT c FROM Car c",Car.class);
            Car car =  allCar.getResultList().get(0);
            car.setWheels(Arrays.asList(new Wheel(45)));
            entityManager.persist(car);
        });
        
        entityManager.close();
    }
    
    private void inTx( EntityManager entityManager, Runnable runnable ) {
        entityManager.getTransaction().begin();
        try {
            runnable.run();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

}
