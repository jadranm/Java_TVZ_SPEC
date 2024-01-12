package hr.java.shop.demo.domain;

import java.math.BigDecimal;

/**
 * Sučelje koje predstavlja jestive stavke.
 * Implementirane klase moraju pružiti metode za izračun broja kilokalorija i cijene za jestive stavke.
 */
public interface Edible {
    /**
     * Računa broj kilokalorija za ovu jestivu stavku.
     *
     * @return Broj kilokalorija za ovu jestivu stavku.
     */
    BigDecimal calculateCalories();

    /**
     * Računa cijenu za ovu jestivu stavku.
     *
     * @return Cijena za ovu jestivu stavku.
     */
    BigDecimal calculatePrice();

}
