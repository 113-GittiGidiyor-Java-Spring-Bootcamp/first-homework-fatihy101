package git.fatihy101.utils;

import git.fatihy101.models.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class TestData {
    public static void create() {
        Student s1 = new Student("Mehmet", "Fatih/İstanbul", "", LocalDate.of(1999, Month.FEBRUARY,5));
        Student s2 = new Student("Ali", "Esenler/İstanbul", "", LocalDate.of(1995, Month.JANUARY,30));
        Student s3 = new Student("Ayşe", "Bebek/İstanbul", "", LocalDate.of(1996, Month.APRIL,20));

        Course c1 = new Course("Math", "M101", 4);
        Course c2 = new Course("Object Oriented Programming", "OOP102", 6);
        Course c3 = new Course("Differential Equations", "DIF001", 5);

        Instructor i1 = new PermanentInstructor("Ercan", "Beşiktaş/İstanbul", "051223432", 6000);
        Instructor i2 = new VisitingResearcher("Ahmet", "Beşiktaş/İstanbul", "0512239872", 25);

        List<Object> objects = List.of(s1, s2, s3, c1, c2, c3, i1, i2);
        i1.setCourseList(List.of(c1, c2));
        i2.setCourseList(List.of(c3));

        /*
         c1.setInstructor(i1);
         c2.setInstructor(i1);
         c3.setInstructor(i2);
        */

        c1.setStudentList(List.of(s1, s2));
        c2.setStudentList(List.of(s1, s3));
        c3.setStudentList(List.of(s2, s3));

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            em.getTransaction().begin();

            for(Object object : objects) {
                em.persist(object);
            }

            em.getTransaction().commit();

            System.out.println("All data persisted...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }

    }
}
