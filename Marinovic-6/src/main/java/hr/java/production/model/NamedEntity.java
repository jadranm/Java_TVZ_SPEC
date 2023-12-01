package hr.java.production.model;

import java.util.Objects;

/**
 * Apstraktna osnovna klasa koja predstavlja entitet sa nazivom.
 * Svi entiteti koji imaju naziv trebaju naslijediti ovu klasu.
 */
public abstract class NamedEntity {

    String name;
    Integer id;

    /**
     * Konstruktor za inicijalizaciju entiteta sa zadanim nazivom.
     *
     * @param name Naziv entiteta.
     */

    public NamedEntity(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedEntity that = (NamedEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
