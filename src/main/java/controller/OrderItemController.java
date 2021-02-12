package controller;

import model.Employee;
import model.MenuItem;
import model.Order;
import model.OrdersItems;
import repository.OrderItemRepository;
import util.ScannerExt;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderItemController {

    private final ScannerExt scannerExt;
    private OrderItemRepository orderItemRepository;

    public OrderItemController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        orderItemRepository=new OrderItemRepository();
    }

    public void addOrderItem(MenuItem menuItem, Order order){
        OrdersItems ordersItem = new OrdersItems();
        ordersItem.setCreatedBy(EmployeeController.getCurrentEmployee().getId());
        ordersItem.setOrder(order);
        System.out.println("Vendos numrin sa");
        int quantity =scannerExt.scanNumberField();
        ordersItem.setQuantity(quantity);
        ordersItem.setMenuItem(menuItem);
        ordersItem.setCreatedOn(LocalDateTime.now());
        ordersItem.setPrice(quantity * menuItem.getUnitPrice());
        orderItemRepository.save(ordersItem);

    }


}
