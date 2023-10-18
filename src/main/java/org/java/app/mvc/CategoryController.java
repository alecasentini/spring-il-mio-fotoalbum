package org.java.app.mvc;

import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.pojo.Category;
import org.java.app.db.serv.PhotoService;
import org.java.app.db.serv.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String getIndex(Model model) {

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "category-index";
	}

	@GetMapping("/create")
	public String getCreate(Model model) {

		List<Photo> photos = photoService.findAll();
		Category category = new Category();

		model.addAttribute("category", category);
		model.addAttribute("photos", photos);

		return "category-create";
	}

	@PostMapping("/create")
	public String storeCategory(
			@Valid @ModelAttribute Category category,
			BindingResult bindingResult,
			Model model
		) {

		categoryService.save(category);

		List<Photo> photos = photoService.findAll();
		for (Photo photo : photos) {

			if (category.hasPhoto(photo)) 
				photo.addCategory(category);
			else photo.removeCategory(category);

			photoService.save(photo);
		}


		return "redirect:/categories";
    }
	
	@GetMapping("/{id}/delete")
	public String deleteCategory(@PathVariable int id) {
	    Category category = categoryService.findById(id);

	    List<Photo> photos = photoService.findAll();
	    for (Photo photo : photos) {
	        if (photo.hasCategory(category)) {
	            photo.removeCategory(category);
	            photoService.save(photo);
	        }
	    }

	    categoryService.delete(category);

	    return "redirect:/categories";
	}
}