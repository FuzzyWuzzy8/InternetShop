package pl.air.internetShop.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.air.internetShop.exception.NoDataFoundException;
import pl.air.internetShop.model.Company;
import pl.air.internetShop.model.Product;
import pl.air.internetShop.model.Category;
import pl.air.internetShop.repo.CompanyRepository;
import pl.air.internetShop.repo.ProductRepository;
import pl.air.internetShop.repo.CategoryRepository;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired private ProductRepository prodRepo;
	@Autowired private CompanyRepository comRepo;
	@Autowired private CategoryRepository catRepo;
	
	
	/* -------------------------------------------------------- */
	/* READ */
	
	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Product> opt = prodRepo.findById(id);
		
		if (opt.isPresent()) {
			Product product = opt.get();
			model.addAttribute("product", product);
		}
		else {
			throw new NoDataFoundException("Nie znaleziono produktu o id = " + id);
		}			
		
		return "product";
	}
	
	@GetMapping()
	public String displayAll(Model model) {
		List<Product> all = prodRepo.findAll();
		model.addAttribute("products", all);
		return "products";
	}
	
	
	/* -------------------------------------------------------- */
	/* CREATE */
	
	@GetMapping(value = "/form")
	public String displayForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		addProductDataToModel(model);
		
		return "product-form";
	}

	private void addProductDataToModel(Model model) {
		List<Company> companies = comRepo.findAll();
		List<Category> categories = catRepo.findAll();
		model.addAttribute("companies", companies);
		model.addAttribute("categories", categories);
	}
	
	
	/* -------------------------------------------------------- */
	/* UPDATE */
	
	@GetMapping(value = "/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Product> opt = prodRepo.findById(id);
		
		if (opt.isPresent()) {
			Product product = opt.get();
			model.addAttribute("product", product);
			addProductDataToModel(model);
		}
		else {
			throw new NoDataFoundException("Nie znaleziono produktu o id = " + id);
		}			

		return "product-form";
	}

	
	/* -------------------------------------------------------- */
	/* SAVE Form (CREATE or UPDATE) */
	
	@PostMapping(value = "/save")
	public String saveOne(@Valid Product product, Errors errors, Model model) {
		if (errors.hasErrors()) {
			addProductDataToModel(model);
			return "product-form";
		}
			
		prodRepo.save(product);
		
		return "redirect:/products";
	}
	
	
	/* -------------------------------------------------------- */
	/* DELETE */
	
	@GetMapping(value = "/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (! prodRepo.existsById(id))
			throw new NoDataFoundException("Nie znaleziono produktu o id = " + id);
		
		prodRepo.deleteById(id);
		
		return "redirect:/products";
	}
}






