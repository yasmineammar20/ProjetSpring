package tn.iit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.iit.dao.CompteDaoSpringData;
import tn.iit.entity.Compte;

@Service
public class CompteService {

	private final CompteDaoSpringData compteDaoSpringData;

	@Autowired
	public CompteService(CompteDaoSpringData compteDaoSpringData) {
		super();
		this.compteDaoSpringData = compteDaoSpringData;
	}

	public void save(Compte compte) {
		compteDaoSpringData.saveAndFlush(compte);
	}

	public List<Compte> getAll() {
		return compteDaoSpringData.findAll();

	}

	public Compte getById(Long rib) {
		return compteDaoSpringData.getById(rib);
	}

	public void delete(Compte compte) {
		compteDaoSpringData.delete(compte);
	}

	public void update(Compte compte) {
		compteDaoSpringData.saveAndFlush(compte);
	}
}