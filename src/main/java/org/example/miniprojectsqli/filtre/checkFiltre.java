package org.example.miniprojectsqli.filtre;

import org.example.miniprojectsqli.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

public class checkFiltre implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(checkFiltre.class);

    @Override
    public void isPositiveAndSupAZero(Double price) {
        if (price <= 0) {
            logger.error("The price must be greater than zero. Price: " + price);
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }

    @Override
    public void isPresent(Optional<Product> optional) {
        if (!optional.isPresent()) {
            logger.error("This product does not exist");
            throw new NoSuchElementException("This product does not exist");
        }
    }

}
