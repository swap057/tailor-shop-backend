package in.sp.tailor.module.pendingwork;
import java.util.List;

public interface PendingWorkDao {
    List<PendingOrder> getAllPendingOrders();
    PendingOrder getOrderDetails(int orderId);
    boolean updateOrderProgress(PendingOrder order);
    boolean updateOrderMeasurements(PendingOrder order);
}