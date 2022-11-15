package pl.air.internetShop.init;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.air.internetShop.model.*;
import pl.air.internetShop.repo.*;

@Repository
public class DataLoaderPL implements DataLoader {

	@Autowired private CompanyRepository comRepo;
	@Autowired private ProductRepository prodRepo;
	@Autowired private CategoryRepository catRepo;

	@Override
	@Transactional
	public void insertData() {
		/* producenci */
		Company amd = createCompany("AMD",  "Advanced Micro Device");
		Company intel = createCompany("Intel",   "Intel Technology");
		Company goodram = createCompany("GoodRam", "GoodRam memory");
		Company kingston = createCompany("Kingston",   "Kingston Technology");

		/* kategoria */
		Category procesors = createCategory("Procesory");
		Category ram = createCategory("Pamięć RAM");
		Category ssd = createCategory("Dyski SSD");

		/* produkty */
		Product r1 = createProduct("Ryzen 3 3400",  "R3", "2017-02-01", "400", amd, procesors);
		Product r2 = createProduct("Ryzen 5 5600X", "R5", "2009-07-15", "1200", amd, procesors);
		Product r3 = createProduct("Ryzen 7 5800X", "R7", "2020-06-01", "1600", amd, procesors);
		Product r4 = createProduct("Ryzen 9 5900X", "R9", "2020-09-15", "2600", amd, procesors);

		Product i1 = createProduct("Core i3 10300", "i3", "2019-01-01", "522", intel, procesors);
		Product i2 = createProduct("Core i5 10400", "i5", "2019-06-01", "720", intel, procesors);
		Product i3 = createProduct("Core i7 10700", "i7", "2019-06-01", "1500", intel, procesors);
		Product i4 = createProduct("Core i9 10900", "i9", "2019-08-01", "4500", intel, procesors);

		Product g1 = createProduct("GoodRam 16GB 3200MHz DDR4 CL15", "GDR16", "2015-01-01", "600", goodram, ram);
		Product g2 = createProduct("GoodRam 8GB 3200MHz DDR4 CL15", "GDR8", "2015-01-01", "440", goodram, ram);
		Product k1 = createProduct("Kingston 32GB 3600MHz DDR4 CL17", "KDR32", "2017-06-01", "1200", kingston, ram);
		Product k2 = createProduct("Kingston 32GB 3600MHz DDR4 CL17", "KDR32", "2017-06-01", "1200", kingston, ram);

		Product g3 = createProduct("GoodRam 1000GB SSD",   "G1TBSSD", "2015-01-01", "600", goodram, ssd);
		Product g4 = createProduct("GoodRam 500GB SSD", "G05TBSSD",  "2015-01-01", "440", goodram, ssd);
		Product k3 = createProduct("Kingston 1000GB SSD", "K05SSD",    "2018-06-01", "700", kingston, ssd);
		Product k4 = createProduct("Kingston 500GB SSD", "K05SSD",    "2018-06-01", "400", kingston, ssd);

		// depRepo.save(amd);
		// depRepo.save(intel);
		// depRepo.save(goodram);
		// depRepo.save(kingston);
		comRepo.saveAll(
				List.of(amd, intel, goodram, kingston)
		);

		catRepo.saveAll(
				List.of(procesors, ram, ssd)
		);

		prodRepo.saveAll(
				List.of(r1, r2, r3, r4, i1, i2, i3, i4, g1, g2, g3, g4, k1, k2, k3, k4)
		);

	}

	private Product createProduct(String firstName, String lastName, String releaseDate, String price, Company company, Category category) {
		Product one = new Product();
		one.setFirstName(firstName);
		one.setLastName(lastName);
		one.setReleaseDate(LocalDate.parse(releaseDate));
		one.setPrice(new BigDecimal(price));
		one.setCompany(company);
		one.setCategory(category);
		return one;
	}

	private Category createCategory(String name) {
		Category one = new Category();
		one.setName(name);
		return one;
	}

	private Company createCompany(String name, String description) {
		Company one = new Company();
		one.setName(name);
		one.setDescription(description);
		return one;
	}
}
