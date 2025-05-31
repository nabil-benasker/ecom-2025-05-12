package net.benasker.billingservice.projection;

import net.benasker.billingservice.entities.Bill;
import net.benasker.billingservice.entities.ProductItem;
import net.benasker.billingservice.model.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

@Projection(name = "all", types = Bill.class)
public interface BillProjection {

    Long getId();
    Date getBillingDate();
    Long getCustomerId();
    List<ProductItem> getProductItems();
    Customer getCustomer();
}
