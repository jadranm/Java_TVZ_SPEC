package hr.java.production.model;

/**
 * Predstavlja kategoriju koja ima naziv i opis.
 */
public class Category extends NamedEntity {

    String description;

    /**
     * Konstruktor za inicijalizaciju kategorije sa zadanim nazivom i opisom.
     *
     * @param name Naziv kategorije.
     * @param description Opis kategorije.
     */
    public Category(String name, String description) {
        super(name);
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
}