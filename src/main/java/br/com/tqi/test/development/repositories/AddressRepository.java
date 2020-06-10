package br.com.tqi.test.development.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tqi.test.development.entities.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
