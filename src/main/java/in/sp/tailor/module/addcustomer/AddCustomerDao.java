package in.sp.tailor.module.addcustomer;

public interface AddCustomerDao {
    int saveCustomer(Customer c);
    int saveOrder(ShopOrder o);
}