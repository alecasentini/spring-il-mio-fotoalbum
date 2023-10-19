package org.java.app.mvc;

import java.security.Principal;
import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.pojo.Category;
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.PhotoService;
import org.java.app.mvc.auth.pojo.User;
import org.java.app.mvc.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
    private CategoryService categoryService;
	
	@Autowired
    private UserService userService;
	
	@GetMapping
	public String getIndex(@RequestParam(value = "titolo", required = false) String titolo, Model model, Principal principal) {
	    List<Photo> photos;
	    User user = (User) userService.loadUserByUsername(principal.getName());
	    boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
	    if (isAdmin) {
	        
	        if (titolo != null) {
	            photos = photoService.findByTitoloContaining(titolo);
	        } else {
	            photos = photoService.findAll();
	        }
	    } else {
	        
	        if (titolo != null) {
	            photos = photoService.findByTitoloContainingAndUserId(titolo, user.getId());
	        } else {
	            photos = photoService.findAllByUserId(user.getId());
	        }
	    }

	    if (photos.isEmpty()) {
	        model.addAttribute("noPhotos", true);
	    } else {
	        model.addAttribute("photos", photos);
	    }

	    return "photo-index";
	}


	@GetMapping("/{id}")
	public String getPhotoDetails(@PathVariable int id, Model model, Principal principal) {
	    Photo photo = photoService.findById(id).get();
	    User user = (User) userService.loadUserByUsername(principal.getName());
	    boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
	    if (!isAdmin && photo.getUser().getId() != user.getId()) {
	        
	        return "redirect:/error";
	    }
	    model.addAttribute("photo", photo);
	    return "photo-show";
	}
	
	@GetMapping("/create")
	public String getNewPhotoForm(Model model) {
		List<Category> categories=categoryService.findAll();
	    model.addAttribute("photo", new Photo());
	    model.addAttribute("categories", categories);
	    return "photo-create";
	}

	@PostMapping("/create")
	public String createPhoto(@Valid @ModelAttribute("photo") Photo photo, BindingResult bindingResult, Model model, Principal principal) {
	    if (bindingResult.hasErrors()) {
	    	 List<Category> categories = categoryService.findAll();
	         model.addAttribute("categories", categories);
	        return "photo-create";
	    }
	    User user = (User) userService.loadUserByUsername(principal.getName());
	    photo.setUser(user);
	    photoService.save(photo);
	    return "redirect:/photos";
	}
	
	@GetMapping("/{id}/edit")
	public String getEditPhotoForm(@PathVariable int id, Model model, Principal principal) {
	    Photo photo = photoService.findById(id).get();
	    User user = (User) userService.loadUserByUsername(principal.getName());
	    boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
	    if (!isAdmin && photo.getUser().getId() != user.getId()) {
	        return "redirect:/error";
	    }
	    List<Category> categories=categoryService.findAll();
	    model.addAttribute("photo", photo);
	    model.addAttribute("categories", categories);
	    return "photo-edit"; 
	}
	@PostMapping("/{id}/edit")
	public String editPhoto(@PathVariable int id, @Valid @ModelAttribute("photo") Photo updatedPhoto, BindingResult bindingResult, Model model, Principal principal) {
	    User user = (User) userService.loadUserByUsername(principal.getName());
	    boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));

	    if (!isAdmin && bindingResult.hasErrors()) {
	    	 List<Category> categories = categoryService.findAll();
	         model.addAttribute("categories", categories);
	        return "photo-edit";
	    }
	    
	    Photo existingPhoto = photoService.findById(id).get();
	    if (!isAdmin && existingPhoto.getUser().getId() != user.getId()) {
	        return "redirect:/error";
	    }

	    if (isAdmin) {
	        existingPhoto.setVisibile(updatedPhoto.isVisibile());
	        photoService.save(existingPhoto);
	    } else {
	        updatedPhoto.setId(id);
	        updatedPhoto.setUser(user);
	        photoService.save(updatedPhoto);
	    }

	    return "redirect:/photos";
	}

	
	@PostMapping("/{id}/delete")
	public String deletePhoto(@PathVariable int id) {
	    photoService.deleteById(id);
	    return "redirect:/photos";
	}
}