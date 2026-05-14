package in.sp.tailor.module.pendingwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PendingWorkController {

    @Autowired
    private PendingWorkDao pendingWorkDao;

    @GetMapping("/pending-orders")
    public List<PendingOrder> getPendingOrders() {
        return pendingWorkDao.getAllPendingOrders();
    }

    @GetMapping("/order-details/{orderId}")
    public PendingOrder getOrderDetails(@PathVariable int orderId) {
        return pendingWorkDao.getOrderDetails(orderId);
    }

    @PostMapping("/update-progress")
    public String updateProgress(@RequestBody PendingOrder order) {
        boolean success = pendingWorkDao.updateOrderProgress(order);
        return success ? "Progress Updated Successfully" : "Update Failed";
    }
}