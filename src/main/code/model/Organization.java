package main.code.model;

import java.time.LocalDate;

public class Organization {

    private final Link link;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(Link link, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.link = link;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }
}
