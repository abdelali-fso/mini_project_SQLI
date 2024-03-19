package org.example.miniprojectsqli.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity product have id auto generated
 * and the code unique
 * and the price must be greater than strictly zero
 * and libelle
 * and for each product we have category
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(unique = true)
    private Long code;
    private String libelle;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
