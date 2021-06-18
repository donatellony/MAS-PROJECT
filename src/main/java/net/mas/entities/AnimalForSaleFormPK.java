package net.mas.entities;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AnimalForSaleFormPK implements Serializable {
    private long clientId;
    private long animalId;

    public AnimalForSaleFormPK() {
    }

    public AnimalForSaleFormPK(long clientId, long animalId) {
        this.clientId = clientId;
        this.animalId = animalId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalForSaleFormPK that = (AnimalForSaleFormPK) o;
        return clientId == that.clientId && animalId == that.animalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getAnimalId());
    }
}