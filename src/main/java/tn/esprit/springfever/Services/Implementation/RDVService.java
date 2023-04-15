package tn.esprit.springfever.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.springfever.configuration.MailConfiguration;
import tn.esprit.springfever.entities.DemandeAdmission;
import tn.esprit.springfever.entities.RDV;
import tn.esprit.springfever.model.RDVDTO;
import tn.esprit.springfever.Repositories.DemandeAdmissionRepository;
import tn.esprit.springfever.Repositories.RDVRepository;
import tn.esprit.springfever.Repositories.SalleRepository;
import tn.esprit.springfever.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RDVService {


    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SalleService salleService;
    @Autowired
    private MailConfiguration mailConfiguration;
    @Autowired
    private DemandeAdmissionRepository demandeAdmissionRepository;

    @Autowired
    private  RDVRepository rDVRepository;



    public RDVService( RDVRepository rDVRepository) {
        this.rDVRepository = rDVRepository;

    }



    public List<RDVDTO> findAll() {
         List<RDV> rDVs = rDVRepository.findAll(Sort.by("idRDV"));
        return rDVs.stream()
                .map((rDV) -> mapToDTO(rDV, new RDVDTO()))
                .collect(Collectors.toList());
    }

    public RDVDTO get( Long idRDV) {
        return rDVRepository.findById(idRDV)
                .map(rDV -> mapToDTO(rDV, new RDVDTO()))
                .orElseThrow(NotFoundException::new);
    }


    public void update( Long idRDV,  RDVDTO rDVDTO) {
         RDV rDV = rDVRepository.findById(idRDV)
                .orElseThrow(NotFoundException::new);
        mapToEntity(rDVDTO, rDV);
        rDVRepository.save(rDV);
    }

    public void delete( Long idRDV) {
        rDVRepository.deleteById(idRDV);
    }

    private RDVDTO mapToDTO( RDV rDV,  RDVDTO rDVDTO) {
        rDVDTO.setIdRDV(rDV.getIdRDV());
        rDVDTO.setDate(rDV.getDate());
        rDVDTO.setDemande(rDV.getDemande());
         return rDVDTO;
    }

    private RDV mapToEntity( RDVDTO rDVDTO,  RDV rDV) {
        rDV.setDate(rDVDTO.getDate());
         DemandeAdmission demandeAdmission = rDVDTO.getDemande() == null ? null : demandeAdmissionRepository.findById(rDVDTO.getDemande().getIdAdmission())
                .orElseThrow(() -> new NotFoundException("rDVuser not found"));
        rDV.setDemande(demandeAdmission);
        return rDV;
    }

 /*   @Scheduled(fixedRate = 100000)
    public void etatTuteur(){
        LocalDate l = LocalDate.now();
        List<User> users = userRepository.findAll();
        List<Salle> salles = salleRepository.findAll();

        for (User u : users){
        }
        for(User u:users)
        {


            if (u.getEtatuser().equals("non disponible") && u.getDemandeAdmissionsEvaluateur()
                    .stream()
                    .filter(evaluateur -> evaluateur.getDateAdmission().equals(l))
                    .anyMatch(evaluateur -> true))

            {



                u.setEtatuser("disponible");


                userRepository.save(u);


            }



        }






    }

*/

}
