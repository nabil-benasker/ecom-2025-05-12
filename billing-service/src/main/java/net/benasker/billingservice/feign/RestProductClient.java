package net.benasker.billingservice.feign;

import net.benasker.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface RestProductClient {
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable String id);
    @GetMapping("/api/products")
    PagedModel<Product> getAllProducts();
}
