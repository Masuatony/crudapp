package com.example.firstcrud.GL;

import com.example.firstcrud.GLMaitenance.AccountGls;
import com.example.firstcrud.GLMaitenance.AccountsGlRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GlRepo extends JpaRepository<Gl,Long> {
    @Query(value = "SELECT general_ledger from gl where account_number=?1", nativeQuery = true)
    String findGLByAccountNumber(String accountNumber);

    @Query(value = "SELECT account_number from gl where general_ledger=?1", nativeQuery = true)
    String findAccountBygl(String gl);

    @Query(value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl  from gl ag  where ag.type=:type  and ag.base_country =:subsidiary",nativeQuery = true)
    List<GlRepo.Glinfo> fetchGls(String type, String subsidiary);

    @Query(value = "SELECT ag.currency  from gl ag  where ag.account_number=:ac",nativeQuery = true)
    String findCurrencyByAc(String ac);
    @Query(value = "SELECT COALESCE(ag.type, 'Not Configured') FROM gl ag WHERE ag.account_number=:ac",nativeQuery = true)
    String findTypeByAccountNum(String ac);


    Optional<Gl> findByGeneralLedger(String generalLedger);
    Optional<Gl> findByAccountNumber(String accountNumber);

    @Query(nativeQuery = true,value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl  from gl ag  where gl_name  like 'BANK OF UGANDA%' and ag.currency=:currency")
    AccountsGlRepo.Glinfo getBOUGldetails(String currency);

    @Query(nativeQuery = true,value ="SELECT  ag.account_number as ac ,ag.currency ,ag.general_ledger as gl   from gl ag  where gl_name  like 'CITIBANK%' and ag.currency=:currency")
    AccountsGlRepo.Glinfo getCITIGldetails(String currency);


    @Query(nativeQuery = true,value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl  from gl ag  where gl_name  like 'CBK CLEARING%' and ag.currency=:currency")
    AccountsGlRepo.Glinfo getCBKGldetails(String currency);
    @Query(nativeQuery = true,value = "SELECT base_country  from gl ag WHERE ag.type =:type and ag.currency =:currency")
    String getBaseCountry(String type,String currency);

    @Query(nativeQuery = true,value = "SELECT *  from gl ag WHERE ag.type =:type and ag.currency =:currency")
    AccountGls fecthByTypeAndcurrency(String currency,String type);
    @Query(nativeQuery = true,value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl from gl ag WHERE ag.type =:type and ag.currency =:currency and ag.base_country=:subsidiary")
    AccountsGlRepo.Glinfo fetchTypeAndcurrencySubsidiary(String currency, String type, String subsidiary);
    Optional<Gl> findByTypeAndCurrencyAndBaseCountry(String type, String currency, String baseCountry);
    boolean existsByTypeAndBaseCountry(String type, String baseCountry);



    interface Glinfo{
        String getAc();
        String getCurrency();
        String getGl();
    }
    Optional<Gl> findByCountryAndCurrencyAndBaseCountry(String country, String currency, String baseCountry);
    List<Gl> findByCountryAndBaseCountry(String country, String baseCountry);

    @Query(value = "SELECT * FROM gl",  nativeQuery = true)
    List<Gl> getAllData();
    @Query(nativeQuery = true, value = "SELECT * FROM gl WHERE country = ?1")
    List<String> getByCountry(String country);
//    @Query(nativeQuery = true, value = "SELECT DISTINCT type FROM gl WHERE base_country = :baseCountry")
//    List<String> findSpecificTypeByBaseCountry(String type);

    @Query(nativeQuery = true, value = "SELECT * FROM gl WHERE base_country = :baseCountry")
    List<Gl> findAllByBaseCoutry(String baseCountry);

//    @Query(nativeQuery = true, value = "SELECT DISTINCT baseCountry FROM gl WHERE country IS NOT NULL AND country <> ''")
//    List<String> findBaseCountryByCountry(String country);

   @Query(value = "SELECT DISTINCT country FROM gl WHERE base_country = :baseCountry", nativeQuery = true)
    List<String> findByDistinctBaseCountry(String baseCountry);
   @Query(nativeQuery = true, value = "SELECT DISTINCT base_country FROM gl WHERE country IS NOT NULL AND country <> ''")
   List<String> findLocalSubsidiary(String input);
   @Query(nativeQuery = true, value = "SELECT DISTINCT base_country FROM gl WHERE type IS NOT NULL AND type <> ''")
   List<String> findSwiftSubsidiary(String input);
   @Query(nativeQuery = true, value = "SELECT DISTINCT type FROM gl WHERE base_country = :baseCountry")
    List<String> filterTypeBaseCountry(String baseCountry);
}

