import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Locale;

public class Course {
    private int semester;
    private String courseID;
    private String professor;
    private String title;
    private int credits;
    private ArrayList<String> prerequisites;
    private String schedule;
    private String location;
    private HashMap<String, Double> grades;
    private int course_limit;
    private LocalDate drop_deadline;
    public Course(int semester, String courseID, String title, String professor, int credits, ArrayList<String> prerequisites, String schedule, String location, int course_limit, LocalDate drop_deadline){
        this.semester=semester;
        this.courseID=courseID;
        this.professor=professor;
        this.title=title;
        this.credits=credits;
        this.prerequisites=prerequisites;
        this.schedule=schedule;
        this.location=location;
        this.course_limit=course_limit;
        this.drop_deadline=drop_deadline;
        this.grades = new HashMap<>();
    }

    public LocalDate getDrop_deadline() {
        return drop_deadline;
    }

    public void setDrop_deadline(LocalDate drop_deadline) {
        this.drop_deadline = drop_deadline;
    }

    public int getCourse_limit() {
        return course_limit;
    }

    public void setCourse_limit(int course_limit) {
        this.course_limit = course_limit;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void addGrade(String studentName, double grade) {
        grades.put(studentName, grade);
    }

    public Double getGrade(String studentID) {
        return grades.get(studentID);
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String toString() {
        return "Course{" +
                "semester=" + this.semester +
                ", courseID='" + this.courseID + '\'' +
                ", professor='" + this.professor + '\'' +
                ", title='" + this.title + '\'' +
                ", credits=" + this.credits +
                ", prerequisites=" + this.prerequisites +
                ", schedule='" + this.schedule + '\'' +
                ", location='" + this.location + '\'' +
                '}';
    }
}
