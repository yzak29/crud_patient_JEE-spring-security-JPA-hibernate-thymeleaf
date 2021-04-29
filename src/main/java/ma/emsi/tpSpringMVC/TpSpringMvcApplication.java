package ma.emsi.tpSpringMVC;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.emsi.tpSpringMVC.Repository.PatientRepository;
import ma.emsi.tpSpringMVC.dao.Patient;

@SpringBootApplication
public class TpSpringMvcApplication implements CommandLineRunner {
	
	@Autowired
	PatientRepository patientRepository ;

	public static void main(String[] args) {
		SpringApplication.run(TpSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		patientRepository.save(new Patient(null , "hamid" , new Date() , 2003 , true));
		patientRepository.save(new Patient(null , "khadija" , new Date() , 8503 , false));
		patientRepository.save(new Patient(null , "adinnnl" , new Date() , 1475 , false));
		
	}

}
