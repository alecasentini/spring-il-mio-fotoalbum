package org.java.app.api.dto;

import java.util.List;

public class PhotoDTO {

    private int id;
    private String titolo;
    private String descrizione;
    private String url;
    private boolean visibile;
    private List<Integer> categoryIds;

    public PhotoDTO() { }

    public PhotoDTO(String titolo, String descrizione, String url, boolean visibile, List<Integer> categoryIds) {
        setTitolo(titolo);
        setDescrizione(descrizione);
        setUrl(url);
        setVisibile(visibile);
        setCategoryIds(categoryIds);
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
    
	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
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
