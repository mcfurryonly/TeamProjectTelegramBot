package pro.sky.teamproject.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] picture;
    private String description;
    private final boolean process = false;

    @ManyToOne
    @JoinColumn(name = "id_visitor")
    private Visitor visitor;

    public Report(Visitor visitor, byte[] picture, String description) {
        this.visitor = visitor;
        this.picture = picture;
        this.description = description;
    }

    public Report() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return process == report.process && Objects.equals(id, report.id) && Arrays.equals(picture, report.picture) && Objects.equals(description, report.description) && Objects.equals(visitor, report.visitor);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, description, process, visitor);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", picture=" + Arrays.toString(picture) +
                ", description='" + description + '\'' +
                ", process=" + process +
                ", visitor=" + visitor +
                '}';
    }
}
