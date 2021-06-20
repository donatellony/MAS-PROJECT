package net.mas.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    private long clientId;
    private String name;
    private String surname;
    private String town;
    private String aboutMe;
    private boolean isBanned;
    private boolean isVeterinarian;
    private boolean isAnimalSeller;
    private Set<Document> documents = new HashSet<>();
    private Set<Picture> pictures = new HashSet<>();
    private Set<AnimalForSaleForm> animalForSaleForms = new HashSet<>();
    private Set<Animal> animals = new HashSet<>();

    public Client() {
    }

    public Client(String name, String surname, String town, String aboutMe){
        this(name, surname, town);
        setAboutMe(aboutMe);
    }

    public Client(String name, String surname, String town) {
        setName(name);
        setSurname(surname);
        setTown(town);
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getClientId() {
        return clientId;
    }

    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String email) {
        this.surname = email;
    }

    @Basic
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public Client addAnimal(Animal animal) {
        animals.add(animal);
        animal.setOwner(this);
        return this;
    }

    public Client removeAnimal(Animal animal) {
        animals.remove(animal);
        animal.setOwner(null);
        return this;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + ", from " + getTown();
    }

    @Basic
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Basic
    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Basic
    public boolean isVeterinarian() {
        return isVeterinarian;
    }

    public void setVeterinarian(boolean veterinarian) {
        isVeterinarian = veterinarian;
    }

    @Basic
    public boolean isAnimalSeller() {
        return isAnimalSeller;
    }

    public void setAnimalSeller(boolean animalSeller) {
        isAnimalSeller = animalSeller;
    }

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<AnimalForSaleForm> getAnimalForSaleForms() {
        return animalForSaleForms;
    }

    public void setAnimalForSaleForms(Set<AnimalForSaleForm> animalForSaleForms) {
        this.animalForSaleForms = animalForSaleForms;
    }
}
