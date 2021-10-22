/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritanceorm2;

import inheritanceorm2.FulltimeEmployee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TableFulltimeEmployee  {
    
    public static void updateEmployee(FulltimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee femp = em.find(FulltimeEmployee.class, emp.getId());
        femp.setName(emp.getName());
        femp.setSalary(emp.getSalary());
        em.getTransaction().begin();
        try {
            em.persist(femp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
   public static FulltimeEmployee findEmployeeById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee emp = em.find(FulltimeEmployee.class, id);
        em.close();
        return emp;
    }
   
   public static void removeEmployee(FulltimeEmployee emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee femp = em.find(FulltimeEmployee.class, emp.getId());
        em.getTransaction().begin();
        try {
            em.remove(femp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
                
    }
   public static List<Employee> findAllEmployee() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        List<Employee> empList = (List<Employee>) em.createNamedQuery("FULLTIMEEMPLOYEE.findAll").getResultList();
        em.close();
        return empList;
    }
}
