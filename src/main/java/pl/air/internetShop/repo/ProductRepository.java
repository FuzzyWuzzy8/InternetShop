package pl.air.internetShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import pl.air.internetShop.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByFirstNameAndLastName(String firstName, String lastName);

    List<Product> findAllByReleaseDateAfter(LocalDate releaseDate);

    List<Product> findAllByPriceGreaterThan(BigDecimal price);

    List<Product> findAllByCompany(Company company);

    List<Product> findAllByCategory(Category category);


    @Query( "SELECT COUNT(e) " +
            "FROM Product e " +
            "WHERE e.company = :company "
    )
    long countByCompany(Company company);

}
