package tn.esprit.springfever.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springfever.dto.Job_RDV_DTO;
import tn.esprit.springfever.Services.Implementation.JobRDVReminderTasklet;
import tn.esprit.springfever.Services.Interface.IDisponibilites;
import tn.esprit.springfever.Services.Interface.IJobRDV;
import tn.esprit.springfever.entities.Disponibilites;
import tn.esprit.springfever.entities.Job_RDV;
import tn.esprit.springfever.Repositories.JobRdvRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@Configuration
public class JobRdvController {
    @Autowired
    IJobRDV iJobRDV;
    @Autowired
    JobRdvRepository jobRdvRepository;

    @Autowired
    JobRDVReminderTasklet jobRDVReminderTasklet; //Could not autowire. No beans of 'JobRDVReminderTasklet' type found.
    @Autowired
    IDisponibilites iDisponibilites;


    @PostMapping("addJobRDVAAndAssign/")
    public ResponseEntity<Job_RDV> addJobRDV(@RequestParam ("salle") String salle,@RequestParam("Id_JobApplication") Long Id_Job_Application,
                             @RequestParam("IdJury") Long idJury,@RequestParam("IdDispoCandidate") Long idDispoCandidate,
                             @RequestParam("IdDidpoJury")Long idDispoJury,@RequestParam("interviewDuration") int interviewDuration) throws JsonProcessingException {

        Long idJobRDV = iJobRDV.addJobRDV(new Job_RDV()).getID_Job_DRV(); // créer un nouveau Job_RDV et récupérer son ID
        System.out.println("hereeee");
        System.out.println("h");
        Job_RDV jobRdv =jobRdvRepository.findById(idJobRDV).orElse(null);
        jobRdv.setSalle_Rdv(salle);
        iJobRDV.AssignJobApplicationAndJuryToRDV(Id_Job_Application,jobRdv.getID_Job_DRV(),idJury);
        System.out.println("mrigel");
        iDisponibilites.AssignJobRdvTODisponibilities(idDispoCandidate,idDispoJury,jobRdv.getID_Job_DRV());
        System.out.println("mrigel2");
     iJobRDV.findFirstAvailableDateTime(idDispoCandidate, idDispoJury, interviewDuration);

        System.out.println("mrigel3");
        iJobRDV.FixationRDV(jobRdv.getID_Job_DRV());
        System.out.println("mrigel4");
        return ResponseEntity.status(HttpStatus.CREATED).body(jobRdv);


    }



    @GetMapping("getAllJobRDVS/")
    public List<Job_RDV> getAllJobRDVs() {
        return iJobRDV.getAllJobRDVs();

    }

    @PutMapping("/updateJobRDv/{id}")
    @ResponseBody

    public Job_RDV updateJobRDV(@PathVariable("id") Long ID_Job_DRV, @RequestBody Job_RDV_DTO jobRdvDto )  {return  iJobRDV.updateJobRDV(ID_Job_DRV,jobRdvDto);}



    @DeleteMapping("deleteJobRDV")
    public  String deleteJobOffer( Long  ID_Job_DRV) {
        return iJobRDV.deleteJobOffer(ID_Job_DRV);

    }



    /*@PutMapping("AssignJobApplicationAndJuryToRDV/")
    public String AssignJobApplicationAndJuryToRDV(Long Id_Job_Application, Long ID_Job_DRV, Long idJury){
        return iJobRDV.AssignJobApplicationAndJuryToRDV(Id_Job_Application,ID_Job_DRV,idJury);


    }*/







    /*@GetMapping("/Determinate-Appointment-Date/{dispoCandidate}/{dispoJury}/{interviewDuration}")
    public ResponseEntity<LocalDateTime> findFirstAvailableDateTime(
            @ApiParam(value = "ID de la disponibilité du candidat", required = true) @PathVariable Long dispoCandidate,
            @ApiParam(value = "ID de la disponibilité du jury", required = true) @PathVariable Long dispoJury,
            @ApiParam(value = "Durée de l'entretien en minutes", required = true) @PathVariable int interviewDuration) {

        LocalDateTime firstAvailableDateTime = iJobRDV.findFirstAvailableDateTime(dispoCandidate,dispoJury,interviewDuration);

        if (firstAvailableDateTime == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(firstAvailableDateTime, HttpStatus.OK);
        }
    }*/

    /*@GetMapping("/CalculDistance/{id}")
    public double calculateDistance(@PathVariable("id") Long idRDV){
        return iJobRDV.calculateDistance(idRDV);

    }*/

    /*@GetMapping("/send-email-To-Fix-RDV-For-Interview/{id}")
    public void FixationRDV(@PathVariable("id") Long id) throws JsonProcessingException {
        iJobRDV.FixationRDV(id);

    }*/

    /*@PostMapping("/jobRDV/{id}/sendReminderSMS")
    public ResponseEntity<String> sendReminderSMS(@PathVariable(value = "id") Long id) {
        Job_RDV rdv = jobRdvRepository.findById(id).orElse(null);
        iJobRDV.sendReminderSMS(rdv);
        return ResponseEntity.ok().body("Reminder SMS sent successfully.");
    }*/

    @PostMapping("/send-reminders")
    public ResponseEntity<String> sendReminders() {
        try {
            jobRDVReminderTasklet.execute(null, null);
            return ResponseEntity.ok("Reminders sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending reminders: " + e.getMessage());
        }
    }








}
