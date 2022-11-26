package tn.iit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import tn.iit.entity.Compte;

@Repository
public class CompteDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Compte compte) {
		em.persist(compte);
	}

	public Compte getById(Long rib) {
		return em.find(Compte.class, rib);

	}

	@Transactional
	public void delete(Compte compte) {
		em.remove(compte);
	}

	@Transactional
	public void update(Compte compte) {
		em.merge(compte);
	}

	public List<Compte> getAll() {
		return em.createQuery("select c from Compte c", Compte.class).getResultList();
	}
}
