package tn.esprit.springfever.Repositories;

import org.springframework.stereotype.Repository;
import tn.esprit.springfever.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}
