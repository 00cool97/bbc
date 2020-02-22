package bf.orangemoney.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author SAWADOGO Hubert
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrans")
    private Integer idTrans;
    
    @Column(name = "montantTrans")
    private long montantTrans;
    
    @Transient
    private String montTransF;
    
    @Column(name = "montantPaye")
    private long montantPaye;
    
    @Transient
    private String montPayeF;
    
    @Column(name = "montantRest")
    private long montantRest;
    
    @Transient
    private String montRestF;
    
    @Column(name = "dateTrans")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateTrans;
    
    @Column(name = "referencePiece", length = 50)
    private String referencePiece;
    
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    
    @JsonManagedReference
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client idClient;
    
    @JsonManagedReference
    @JoinColumn(name = "idOperation", referencedColumnName = "idOperation")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Operation idOperation;
    
    @JsonManagedReference
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produit idProduit;
    
    @JsonManagedReference
    @JoinColumn(name = "idPiece", referencedColumnName = "idPiece")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Piecejustificative idPiece;

    public Transaction() {
    }

    public Transaction(Integer idTrans) {
        this.idTrans = idTrans;
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
    }

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Produit getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Produit idProduit) {
		this.idProduit = idProduit;
	}

	public String getReferencePiece() {
		return referencePiece;
	}

	public void setReferencePiece(String referencePiece) {
		this.referencePiece = referencePiece;
	}

	public long getMontantTrans() {
		return montantTrans;
	}

	public void setMontantTrans(long montantTrans) {
		this.montantTrans = montantTrans;
	}

	public String getMontTransF() {
		return montTransF;
	}

	public void setMontTransF(String montTransF) {
		this.montTransF = montTransF;
	}

	public long getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(long montantPaye) {
		this.montantPaye = montantPaye;
	}

	public String getMontPayeF() {
		return montPayeF;
	}

	public void setMontPayeF(String montPayeF) {
		this.montPayeF = montPayeF;
	}

	public long getMontantRest() {
		return montantRest;
	}

	public void setMontantRest(long montantRest) {
		this.montantRest = montantRest;
	}

	public String getMontRestF() {
		return montRestF;
	}

	public void setMontRestF(String montRestF) {
		this.montRestF = montRestF;
	}

	public Date getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(Date dateTrans) {
        this.dateTrans = dateTrans;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Operation getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Operation idOperation) {
        this.idOperation = idOperation;
    }

    public Piecejustificative getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(Piecejustificative idPiece) {
        this.idPiece = idPiece;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrans != null ? idTrans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.idTrans == null && other.idTrans != null) || (this.idTrans != null && !this.idTrans.equals(other.idTrans))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "om.bf.Transaction[ idTrans=" + idTrans + " ]";
    }
    
}
