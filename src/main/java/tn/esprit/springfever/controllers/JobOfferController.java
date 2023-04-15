package tn.esprit.springfever.controllers;

import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.springfever.Repositories.JobCategoryRepository;
import tn.esprit.springfever.Repositories.JobRdvRepository;
import tn.esprit.springfever.Services.Interface.IJobFileImage;
import tn.esprit.springfever.Services.Interface.IJobOffer;
import tn.esprit.springfever.entities.Image;
import tn.esprit.springfever.entities.Image_JobOffer;
import tn.esprit.springfever.entities.Job_Category;
import tn.esprit.springfever.entities.Job_Offer;
import tn.esprit.springfever.Repositories.JobOfferRepository;

import java.io.IOException;
import java.util.List;

@RestController
public class JobOfferController  {
    @Autowired
    IJobOffer iJobOffer;
    @Autowired
    JobCategoryRepository jobCategoryRepository;


    @Autowired
    JobOfferRepository jobOfferRepository;
    @Autowired
    IJobFileImage iJobFileImage;
    @Autowired
    private JobRdvRepository jobRdvRepository;

    /*@PostMapping(value = "addJobOffer/" )
    public ResponseEntity<?> addJobOffer(@Validated @RequestBody Job_Offer job_offer){
        iJobOffer.addJobOffer(job_offer);
        return new ResponseEntity<>( HttpStatus.OK);

    }*/
    @PostMapping(value = "addJobOffer/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = "application/json" )
    public ResponseEntity<?> addJobOffer(@RequestParam("title")String title , @RequestParam("subject") String subject , @RequestParam("Id_Job_Category")
            Long Id_Job_Category,
                                         @RequestBody MultipartFile image) throws IOException {
        Long Id_Job_Offer = iJobOffer.addJobOffer(new Job_Offer()).getId_Job_Offer();
        Job_Offer job_offer=jobOfferRepository.findById(Id_Job_Offer).orElse(null);
        job_offer.setSubject(subject);
        job_offer.setTitle(title);
        Job_Category  jobCategory= jobCategoryRepository.findById(Id_Job_Category).orElse(null);
        job_offer.setJobCategory(jobCategory);
        if(image != null){
            try {
                Image_JobOffer savedImageData = iJobFileImage.save(image.getBytes(), image.getOriginalFilename());
                Long id= savedImageData.getId();
                System.out.println(id);
                job_offer.setImage(savedImageData);
                System.out.println(job_offer.getImage().getId());
                jobOfferRepository.save(job_offer);
            } catch (Exception e) {
                e.toString();
            }
        }


        return new ResponseEntity<>( HttpStatus.OK);}

    @GetMapping("getAllJobOffers/")
    public List<Job_Offer> getAllJobOffers(){
        return iJobOffer.getAllJobOffers();
    }

    @PutMapping("updateJobOffer/{id}")
    public Job_Offer updateJobOffer(@PathVariable("id") Long Id_Job_Offer ,@RequestBody Job_Offer job_offer ){
        return iJobOffer.updateJobOffer(Id_Job_Offer,job_offer);

    }



    @DeleteMapping("deleteJobOffer/{id}")
    public  String deleteJobOffer(@PathVariable("id") Long  Id_Job_Offer){
        return iJobOffer.deleteJobOffer(Id_Job_Offer);
    }
    /*@PutMapping("AssignJobOfferToCategory/{Id_Job_Offer}/{Id_Job_Category}")
    public String AssignCategoryToJobOffer( Long Id_Job_Offer, Long Id_Job_Category){
        return iJobOffer.AssignCategoryToJobOffer(Id_Job_Offer,Id_Job_Category);

    }*/


    @PutMapping("AssignImageAndJobCategoryToJobOffer/")
    public String AssignImageAndJobCategoryToJobOffer(Long Id_Job_Offer , Long id , Long Id_Job_Category ){
        return iJobOffer.AssignImageAndJobCategoryToJobOffer(Id_Job_Offer,id,Id_Job_Category);

    }




    @GetMapping(value = "/rss", produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getRSSFeed() throws FeedException {
        String rssFeed = iJobOffer.generateRSSFeed();
        return ResponseEntity.ok(rssFeed);
    }
    @GetMapping("StatNbOffresParCategorie/")
    public List<Object[]> countJobOffersByCategory(){
        return iJobOffer.countJobOffersByCategory();
    }


    /*@GetMapping("/job-offers/sort-by-application-count")
    public List<Job_Offer> getJobOffersSortedByApplicationCount() {
        List<Job_Offer> jobOffers = jobOfferRepository.findAll();
        jobOffers.sort(Comparator.comparingInt(j -> j.getJobApplications().size()));
        Collections.reverse(jobOffers);
        return jobOffers;
    }*/
    /*@GetMapping("/job-offers/sort-by-application-count")
    public String getJobOffersSortedByApplicationCount(){
        try {
            return iJobOffer.getJobOffersSortedByApplicationCount();

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }*/




}
