/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritanceorm2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author macintoch
 */
public class TableParttimeEmployee {
    
     public static void updateEmployee(ParttimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee pemp = em.find(ParttimeEmployee.class, emp.getId());
        pemp.setName(emp.getName());
        pemp.setHourWork(emp.getHourWork());
        em.getTransaction().begin();
        try {
            em.persist(pemp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
     
    public static void removeEmployee(ParttimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee pemp = em.find(ParttimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(pemp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
                
    }
      public static ParttimeEmployee findEmployeeById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee emp = em.find(ParttimeEmployee.class, id);
        em.close();
        return emp;
    }
}
