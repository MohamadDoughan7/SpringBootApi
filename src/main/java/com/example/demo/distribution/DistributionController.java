package com.example.demo.distribution;

import com.example.demo.distribution.request.AddDistributionRequest;
import com.example.demo.distribution.response.DistributionResponse;
import com.example.demo.distribution.response.DistributionResponseList;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is for handling the distribution-related HTTP requests.
 */
@RestController
@RequestMapping(path = "api/v1/distribution")
@AllArgsConstructor
public class DistributionController {

  private final DistributionService distributionService;

  /**
   * Retrieves the list of available distributions.
   *
   * @return the list of distributions
   */
  @GetMapping
  public ResponseEntity<DistributionResponseList> getDistributions() {

    /*
    Get the list of distribution from the service.
     */
    DistributionResponseList distributions =
        distributionService.getDistribution();

    /*
    Generating the response.
     */
    return new ResponseEntity<>(distributions, HttpStatus.OK);


  }

  /**
   * Adds a new distribution to the database.
   *
   * @param request the distribution request body to be added
   * @return the added distribution response
   */
  @PostMapping
  public ResponseEntity<DistributionResponse> createNewDistribution(@Valid
  @RequestBody AddDistributionRequest request) {

    /*
    Preparing the response.
     */
    DistributionResponse newDistribution =
        distributionService.addNewDistribution(
        request.getTeacherId(),
        request.getCourseId());

    /*
    Generating the response.
     */
    return ResponseEntity.ok(newDistribution);
  }

  /**
   * Deletes a distribution from the database.
   *
   * @param teacherId the ID of the teacher of the distribution to be deleted
   * @param courseId  the ID of the course of the distribution to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{teacherId}-{courseId}")
  public ResponseEntity<String> deleteDistribution(
      @PathVariable("teacherId") Long teacherId,
      @PathVariable("courseId") Long courseId) {

    /*
    Preparing the response.
     */
    distributionService.deleteDistributionByIds(teacherId, courseId);

    /*
    Generating the response.
     */
    return ResponseEntity.ok("Distribution deleted successfully");
  }
}







