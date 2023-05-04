package com.yahir.Workaholic.Tags;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Tag {
    
    @Id
    @SequenceGenerator(
        name="tag_id_generator",
        sequenceName = "tag_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tag_id_generator"
    )
    private Integer id;
    private String tag_name;
}
