package com.example.firstcrud.Configuration;

import com.example.firstcrud.Configuration.Data.Student;
import com.example.firstcrud.Configuration.Requests.CreateStudent;
import com.example.firstcrud.Configuration.Requests.UpdateStatus;
import com.example.firstcrud.DTO.EntityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/student")
public record StudentController(StudentRepo studentRepo, StudentService service) {
    @PostMapping("/add")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) throws Exception {
        return new ResponseEntity<>(service.addStudent(student), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getStudents(),HttpStatus.FOUND);
    }
    @GetMapping("/get/paginated")
    public ResponseEntity<?> findPaginated(@RequestParam int page, @RequestParam int size){
        EntityResponse response = new EntityResponse<>();
        Map<String,Object> datas = service.findPaginated(page,size);
        response.setEntity(datas);
        response.setStatusCode(HttpStatus.FOUND.value());
        response.setMessage(HttpStatus.FOUND.getReasonPhrase());
        return ResponseEntity.ok().body(response);
    }
//todo this two methods perfom same job
    @GetMapping("/get/paginated/entity")
    public ResponseEntity<?> findPaginatedResponse(@RequestParam("page") int page, @RequestParam int size){
        Map<String,Object> response = service.findPaginated(page,size);
        return ResponseEntity.ok(new resp(HttpStatus.FOUND.getReasonPhrase(),HttpStatus.FOUND.value(),response));
    }
    record resp(String message, Integer statusCode, Map<String, Object> response) {
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("searchValue") String searchValue){
        Map<String,Object> response = service.searchStudent(page,size,searchValue);
        return ResponseEntity.ok(new resp("Search value found",HttpStatus.FOUND.value(),response));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Student student){
        return new ResponseEntity<>(service.updateStudent(student), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    @PutMapping("/update/status")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatus updateStatus){
        return new ResponseEntity<>(service.updateStudentStatus(updateStatus), HttpStatus.ACCEPTED);
    }
    @GetMapping("/find/byId")
    public ResponseEntity<?> findById(@RequestParam int page,@RequestParam int size, @RequestParam Long id){
        Map<String,Object> response = service.findStudentById(page,size,id);
        return ResponseEntity.ok( new resp("Value found",HttpStatus.FOUND.value(),response));
    }
}
