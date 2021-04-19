package ru.x5.mpk.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.x5.mpk.server.entities.TestEntity;

public interface TestEntityRepository extends CrudRepository<TestEntity, String>, JpaRepository<TestEntity, String> {
}
