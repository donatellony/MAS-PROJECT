package net.mas.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
public class VerificationOfDocument extends Verification{
    private Document target;

    public VerificationOfDocument(){

    }

    @Override
    @Transient
    public String getType() {
        return "Document";
    }

    public VerificationOfDocument(LocalDate startDate, Verifier verifier, Document target){
        super(startDate, verifier);
        setTarget(target);
    }

    @OneToOne(mappedBy = "verification")
    public Document getTarget() {
        return target;
    }

    public void setTarget(Document target) {
        this.target = target;
    }
}
