package hr.java.production.model;

/**
 * Apstraktna osnovna klasa koja predstavlja entitet sa nazivom.
 * Svi entiteti koji imaju naziv trebaju naslijediti ovu klasu.
 */
public abstract class NamedEntity {

    String name;


    /**
     * Konstruktor za inicijalizaciju entiteta sa zadanim nazivom.
     *
     * @param name Naziv entiteta.
     */
    public NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
