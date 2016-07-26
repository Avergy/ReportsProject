package entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Siry on 22.07.2016.
 */
public class Order {

    private long id;
    private Date date;
    private String login;
    private String firstName;
    private String secondName;
    private long cost;
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(long id, Date date, String login, String firstName, String secondName,
                 long cost, List<OrderItem> orderItems) {
        this.id = id;
        this.date = date;
        this.login = login;
        this.firstName = firstName;
        this.secondName = secondName;
        this.cost = cost;
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (cost != order.cost) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (login != null ? !login.equals(order.login) : order.login != null) return false;
        if (firstName != null ? !firstName.equals(order.firstName) : order.firstName != null) return false;
        if (secondName != null ? !secondName.equals(order.secondName) : order.secondName != null) return false;
        return orderItems != null ? orderItems.equals(order.orderItems) : order.orderItems == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + (orderItems != null ? orderItems.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", cost=" + cost +
                ", orderItems=" + orderItems +
                '}';
    }
}
