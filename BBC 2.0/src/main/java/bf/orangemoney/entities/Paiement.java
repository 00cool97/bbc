/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author SAWADOGO Hubert
 */
@Entity
@Table(name = "paiement")
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPaie")
    private Integer idPaie;
    
    @Column(name = "montantPaie")
    private long montantPaie;
    
    @Transient
    private String montPaieF;
    
    @Column(name = "datePaie")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date datePaie;
    
    @JsonManagedReference
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client idClient;

    public Paiement() {
    }

    public Paiement(Integer idPaie) {
        this.idPaie = idPaie;
    }

    public Integer getIdPaie() {
        return idPaie;
    }

    public void setIdPaie(Integer idPaie) {
        this.idPaie = idPaie;
    }

	public long getMontantPaie() {
		return montantPaie;
	}

	public void setMontantPaie(long montantPaie) {
		this.montantPaie = montantPaie;
	}

	public String getMontPaieF() {
		return montPaieF;
	}

	public void setMontPaieF(String montPaieF) {
		this.montPaieF = montPaieF;
	}

	public Date getDatePaie() {
        return datePaie;
    }

    public void setDatePaie(Date datePaie) {
        this.datePaie = datePaie;
    }

    public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaie != null ? idPaie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paiement)) {
            return false;
        }
        Paiement other = (Paiement) object;
        if ((this.idPaie == null && other.idPaie != null) || (this.idPaie != null && !this.idPaie.equals(other.idPaie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "om.bf.Paiement[ idPaie=" + idPaie + " ]";
    }
    
}
