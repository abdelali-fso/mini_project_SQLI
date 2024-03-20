package org.example.miniprojectsqli.filtre;

import org.example.miniprojectsqli.model.Product;

import java.util.Optional;

public interface Filter {
    void isPositiveAndSupAZero(Double prix);
    void isPresent(Optional<Product> optional) throws Exception;
}
