package in.sp.tailor.module.findcustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class FindCustomerController {

    @Autowired
    private FindCustomerDao findCustomerDao;

    @GetMapping("/customers")
    public List<CustomerProfileDto> getAllCustomers() {
        // Calls the DAO to get all ACTIVE customer data and sends it as JSON to React
        return findCustomerDao.getAllCustomersWithDetails();
    }

    // List customers that have been hidden (soft-deleted), for the Restore view
    @GetMapping("/hidden-customers")
    public List<CustomerProfileDto> getHiddenCustomers() {
        return findCustomerDao.getHiddenCustomers();
    }

    // Soft-delete: hide a customer from the directory (reversible)
    @PostMapping("/hide-customer/{id}")
    public String hideCustomer(@PathVariable int id) {
        boolean ok = findCustomerDao.setCustomerActive(id, false);
        if (ok) {
            return "Customer Hidden Successfully";
        } else {
            throw new RuntimeException("Failed to hide customer");
        }
    }

    // Restore a previously hidden customer
    @PostMapping("/restore-customer/{id}")
    public String restoreCustomer(@PathVariable int id) {
        boolean ok = findCustomerDao.setCustomerActive(id, true);
        if (ok) {
            return "Customer Restored Successfully";
        } else {
            throw new RuntimeException("Failed to restore customer");
        }
    }
}