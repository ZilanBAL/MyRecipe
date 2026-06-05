# MyRecipe_API

[![Java CI](https://github.com/ZilanBAL/MyRecipe_API/actions/workflows/ci.yml/badge.svg)](https://github.com/ZilanBAL/MyRecipe_API/actions/workflows/ci.yml)

Une API REST pour gérer vos recettes et leurs ingrédients.

## Stack technique

- Java 25
- Spring Boot 4.0.6 (Web MVC, Data JPA, Validation)
- PostgreSQL 18
- Lombok
- Springdoc OpenAPI (Swagger UI)
- Docker / Docker Compose
- Maven

## Prérequis

- Docker et Docker Compose
- (Optionnel pour le dev local hors Docker) JDK 25 et Maven

## Configuration

Le projet attend les variables d'environnement suivantes. Crée un fichier `.env` à la racine :

```env
# Application
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=8080

# Connexion JDBC de l'application
DB_URL=jdbc:postgresql://db:5432/myrecipe
DB_USERNAME=myrecipe
DB_PASSWORD=changeme

# Base PostgreSQL
POSTGRES_DB=myrecipe
POSTGRES_USERNAME=myrecipe
POSTGRES_PASSWORD=changeme

# CI / publication d'image Docker
DOCKER_USERNAME=...
DOCKER_TOKEN=...
```

## Lancer le projet

### Avec Docker Compose (recommandé)

```bash
docker compose up --build
```

Services démarrés :
- **API** : http://localhost:8080
- **Adminer** (UI base de données) : http://localhost:8081
- **PostgreSQL** : `localhost:5432`

### En local sans Docker

```bash
./mvnw spring-boot:run
```

## Endpoints principaux

### Recettes — `/recette`

| Méthode | URL              | Description                  |
|---------|------------------|------------------------------|
| GET     | `/recette/liste` | Liste toutes les recettes    |
| GET     | `/recette/{id}`  | Détail d'une recette         |
| POST    | `/recette`       | Crée une recette             |
| PUT     | `/recette/{id}`  | Modifie une recette          |
| DELETE  | `/recette/{id}`  | Supprime une recette         |

### Ingrédients — `/ingredient`

| Méthode | URL                | Description                  |
|---------|--------------------|------------------------------|
| GET     | `/ingredient/liste`| Liste tous les ingrédients   |
| GET     | `/ingredient/{id}` | Détail d'un ingrédient       |
| POST    | `/ingredient`      | Crée un ingrédient           |
| PUT     | `/ingredient/{id}` | Modifie un ingrédient        |
| DELETE  | `/ingredient/{id}` | Supprime un ingrédient       |


## Structure du projet

```
src/main/java/com/mns/cda/myrecipe_api/
├── MyRecipeApiApplication.java
├── controller/   # Endpoints REST (Recette, Ingredient)
├── dao/          # Repositories JPA
└── model/        # Entités (Recette, Ingredient)
```

## CI/CD

Le workflow GitHub Actions (`.github/workflows/ci.yml`) construit le projet, exécute les tests et publie l'image Docker (identifiants via `DOCKER_USERNAME` / `DOCKER_TOKEN`).
