package org.java.app.db.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 128, nullable = false)
	private String titolo;

	@Column(length = 256)
	private String descrizione;

	@Column(length = 256)
	private String url;

	private boolean visibile;

	public Photo() { }
	public Photo(String titolo, String descrizione, String url, boolean visibile) {

		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setVisibile(visibile);
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

	@Override
	public String toString() {

		return "[" + getId() + "] Foto:\n"
					+ "titolo: " + getTitolo() + "\n"
					+ "descrizione: " + getDescrizione() + "\n"
					+ "url: " + getUrl() + "\n"
					+ "visibile: " + isVisibile() + "\n";
    }
}
