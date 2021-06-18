package net.mas.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Moderator extends Worker {
    private short knowledgeLevel;

    public Moderator() {
    }

    public Moderator(String name, String surname, float paymentPerHour, short knowledgeLevel) {
        super(name, surname, paymentPerHour);
        setKnowledgeLevel(knowledgeLevel);
    }

    @Override
    @Transient
    public float getSalary() {
        float result = getPaymentPerHour() * 40 * 4;
        if (getKnowledgeLevel() >= 9)
            result += 7000;
        else if (getKnowledgeLevel() >= 5)
            result += 4000;
        else if (getKnowledgeLevel() >= 3)
            result += 1000;
        return result;
    }

    @Basic
    public short getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(short knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }
}
