package ru.x5.mpk.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.x5.mpk.server.entities.MpkAddress;
import ru.x5.mpk.server.entities.TestEntity;

public interface MpkAddressRepository extends CrudRepository<MpkAddress, String>, JpaRepository<MpkAddress, String> {
}
