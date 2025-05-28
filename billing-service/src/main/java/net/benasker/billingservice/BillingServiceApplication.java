package net.benasker.billingservice;

import net.benasker.billingservice.entities.Bill;
import net.benasker.billingservice.entities.ProductItem;
import net.benasker.billingservice.feign.RestCustomerClient;
import net.benasker.billingservice.feign.RestProductClient;
import net.benasker.billingservice.model.Customer;
import net.benasker.billingservice.model.Product;
import net.benasker.billingservice.repository.BillRepository;
import net.benasker.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        RestCustomerClient restCustomerClient,
                                        RestProductClient restProductClient) {

        return args -> {
            Collection<Customer> content = restCustomerClient.getAllCustomer().getContent();
            content.forEach(customer -> {
                Bill bill = billRepository.save(Bill.builder()
                        .billingDate(new Date())
                        .customerId(customer.getId().toString())
                        .customer(customer).
                        build());
                billRepository.save(bill);

                Collection<Product> products = restProductClient.getAllProducts().getContent();
                products.forEach(product -> {
                    productItemRepository.save(ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quality(1 + new Random().nextInt(10))
                            .price(product.getPrice())
                            .build());
                });

            });


        };

    }

}
