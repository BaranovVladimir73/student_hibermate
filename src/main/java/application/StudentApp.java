package application;


import application.config.HibernateConfig;
import application.dao.StudentDao;
import application.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);

        for (Student student: studentDao.findAll()) {
            System.out.println(student);
        }

        System.out.println(studentDao.findById(1L));

        //Student student = Student.builder()
        //        .id(1L)
        //        .name("Ivanov Sergey")
        //        .mark(4)
        //        .build();

        //studentDao.update(student);
        //studentDao.deleteById(2L);

        for (int i = 0; i < 1000; i++) {
            String nameStudent = String.format("Студент%s", i);
            int min = 1;
            int max = 5;
            int mark = (int) (Math.random()*((max-min)+1))+min;
            Student student = Student.builder()
                    .name(nameStudent)
                    .mark(mark)
                    .build();
            studentDao.insert(student);
        }

    }
}
