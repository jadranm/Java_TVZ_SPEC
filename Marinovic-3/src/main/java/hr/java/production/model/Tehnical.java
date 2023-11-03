package hr.java.production.model;

/**
 * Sučelje koje definira tehničke karakteristike proizvoda.
 * Samo klasa Laptop je dozvoljena implementacija ovog sučelja.
 */
public sealed interface Tehnical permits Laptop {


    /**
     * Vraća trajanje jamstva proizvoda.
     *
     * @return Trajanje jamstva proizvoda u mjesecima.
     */
    Integer getWarrantyDuration();
}
