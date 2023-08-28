package com.example.demo.distribution.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the list of distributions that will be displayed in the response.
 */
@AllArgsConstructor
@Data
public class DistributionResponseList {

  private List<DistributionResponse> distributions;

}

