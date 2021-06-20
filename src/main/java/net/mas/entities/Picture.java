package net.mas.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Picture {
    private long pictureId;
    private byte[] photo;
    private Client owner;

    private VerificationOfPicture verification;

    public Picture(){}

    public Picture(byte[] photo, Client owner){
        setPhoto(photo);
        setOwner(owner);
    }

    public Picture(byte[] photo, Client owner, VerificationOfPicture verification){
        this(photo, owner);
        setVerification(verification);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @Lob
//    @Column(columnDefinition = "BLOB")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verificationId", nullable = true)
    public VerificationOfPicture getVerification() {
        return verification;
    }

    public void setVerification(VerificationOfPicture verification) {
        this.verification = verification;
    }
}
