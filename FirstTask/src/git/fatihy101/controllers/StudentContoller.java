package git.fatihy101.controllers;

import git.fatihy101.models.Student;
import git.fatihy101.services.StudentService;

import java.util.List;

public class StudentContoller {
    StudentService service = new StudentService();


    public Student findStudent(int id) {
        return service.findById(id);
    }

    public List<Student> findAllStudents() {
        return service.findAll();
    }

    public void saveStudent(Student customer) {
        service.saveToDatabase(customer);
    }


    public void deleteStudent(int id) {
        service.deleteFromDatabase(id);
    }

    public void updateStudent(Student customer, int id) {
        service.updateOnDatabase(customer, id);
    }
}
