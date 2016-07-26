package entity;

/**
 * Created by Siry on 20.07.2016.
 */
public class Phone {

    private long id;
    private String name;
    private long price;
    private String brand;
    private String color;
    private int quantityStock;
    private int quantityOrder;

    public Phone() {
    }

    public Phone(long id, String name, long price, String brand, String color, int quantityStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.quantityStock = quantityStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        return id == phone.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", quantityStock=" + quantityStock +
                ", quantityOrder=" + quantityOrder +
                '}';
    }
}
