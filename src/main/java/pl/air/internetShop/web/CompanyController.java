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
import pl.air.internetShop.repo.CompanyRepository;
import pl.air.internetShop.repo.ProductRepository;

@Controller
@RequestMapping(value = "/companies")
public class CompanyController {
	
	@Autowired private CompanyRepository comRepo;
	@Autowired private ProductRepository prodRepo;
	
	
	/* -------------------------------------------------------- */
	/* READ */
	
	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Company> opt = comRepo.findById(id);
		
		if (opt.isPresent()) {
			Company company = opt.get();
			model.addAttribute("company", company);
			
			List<Product> products = prodRepo.findAllByCompany(company);
			model.addAttribute("products", products);
		}
		else {
			throw new NoDataFoundException("Nie znaleziono działu o id = " + id);
		}			
		
		return "company";
	}
	
	@GetMapping()
	public String displayAll(Model model) {
		List<Company> all = comRepo.findAll();
		model.addAttribute("companies", all);
		return "companies";
	}
	
	
	/* -------------------------------------------------------- */
	/* CREATE */
	
	@GetMapping(value = "/form")
	public String displayForm(Model model) {
		Company company = new Company();
		model.addAttribute("company", company);
		return "company-form";
	}

	
	/* -------------------------------------------------------- */
	/* UPDATE */
	
	@GetMapping(value = "/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Company> opt = comRepo.findById(id);
		
		if (opt.isPresent()) {
			Company company = opt.get();
			model.addAttribute("company", company);
		}
		else {
			throw new NoDataFoundException("Nie znaleziono działu o id = " + id);
		}			

		return "company-form";
	}

	
	/* -------------------------------------------------------- */
	/* SAVE Form (CREATE or UPDATE) */
	
	@PostMapping(value = "/save")
	public String saveOne(@Valid Company company, Errors errors) {
		if (errors.hasErrors())
			return "company-form";
			
		comRepo.save(company);
		
		return "redirect:/companies";
	}
	
	
	/* -------------------------------------------------------- */
	/* DELETE */
	
	@GetMapping(value = "/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (! comRepo.existsById(id))
			throw new NoDataFoundException("Nie znaleziono producenta o id = " + id);
		
		Company company = new Company();
		company.setId(id);
		long comCount = prodRepo.countByCompany(company);
		if (comCount == 0)
			comRepo.deleteById(id);
		
		return "redirect:/companies";
	}
}






