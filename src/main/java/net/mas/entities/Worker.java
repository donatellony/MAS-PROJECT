package net.mas.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Worker {
    private String name;
    private String surname;
    private float paymentPerHour;
    private static float minPaymentPerHour = 15.0f;
    private long workerId;

    public Worker() {
    }

    public Worker(String name, String surname, float paymentPerHour) {
        setName(name);
        setSurname(surname);
        setPaymentPerHour(paymentPerHour);
    }

    public static float getMinPaymentPerHour() {
        return minPaymentPerHour;
    }

    public static void setMinPaymentPerHour(float minPaymentPerHour) {
        Worker.minPaymentPerHour = minPaymentPerHour;
    }

    @Transient
    public abstract float getSalary();

    @Transient
    public String getRole(){
        return this.getClass().getSimpleName();
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getWorkerId() {
        return workerId;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    public float getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(float paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + " (" + getWorkerId() + ")";
    }
}
