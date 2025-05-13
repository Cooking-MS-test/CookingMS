package OrderAndMenuCustomization;

import Notification_And_Alerts.Chef;
import Notification_And_Alerts.Ingredient;
import Notification_And_Alerts.NotificationService;

import java.util.*;
import java.util.stream.Collectors;

public class SubstitutionService {
    private Map<String, List<Ingredient>> substitutionMap;
    private NotificationService notificationService;
    private Map<String, List<String>> SubstitutionMap;
    public SubstitutionService() {
        SubstitutionMap = new HashMap<>();
        // Initialize with common substitutions
        SubstitutionMap.put("dairy cheese", List.of("vegan cheese", "cashew cheese"));
        SubstitutionMap.put("peanuts", List.of("almonds", "sunflower seeds"));
        SubstitutionMap.put("gluten pasta", List.of("rice pasta", "quinoa pasta"));
        SubstitutionMap.put("salmon", List.of("trout", "cod"));
        SubstitutionMap.put("avocado", List.of("hummus", "guacamole"));
        SubstitutionMap.put("quinoa", List.of("couscous", "bulgur wheat"));
    }

    public SubstitutionService(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.substitutionMap = new HashMap<>();
        initializeDefaultSubstitutions();
    }

    private void initializeDefaultSubstitutions() {
        // Can be loaded from DB or config file
        substitutionMap.put("dairy cheese", List.of(
                new Ingredient("vegan cheese", Set.of("vegan", "dairy-free")),
                new Ingredient("cashew cheese", Set.of("vegan", "dairy-free", "nut"))
        ));

        substitutionMap.put("peanuts", List.of(
                new Ingredient("almonds", Set.of("nut-free")),
                new Ingredient("sunflower seeds", Set.of("nut-free", "seed"))
        ));
        substitutionMap.put("gluten pasta", List.of(
                new Ingredient("rice pasta", Set.of("nut-free")),
                new Ingredient("quinoa pasta", Set.of("nut-free", "seed"))
        ));
    }
    public List<String> GetSubstitutions(String ingredient) {
        return SubstitutionMap.getOrDefault(ingredient, List.of());
    }

    public List<Ingredient> getSubstitutions(Ingredient original, Set<String> restrictions) {
        List<Ingredient> allAlternatives = substitutionMap.getOrDefault(
                original.getName().toLowerCase(),
                Collections.emptyList()
        );

        return allAlternatives.stream()
                .filter(alt -> alt.isAvailable())
                .filter(alt -> restrictions.stream()
                        .allMatch(alt::matchesDietaryRestriction))
                .collect(Collectors.toList());
    }

    public void applySubstitution(Ingredient original, Ingredient substitute, Chef chef) {
        String alert = String.format(
                "A substitution has been applied: %s replaced with %s",
                original.getName(),
                substitute.getName()
        );
        notificationService.sendNotification(chef.getName()+ alert);
    }
}
