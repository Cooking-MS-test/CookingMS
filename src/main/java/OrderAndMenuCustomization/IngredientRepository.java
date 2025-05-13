package OrderAndMenuCustomization;

import models.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Optional<Ingredient> findByName(String name);
    List<Ingredient> findAllAvailable();
}
