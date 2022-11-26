package tn.iit.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.dao.ClientDaoSpringData;
import tn.iit.entity.Client;
import tn.iit.entity.Compte;
import tn.iit.service.ClientService;
import tn.iit.service.CompteService;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
@RequestMapping("/client")
public class ClientController {

	private final CompteService compteService;
	private final ClientService clientService;

	@Autowired
	ClientDaoSpringData tutorialRepository;

	@Autowired
	public ClientController(CompteService compteService, ClientService clientService) {
		super();
		this.compteService = compteService;
		this.clientService = clientService;
	}

	@ResponseBody
	@GetMapping("/all-json")
	public List<Client> getAllJson() {
		return clientService.getAll();

	}

	@GetMapping("/all")
	public ModelAndView getAll2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("client", clientService.getAll());
		modelAndView.setViewName("client");
		return modelAndView;
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "cin", required = true) String cin,
			@RequestParam(name = "nom", required = true) String nom,
			@RequestParam(name = "prenom", required = true) String prenom) {
		Client client = new Client(cin, nom, prenom);// FIXME
		clientService.save(client);
		return "redirect:/client/all";

	}

	@PostMapping("/save-client")
	public ResponseEntity<Client> createTutorial(@RequestBody Client tutorial) {
		try {
			Client _tutorial = tutorialRepository.save(new Client(tutorial.getCin(),tutorial.getNom(), tutorial.getPrenom()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/getid/{cin}")
	public ResponseEntity<Client> getTutorialById(@PathVariable("cin") String cin) {
		Optional<Client> tutorialData = tutorialRepository.findById(cin);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{cin}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("cin") String cin) {
		try {
			tutorialRepository.deleteById(cin);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/deleteall")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping("/delete-path/{cin}")
	public String delete_path(@PathVariable(name = "cin", required = true) String cin) {
		clientService.delete(clientService.getById(cin));
		return "redirect:/client/all";

	}
	/*
	 * @GetMapping("/delete-request") public String delete_all() {
	 * clientService.delete(clientService.getAll()); return "redirect:/client/all";
	 * 
	 * }
	 */

	@GetMapping("/delete-request/{cin}")
	public String delete_request(@RequestParam(name = "cin", required = true) String cin) {
		clientService.delete(clientService.getById(cin));
		return "redirect:/client/all";

	}

	@ResponseBody
	@GetMapping("/delete-ajax")
	public String deleteAjax(@RequestParam(name = "cin", required = true) String cin) {
		clientService.delete(clientService.getById(cin));
		return "redirect:/client/all";

	}

	/*
	 * @PostMapping("/edit") public String update(Model model, @RequestParam(name =
	 * "cin", required = true) String cin) { Client client =
	 * clientService.getById(cin); model.addAttribute("client", client); return
	 * "editClient"; }
	 */

	@PutMapping("/edit/{cin}")
	public ResponseEntity<Client> updateTutorial(@PathVariable("cin") String cin, @RequestBody Client tutorial) {
		Optional<Client> tutorialData = tutorialRepository.findById(cin);

		if (tutorialData.isPresent()) {
			Client _tutorial = tutorialData.get();
			_tutorial.setNom(tutorial.getNom());
			_tutorial.setPrenom(tutorial.getPrenom());
			_tutorial.setCin(tutorial.getCin());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/update")
	// @modelAttribut ne5o compte el kol
	public String update(@RequestParam(name = "cin", required = true) String cin,
			@RequestParam(name = "nom", required = true) String nom,
			@RequestParam(name = "prenom", required = true) String prenom) {

		Client client = new Client(cin, nom, prenom);// fixme
		clientService.update(client);
		return "redirect:/client/all";

	}
}