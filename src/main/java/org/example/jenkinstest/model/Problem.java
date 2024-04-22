package org.example.jenkinstest.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jenkinstest.enums.Difficulty;

import java.io.Serializable;
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Problem implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String problemTitle;
    private boolean status;
    private double acceptance;
    private Difficulty difficulty;
    private double frequency;
}
