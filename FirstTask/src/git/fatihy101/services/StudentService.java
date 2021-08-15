package git.fatihy101.services;

import git.fatihy101.models.Student;
import git.fatihy101.repository.CrudRepository;
import git.fatihy101.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService implements CrudRepository<Student> {
    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");


    @Override
    public List<Student> findAll() {
        return em.createQuery("select  s from Student s", Student.class).getResultList();
    }

    @Override
    public Student findById(int id) {
        return em.find(Student.class, id);
    }

    @Override
    public void saveToDatabase(Student student) {
        try{
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteFromDatabase(Student student) {
        try {
            em.getTransaction().begin();

            Student foundStudent = em.createQuery("from Student s WHERE s.id =:id", Student.class).setParameter("id", student.getId()).getSingleResult();
            em.remove(foundStudent);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteFromDatabase(int id) {
        try {
            em.getTransaction().begin();

            Student foundStudent = em.find(Student.class, id);
            em.remove(foundStudent);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void updateOnDatabase(Student object, int id) {
        try {
            em.getTransaction().begin();

            Student foundStudent = em.find(Student.class, id);
            foundStudent.setAddress(foundStudent.getAddress());
            foundStudent.setName(foundStudent.getName());
            foundStudent.setId(foundStudent.getId());
            em.merge(foundStudent);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }
}
