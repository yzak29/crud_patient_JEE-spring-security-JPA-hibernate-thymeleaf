package ma.emsi.tpSpringMVC.web;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ma.emsi.tpSpringMVC.Repository.PatientRepository;
import ma.emsi.tpSpringMVC.dao.Patient;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping(path="/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(path="/deletePatient")
	public String delete(@RequestParam(name = "id")Long id ,int page , int size , String keyword) {
		patientRepository.deleteById(id);
		return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
	}
	
	@GetMapping(path="/patients")
	public String patient(Model model ,
			@RequestParam(name = "page" , defaultValue ="0") int page ,
			@RequestParam(name = "size" , defaultValue ="5") int size ,
			@RequestParam(name = "keyword" , defaultValue ="") String cs){
		Page<Patient> pagePatients = patientRepository.findByNomContains(cs, PageRequest.of(page, size));
		model.addAttribute("patients" , pagePatients.getContent());
		model.addAttribute("page", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage" , page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", cs);
		return "patients";
	}
	
	@GetMapping(path="/formPatient")
	public String formPatient(Model model){
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode", "new");
		return"formPatient";
	}
	
	@PostMapping(path="/savePatient")
	public String savePatient(Model model , @Valid Patient patient , BindingResult b,@RequestParam String mode){
		if(b.hasErrors()) {
			model.addAttribute("mode", mode);
			return "formPatient";
		}
			model.addAttribute("patient", patient);
			patientRepository.save(patient);
			return"redirect:/confirmation?id="+patient.getId()+"&nom="+patient.getNom()+"&dateNaissance="+patient.getDateNaissance()+"&score="+patient.getScore()+"&malade="+patient.isMalade()+"&mode="+mode;
	}
	
	@GetMapping(path="/editPatient")
	public String editPatient(Model model,@RequestParam long id){
		Patient patient = patientRepository.findById(id).get();
		model.addAttribute("patient", patient);
		model.addAttribute("mode", "edit");
		return"formPatient";
	}
	
	@GetMapping(path="/confirmation")
	public String confirmation(Model model , @RequestParam long id,
							   @RequestParam String nom,
							   @RequestParam String dateNaissance,
							   @RequestParam String score,
							   @RequestParam String malade,
							   @RequestParam String mode){
		model.addAttribute("id", id);
		model.addAttribute("nom", nom);
		model.addAttribute("dateNaissance", dateNaissance);
		model.addAttribute("score", score);
		model.addAttribute("malade", malade);
		model.addAttribute("mode", mode);
			return"confirmation";
	}
	

}
