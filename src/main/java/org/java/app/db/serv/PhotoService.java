package org.java.app.db.serv;

import org.java.app.db.pojo.Photo;
import org.java.app.db.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepo photoRepo;

    public Photo save(Photo photo) {
        return photoRepo.save(photo);
    }

    public List<Photo> findAll() {
        return photoRepo.findAll();
    }

    public Optional<Photo> findById(int id) {
        return photoRepo.findById(id);
    }

    public List<Photo> findByTitoloContaining(String titolo) {
        return photoRepo.findByTitoloContaining(titolo);
    }

    public List<Photo> findByTitoloContainingAndUserId(String titolo, int userId) {
        return photoRepo.findByTitoloContainingAndUserId(titolo, userId);
    }

    public List<Photo> findAllByUserId(int userId) {
        return photoRepo.findAllByUserId(userId);
    }

    public void deleteById(int id) {
        photoRepo.deleteById(id);
    }
}
