package br.com.antonio.eventostec.repositories;

import br.com.antonio.eventostec.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdressRepository extends JpaRepository<Address, UUID> {
}
