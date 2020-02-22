package bf.orangemoney.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author SAWADOGO Hubert
 */
@Entity
@Table(name = "piecejustificative")
public class Piecejustificative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPiece")
    private Integer idPiece;
    
    @Size(max = 50)
    @Column(name = "libellePiece")
    private String libellePiece;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPiece", fetch = FetchType.LAZY)
    private List<Transaction> transactionList;

    public Piecejustificative() {
    }

    public Piecejustificative(Integer idPiece) {
        this.idPiece = idPiece;
    }

    public Integer getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(Integer idPiece) {
        this.idPiece = idPiece;
    }

    public String getLibellePiece() {
        return libellePiece;
    }

    public void setLibellePiece(String libellePiece) {
        this.libellePiece = libellePiece;
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
        hash += (idPiece != null ? idPiece.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piecejustificative)) {
            return false;
        }
        Piecejustificative other = (Piecejustificative) object;
        if ((this.idPiece == null && other.idPiece != null) || (this.idPiece != null && !this.idPiece.equals(other.idPiece))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "om.bf.Piecejustificative[ idPiece=" + idPiece + " ]";
    }
    
}
