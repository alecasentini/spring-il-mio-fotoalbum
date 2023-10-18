package org.java.app.mvc;

import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoService;
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

	@GetMapping
	public String getIndex(@RequestParam(value = "titolo", required = false) String titolo, Model model) {
	    List<Photo> photos;
	    if (titolo != null) {
	        photos = photoService.findByTitoloContaining(titolo);
	    } else {
	        photos = photoService.findAll();
	    }

	    if (photos.isEmpty()) {
	        model.addAttribute("noPhotos", true);
	    } else {
	        model.addAttribute("photos", photos);
	    }

	    return "photo-index";
	}

	@GetMapping("/{id}")
	public String getPhotoDetails(@PathVariable int id, Model model) {
	    Photo photo = photoService.findById(id);
	    model.addAttribute("photo", photo);
	    return "photo-show";
	}
	
	@GetMapping("/create")
	public String getNewPhotoForm(Model model) {
	    model.addAttribute("photo", new Photo());
	    return "photo-create";
	}

	@PostMapping("/create")
	public String createPhoto(@Valid @ModelAttribute("photo") Photo photo, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "photo-create";
	    }
	    photoService.save(photo);
	    return "redirect:/photos";
	}
}