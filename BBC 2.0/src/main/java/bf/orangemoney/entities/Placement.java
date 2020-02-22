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
@Table(name = "placement")
public class Placement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlacement")
	private Integer idPlacement;
	
	@Column(name = "montantPlacement")
	private long montantPlacement;
	
	@Transient
	private String montPlacementF;
	
	@Column(name = "datePlacement")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	private Date datePlacement;
	
	@JsonManagedReference
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client idClient;
	
	public Placement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPlacement() {
		return idPlacement;
	}

	public void setIdPlacement(Integer idPlacement) {
		this.idPlacement = idPlacement;
	}
	
	public long getMontantPlacement() {
		return montantPlacement;
	}

	public void setMontantPlacement(long montantPlacement) {
		this.montantPlacement = montantPlacement;
	}

	public String getMontPlacementF() {
		return montPlacementF;
	}

	public void setMontPlacementF(String montPlacementF) {
		this.montPlacementF = montPlacementF;
	}

	public Date getDatePlacement() {
		return datePlacement;
	}

	public void setDatePlacement(Date datePlacement) {
		this.datePlacement = datePlacement;
	}

	public Client getIdClient() {
		return idClient;
	}

	public void setIdClient(Client idClient) {
		this.idClient = idClient;
	}
}
