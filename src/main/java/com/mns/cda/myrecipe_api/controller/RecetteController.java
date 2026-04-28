package com.mns.cda.myrecipe_api.controller;

import com.mns.cda.myrecipe_api.dao.RecetteDao;
import com.mns.cda.myrecipe_api.model.Recette;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recette")
@Tag(name ="Recettes", description = "Gestion des recettes")
public class RecetteController {
    
    private final RecetteDao recetteDao;

    @GetMapping("/liste")
    @Operation(summary = "Récupère la liste de toutes les recettes",
            description = "Récupère la liste de toutes les recettes présentes dans la base de données.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des recettes récupérée avec succès")
    })
    public List<Recette> getRecetteList() {
        return recetteDao.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une recette par son ID",
            description = "Cette méthode permet de récupérer les informations d'une recette spécifique en utilisant son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recette récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Recette non trouvée")
    })
    public ResponseEntity<Recette> getRecetteById(@PathVariable Integer id) {

        Optional<Recette> optionalRecette = recetteDao.findById(id);

        if (optionalRecette.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalRecette.get(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Ajoute une recette à la base de données",
            description = "Cette méthode permet de d'ajouter une nouvelle recette en base de données.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recette ajoutée avec succès")
    })
    public ResponseEntity<Recette> create(@RequestBody Recette recetteToInsert) {

        recetteToInsert.setId(null);
        recetteDao.save(recetteToInsert);

        return new ResponseEntity<>(recetteToInsert, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime une recette par son ID",
            description = "Cette méthode permet de supprimer une recette spécifique en utilisant son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Recette supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Recette non trouvé")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        Optional<Recette> optionalRecette = recetteDao.findById(id);

        if (optionalRecette.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recetteDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifie une recette par son ID",
            description = "Cette méthode permet de modifier les informations d'une recette spécifique en utilisant son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recette modifiée avec succès"),
            @ApiResponse(responseCode = "404", description = "Recette non trouvé")
    })
    public ResponseEntity<Recette> update(
            @PathVariable Integer id,
            @RequestBody Recette recetteToUpdate) {

        Optional<Recette> optionalRecette = recetteDao.findById(id);

        if(optionalRecette.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recetteToUpdate.setId(id);

        recetteDao.save(recetteToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
            
}
