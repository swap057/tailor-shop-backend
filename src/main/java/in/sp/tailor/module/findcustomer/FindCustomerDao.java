package in.sp.tailor.module.findcustomer;

import java.util.List;

public interface FindCustomerDao {
   
    List<CustomerProfileDto> getAllCustomersWithDetails();
}