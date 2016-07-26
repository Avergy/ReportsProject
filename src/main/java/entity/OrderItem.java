package entity;

/**
 * Created by Siry on 22.07.2016.
 */
public class OrderItem {

    private long idOrder;
    private int quantity;

    private long idPhone;
    private String name;
    private long price;
    private String brand;
    private String color;
    private int quantityStock;

    public OrderItem() {
    }

    public OrderItem(long idOrder, int quantity, long idPhone, String name,
                     long price, String brand, String color, int quantityStock) {
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.idPhone = idPhone;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.quantityStock = quantityStock;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(long idPhone) {
        this.idPhone = idPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (idOrder != orderItem.idOrder) return false;
        if (quantity != orderItem.quantity) return false;
        if (idPhone != orderItem.idPhone) return false;
        if (price != orderItem.price) return false;
        if (quantityStock != orderItem.quantityStock) return false;
        if (name != null ? !name.equals(orderItem.name) : orderItem.name != null) return false;
        if (brand != null ? !brand.equals(orderItem.brand) : orderItem.brand != null) return false;
        return color != null ? color.equals(orderItem.color) : orderItem.color == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idOrder ^ (idOrder >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (int) (idPhone ^ (idPhone >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + quantityStock;
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "idOrder=" + idOrder +
                ", quantity=" + quantity +
                ", idPhone=" + idPhone +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", quantityStock=" + quantityStock +
                '}';
    }
}
