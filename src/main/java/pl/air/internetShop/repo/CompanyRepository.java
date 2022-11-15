package pl.air.internetShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.air.internetShop.model.*;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);

}
