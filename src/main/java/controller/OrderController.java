package controller;

import model.Employee;
import model.Order;
import model.Table;
import repository.OrderRepository;
import util.ScannerExt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrderController {

    private final ScannerExt scannerExt;
    private OrderRepository orderRepository;

    public OrderController(ScannerExt scannerExt) {
        this.scannerExt = scannerExt;
        this.orderRepository=new OrderRepository();

    }

    public void showMyOrders(){

            System.out.println("Zgjidhni nje nga opsionet me poshte!");
            boolean logout = true;
            while(logout){
                System.out.println("Zgjidhni nje nga opsionet me poshte!");
                System.out.println("1.Porosite e dites!");
                System.out.println("2.Porosite e hapura");
                System.out.println("3.Logout!");

                Integer choise = this.scannerExt.scanRestrictedFieldNumber(Arrays.asList(1,2,3));
                OrderController orderController = new OrderController(scannerExt);
                switch (choise){
                    case 1:
                    showMyOrdersTakedOn();break;
                    case 2:
                        showMyOpenOrders();break;
                    case 3: logout = false; break;
                    default: break;
                }
            }
        }

    private void showMyOpenOrders() {


    }

    private void showMyOrdersTakedOn() {
    }

    public Order addOrder(){
        Order order = new Order();
        order.setCreatedBy(EmployeeController.getCurrentEmployee().getId());
        order.setIsDeleted(false);
        order.setCreatedOn(LocalDateTime.now());
        order.setEmployee(EmployeeController.getCurrentEmployee());
        TableController tableController = new TableController(scannerExt);
        Table table = tableController.selctTable();
        order.setTable(table);
        orderRepository.save(order);
        return order;
    }
}
