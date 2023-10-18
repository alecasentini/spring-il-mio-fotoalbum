package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "categories")
	private List<Photo> photos;

	public Category() { }
	public Category(String name, Photo... photos) {

		setName(name);
		setPhotos(Arrays.asList(photos));
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public boolean hasPhoto(Photo photo) {

		if (getPhotos() == null) return false;

		for (Photo p : getPhotos()) 
			if (p.getId() == photo.getId())
				return true;

		return false;
	}
	public void addPhotos(Photo... photos) {

		getPhotos().addAll(Arrays.asList(photos));
	}
	public void removePhotos(Photo... photos) {

		getPhotos().removeAll(Arrays.asList(photos));
	}

	@Override
	public String toString() {

		return "[" + getId() + "] " + getName();
    }
}