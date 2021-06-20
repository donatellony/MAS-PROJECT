package net.mas.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Document {
    private long documentId;
    private byte[] document;
    private VerificationOfDocument verification;
    private Client owner;

    public Document(){

    }

    public Document(byte[] document, Client owner){
        setDocument(document);
        setOwner(owner);
    }

    public Document(byte[] document, Client owner, VerificationOfDocument verification){
        this(document, owner);
        setVerification(verification);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verificationId", nullable = false)
    public VerificationOfDocument getVerification() {
        return verification;
    }

    public void setVerification(VerificationOfDocument verification) {
        this.verification = verification;
    }

    @Lob
    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}
