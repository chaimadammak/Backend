package tn.esprit.springfever.model;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.springfever.entities.DemandeAdmission;
import tn.esprit.springfever.entities.Salle;

import java.time.LocalDate;


@Getter
@Setter
public class RDVDTO {
    /*

    private Long idRDV;



    private LocalDate date;

    @JsonProperty("rDVuser")
    private Long rDVuser;

    private Long demandeRdv;

     */


    private Long idRDV;
    private LocalDate date;
    private Salle salle;
    private DemandeAdmission demande;

}
