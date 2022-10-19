package models;

public enum OrderStatus {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED ("delivered");
    String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
