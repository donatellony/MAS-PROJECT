package net.mas.entities;

import javax.persistence.*;
import java.time.LocalDate;

enum VerificationState {ACCEPTED, DECLINED, NONE}

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Verification {
    private long verificationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private VerificationState state;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(long verificationId) {
        this.verificationId = verificationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Enumerated(EnumType.STRING)
    public VerificationState getState() {
        return state;
    }

    public void setState(VerificationState state) {
        this.state = state;
    }
}
