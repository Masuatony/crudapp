package com.example.firstcrud.GLMaitenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsGlRepo extends JpaRepository<AccountGls,Long> {
    @Query(value = "SELECT general_ledger from account_gls where account_number=?1", nativeQuery = true)
    String findGLByAccountNumber(String accountNumber);

    @Query(value = "SELECT account_number from account_gls where general_ledger=?1", nativeQuery = true)
    String findAccountBygl(String gl);

    @Query(value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl  from account_gls ag  where ag.type=:type  and ag.base_country =:subsidiary",nativeQuery = true)
    List<Glinfo> fetchGls(String type,String subsidiary);

    @Query(value = "SELECT ag.currency  from account_gls ag  where ag.account_number=:ac",nativeQuery = true)
    String findCurrencyByAc(String ac);
    @Query(value = "SELECT COALESCE(ag.type, 'Not Configured') FROM account_gls ag WHERE ag.account_number=:ac",nativeQuery = true)
    String findTypeByAccountNum(String ac);


    Optional<AccountGls> findByGeneralLedger(String generalLedger);
    Optional<AccountGls> findByAccountNumber(String accountNumber);

    @Query(nativeQuery = true,value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl  from account_gls ag  where gl_name  like 'BANK OF UGANDA%' and ag.currency=:currency")
    Glinfo getBOUGldetails(String currency);

    @Query(nativeQuery = true,value ="SELECT  ag.account_number as ac ,ag.currency ,ag.general_ledger as gl   from account_gls ag  where gl_name  like 'CITIBANK%' and ag.currency=:currency")
    Glinfo getCITIGldetails(String currency);


    @Query(nativeQuery = true,value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl  from account_gls ag  where gl_name  like 'CBK CLEARING%' and ag.currency=:currency")
    Glinfo getCBKGldetails(String currency);
    @Query(nativeQuery = true,value = "SELECT base_country  from account_gls ag WHERE ag.type =:type and ag.currency =:currency")
    String getBaseCountry(String type,String currency);

    @Query(nativeQuery = true,value = "SELECT *  from account_gls ag WHERE ag.type =:type and ag.currency =:currency")
    AccountGls fecthByTypeAndcurrency(String currency,String type);
    @Query(nativeQuery = true,value = "SELECT ag.account_number as ac ,ag.currency ,ag.general_ledger as gl from account_gls ag WHERE ag.type =:type and ag.currency =:currency and ag.base_country=:subsidiary")
    Glinfo fetchTypeAndcurrencySubsidiary(String currency,String type,String subsidiary);
    Optional<AccountGls> findByTypeAndCurrencyAndBaseCountry(String type, String currency, String baseCountry);
    boolean existsByTypeAndBaseCountry(String type, String baseCountry);



    interface Glinfo{
        String getAc();
        String getCurrency();
        String getGl();
    }
    Optional<AccountGls> findByCountryAndCurrencyAndBaseCountry(String country, String currency, String baseCountry);
    List<AccountGls> findByCountryAndBaseCountry(String country, String baseCountry);
    @Query(value = "SELECT DISTINCT base_country FROM account_gls WHERE country IS NOT NULL AND country <> ''", nativeQuery = true)
    List<String> findDistinctBaseCountryLocalNostros();
    @Query(value = "SELECT DISTINCT base_country FROM account_gls WHERE type IS NOT NULL AND type <> ''", nativeQuery = true)
    List<String> findDistinctBaseCountrySwiftNostros();
    @Query(value = "SELECT DISTINCT country FROM account_gls WHERE base_country = :baseCountry", nativeQuery = true)
    List<String> findDistinctCountryByBaseCountry(String baseCountry);
    @Query(value = "SELECT DISTINCT type FROM account_gls WHERE base_country = :baseCountry", nativeQuery = true)
    List<String> findDistinctTypeByBaseCountry(String baseCountry);

}
