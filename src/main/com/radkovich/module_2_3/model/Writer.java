package main.com.radkovich.module_2_3.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "writer")
public class Writer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Writer() {
    }

    public Writer(String firstName, String lastName, List<Post> posts, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.status = status;
    }

    public Writer(long writerId, String firstName, String lastName, List<Post> posts, Status writerStatus) {
        this.id = writerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.status = writerStatus; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", posts=" + posts +
                ", status=" + status +
                '}';
    }
}
