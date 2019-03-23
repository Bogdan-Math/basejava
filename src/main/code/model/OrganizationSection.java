package main.code.model;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class OrganizationSection extends Section {

    private static final long serialVersionUID = 1L;

    private final List<Organization> organizations;

    public OrganizationSection(Organization... organizations) {
        this(asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                '}';
    }
}
