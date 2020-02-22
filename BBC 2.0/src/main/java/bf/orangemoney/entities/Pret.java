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
@Table(name = "pret")
public class Pret implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPret")
	private Integer idPret;
	
	@Column(name = "montantPret")
	private long montantPret;
	
	@Transient
	private String montPretF;
	
	@Column(name = "datePret")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date datePret;
	
	@JsonManagedReference
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client idClient;

	public Pret() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPret() {
		return idPret;
	}

	public long getMontantPret() {
		return montantPret;
	}

	public void setMontantPret(long montantPret) {
		this.montantPret = montantPret;
	}

	public String getMontPretF() {
		return montPretF;
	}

	public void setMontPretF(String montPretF) {
		this.montPretF = montPretF;
	}

	public void setIdPret(Integer idPret) {
		this.idPret = idPret;
	}

	public Date getDatePret() {
		return datePret;
	}

	public void setDatePret(Date datePret) {
		this.datePret = datePret;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}
}
