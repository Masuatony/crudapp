package com.example.firstcrud.GL;

import com.example.firstcrud.DTO.EntityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v2/gl-maintenace")
public class GlHandler {
    @Autowired
    private  final GlRepo glRepo;
//    public GlHandler( GlRepo glRepo){
////        this.resource = resource;
//        this.glRepo = glRepo;
//    }
//    @PostMapping("/add")
//    Gl addGl(@RequestBody Gl requests){
//        return glRepo.save(requests);
//    }

//    @PostMapping("/addList")
//   public List<Gl> addMany(@RequestBody List<Gl> gls){
//        return service.
//    }

    @PostMapping("/add")
    public ResponseEntity<?> addMany(@RequestBody Gl adds){
        try{
            EntityResponse response = new EntityResponse<>();
            Optional<Gl> optional = glRepo.findByGeneralLedger(adds.getGeneralLedger());
            Optional<Gl> optional1 = glRepo.findByAccountNumber(adds.getAccountNumber());
            if(optional1.isPresent() || optional.isPresent()){
                response.setMessage("Gl already mapped" + adds.getAccountNumber());
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            } else {
//                Gl gls = glRepo.save(adds);
//                adds.setActive(false);
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Gl saved successfuly");
                glRepo.save(adds);
                response.setEntity(adds);
            }
            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @PostMapping("/addList")
    public ResponseEntity<?> addList(@RequestBody List<Gl> gls){
        try {
            EntityResponse response = new EntityResponse<>();
            List<Gl> glList = glRepo.saveAll(gls);
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setMessage(HttpStatus.CREATED.getReasonPhrase());
            response.setEntity(glList);
//            Optional<Gl> optional = glRepo.findByGeneralLedger(gls.g)
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            EntityResponse<Gl> response = new EntityResponse<>();
            Optional<Gl> optional = glRepo.findById(id);
            if(!optional.isPresent()){
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
            } else {
               glRepo.deleteById(id);
               response.setStatusCode(HttpStatus.OK.value());
               response.setMessage("Deleted successfully"+optional);
//               response.setEntity();
            }
            return ResponseEntity.ok().body(response);
        }
        catch (Exception e){
            log.info("---------deleting-----", e.getMessage());
            e.printStackTrace();
            return null;
//            log.info("-----------failing to delete");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try{
            EntityResponse response = new EntityResponse<>();
            List<Gl> data = glRepo.getAllData();
            if(!data.isEmpty()){
                response.setMessage("fetching data");
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(data);
//                response.setEntity(data);
            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Fetching data");
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getKawaida")
    public ResponseEntity<?> getData(){
        try{
            EntityResponse response = new EntityResponse<>();
            List<Gl> values = glRepo.findAll();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setEntity(values);
           return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
//
//    @GetMapping("/getted")
//    public

//    @DeleteMapping("/deleteByReqBody")
//    public ResponseEntity<?> deleteByReqBody(@RequestBody Gl gl){
//        try{
//            EntityResponse<Gl> response = new EntityResponse<>();
//            Gl gl1 = glRepo.findById(gl.getId()).orElse(null);
////            if()
//
//                glRepo.deleteById(gl1.getId());
//
//            return ResponseEntity.ok().body(gl1);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    public
    @GetMapping("/get/byCountry")
    public ResponseEntity<?> getByOnlyCountry(@RequestParam String country){
        try{
            EntityResponse response = new EntityResponse<>();
            List<String> gls = glRepo.getByCountry(country);
            if(gls.isEmpty()){
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            } else {
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(gls);
            }
            return ResponseEntity.ok().body(gls);

        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
//    @GetMapping("/getByType")
//    public ResponseEntity<?> getByDistinctCountry(@RequestParam String type){
//        try {
//                EntityResponse response = new EntityResponse<>();
//                List<String> gls = glRepo.findSpecificTypeByBaseCountry(type);
//                List<String> filteredTypes = gls.stream()
//                        .filter(types -> !types.isEmpty() || types !=null)
//                        .toList();
//             response.setMessage(HttpStatus.OK.getReasonPhrase());
//             response.setStatusCode(HttpStatus.FOUND.value());
//             response.setEntity(filteredTypes);
//                return ResponseEntity.ok().body(gls);
//        } catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    @GetMapping("/getByBaseCountry")
    public ResponseEntity<?> getByBaseCountry(@RequestParam String baseCountry){
        try {
            EntityResponse response = new EntityResponse<>();
            List<Gl> allByCountry = glRepo.findAllByBaseCoutry(baseCountry);
            response.setMessage(HttpStatus.FOUND.getReasonPhrase());
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setEntity(allByCountry);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/filterCountry")
    public ResponseEntity<?> findCountryByBaseCountry(@RequestParam String baseCountry){
        try {
            EntityResponse response = new EntityResponse();
            List<String> countries = glRepo.findByDistinctBaseCountry(baseCountry);
            List<String> filteredCountries = countries.stream()
                    .filter(country -> !country.isEmpty())
                    .toList();
            response.setMessage(HttpStatus.FOUND.getReasonPhrase());
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setEntity(filteredCountries);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //First approach
    @GetMapping("/getSubsidiaries")
    public ResponseEntity<?> filterType(@RequestParam String input){
        try {
            EntityResponse response = new EntityResponse<>();
            List<String> subsidiaries;
            if(input.equals("Local")){
                List<String> local = glRepo.findLocalSubsidiary(input);
                List<String> filteredLocal = local.stream()
                        .filter(l -> !l.isEmpty())
                        .collect(Collectors.toList());
                response.setEntity(filteredLocal);
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                return ResponseEntity.ok().body(response);
            } else {
                List<String> swift = glRepo.findSwiftSubsidiary(input);
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setEntity(swift);
                return ResponseEntity.ok().body(response);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }
    //second approach
    @GetMapping("/filterSubs")
    public ResponseEntity<?> filterSubs(@RequestParam String values){
        try {
            EntityResponse response = new EntityResponse<>();
            List<String> subsidiaries;
            if(values.equalsIgnoreCase("Local")){
                subsidiaries = glRepo.findLocalSubsidiary(values);
            } else {
                subsidiaries = glRepo.findSwiftSubsidiary(values);
            }
            if(!subsidiaries.isEmpty()){
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setEntity(subsidiaries);
            }else{
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
           return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //filter ype based on baseCountry
    @GetMapping("/filterType")
    public ResponseEntity<?> filterTypeByBaseCountry(@RequestParam String baseCountry){
        try {
            EntityResponse response = new EntityResponse();
            List<String> types = glRepo.filterTypeBaseCountry(baseCountry);
            List<String> filteredTypes = types.stream()
                    .filter(type -> !type.isEmpty())
                    .collect(Collectors.toList());
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setMessage(HttpStatus.FOUND.getReasonPhrase());
            response.setEntity(filteredTypes);
            return ResponseEntity.ok().body(response);
//            if()
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //update Logics
    @PutMapping ("/updateInfo")
    public ResponseEntity<?> updateInfo(@RequestBody Gl gls){
        try {
            EntityResponse response = new EntityResponse<>();
            Optional<Gl> findByIds = glRepo.findById(gls.getId());
            if(findByIds.isPresent()){
                Gl updateGl = findByIds.get();
                updateGl.setCountry(gls.getCountry());
                updateGl.setGeneralLedger(gls.getGeneralLedger());
                updateGl.setCurrency(gls.getCurrency());
                updateGl.setAccountNumber(gls.getAccountNumber());
                updateGl.setType(gls.getType());
                updateGl.setBaseCountry(gls.getBaseCountry());
                updateGl.setGlName(gls.getGlName());
//                updateGl.getCreatedOn(new Date());
//                updateGl.setActive(false);
//                updateGl.
                Gl updatedGl = glRepo.save(updateGl);
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Update Succseesful");
                response.setEntity(updatedGl);

            }else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
            return ResponseEntity.ok().body(response);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
}



}
