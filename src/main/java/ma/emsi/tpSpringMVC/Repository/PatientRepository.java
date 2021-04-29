package ma.emsi.tpSpringMVC.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.tpSpringMVC.dao.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	public Page<Patient> findByNomContains(String cs , Pageable pageable);
}
