package com.assignment.priority.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RatingDto {

    private Integer categoryId;

    private Integer priorityOrder;

    @Min(value = 1, message = "rating should be between 1 and 5")
    @Max(value = 5, message = "rating should be between 1 and 5")
    private Integer satisfactionRating;

}
