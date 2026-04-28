INSERT INTO ingredient (id, nom, categorie) VALUES
                (1, 'Tomate', 'Fruit'),
                (2, 'Pâtes', 'Céréale'),
                (3, 'Basilic', 'Herbe'),
                (4, 'Poulet', 'Viande'),
                (5, 'Crème fraîche', 'Produit laitier');

INSERT INTO recette (
    id, titre_recette, nom_plat, description_recette,
    temp_preparation_minute, date_recette, date_recette_modifier) VALUES
      (1, 'Pâtes à la Tomate', 'Pâtes', 'Un plat simple et rapide à base de tomates fraîches.',
       15, now(), now()),

      (2, 'Poulet Crème Basilic', 'Poulet', 'Poulet mijoté dans une sauce crémeuse au basilic.',
       30, now(), now()),

      (3, 'Salade Fraîcheur', 'Salade', 'Salade légère avec tomates et basilic.',
       10, now(), now());


INSERT INTO recette_ingredient (recette_id, ingredient_id) VALUES
                       (1, 1),
                       (1, 2),
                       (1, 3),
                       (2, 4),
                       (2, 5),
                       (2, 3),
                       (3, 1),
                       (3, 3);
