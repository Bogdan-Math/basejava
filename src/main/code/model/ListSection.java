package main.code.model;

import java.util.List;

import static java.util.Arrays.asList;

public class ListSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<String> items;

    public ListSection() {
    }

    public ListSection(String... items) {
        this(asList(items));
    }

    public ListSection(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "items=" + items +
                '}';
    }
}
