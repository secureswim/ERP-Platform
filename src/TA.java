import java.util.ArrayList;
class TA extends Student {
//   boolean is_TA=true;
    private ArrayList<String> assigned_courses;
    public TA(String name, String email, String password, String studentId, int semester, ArrayList<String> enrolled_courses, ArrayList<String> completed_courses, double s, double c, ArrayList<String> assigned_courses){
        super(name, email, password, studentId, semester, enrolled_courses, completed_courses, s, c);
        this.assigned_courses=assigned_courses;
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

}

