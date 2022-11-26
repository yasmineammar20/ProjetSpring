package tn.iit.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tn.iit.entity.Compte;
import tn.iit.service.ClientService;
import tn.iit.service.CompteService;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
@RequestMapping("/compte")
public class CompteController {

	private final CompteService compteService;
	private final ClientService clientService;

	@Autowired
	public CompteController(CompteService compteService, ClientService clientService) {
		super();
		this.compteService = compteService;
		this.clientService = clientService;
	}

	@ResponseBody
	@GetMapping("/all-json")
	public List<Compte> getAllJson() {
		return compteService.getAll();

	}

	@GetMapping("/all")
	public ModelAndView getAll2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("comptes", compteService.getAll());
		modelAndView.setViewName("comptes");
		return modelAndView;
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "solde", required = true) float solde,
			@RequestParam(name = "cin", required = true) String cin) {
		Compte compte = new Compte(solde, clientService.getById(cin));
		compteService.save(compte);
		return "redirect:/compte/all";

	}

	@GetMapping("/delete-path/{rib}")
	public String delete_path(@PathVariable(name = "rib", required = true) Long rib) {
		compteService.delete(compteService.getById(rib));
		return "redirect:/compte/all";

	}

	@GetMapping("/delete-request")
	public String delete_request(@RequestParam(name = "rib", required = true) Long rib) {
		compteService.delete(compteService.getById(rib));
		return "redirect:/compte/all";

	}

	@ResponseBody
	@GetMapping("/delete-ajax")
	public String deleteAjax(@RequestParam(name = "rib", required = true) Long rib) {
		compteService.delete(compteService.getById(rib));
		return "redirect:/compte/all";

	}

	@PostMapping("/edit")
	public String update(Model model, @RequestParam(name = "rib", required = true) Long rib) {
		Compte compte = compteService.getById(rib);
		model.addAttribute("compte", compte);
		return "edit";
	}

	@PostMapping("/update")

	public String update(@RequestParam(name = "solde", required = true) float solde,
			@RequestParam(name = "cin", required = true) String cin,
			@RequestParam(name = "rib", required = true) Long rib) {

		Compte compte = new Compte(rib, solde, clientService.getById(cin));
		compteService.update(compte);
		return "redirect:/compte/all";

	}
}