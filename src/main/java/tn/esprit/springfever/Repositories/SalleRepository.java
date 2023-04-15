package tn.esprit.springfever.Repositories;

import org.springframework.stereotype.Repository;
import tn.esprit.springfever.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {


    List<Salle> findByEtat(String etat);

}
