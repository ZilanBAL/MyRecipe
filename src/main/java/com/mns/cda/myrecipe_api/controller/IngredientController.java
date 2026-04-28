package com.mns.cda.myrecipe_api.controller;

import com.mns.cda.myrecipe_api.dao.IngredientDao;
import com.mns.cda.myrecipe_api.model.Ingredient;
import com.mns.cda.myrecipe_api.model.Ingredient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredient")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name="Ingrédients", description ="Gestion des ingrédients possibles")
public class IngredientController {

    protected final IngredientDao ingredientDao;

    @Operation(summary="Récupérer tous les ingrédients")
    @ApiResponse(responseCode= "200", description="Liste des ingrédients")
    @GetMapping("/liste")
    public List<Ingredient> getAllIngredients() {
        return ingredientDao.findAll();
    }


    @Operation(summary="Récupérer un ingrédient")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrédient trouvé"),
            @ApiResponse(responseCode = "404", description = "Ingrédient non trouvé")})
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable int id) {
        Optional<Ingredient> ingredient = ingredientDao.findById(id);

        if (ingredient.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ingredient.get(), HttpStatus.OK);
    }


    @Operation(summary = "Crée un ingrédient")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Ingrédient crée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    @PostMapping
    public ResponseEntity<Ingredient> create(@RequestBody @Valid Ingredient ingredient) {
        Ingredient ingredientSaved = ingredientDao.save(ingredient);

        return new ResponseEntity<>(ingredientSaved, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Supprimer un ingrédient",
            description = "Supprime un ingrédient existant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ingrédient supprimé"),
            @ApiResponse(responseCode = "404", description = "Ingrédient non trouvé")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Ingredient> ingredient = ingredientDao.findById(id);
        if (ingredient.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ingredientDao.delete(ingredient.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(
            summary = "Mettre à jour un logiciel",
            description = "Met à jour les informations d’un logiciel ('name', 'description', 'ingredientType')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logiciel mis à jour"),
            @ApiResponse(responseCode = "404", description = "Logiciel non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")})
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(
            @PathVariable Integer id,
            @RequestBody Ingredient ingredientToUpdate) {

        Optional<Ingredient> optionalIngredient = ingredientDao.findById(id);

        if(optionalIngredient.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ingredientToUpdate.setId(id);

        ingredientDao.save(ingredientToUpdate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
