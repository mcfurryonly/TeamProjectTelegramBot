package pro.sky.teamproject.model;

import nonapi.io.github.classgraph.json.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Shelter {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String address;
    @Lob
    private byte[] schema;

    public Shelter(String name, String description, String address, byte[] schema) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.schema = schema;
    }

    public Shelter() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getSchema() {
        return schema;
    }

    public void setSchema(byte[] schema) {
        this.schema = schema;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(id, shelter.id) && Objects.equals(name, shelter.name) && Objects.equals(description, shelter.description) && Objects.equals(address, shelter.address) && Arrays.equals(schema, shelter.schema);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, address);
        result = 31 * result + Arrays.hashCode(schema);
        return result;
    }


    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", schema=" + Arrays.toString(schema) +
                '}';
    }

}
