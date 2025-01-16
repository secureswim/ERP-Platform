import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

class Professor extends User {
    private String professorID;
    private ArrayList<String> assigned_courses;

    Scanner scanObj = new Scanner(System.in);

    public Professor(String name, String email, String password, String professorID, ArrayList<String> assigned_courses) {
        super(name, email, password);
        this.assigned_courses = assigned_courses;
        this.professorID = professorID;
    }

    public void manage_courses(ArrayList<Course> all_courses) {
        System.out.print("Enter courseID to view or edit details: ");
        String courseID = scanObj.nextLine();
        int flag=0;
        for(String course:this.assigned_courses){
            if(course.equals(courseID)){
                flag=1;
            }
        }
        if(flag==0){
            System.out.println("You cant view or edit this course as it is not assigned to you");

        }else {
            Course temp_course = null;
            for (Course course : all_courses) {
                if (course.getCourseID().equals(courseID)) {  // Use equals() for string comparison
                    temp_course = course;
                }
            }

            int answer = 1;  // This is for demonstration purposes. You can modify it as per the program logic.

            if (answer == 1) {
                System.out.println(temp_course.toString());
                System.out.print("Do you want to edit: Y or N");
                String c = scanObj.nextLine();

                if (c.equals("Y")) {
                    answer = 2;
                }
            }
            if (answer == 2) {
                System.out.println("Enter detail to edit: ");
                System.out.println("1. Semester ");
                System.out.println("2. Title ");
                System.out.println("3. Credits ");
                System.out.println("4. Schedule ");
                System.out.print("5. Location ");

                int c2 = scanObj.nextInt();
                scanObj.nextLine();  // Clear the buffer

                if (c2 == 1) {
                    System.out.print("Enter new semester: ");
                    int new_semester = scanObj.nextInt();
                    temp_course.setSemester(new_semester);
                    scanObj.nextLine();  // Clear the buffer
                } else if (c2 == 2) {
                    System.out.println("Enter new title: ");
                    String new_title = scanObj.nextLine();
                    temp_course.setTitle(new_title);
                } else if (c2 == 3) {
                    System.out.print("Enter new credits: ");
                    int new_credits = scanObj.nextInt();  // Changed to float input
                    scanObj.nextLine();  // Clear the buffer
                    temp_course.setCredits(new_credits);
                } else if (c2 == 4) {
                    System.out.print("Enter new schedule: ");
                    String new_schedule = scanObj.nextLine();
                    temp_course.setSchedule(new_schedule);
                } else if (c2 == 5) {
                    System.out.print("Enter new location: ");
                    String new_location = scanObj.nextLine();
                    temp_course.setLocation(new_location);
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }

    public void view_enrolled_students(ArrayList<Student> all_students, ArrayList<Course> all_courses) {
        System.out.print("Enter courseID to view enrolled students: ");
        String courseID = scanObj.nextLine();
        int flag=0;
        for(String cp:this.assigned_courses){
            if(cp.equals(courseID)){
                flag=1;
            }
        }
        if(flag!=1){
            System.out.println("You cant view enrolled students as this course is not assigned to you");
        }
        else {
            System.out.println("Enrolled students: ");
            for (Student student : all_students) {
                for (String course : student.getEnrolled_courses()) {
                    if(course.equals(courseID)){
                        System.out.println(student.getName());
                    }
                }
            }
        }
    }

    public void assign_grade(ArrayList<Student> all_students,ArrayList<Course> all_courses){
        System.out.print("Enter courseID to assign grades: ");
        String courseID = scanObj.nextLine();
        int flag=0;
        for(String course:this.assigned_courses){
            if(course.equals(courseID)){
                flag=1;
            }
        }
        if(flag==0) {
            System.out.println("You cant assign grades in this course as it is not assigned to you");
        }
        else {
            System.out.print("Enter studentID to assign grades: ");
            String studentID = scanObj.nextLine();

            System.out.print("Enter grade: ");
            Double grade = scanObj.nextDouble();
            scanObj.nextLine();

            for (Course course : all_courses) {
                if (course.getCourseID().equals(courseID)) {
                    course.addGrade(studentID,grade);
                }
            }

            System.out.println("Grade Assigned. ");
            for(Student student:all_students){
                if(student.getStudentId().equals(studentID)){
                    for(Course course:all_courses){
                        if(course.getCourseID().equals(courseID)){
                            ArrayList<String> new_enrolled=student.getEnrolled_courses();
                            new_enrolled.remove(course.getCourseID());
                            student.setEnrolled_courses(new_enrolled);
                            ArrayList<String> new_completed=student.getCompleted_courses();
                            new_completed.add(course.getCourseID());
                            student.setCompleted_courses(new_completed);
                        }
                    }
                }
            }
        }
    }

    public void view_feedback(ArrayList<Feedback<?>> all_feedback){
        System.out.println("Enter courseID for which you want to view feedback: ");
        String courseID=scanObj.nextLine();

        ArrayList<String> assignedCourses=this.getAssigned_courses();

        int flag=0;

        for(String course: assignedCourses){
            if(course.equals(courseID)){
                flag=1;
            }
        }

        if(flag==0){
            System.out.println("You can't view feedback for a course that is not assigned to you. ");
            return;
        }
        
        int counter=1;
        
        for(Feedback<?> feedback:all_feedback){
            if(feedback.getCourseID().equals(courseID)){
                System.out.println("Student: "+ feedback.getStudent_name());
                System.out.print("Feedback: ");
                System.out.println(feedback.getFeedback());
            }
        }
    }

    public void assign_ta(ArrayList<Student> all_students, ArrayList<TA> all_ta){
        System.out.print("Enter StudentID of TA: ");
        String studentID=scanObj.nextLine();

        Student current_student=null;
        for(Student student:all_students){
            if(student.getStudentId().equals(studentID)){
                current_student=student;
            }
        }

        System.out.print("Enter courseID: ");
        String courseID=scanObj.nextLine();

        int flag=0;

        for(String course:this.assigned_courses){
            if(course.equals(courseID)){
                flag=1;
            }
        }
        if(flag==0) {
            System.out.println("You cant assign grades in this course as it is not assigned to you");
            return;
        }
        else {
            ArrayList<String> c = new ArrayList<>();
            c.add(courseID);

            TA ta = new TA(current_student.getName(), current_student.getEmail(), current_student.getPassword(), current_student.getStudentId(), current_student.getSemester(), current_student.getEnrolled_courses(), current_student.getCompleted_courses(), current_student.getS(), current_student.getC(), c);
            all_ta.add(ta);

            System.out.println("TA assigned. ");
        }
    }

    public String getProfessorID() {
        return professorID;
    }

    public void setProfessorID(String professorID) {
        this.professorID = professorID;
    }

    public ArrayList<String> getAssigned_courses() {
        return assigned_courses;
    }

    public void setAssigned_courses(ArrayList<String> assigned_courses) {
        this.assigned_courses = assigned_courses;
    }
}
