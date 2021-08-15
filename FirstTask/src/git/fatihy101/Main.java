package git.fatihy101;

import git.fatihy101.controllers.StudentContoller;
import git.fatihy101.models.Student;
import git.fatihy101.utils.EntityManagerUtils;
import git.fatihy101.utils.TestData;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(validateTestData() == 0) {
            TestData.create();
        }

        StudentContoller studentContoller = new StudentContoller();

        List<Student> returnedList = studentContoller.findAllStudents();
        for (Student customer : returnedList) {
            System.out.println(customer);
        }

    }


    private static int validateTestData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return em.createQuery("from Student", Student.class).getResultList().size();
    }

}
