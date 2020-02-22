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
@Table(name = "retraitplacement")
public class RetraitPlacement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRetraitPlacement")
	private Integer idRetraitPlacement;
	
	@Column(name = "montantRetrait")
	private long montantRetrait;
	
	@Transient
	private String montRetraitF;
	
	@Column(name = "dateRetrait")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date dateRetrait;

	@JsonManagedReference
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client idClient;

	public RetraitPlacement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdRetraitPlacement() {
		return idRetraitPlacement;
	}

	public void setIdRetraitPlacement(Integer idRetraitPlacement) {
		this.idRetraitPlacement = idRetraitPlacement;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}

	public long getMontantRetrait() {
		return montantRetrait;
	}

	public void setMontantRetrait(long montantRetrait) {
		this.montantRetrait = montantRetrait;
	}

	public String getMontRetraitF() {
		return montRetraitF;
	}

	public void setMontRetraitF(String montRetraitF) {
		this.montRetraitF = montRetraitF;
	}

	public Date getDateRetrait() {
		return dateRetrait;
	}

	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}
}
