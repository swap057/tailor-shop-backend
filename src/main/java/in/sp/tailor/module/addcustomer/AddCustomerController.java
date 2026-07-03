package in.sp.tailor.module.addcustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class AddCustomerController {

    @Autowired
    private AddCustomerDao addCustomerDao;

    // Matches 'addCustomer' in api.js
    @PostMapping("/add-customer")
    public int addCustomer(@RequestBody Customer customer) {
        // Frontend sends: fullName, mobileNo, address, photoPath
        return addCustomerDao.saveCustomer(customer);
    }

    // Update an existing customer's profile (name, mobile, village)
    @PostMapping("/update-customer")
    public String updateCustomer(@RequestBody Customer customer) {
        boolean ok = addCustomerDao.updateCustomer(customer);
        if (ok) {
            return "Customer Updated Successfully";
        } else {
            throw new RuntimeException("Failed to update customer");
        }
    }

    // Matches 'addOrder' in api.js
    @PostMapping("/add-order")
    public String addOrder(@RequestBody ShopOrder order) {
        // Frontend sends: customerId, deadlineDate, shirtLength... remark, etc.
        int result = addCustomerDao.saveOrder(order);
        if(result > 0) {
            return "Order Saved Successfully";
        } else {
            throw new RuntimeException("Failed to save order");
        }
    }
}