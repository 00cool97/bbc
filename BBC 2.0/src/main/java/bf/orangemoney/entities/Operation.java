package bf.orangemoney.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOperation")
    private Integer idOperation;
    
    @Size(max = 50)
    @Column(name = "libelleOperation")
    private String libelleOperation;
    
    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "produit_operation", joinColumns = {
        @JoinColumn(name = "idOperation", referencedColumnName = "idOperation")}, inverseJoinColumns = {
        @JoinColumn(name = "idProduit", referencedColumnName = "idProduit")})
    private List<Produit> produitList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperation", fetch = FetchType.LAZY)
    private List<Transaction> transactionList;

    public Operation() {
    }

    public Operation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public String getLibelleOperation() {
        return libelleOperation;
    }

    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }

    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperation != null ? idOperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.idOperation == null && other.idOperation != null) || (this.idOperation != null && !this.idOperation.equals(other.idOperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "om.bf.Operation[ idOperation=" + idOperation + " ]";
    }
    
}
