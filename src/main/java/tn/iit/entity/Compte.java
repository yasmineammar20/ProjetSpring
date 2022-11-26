package tn.iit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_compte")
public class Compte implements Serializable /* Spec JEE */ {

	private static final long serialVersionUID = 1L;

	@Id /* PK */
	@GeneratedValue(strategy = GenerationType.AUTO) /* auto-increment */
	@Include
	private Long rib;
	private float solde;

	@ManyToOne // defautl fetch = EAGER (client chargé)
	@JoinColumn(name = "id_client")
	private Client client;

	public Compte(float solde, Client client) {
		super();
		this.solde = solde;
		this.client = client;
	}

}