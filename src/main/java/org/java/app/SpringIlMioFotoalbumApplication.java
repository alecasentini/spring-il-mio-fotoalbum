package org.java.app;

import org.java.app.db.pojo.Photo;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private PhotoService photoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Photo photo1 = new Photo("Titolo1", "Descrizione1", "url_foto1", true);
		Photo photo2 = new Photo("Titolo2", "Descrizione2", "url_foto2", false);
		Photo photo3 = new Photo("Titolo3", "Descrizione3", "url_foto3", true);
		Photo photo4 = new Photo("Titolo4", "Descrizione4", "url_foto4", false);
		Photo photo5 = new Photo("Titolo5", "Descrizione5", "url_foto5", true);

		photoService.save(photo1);
		photoService.save(photo2);
		photoService.save(photo3);
		photoService.save(photo4);
		photoService.save(photo5);

		System.out.println("Inserimento OK!");
    }
}