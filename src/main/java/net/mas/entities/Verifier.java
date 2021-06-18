package net.mas.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Verifier extends Worker {
    private int verificationsNumber;

    public Verifier() {
    }

    public Verifier(String name, String surname, float paymentPerHour, int verificationsNumber) {
        super(name, surname, paymentPerHour);
        setVerificationsNumber(verificationsNumber);
    }

    @Override
    @Transient
    public float getSalary() {
        float salary = getPaymentPerHour() * 40 * 4;
        if (getVerificationsNumber() >= 100)
            salary += 1500;
        else if (getVerificationsNumber() >= 40)
            salary += 1000;
        else if (getVerificationsNumber() >= 20)
            salary += 500;
        return salary;
    }

    @Basic
    public int getVerificationsNumber() {
        return verificationsNumber;
    }

    public void setVerificationsNumber(int verificationsNumber) {
        this.verificationsNumber = verificationsNumber;
    }
}
