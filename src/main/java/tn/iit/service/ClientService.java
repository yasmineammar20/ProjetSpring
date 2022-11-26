package tn.iit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.iit.dao.ClientDaoSpringData;
import tn.iit.dao.CompteDaoSpringData;
import tn.iit.entity.Client;
import tn.iit.entity.Compte;

@Service
public class ClientService {

	private final ClientDaoSpringData clientDaoSpringData;

	@Autowired
	public ClientService(ClientDaoSpringData clientDaoSpringData) {
		super();
		this.clientDaoSpringData = clientDaoSpringData;
	}

	public void save(Client client) {
		clientDaoSpringData.saveAndFlush(client);
	}

	public List<Client> getAll() {
		return clientDaoSpringData.findAll();

	}

	public Client getById(String cin) {
		return clientDaoSpringData.getById(cin);
	}

	public void delete(Client client) {
		clientDaoSpringData.delete(client);
	}

	public void update(Client client) {
		clientDaoSpringData.saveAndFlush(client);
	}
}