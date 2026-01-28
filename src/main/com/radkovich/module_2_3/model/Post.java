package main.com.radkovich.module_2_3.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "post_label",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"))
    private Set<Label> labels;

    @ManyToOne
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private Writer writer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Post(String content, LocalDateTime created, LocalDateTime updated, Set<Label> labels, Status status) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.status = status;
    }

    public Post(String content, LocalDateTime creationDate, Set<Label> labels, Status status) {
        this.content = content;
        this.created = creationDate;
        this.labels = labels;
        this.status = status;
    }

    public Post(String content, LocalDateTime modificationDate, Status status) {
        this.content = content;
        this.created = modificationDate;
        this.status = status;
    }

    public Post(long postId, String content, LocalDateTime created, LocalDateTime updated, Set<Label> labels, Status postStatus) {
        this.id = postId;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.status = postStatus;
    }

    public Post() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addLabel(Label label) {
        if (labels == null) {
            labels = new HashSet<>();
        }
        labels.add(label);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                ", status=" + status +
                '}';
    }
}
