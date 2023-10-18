package org.java.app;

import org.java.app.db.pojo.Photo;
import org.java.app.db.pojo.Category;
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private CategoryService categoryService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category paesaggi = new Category("Paesaggi");
		Category ritratti = new Category("Ritratti");
		Category macro = new Category("Macro");
		Category sport = new Category("Sport");
		Category stillLife = new Category("Still Life");
		Category astronomiche = new Category("Astronomiche");
		Category street = new Category("Street");
		Category architettura = new Category("Architettura");
		Category moda = new Category("Moda");
		Category reportage = new Category("Reportage");

		categoryService.save(paesaggi);
		categoryService.save(ritratti);
		categoryService.save(macro);
		categoryService.save(sport);
		categoryService.save(stillLife);
		categoryService.save(astronomiche);
		categoryService.save(street);
		categoryService.save(architettura);
		categoryService.save(moda);
		categoryService.save(reportage);

		Photo photo1 = new Photo("Parigi", "Parigi", "https://www.vadoaparigi.com/wp-content/uploads/2023/01/Cosa-vedere-a-Parigi-in-5-giorni.jpg", true, paesaggi, ritratti, macro);
		Photo photo2 = new Photo("Tokyo", "Tokyo", "https://www.gotokyo.org/it/plan/tokyo-outline/images/main.jpg", false, sport, stillLife, astronomiche);
		Photo photo3 = new Photo("Mare", "Mare", "https://www.viaggianelsalento.it/images/benefici-del-mare.jpg", true, street, architettura, moda);
		Photo photo4 = new Photo("Montagna", "Montagna", "https://www.spazio50.org/wp-content/uploads/2022/08/montagna.jpg", false, reportage, paesaggi, ritratti);
		Photo photo5 = new Photo("Deserto", "Deserto", "https://staticgeopop.akamaized.net/wp-content/uploads/sites/32/2023/07/deserto-piu-grande-del-mondo.jpg?im=AspectCrop=(16,9);", true, macro, sport, stillLife);

		photoService.save(photo1);
		photoService.save(photo2);
		photoService.save(photo3);
		photoService.save(photo4);
		photoService.save(photo5);

		System.out.println("Inserimento OK!");
    }
}