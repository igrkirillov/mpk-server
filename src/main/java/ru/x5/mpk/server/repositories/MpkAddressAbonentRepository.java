package ru.x5.mpk.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.x5.mpk.server.entities.Abonent;
import ru.x5.mpk.server.entities.MpkAddressAbonentLink;

public interface MpkAddressAbonentRepository extends CrudRepository<MpkAddressAbonentLink, String>, JpaRepository<MpkAddressAbonentLink, String> {
}
