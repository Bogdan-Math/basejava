package main.code.model;

public enum SectionType {

    PERSONAL("Personal"),
    OBJECTIVE("Objective"),
    ACHIEVEMENT("Achievement"),
    QUALIFICATION("Qualification"),
    EXPERIENCE("Experience"),
    EDUCATION("Education");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
