package net.benasker.billingservice.web;

import net.benasker.billingservice.entities.Bill;
import net.benasker.billingservice.feign.RestCustomerClient;
import net.benasker.billingservice.feign.RestProductClient;
import net.benasker.billingservice.repository.BillRepository;
import net.benasker.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    BillRepository billRepository;
    @Autowired
    ProductItemRepository productItemRepository;
    @Autowired
    RestCustomerClient restCustomerClient;
    @Autowired
    RestProductClient restProductClient;

    @GetMapping("/bill/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(restCustomerClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(restProductClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
