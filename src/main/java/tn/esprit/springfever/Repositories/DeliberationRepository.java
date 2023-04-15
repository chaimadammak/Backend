package tn.esprit.springfever.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.esprit.springfever.entities.Claim;
import tn.esprit.springfever.entities.Deliberation;

import java.util.List;

@EnableJpaRepositories
public interface DeliberationRepository extends JpaRepository<Deliberation,Long> {


    Deliberation findByRdvDemandeCondidatUserid(Long rdv_demande_condidat_userid) ;

}
