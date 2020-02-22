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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author SAWADOGO Hubert
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient")
    private Integer idClient;
    
    @Size(max = 25)
    @Column(name = "nom")
    private String nom;
    
    @Size(max = 50)
    @Column(name = "prenom")
    private String prenom;
    
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "numcnib", length = 50)
    private String numCNIB;
    
    @Column(name = "montantPaye")
    private long montantPaye;
    
    @Transient
    private String montPayeF;
    
    @Column(name = "montantCredit")
    private long montantCredit;
    
    @Transient
    private String montCreditF;
    
    @Column(name = "montantRest")
    private long montantRest;
    
    @Transient
    private String montRestF;
    
    @Column(name = "montantPlace")
    private long montantPlace;
    
    @Transient
    private String montPlaceF;
    
    @Column(name = "montantRetire")
    private long montantRetire;
    
    @Transient
    private String montRetireF;
    
    @Column(name = "montantPlaceRest")
    private long montantPlaceRest;
    
    @Transient
    private String montPlaceRestF;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient", fetch = FetchType.LAZY)
    private List<Transaction> transactionList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient", fetch = FetchType.LAZY)
    private List<Canal> canalList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient", fetch = FetchType.LAZY)
    private List<Placement> placementList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient", fetch = FetchType.LAZY)
    private List<Paiement> paiementList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient", fetch = FetchType.LAZY)
    private List<Pret> pretList;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient", fetch = FetchType.LAZY)
    private List<RetraitPlacement> retraitPlacementList;
    
    public boolean enableDelete() {
    	if(transactionList != null) {
    		return false;
    	}
    	else if(canalList != null) {
    		return false;
    	}
    	else if(placementList != null) {
    		return false;
    	}
    	else if(paiementList != null) {
    		return false;
    	}
    	else if(retraitPlacementList != null) {
    		return false;
    	}
    	return true;
    }

    public Client() {
    }

    public Client(Integer idClient) {
        this.idClient = idClient;
    }

	public String getNumCNIB() {
		return numCNIB;
	}

	public long getMontantPlaceRest() {
		return montantPlaceRest;
	}

	public void setMontantPlaceRest(long montantPlaceRest) {
		this.montantPlaceRest = montantPlaceRest;
	}

	public String getMontPlaceRestF() {
		return montPlaceRestF;
	}

	public void setMontPlaceRestF(String montPlaceRestF) {
		this.montPlaceRestF = montPlaceRestF;
	}

	public void setNumCNIB(String numCNIB) {
		this.numCNIB = numCNIB;
	}

	public Integer getIdClient() {
        return idClient;
    }

    public List<Pret> getPretList() {
		return pretList;
	}

	public List<RetraitPlacement> getRetraitPlacementList() {
		return retraitPlacementList;
	}

	public void setRetraitPlacementList(List<RetraitPlacement> retraitPlacementList) {
		this.retraitPlacementList = retraitPlacementList;
	}

	public void setPretList(List<Pret> pretList) {
		this.pretList = pretList;
	}

	public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Placement> getPlacementList() {
		return placementList;
	}

	public void setPlacementList(List<Placement> placementList) {
		this.placementList = placementList;
	}

	public List<Canal> getCanalList() {
		return canalList;
	}

	public void setCanalList(List<Canal> canalList) {
		this.canalList = canalList;
	}

	public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
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

	public long getMontantCredit() {
		return montantCredit;
	}

	public void setMontantCredit(long montantCredit) {
		this.montantCredit = montantCredit;
	}

	public String getMontCreditF() {
		return montCreditF;
	}

	public void setMontCreditF(String montCreditF) {
		this.montCreditF = montCreditF;
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

	public long getMontantPlace() {
		return montantPlace;
	}

	public void setMontantPlace(long montantPlace) {
		this.montantPlace = montantPlace;
	}

	public String getMontPlaceF() {
		return montPlaceF;
	}

	public void setMontPlaceF(String montPlaceF) {
		this.montPlaceF = montPlaceF;
	}

	public long getMontantRetire() {
		return montantRetire;
	}

	public void setMontantRetire(long montantRetire) {
		this.montantRetire = montantRetire;
	}

	public String getMontRetireF() {
		return montRetireF;
	}

	public void setMontRetireF(String montRetireF) {
		this.montRetireF = montRetireF;
	}

	public List<Paiement> getPaiementList() {
		return paiementList;
	}

	public void setPaiementList(List<Paiement> paiementList) {
		this.paiementList = paiementList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "om.bf.Client[ idClient=" + idClient + " ]";
    }
    
}
