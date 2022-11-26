package tn.iit;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.iit.dao.ClientDaoSpringData;
import tn.iit.dao.CompteDaoSpringData;
import tn.iit.entity.Client;
import tn.iit.entity.Compte;

@SpringBootApplication
public class FirstSpringBootProjectApplication implements CommandLineRunner {
	@Autowired
	private CompteDaoSpringData compteDaoSpringData;

	@Autowired
	private ClientDaoSpringData clientDaoSpringData;

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootProjectApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		Client client = new Client("057", "Ben Hmaida", "Mariam");

		clientDaoSpringData.saveAndFlush(client);

		/*Compte compte = new Compte(500, client);
		compteDaoSpringData.saveAndFlush(compte);*/

		// Client client = clientDaoSpringData.getById("057");

		// bidirectionnelle Compte compte =
		// si relation unidirectionnelle
		// List<Compte> comptes = client.getComptes() ;
		// //compteDaoSpringData.findByClientCin("057");
		// comptes.forEach(c -> System.out.println(c));
	}

}
