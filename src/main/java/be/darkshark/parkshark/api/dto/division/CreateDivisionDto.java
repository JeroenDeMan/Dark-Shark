package be.darkshark.parkshark.api.dto.division;

public class CreateDivisionDto {

    private String name;

    private String originalName;

    private String director_id;

    private String parent_division_id;

    public String getName() {
        return name;
    }

    public CreateDivisionDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getOriginalName() {
        return originalName;
    }

    public CreateDivisionDto setOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    public String getDirector_id() {
        return director_id;
    }

    public CreateDivisionDto setDirector_id(String director_id) {
        this.director_id = director_id;
        return this;
    }

    public String getParent_division_id() {
        return parent_division_id;
    }

    public CreateDivisionDto setParent_division_id(String parent_division_id) {
        this.parent_division_id = parent_division_id;
        return this;
    }

}
