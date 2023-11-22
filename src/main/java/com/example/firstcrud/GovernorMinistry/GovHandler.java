package com.example.firstcrud.GovernorMinistry;

import com.example.firstcrud.DTO.EntityResponse;
import com.example.firstcrud.GovernorMinistry.Models.Governor;
import com.example.firstcrud.GovernorMinistry.Service.GovernorRecon;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v5/hand")
public class GovHandler {
    @Autowired
    private GovernorRecon recon;
    @GetMapping("/reconcile")
    public ResponseEntity initiate() throws IOException {
        boolean response;
        List<Governor> list = (List<Governor>) recon.reconcile();
//        response.setEntity(list);
//        response.setMessage("reconciled");
//        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok().body(list);
    }
}
