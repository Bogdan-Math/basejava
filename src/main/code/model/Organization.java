package main.code.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Arrays.asList;
import static main.code.util.DateUtil.NOW;
import static main.code.util.DateUtil.of;

public class Organization {

    private final Link link;
    private final List<Position> positions;

    public Organization(String name, String url, Position... positions) {
        this.link = new Link(name, url);
        this.positions = asList(positions);
    }

    public Organization(Link link, List<Position> positions) {
        this.link = link;
        this.positions = positions;
    }

    public static class Position {

        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }
    }
}
