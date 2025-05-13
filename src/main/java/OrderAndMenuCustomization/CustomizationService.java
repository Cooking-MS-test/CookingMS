package OrderAndMenuCustomization;

import Notification_And_Alerts.Customer;
import Notification_And_Alerts.Ingredient;
import Notification_And_Alerts.NotificationService;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

public class CustomizationService {
    private IngredientRepository ingredientRepo;
    private NotificationService notificationService;

    public CustomizationService(IngredientRepository ingredientRepo,
                                NotificationService notificationService) {
        this.ingredientRepo = ingredientRepo;
        this.notificationService = notificationService;
    }

    public MealCustomization createCustomMeal(Customer customer, List<String> ingredientNames) {
        List<Ingredient> ingredients = ingredientNames.stream()
                .map(name -> ingredientRepo.findByName(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        MealCustomization customization = new MealCustomization(customer, ingredients);

        if (!customization.isValid()) {
            notificationService.sendNotification(
                    customer.getEmail()+
                    "Custom meal issues: " +
                            String.join(", ", customization.getValidationMessages())
            );
        }

        return customization;
    }
}
