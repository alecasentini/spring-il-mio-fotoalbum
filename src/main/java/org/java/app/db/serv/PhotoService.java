package org.java.app.db.serv;

import org.java.app.db.pojo.Photo;
import org.java.app.db.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepo photoRepo;

	public void save(Photo photo) {

		photoRepo.save(photo);
	}
	public List<Photo> findAll() {

		return photoRepo.findAll();
	}
	public Photo findById(int id) {

		return photoRepo.findById(id).get();
	}
	public List<Photo> findByTitoloContaining(String titolo) {
	    return photoRepo.findByTitoloContaining(titolo);
	}
}
