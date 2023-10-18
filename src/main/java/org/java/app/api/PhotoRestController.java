package org.java.app.api;

import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PhotoDTO;
import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class PhotoRestController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/photos")
	public ResponseEntity<List<Photo>> getAll(@RequestParam(required = false) String titolo) {

		List<Photo> photos;

		if (titolo != null) {
			photos = photoService.findByTitoloContaining(titolo);
		} else {
			photos = photoService.findAll();
		}

		return new ResponseEntity<>(photos, HttpStatus.OK);
	}

}