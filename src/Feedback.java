public class Feedback<T> {
    private String student_name;
    private T feedback;
    private String courseID;

    public Feedback(String student_name, T feedback, String courseID) {
        this.student_name = student_name;
        this.feedback = feedback;
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public T getFeedback() {
        return feedback;
    }

    public void setFeedback(T feedback) {
        this.feedback = feedback;
    }
}
