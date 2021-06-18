package net.mas.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class VerificationOfDocument extends Verification{
    private Document target;

    @OneToOne(mappedBy = "verification")
    public Document getTarget() {
        return target;
    }

    public void setTarget(Document target) {
        this.target = target;
    }
}
