package application.dao;

import application.entity.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Repository
@RequiredArgsConstructor
public class StudentDao {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public Iterable<Student> findAll() {
        return Collections.unmodifiableList(sessionFactory.getCurrentSession().createQuery("from Student s").list());
    }

    @Transactional(readOnly = true)
    public Student findById(Long id){
        return (Student) sessionFactory.getCurrentSession()
                .createQuery("select s from Student s where s.id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Transactional
    public void insert(Student student) {
        sessionFactory.getCurrentSession().save(student);

    }

    @Transactional
    public void update(Student student) {
        sessionFactory.getCurrentSession()
                .createQuery("update Student set name = :name, mark = :mark  where id = :id")
                .setParameter("id", student.getId())
                .setParameter("name", student.getName())
                .setParameter("mark", student.getMark())
                .executeUpdate();
    }

    @Transactional
    public void deleteById(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete Student where id = :id")
                .setParameter("id", id)
                .executeUpdate();

    }

}
