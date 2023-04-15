package tn.esprit.springfever.Repositories;
import org.springframework.stereotype.Repository;
import tn.esprit.springfever.entities.DemandeAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.springfever.entities.Salle;
import tn.esprit.springfever.model.Diplome;

import java.time.LocalDate;

@Repository
public interface DemandeAdmissionRepository extends JpaRepository<DemandeAdmission, Long> {

    public DemandeAdmission findByRdvDemandeSalle(Salle rdvDemande_salle) ;

    public DemandeAdmission findDemandeAdmissionByEvaluateurAndDateAdmission(Long evaluateur, LocalDate dateAdmission) ;

    Long countByDiplome(Diplome PREPA);

}
