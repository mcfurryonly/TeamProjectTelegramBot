package pro.sky.teamproject.model.enums;

public enum TypeShelter {

    DOG("Приют для собак"),
    CAT("Приют для кошек");

    private final String shelterName;

    TypeShelter(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterName() {
        return shelterName;
    }
}
