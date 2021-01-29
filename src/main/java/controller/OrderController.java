package controller;

import model.Employee;
import model.Order;
import model.Table;
import repository.OrderRepository;
import util.ScannerExt;

import java.time.LocalDateTime;
import java.util.Arrays;

public class OrderController {

    private final ScannerExt scannerExt;
    private OrderRepository orderRepository;
    private Employee employee;

    public OrderController(ScannerExt scannerExt, Employee employee) {
        this.scannerExt = scannerExt;
        this.orderRepository=new OrderRepository();
        this.employee= employee;
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
                OrderController orderController = new OrderController(scannerExt,employee);
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
        this.orderRepository.showMyOpenOrders();
    }

    private void showMyOrdersTakedOn() {
    }

    public void addOrder(){
        Order order = new Order();
        order.setCreatedBy(employee.getId());
        order.setIsDeleted(false);
        order.setCreatedOn(LocalDateTime.now());
        order.setEmployee(employee);
        TableController tableController = new TableController(scannerExt,employee);
        Table table = tableController.selctTable();
        order.setTable(table);
        orderRepository.addOrder(order);
    }
}
