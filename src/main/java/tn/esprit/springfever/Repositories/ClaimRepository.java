package tn.esprit.springfever.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.esprit.springfever.entities.Claim;
import tn.esprit.springfever.enums.ClaimStatus;
import tn.esprit.springfever.enums.ClaimSubject;

import java.util.List;

@EnableJpaRepositories
public interface ClaimRepository extends JpaRepository<Claim,Long> {

    public Claim findClaimByIdClaim(Long idClaim) ;
    @Query("SELECT C FROM Claim C  WHERE  C.claimStatus=:claimStatus and C.claimSubject=:claimSubject ")
    public List<Claim> findAllByClaimSubjectAndClaimStatus(ClaimSubject claimSubject, ClaimStatus claimStatus) ;

    public List<Claim> findByUsersUserid(Long users_userid) ;

}
