package tn.iit.bean;

import org.springframework.stereotype.Component;

@Component
public class HelloBean {
	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public void m () {
		System.out.println("hello hello hello");
	}
}
