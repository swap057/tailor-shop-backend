package in.sp.tailor.module.findcustomer;

import java.util.List;

public interface FindCustomerDao {

    List<CustomerProfileDto> getAllCustomersWithDetails();
    List<CustomerProfileDto> getHiddenCustomers();
    boolean setCustomerActive(int customerId, boolean active);
}