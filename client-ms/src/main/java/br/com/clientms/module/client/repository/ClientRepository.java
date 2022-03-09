package br.com.clientms.module.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clientms.module.client.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameIgnoreCaseContaining(String name);

    List<Client> findByCnpj(String cnpj);

    boolean existsByNameIgnoreCaseContaining(String name);

    boolean existsByCnpj(String cnpj);
}