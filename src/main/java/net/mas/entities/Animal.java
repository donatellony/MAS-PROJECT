package net.mas.entities;

import javax.persistence.*;

enum AnimalSize {EXTRA_SMALL, SMALL, MEDIUM, BIG, EXTRA_BIG}
enum AnimalStatus {LOOKING_FOR_LOVE, FOR_SALE, DELETED, NONE}

@Entity
public class Animal {
    private long animalId;
    private String name;
    private String breed;
    private String type;
    private boolean isMale;

    private AnimalSize size;
    private AnimalStatus status;

    private Client owner;
    private AnimalForSaleForm form;

    public Animal() {
    }

    public Animal(String name, String breed, AnimalSize size) {
        setName(name);
        setBreed(breed);
        setSize(size);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Enumerated(EnumType.STRING)
    public AnimalSize getSize() {
        return size;
    }

    public void setSize(AnimalSize size) {
        this.size = size;
    }

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return getName() + ", the " + "(" + getBreed() + ") has " + getSize() + " size";
    }

    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    @Enumerated(EnumType.STRING)
    public AnimalStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientId", nullable = true, insertable = false, updatable = false)
    @JoinColumn(name = "animalId", nullable = true, insertable = false, updatable = false)
    public AnimalForSaleForm getForm() {
        return form;
    }

    public void setForm(AnimalForSaleForm form) {
        this.form = form;
    }
}
