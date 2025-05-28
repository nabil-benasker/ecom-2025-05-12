package net.benasker.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.benasker.billingservice.model.Product;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private double price;
    private int quality;
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;
}
