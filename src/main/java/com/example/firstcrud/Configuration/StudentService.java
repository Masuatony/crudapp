package com.example.firstcrud.Configuration;

import com.example.firstcrud.Configuration.Constants.ConfigFlags;
import com.example.firstcrud.Configuration.Data.Student;
import com.example.firstcrud.Configuration.Requests.UpdateStatus;
import com.example.firstcrud.DTO.EntityResponse;
import lombok.extern.slf4j.Slf4j;
//import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public EntityResponse addStudent(Student student) throws Exception{
        try{
            Optional<Student> optional = studentRepo.findByEmail(student.getEmail());
            Optional<Student> optional1 = studentRepo.findByPhone(student.getPhone());
            if(optional1.isPresent()){
                log.info("----------phone number taken");
                String Message = "Phone number taken";
                return EntityResponse.builder()
                        .message(Message)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build();
            }else if(optional.isPresent()){
                log.info("--------duplicate found");
                String Message = "Duplicate email not allowed ";
               return EntityResponse.builder()
                        .message(Message)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build();
            } else if (optional.isEmpty() || optional == null && optional1.isEmpty() && optional1 == null){
                student.setCreatedOn(new Date());
                student.setCreationFlag(ConfigFlags.Y);
                student.setModifiedFlag(ConfigFlags.N);
//                student.setUpdatedOn();
                student.setStatus("Inactive");
//                student.setCreationFlag();
                log.info("-----------saving student");
                studentRepo.save(student);
                log.info("--------saved");

            }
            return EntityResponse.builder()
                    .message("Created Successfully!")
                    .statusCode(HttpStatus.CONTINUE.value())
                    .entity(student)
                    .build();

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public EntityResponse getStudents(){
        try {
            EntityResponse response = new EntityResponse<>();
            List<Student> list = studentRepo.findAll();
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setMessage(HttpStatus.FOUND.getReasonPhrase());
            response.setEntity(list);
            return response;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public Map<String,Object> findPaginated(int page,int size){
        try {
            Pageable pageable = PageRequest.of(page,size);
            Page<Student> sortedStudents = studentRepo.findAllStudents(pageable);
            List<Student> data = sortedStudents.getContent();
            Map<String,Object> response = new HashMap<>();
            response.put("data",data);
            response.put("currentPage", sortedStudents.getNumber());
            response.put("totalItems", sortedStudents.getTotalElements());
            response.put("totalPages", sortedStudents.getTotalPages());
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    public Map<String,Object> searchStudent(int page, int size, String searchValue){
        try{
            Pageable pageable = PageRequest.of(page,size);
            Page<Student> searchResults = studentRepo.searchStudentsByAnyCat(pageable,searchValue);
            List<Student> data = new ArrayList<>();
            Map<String,Object> response = new HashMap<>();
            data = searchResults.getContent();
            response.put("data",data);
            response.put("currentPage", searchResults.getNumber());
            response.put("totalItems", searchResults.getTotalElements());
            response.put("totalPages", searchResults.getTotalPages());
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    public EntityResponse updateStudent(Student student){
        try {
            Optional<Student> findId = studentRepo.findById(student.getId());
            if(findId.isPresent()){
                student.setUpdatedOn(new Date());
                student.setModifiedFlag(ConfigFlags.Y);
                studentRepo.save(student);
            }
            return EntityResponse.builder()
                    .message("Student Updated with id: " +student.getId()+" Successfully")
                    .statusCode(HttpStatus.OK.value())
                    .entity(student)
                    .build();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public EntityResponse updateStudentStatus(UpdateStatus status){
        try {
            Long foundId = status.getId();
            Student student = new Student();
            Optional<Student> findById = studentRepo.findById(status.getId());
            if(findById.isPresent()){
                student.setStatus(status.getStatus());
                student.setModifiedFlag(ConfigFlags.Y);
                student.setUpdatedOn(new Date());
//                log.info();
            }
            return EntityResponse.builder()
                    .message("Status Updated Successfuly")
                    .statusCode(HttpStatus.ACCEPTED.value())
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Map<String,Object> findStudentById(int page, int size, Long id){
        try {
            Optional<Student> optional = studentRepo.findById(id);
            Map<String,Object> response = new HashMap<>();
            if(optional.isPresent()){
                Pageable pageable = PageRequest.of(page,size);
                Page<Student> dbData = studentRepo.findStudeById(pageable,id);
                List<Student> sortedStude = dbData.getContent();
                response.put("data",sortedStude);
                response.put("totalItems", dbData.getTotalElements());
                response.put("currentPage", dbData.getNumber());
                response.put("totalPages", dbData.getTotalPages());
                log.info("-------finding by id------",optional.get().getId());
//                return response;
            }
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }

    }

}
