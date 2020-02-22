package bf.orangemoney.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author SAWADOGO Hubert
 */
@Entity
@Table(name = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduit")
    private Integer idProduit;
    
    @Size(max = 50)
    @Column(name = "libelleProduit")
    private String libelleProduit;
    
    @Column(name = "etatDepotRetrait")
    private Boolean etatDepotRetrait;
    
    @Column(name = "etatRecharge")
    private Boolean etatRecharge;

    @Column(name = "dotationProduit")
    private long dotationProduit;
    
    @JsonBackReference
    @ManyToMany(mappedBy = "produitList")
    private List<Operation> operationList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduit", fetch = FetchType.LAZY)
    private List<Transaction> transactionList;

    public Produit() {
    }

    public Produit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public Boolean getEtatDepotRetrait() {
		return etatDepotRetrait;
	}

	public void setEtatDepotRetrait(Boolean etatDepotRetrait) {
		this.etatDepotRetrait = etatDepotRetrait;
	}

	public Boolean getEtatRecharge() {
		return etatRecharge;
	}

	public void setEtatRecharge(Boolean etatRecharge) {
		this.etatRecharge = etatRecharge;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public long getDotationProduit() {
		return dotationProduit;
	}

	public void setDotationProduit(long dotationProduit) {
		this.dotationProduit = dotationProduit;
	}

	public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "om.bf.Produit[ idProduit=" + idProduit + " ]";
    }
    
}
