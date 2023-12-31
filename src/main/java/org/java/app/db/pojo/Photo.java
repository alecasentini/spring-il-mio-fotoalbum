package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.java.app.api.dto.PhotoDTO;
import org.java.app.mvc.auth.pojo.User;


@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 128, nullable = false)
	@NotBlank(message = "Il titolo è obbligatorio")
	@Size(max = 128, message = "Il titolo non può superare i 128 caratteri")
	private String titolo;

	@Column(length = 256)
	@NotBlank(message = "La descrizione è obbligatoria")
	@Size(max = 256, message = "La descrizione non può superare i 256 caratteri")
	private String descrizione;

	@Column(length = 256)
	@NotBlank(message = "L'URL è obbligatorio")
	private String url;

	private boolean visibile;
	
	@ManyToMany
	@JsonManagedReference
	private List<Category> categories;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	public Photo() { }
	public Photo(String titolo, String descrizione, String url, boolean visibile, User user, Category... categories) {

		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setVisibile(visibile);
		setUser(user);
		setCategories(Arrays.asList(categories));
	}
	
	public Photo(PhotoDTO photoDto) {
	    setTitolo(photoDto.getTitolo());
	    setDescrizione(photoDto.getDescrizione());
	    setUrl(photoDto.getUrl());
	    setVisibile(photoDto.isVisibile());
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisibile() {
		return visibile;
	}
	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public boolean hasCategory(Category category) {
		if (getCategories() == null) return false;
		for (Category c : getCategories()) 
			if (category.getId() == c.getId())
				return true;
		return false;
	}
	public void addCategory(Category category) {

		getCategories().add(category);
	}
	public void removeCategory(Category category) {

		getCategories().remove(category);
	}
	
	public void fillFromPhotoDto(PhotoDTO photoDto) {
	    setTitolo(photoDto.getTitolo());
	    setDescrizione(photoDto.getDescrizione());
	    setUrl(photoDto.getUrl());
	    setVisibile(photoDto.isVisibile());
	}
	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}
	@Override
	public String toString() {

		return "[" + getId() + "] Foto:\n"
					+ "titolo: " + getTitolo() + "\n"
					+ "descrizione: " + getDescrizione() + "\n"
					+ "url: " + getUrl() + "\n"
					+ "visibile: " + isVisibile() + "\n";
    }
}
