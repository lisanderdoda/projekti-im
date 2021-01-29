package controller;

import model.Employee;
import model.MenuItem;
import model.Order;
import model.OrdersItems;
import repository.OrderItemRepository;
import util.ScannerExt;

import java.time.LocalDate;

public class OrderItemController {

    private final ScannerExt scannerExt;
    private OrderItemRepository orderItemRepository;
    private Employee employee;

    public OrderItemController(ScannerExt scannerExt,Employee employee) {
        this.scannerExt = scannerExt;
        this.employee=employee;
        orderItemRepository=new OrderItemRepository();
    }

    public void addOrderItem(MenuItem menuItem, Order order){
        OrdersItems ordersItem = new OrdersItems();
        ordersItem.setCreatedBy(employee.getId());
        ordersItem.setOrder(order);
        System.out.println("Vendos numrin sa");
        int quantity =scannerExt.scanNumberField();
        ordersItem.setQuantity(quantity);
        ordersItem.setMenuItem(menuItem);
        ordersItem.setCreatedOn(LocalDate.now());
        ordersItem.setDeleted(false);
        ordersItem.setPrice(quantity * menuItem.getUnitPrice());
        orderItemRepository.addOrderItem(ordersItem);

    }


}
