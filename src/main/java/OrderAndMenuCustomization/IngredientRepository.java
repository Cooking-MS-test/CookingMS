package OrderAndMenuCustomization;

import Notification_And_Alerts.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Optional<Ingredient> findByName(String name);
    List<Ingredient> findAllAvailable();
}
