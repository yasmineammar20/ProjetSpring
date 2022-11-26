package tn.iit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "t_client")
public class Client implements Serializable /* Spec JEE */ {

	private static final long serialVersionUID = 1L;

	@Id /* PK */
	@Include
	@Column(length = 20)
	private String cin;
	private String nom;
	private String prenom;

	/*
	 * @OneToMany(mappedBy = "client") // default fetch = LAZY
	 * 
	 * @Exclude
	 * 
	 * @JsonIgnore private List<Compte> comptes;
	 */

}