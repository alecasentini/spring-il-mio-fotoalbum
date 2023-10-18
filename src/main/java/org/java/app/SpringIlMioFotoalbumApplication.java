package org.java.app;

import org.java.app.db.pojo.Photo;
import org.java.app.db.pojo.Category;
import org.java.app.db.serv.CategoryService;
import org.java.app.db.serv.PhotoService;
import org.java.app.mvc.auth.pojo.Role;
import org.java.app.mvc.auth.pojo.User;
import org.java.app.mvc.auth.service.RoleService;
import org.java.app.mvc.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	
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
		
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		
		roleService.save(admin);
		roleService.save(user);
		
		final String pwsAdmin = new BCryptPasswordEncoder().encode("pws");
		final String pwsUser = new BCryptPasswordEncoder().encode("pws");

		User alexAdmin = new User("alexAdmin", pwsAdmin, admin, user);
		User alexUser = new User("alexUser", pwsUser, user);
		User marioUser = new User("marioUser", pwsUser, user);
		
		userService.save(alexAdmin);
		userService.save(alexUser);
		userService.save(marioUser);

		Photo photo1 = new Photo("Parigi", "Parigi", "https://www.vadoaparigi.com/wp-content/uploads/2023/01/Cosa-vedere-a-Parigi-in-5-giorni.jpg", true, alexUser, paesaggi, ritratti, macro);
		Photo photo2 = new Photo("Tokyo", "Tokyo", "https://www.gotokyo.org/it/plan/tokyo-outline/images/main.jpg", false, marioUser, sport, stillLife, astronomiche);
		Photo photo3 = new Photo("Mare", "Mare", "https://www.viaggianelsalento.it/images/benefici-del-mare.jpg", true, alexUser, street, architettura, moda);
		Photo photo4 = new Photo("Montagna", "Montagna", "https://www.spazio50.org/wp-content/uploads/2022/08/montagna.jpg", false, alexUser, reportage, paesaggi, ritratti);
		Photo photo5 = new Photo("Deserto", "Deserto", "https://staticgeopop.akamaized.net/wp-content/uploads/sites/32/2023/07/deserto-piu-grande-del-mondo.jpg?im=AspectCrop=(16,9);", true, marioUser, macro, sport, stillLife);

		photoService.save(photo1);
		photoService.save(photo2);
		photoService.save(photo3);
		photoService.save(photo4);
		photoService.save(photo5);
		
		System.out.println("Inserimento OK!");
    }
}