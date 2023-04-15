package tn.esprit.springfever.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Salle {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsalle;

    @Column
    private Integer numSalle;

    @Size(max = 20)
    @Column
    private String etat;


    @OneToMany(mappedBy = "salle")
    private List<RDV> rdvs;



}
