package org.java.app.mvc;

import java.util.List;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photos")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping
	public String getIndex(Model model) {

		List<Photo> photos = photoService.findAll();

		if (photos.isEmpty()) {
			model.addAttribute("noPhotos", true);
		} else {
			model.addAttribute("photos", photos);
		}

		return "photo-index";
	}
}