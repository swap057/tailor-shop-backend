package in.sp.tailor.module.findcustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class FindCustomerController {

    @Autowired
    private FindCustomerDao findCustomerDao;

    @GetMapping("/customers")
    public List<CustomerProfileDto> getAllCustomers() {
        // Calls the DAO to get all customer data and sends it as JSON to React
        return findCustomerDao.getAllCustomersWithDetails();
    }
}