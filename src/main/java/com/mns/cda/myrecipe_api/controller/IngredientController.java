package com.mns.cda.myrecipe_api.controller;

import com.mns.cda.myrecipe_api.dao.IngredientDao;
import com.mns.cda.myrecipe_api.model.Ingredient;
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
@RequestMapping("/ingredients")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name="Ingrédients", description ="Gestion des ingrédients possibles")
public class IngredientController {

    protected final IngredientDao ingredientDao;

    @Operation(summary="Récupérer tous les ingrédients")
    @ApiResponse(responseCode= "200", description="Liste des ingrédients")
    @GetMapping
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
}
