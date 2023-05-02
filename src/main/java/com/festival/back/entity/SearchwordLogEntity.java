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
@Entity(name = "Searchwordlog")
@Table(name = "Searchwordlog")
public class SearchwordLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private String searchWord;

    public SearchwordLogEntity(String searchWord){
        this.searchWord=searchWord;
    }
}
