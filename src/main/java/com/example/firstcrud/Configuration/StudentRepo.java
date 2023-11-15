package com.example.firstcrud.Configuration;

import com.example.firstcrud.Configuration.Data.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
//    Optional<Student> findByStudentId(Long id);
    Optional<Student> findByEmail(String email);
    Optional<Student> findByPhone(String phone);
    @Query(nativeQuery = true, value = "SELECT * FROM student")
    Page<Student> findAllStudents(Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM student " +
            "WHERE ( phone LIKE %:searchValue% " +
            "OR name LIKE %:searchValue% " +
            "OR email LIKE %:searchValue%" +
            "OR gender LIKE %:searchValue%" +
            "OR status LIKE %:searchValue%)")

    Page<Student> searchStudentsByAnyCat( Pageable pageable,String searchValue);
    @Query(nativeQuery = true, value = "SELECT * FROM student WHERE id = :id")
    Page<Student> findStudeById(Pageable pageable,Long id);

}
