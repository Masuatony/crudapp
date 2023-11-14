package com.example.firstcrud.GLMaitenance;

import com.example.firstcrud.DTO.EntityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/general-ledgers")
public class AccountsGlResource {

    @Autowired
    private final AccountsGlRepo accountsGlRepo;

//    private AuditTrailProvider audit;

    @PostMapping("add")
    public ResponseEntity<?> addGlInfo(@RequestBody AccountGls accountGls){
        try {
            EntityResponse response = new EntityResponse<>();
//            audit.log("Nostro", "Adding New Nostro GL Maintenance");
            Optional<AccountGls> glsOptional = accountsGlRepo.findByGeneralLedger(accountGls.getGeneralLedger());
            Optional<AccountGls> glsOptional1 = accountsGlRepo.findByAccountNumber(accountGls.getAccountNumber());
            if (glsOptional.isPresent() && !glsOptional.get().getCountry().equalsIgnoreCase("South Sudan")){
                response.setMessage("GL With No. " + accountGls.getGeneralLedger() + " Already Mapped To An Account Number!");
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            } else if (glsOptional1.isPresent() && !glsOptional1.get().getCountry().equalsIgnoreCase("South Sudan")) {
                response.setMessage("Account No. " + accountGls.getAccountNumber() + " Already Mapped To A GL!");
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            } else {
                AccountGls gls = accountsGlRepo.save(accountGls);
                response.setMessage("Saved Successfully");
                response.setStatusCode(HttpStatus.CREATED.value());
                response.setEntity(gls);
            }

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Error {}", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGlInfo(@RequestBody AccountGls accountGls){
        try {
            EntityResponse response = new EntityResponse<>();
//            audit.log("Nostro", "Updating Existing Nostro GL Maintenance");
            Optional<AccountGls> byId = accountsGlRepo.findById(accountGls.getId());
            if (byId.isPresent()){
                AccountGls accountGlsToUpdate = byId.get();

                Optional<AccountGls> glsOptional = accountsGlRepo.findByGeneralLedger(accountGls.getGeneralLedger());
                Optional<AccountGls> glsOptional1 = accountsGlRepo.findByAccountNumber(accountGls.getAccountNumber());
                if (glsOptional.isPresent() && !glsOptional.get().getCountry().equalsIgnoreCase("South Sudan") && !glsOptional.get().getId().equals(accountGlsToUpdate.getId())){
                    response.setMessage("GL With No. " + accountGls.getGeneralLedger() + " Already Mapped To An Account Number!");
                    response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
                } else if (glsOptional1.isPresent() && !glsOptional1.get().getCountry().equalsIgnoreCase("South Sudan") && !glsOptional1.get().getId().equals(accountGlsToUpdate.getId())) {
                    response.setMessage("Account No. " + accountGls.getAccountNumber() + " Already Mapped To A GL!");
                    response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
                } else {
                    accountGlsToUpdate.setAccountNumber(accountGls.getAccountNumber());
                    accountGlsToUpdate.setGeneralLedger(accountGls.getGeneralLedger());
                    accountGlsToUpdate.setCurrency(accountGls.getCurrency());
                    accountGlsToUpdate.setGlName(accountGls.getGlName());
                    accountGlsToUpdate.setCountry(accountGls.getCountry());
                    accountGlsToUpdate.setBaseCountry(accountGls.getBaseCountry());
                    accountGlsToUpdate.setType(accountGls.getType());
                    AccountGls updatedAccountGls = accountsGlRepo.save(accountGlsToUpdate);
                    response.setMessage("Updated Successfully");
                    response.setStatusCode(HttpStatus.OK.value());
                    response.setEntity(updatedAccountGls);

                }

            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Error {}", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping("fetch")
    public ResponseEntity<?> fetchGlInfo(){
//        audit.log("Nostro", "Fetcing All Nostro GL Maintenances");
        List<AccountGls> gls = accountsGlRepo.findAll();
        return ResponseEntity.ok().body(gls);
    }

    @GetMapping("find/by/country")
    public ResponseEntity<?> findByCountry(@RequestParam String country, @RequestParam String baseCountry){
        try {
            EntityResponse response = new EntityResponse<>();
//            audit.log("Local Nostro", "Fetching " + baseCountry + " GL Maintenances For " + country + " Local Nostro Accounts");
            List<AccountGls> gls = accountsGlRepo.findByCountryAndBaseCountry(country, baseCountry);
            if (!gls.isEmpty()){
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(gls);
            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Error {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("find/configured/subsidiaries")
    public ResponseEntity<?> findSubsidiaries(@RequestParam String nostroType){
        try {
            EntityResponse response = new EntityResponse<>();
//            audit.log("Nostro", "Fetching All Configured Subsidiaries For " + nostroType + " Nostro GL Configurations");

            List<String> subsidiaries;
            if (nostroType.equalsIgnoreCase("Local")){
                subsidiaries = accountsGlRepo.findDistinctBaseCountryLocalNostros();
            } else {
                subsidiaries = accountsGlRepo.findDistinctBaseCountrySwiftNostros();
            }

            if (!subsidiaries.isEmpty()){
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.FOUND.value());
            } else {
                response.setMessage("No GL Configurations Found for " + nostroType + " Nostros. Contact your System Admin for Assistance.");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }

            response.setEntity(subsidiaries);
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Error {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("find/configured/countries")
    public ResponseEntity<?> findCountriesBySubsidiary(@RequestParam String baseCountry){
        try {
            EntityResponse response = new EntityResponse<>();
//            audit.log("Local Nostro", "Fetching All Configured Statement A/C Countries For " + baseCountry + " Local Nostro GL Configurations");

            List<String> countries = accountsGlRepo.findDistinctCountryByBaseCountry(baseCountry);
            List<String> filteredCountries = countries.stream()
                    .filter(country -> country != null && !country.isBlank())
                    .toList();

            response.setMessage(HttpStatus.FOUND.getReasonPhrase());
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setEntity(filteredCountries);

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Error {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("find/configured/types")
    public ResponseEntity<?> findTypesBySubsidiary(@RequestParam String baseCountry){
        try {
            EntityResponse response = new EntityResponse<>();
//            audit.log("Nostro", "Fetching All Configured Bank Types For " + baseCountry + " Swift Nostro GL Configurations");

            List<String> types = accountsGlRepo.findDistinctTypeByBaseCountry(baseCountry);
            List<String> filteredTypes = types.stream()
                    .filter(type -> type != null && !type.isBlank())
                    .toList();

            response.setMessage(HttpStatus.FOUND.getReasonPhrase());
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setEntity(filteredTypes);

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Error {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            EntityResponse response = new EntityResponse<>();
//            audit.log("Nostro", "Deleting Nostro GL Maintenance With Id " + id);
            Optional<AccountGls> optional = accountsGlRepo.findById(id);
            if (optional.isPresent()){
                accountsGlRepo.deleteById(id);
                response.setMessage("Deleted Successfully");
                response.setStatusCode(HttpStatus.OK.value());
            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }

            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            log.info("Error {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
