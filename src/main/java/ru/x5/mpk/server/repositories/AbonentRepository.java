package ru.x5.mpk.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.x5.mpk.server.entities.Abonent;
import ru.x5.mpk.server.entities.MpkAddress;

import java.util.List;

public interface AbonentRepository extends CrudRepository<Abonent, String>, JpaRepository<Abonent, String> {
    @Query("select e.abonent from MpkAddressAbonentLink e where e.mpkAddress.uid = :mpkAddressUid")
    List<Abonent> findAbonentsBy(@Param("mpkAddressUid") String mpkAddressUid);
}
