package net.benasker.customerservice;

import net.benasker.customerservice.config.CustomerConfigParams;
import net.benasker.customerservice.entities.Customer;
import net.benasker.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {

            customerRepository.save(Customer.builder().
                    name("nabil").email("nabil.benasker@gmail.com").build());
            customerRepository.save(Customer.builder().name("beya").email("beya.benasker@gmail.com").build());
			customerRepository.findAll().forEach(System.out::println);
        };
    }


}
