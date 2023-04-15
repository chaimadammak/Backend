package tn.esprit.springfever.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.springfever.entities.Mcq;


@Repository
public interface McqRepository extends JpaRepository<Mcq, Long> {

}