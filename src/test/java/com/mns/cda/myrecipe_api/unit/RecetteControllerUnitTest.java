package com.mns.cda.myrecipe_api.unit;

import com.mns.cda.myrecipe_api.controller.RecetteController;
import com.mns.cda.myrecipe_api.mock.MockRecette;
import com.mns.cda.myrecipe_api.model.Recette;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RecetteControllerUnitTest {
    @Test
    public void getRecetteAll_MustReturnList() {
        RecetteController recetteController = new RecetteController(new MockRecette());

        List<Recette> response = recetteController.getRecetteList();

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(4, response.size());
    }
}
