package net.mas.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AnimalForSaleForm implements Serializable {
    private String description;
    private float price;
    private AnimalForSaleFormPK formPK;
    private Client owner;
    private Animal target;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @EmbeddedId
    public AnimalForSaleFormPK getFormPK() {
        return formPK;
    }

    public void setFormPK(AnimalForSaleFormPK formPK) {
        this.formPK = formPK;
    }

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false, insertable = false, updatable = false)
    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @OneToOne(mappedBy = "form")
    public Animal getTarget() {
        return target;
    }

    public void setTarget(Animal target) {
        this.target = target;
    }
}
