package pl.air.internetShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.air.internetShop.model.*;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

}
