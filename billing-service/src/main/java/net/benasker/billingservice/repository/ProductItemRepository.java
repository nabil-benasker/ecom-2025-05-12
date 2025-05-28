package net.benasker.billingservice.repository;

import net.benasker.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
}
