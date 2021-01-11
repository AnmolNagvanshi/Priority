package com.assignment.priority.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "ratingId")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    // priority 2 is higher than priority 1
    private Integer priorityOrder;

    @Min(value = 1, message = "rating should be between 1 and 5")
    @Max(value = 5, message = "rating should be between 1 and 5")
    private Integer satisfactionRating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Rating(Category category, Integer priorityOrder,
                  Integer satisfactionRating) {
        this.category = category;
        this.priorityOrder = priorityOrder;
        this.satisfactionRating = satisfactionRating;
    }

}
