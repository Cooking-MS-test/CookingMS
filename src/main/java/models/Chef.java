package models;

public class Chef {
    private String id;
    private String name;
    private String specialization;

    public Chef(String id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
}
