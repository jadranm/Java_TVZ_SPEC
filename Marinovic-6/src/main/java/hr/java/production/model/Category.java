package hr.java.production.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Predstavlja kategoriju koja ima naziv i opis.
 */
public class Category extends NamedEntity implements Serializable {

    String description;

    /**
     * Konstruktor za inicijalizaciju kategorije sa zadanim nazivom i opisom.
     *
     * @param name Naziv kategorije.
     * @param description Opis kategorije.
     */


    public Category(String name, Integer id, String description) {
        super(name, id);
        this.description = description;
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }
}