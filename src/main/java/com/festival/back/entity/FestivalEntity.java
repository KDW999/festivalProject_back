package com.festival.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Festival")
@Table(name = "Festival")
public class FestivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int festivalNumber;
    private String festivalName;
    private String festivalType;
    private String festivalDurationStart;
    private String festivalDurationEnd;
    private String festivalTime;
    private String festivalArea;
    private String festivalCost;
    private int onelineReviewAverage;
    

    
}
