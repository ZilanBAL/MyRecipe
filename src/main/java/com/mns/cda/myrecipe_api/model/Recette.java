package com.mns.cda.myrecipe_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titreRecette;

    private String nomPlat;

    private String descriptionRecette;

    private int tempPreparationMinute;

    @CreatedDate
    private LocalDateTime dateRecette;

    @LastModifiedDate
    private LocalDateTime dateRecetteModifier;

}
