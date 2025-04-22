package com.example.FeedBack.model;


import jakarta.persistence.*;

@Entity
@Table(name = "feedback_responses")
public class FeedBackResponses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private Integer responseId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "stu_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)
    private FeedBackQuestion question;

    @Column(name = "response_value")
    private Integer responseValue;

    @Column(name = "response_text", columnDefinition = "TEXT")
    private String responseText; // For text-based responses

    @Column(name = "time_taken")
    private Long timeTaken;

    public Long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    // Getters and Setters
    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public FeedBackQuestion getQuestion() {
        return question;
    }

    public void setQuestion(FeedBackQuestion question) {
        this.question = question;
    }

    public Integer getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(Integer responseValue) {
        this.responseValue = responseValue;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}
