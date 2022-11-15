package pl.air.internetShop.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.air.internetShop.exception.NoDataFoundException;
import pl.air.internetShop.model.Category;
import pl.air.internetShop.repo.CategoryRepository;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {
	
	@Autowired private CategoryRepository catRepo;
	
	/* -------------------------------------------------------- */
	/* READ */
	
	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Category> opt = catRepo.findById(id);
		
		if (opt.isPresent()) {
			Category category = opt.get();
			model.addAttribute("category", category);
		}
		else {
			throw new NoDataFoundException("Nie znaleziono kategorii o id = " + id);
		}
		
		return "category";
	}
	
	@GetMapping()
	public String displayAll(Model model) {
		List<Category> all = catRepo.findAll();
		model.addAttribute("categories", all);
		return "categories";
	}

}
