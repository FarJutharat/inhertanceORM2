/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritanceorm2;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author macintoch
 */
public class InheritanceORM2 {
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int select = 0;
        System.out.println("Select Menu\n 1 insert employee\n 2 edit data\n 3 delete employee\n 4 exit");
        select = sc.nextInt();
        if(select == 1){ 
            System.out.println("Select insert employee\n 1 Fulltime\n 2 Parttime\n");
            int select1 = sc.nextInt();
            if(select1 == 1){ insertFulltime();}
            else{insertParttime();}
             }
        else if (select == 2){
            System.out.println("Select edit employee\n 1 Fulltime\n 2 Parttime\n");
            int select1 = sc.nextInt();
            if(select1 == 1){updateFulltime();}
            else{updateParttime();}}
        else if (select == 3){
        System.out.println("Select delete employee\n 1 Fulltime\n 2 Parttime\n");
            int select1 = sc.nextInt();
            if(select1 == 1){deleteFulltime();}
            else{deleteParttime();}
        }
        else{ return; }
        
//        FulltimeEmployee emp1 = new FulltimeEmployee();
//        emp1.setName("Bob");
//        emp1.setSalary(4000);
//        persist(emp1);
//        ParttimeEmployee emp2 = new ParttimeEmployee();
//        emp2.setName("Jane");
//        emp2.setHourWork(4);
//        persist(emp2);
    
    }
    
    public static void insertFulltime(){
        System.out.print("Enter id : ");
        long id = sc.nextLong();
        System.out.print("Enter name : ");
        String name = sc.next();
        System.out.print("Enter salary : ");
        int salary = sc.nextInt();
        FulltimeEmployee emp1 = new FulltimeEmployee();
        emp1.setId(id);
        emp1.setName(name);
        emp1.setSalary(salary);
        persist(emp1);
    }
    
    public static void insertParttime(){
        System.out.print("Enter id : ");
        long id = sc.nextLong();
        System.out.print("Enter name : ");
        String name = sc.next();
        System.out.print("Enter workHour : ");
        int hr = sc.nextInt();
        ParttimeEmployee emp1 = new ParttimeEmployee();
        emp1.setId(id);
        emp1.setName(name);
        emp1.setHourWork(hr);
        persist(emp1);
    }
    
     public static void updateFulltime(){
        System.out.print("Enter id : ");
        long id = sc.nextLong();
        FulltimeEmployee emp1 = TableFulltimeEmployee.findEmployeeById(id);
        if(emp1 == null){System.out.println("Not found this id"); return;}
        System.out.println("Select 1 edit name\n 2 edit salary");
        int select = sc.nextInt();
        
        if(select == 1){
            System.out.print("Enter name : ");
            String name = sc.next();
            emp1.setName(name);
            TableFulltimeEmployee.updateEmployee(emp1);
        }else{
            System.out.print("Enter salary : ");
            int salary = sc.nextInt();
            emp1.setSalary(salary);
            TableFulltimeEmployee.updateEmployee(emp1);
        }
        
    }
    
     public static void updateParttime(){
        System.out.print("Enter id : ");
        long id = sc.nextLong();
        ParttimeEmployee emp1 = TableParttimeEmployee.findEmployeeById(id);
        if(emp1 == null){System.out.println("Not found this id"); return;}
        System.out.println("Select 1 edit name\n 2 edit hourWork");
        int select = sc.nextInt();
        
        if(select == 1){
            System.out.print("Enter name : ");
            String name = sc.next();
            emp1.setName(name);
            TableParttimeEmployee.updateEmployee(emp1);
        }else{
            System.out.print("Enter hourWork : ");
            int hr = sc.nextInt();
            emp1.setHourWork(hr);
            TableParttimeEmployee.updateEmployee(emp1);
        }
        
    }
     
    public static void deleteFulltime(){
        System.out.print("Enter id : ");
        long id = sc.nextLong();
        FulltimeEmployee emp1 = TableFulltimeEmployee.findEmployeeById(id);
        if(emp1 == null){System.out.println("Not found this id"); return;}
        TableFulltimeEmployee.removeEmployee(emp1);
    }
    
    public static void deleteParttime(){
        System.out.print("Enter id : ");
        long id = sc.nextLong();
        ParttimeEmployee emp1 = TableParttimeEmployee.findEmployeeById(id);
        if(emp1 == null){System.out.println("Not found this id"); return;}
        TableParttimeEmployee.removeEmployee(emp1);
    }
       
    public static void printAllEmployee(List<AbstractEmployee> employeeList) {
        for(AbstractEmployee emp : employeeList) {
           System.out.print(emp.getId() + " ");
           System.out.print(emp.getName() + " ");
       }
    }


    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InheritanceORM2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }    
 
}
