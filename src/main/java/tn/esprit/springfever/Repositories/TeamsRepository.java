package tn.esprit.springfever.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.esprit.springfever.entities.Teams;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
public interface TeamsRepository extends JpaRepository <Teams,Long> {
    Teams findByIdTeam(Long idTeam ) ;

    default List<Teams> findbyIdProject(Long idProject) {
        return null;
    }
}
