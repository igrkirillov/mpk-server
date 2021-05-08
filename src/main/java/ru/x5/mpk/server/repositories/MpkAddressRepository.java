package ru.x5.mpk.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.x5.mpk.server.entities.MpkAddress;
import ru.x5.mpk.server.entities.TestEntity;

import java.util.List;

public interface MpkAddressRepository extends CrudRepository<MpkAddress, String>, JpaRepository<MpkAddress, String> {
    @Query("select e from MpkAddress e where e.fiasUid in (:fiasUids)")
    List<MpkAddress> findByFiasUidList(@Param("fiasUids") List<String> fiasUids);
}
