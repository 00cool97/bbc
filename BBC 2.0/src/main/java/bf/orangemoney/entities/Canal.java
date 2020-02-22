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

@Entity
@Table(name = "canal")
public class Canal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCanal")
	private Integer idCanal;
	
	@Column(name = "numCarte")
	private String numCarte;
	
	@Column(name = "numAbonne")
	private String numAbonne;
	
	@Column(name = "duree")
	private Integer duree;
	
	@Column(name = "montantAbonnee")
	private long montantAbonnee;
	
	@Transient
	private String montAbonneeF;
	
	@Column(name = "montantPaye")
	private long montantPaye;
	
	@Transient
	private String montPayeF;
	
	@Column(name = "montantRest")
	private long montantRest;
	
	@Transient
	private String montRestF;
	
	@Column(name = "dateAbonnement")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date dateAbonnement;
	
	@JsonManagedReference
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client idClient;

	public Canal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(Integer idCanal) {
		this.idCanal = idCanal;
	}

	public String getNumCarte() {
		return numCarte;
	}

	public void setNumCarte(String numCarte) {
		this.numCarte = numCarte;
	}

	public String getNumAbonne() {
		return numAbonne;
	}

	public void setNumAbonne(String numAbonne) {
		this.numAbonne = numAbonne;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public long getMontantAbonnee() {
		return montantAbonnee;
	}

	public void setMontantAbonnee(long montantAbonnee) {
		this.montantAbonnee = montantAbonnee;
	}

	public String getMontAbonneeF() {
		return montAbonneeF;
	}

	public void setMontAbonneeF(String montAbonneeF) {
		this.montAbonneeF = montAbonneeF;
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

	public Date getDateAbonnement() {
		return dateAbonnement;
	}

	public void setDateAbonnement(Date dateAbonnement) {
		this.dateAbonnement = dateAbonnement;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}
}
