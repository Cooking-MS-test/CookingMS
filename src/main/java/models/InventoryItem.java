package models;

public class InventoryItem {
    private Ingredient ingredient;
    private int quantity;

    public InventoryItem(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() { return ingredient; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
