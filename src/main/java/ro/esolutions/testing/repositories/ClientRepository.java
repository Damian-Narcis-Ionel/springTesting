package ro.esolutions.testing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.testing.entities.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAll();
}
