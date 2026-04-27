package com.mns.cda.myrecipe_api.dao;

import com.mns.cda.myrecipe_api.model.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetteDao extends JpaRepository<Recette, Integer> {
}
