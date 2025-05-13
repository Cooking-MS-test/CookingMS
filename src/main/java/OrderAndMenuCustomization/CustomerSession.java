package OrderAndMenuCustomization;

import Zahi.Chef;
import models.Ingredient;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CustomerSession {
    private Set<String> dietaryRestrictions;
    private SubstitutionService substitutionService;

    public List<Ingredient> requestSubstitutions(Ingredient ingredient) {
        if (!ingredient.isAvailable() ||
                !dietaryRestrictions.stream()
                        .allMatch(ingredient::matchesDietaryRestriction)) {
            return substitutionService.getSubstitutions(ingredient, dietaryRestrictions);
        }
        return Collections.emptyList();
    }

    public void selectSubstitution(Ingredient original, Ingredient substitute, Chef chef) {
        substitutionService.applySubstitution(original, substitute, chef);
    }
}
