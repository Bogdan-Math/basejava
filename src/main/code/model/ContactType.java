package main.code.model;

public enum ContactType {

    PHONE("Phone"),
    MOBILE("Mobile"),
    HOME_PHONE("Home phone"),
    SKYPE("Skype"),
    MAIL("Mail"),
    LINKEDIN("LinkedIn"),
    GITHUB("Github"),
    HOME_PAGE("Home page");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
