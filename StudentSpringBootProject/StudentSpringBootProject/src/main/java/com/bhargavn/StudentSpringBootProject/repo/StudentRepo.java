package com.bhargavn.StudentSpringBootProject.repo;

import com.bhargavn.StudentSpringBootProject.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepo {

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private JdbcTemplate jdbc;

    public void save(Student s) {
        String sql = "Insert into student (id, name, dept) values (?,?,?)";

        int rowsAffected = jdbc.update(sql, s.getId(), s.getName(), s.getDept());
        System.out.println(rowsAffected+" row affected");

    }

    public List<Student> findAll() {
        String sql = "Select * From Student";
        List<Student> students = jdbc.query(sql,(rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setDept(rs.getString("dept"));
            return s;
        });
        System.out.println(students.size()+" rows returned");
        return students;
    }
}
