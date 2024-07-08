package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    @ToString.Exclude
    private Instructor instructor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    public void addReviews(List<Review> reviews) {
        reviews.stream().forEach((review) -> {
            this.reviews.add(review);
            review.setCourse(this);
        });
    }

    public void removeReviews(List<Review> reviews) {
        reviews.stream().forEach((review) -> {
            this.reviews.remove(review);
            review.setCourse(null);
        });
    }

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "course_student", inverseJoinColumns = @JoinColumn(name = "student_id"), joinColumns = @JoinColumn(name = "course_id"))
    @JsonIgnore
    @ToString.Exclude()
    private List<Student> students;

    public void addStudents(List<Student> students) {
        this.students.addAll(students);
    }

    public void removeStudents(List<Student> students) {
        this.students.removeAll(students);
    }

}
