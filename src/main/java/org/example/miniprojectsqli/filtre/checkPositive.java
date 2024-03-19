package org.example.miniprojectsqli.filtre;

public class checkPositive implements FilterPrice {
    @Override
    public boolean isPositiveAndSupAZero(Double prix) {
        return (prix > 0);
    }
}
