package main.com.radkovich.module_2_3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "label")
public class Label {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Label(long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Label(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public Label() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
