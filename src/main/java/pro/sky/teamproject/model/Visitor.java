package pro.sky.teamproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "visitor")
public class Visitor {
    public Visitor(Long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public Visitor() {

    }

    public Visitor(Long telegramUserId, String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.telegramUserId = telegramUserId;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(id, visitor.id) && Objects.equals(name, visitor.name) && Objects.equals(phoneNumber, visitor.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long telegramUserId;



    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(Long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }
}

