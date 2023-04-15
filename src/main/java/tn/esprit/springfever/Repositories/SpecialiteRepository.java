package tn.esprit.springfever.Repositories;
import org.springframework.stereotype.Repository;
import tn.esprit.springfever.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
}
