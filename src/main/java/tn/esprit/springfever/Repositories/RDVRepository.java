package tn.esprit.springfever.Repositories;
import org.springframework.stereotype.Repository;
import tn.esprit.springfever.entities.RDV;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RDVRepository extends JpaRepository<RDV, Long> {
}
