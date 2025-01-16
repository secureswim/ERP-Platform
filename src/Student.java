import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

class Student extends User {

    Scanner scanObj = new Scanner(System.in);

    private String studentId;
    private int semester;
    private ArrayList<String> enrolled_courses;
    private ArrayList<String> completed_courses;
    private double s;
    private double c;

    public Student(String name, String email, String password,String studentId, int semester, ArrayList<String> enrolled_courses, ArrayList<String> completed_courses, double s, double c){
        super(name, email, password);
        this.studentId=studentId;
        this.semester=semester;
        this.enrolled_courses=enrolled_courses;
        this.completed_courses=completed_courses;
        this.s=s;
        this.c=c;
    }
    public void view_available_courses(ArrayList<Course> course_list){
        System.out.println("Available courses for: "+ this.getName()+" in semester "+semester+" are: ");
        for(Course course:course_list){
            if (course.getSemester()==this.getSemester()){
                System.out.println("Semester\t\t\tCourseID\t\t\tTitle\t\t\tProfessor\t\t\tCredits\t\t\tSchedule\t\t\tLocation");
                System.out.println(course.getSemester()+"\t"+course.getCourseID()+"\t"+course.getTitle()+"\t"+course.getProfessor()+"\t"+course.getSchedule()+"\t"+course.getLocation());
                System.out.print("Prerequisites are:");
                if(course.getPrerequisites().size()!=0) {
                    for (String pre : course.getPrerequisites()) {
                        System.out.print(pre + " ");
                    }
                }
                else{
                    System.out.println("None");

                }

                System.out.println("-----------------");

            }
        }
    }

    public void register_for_courses(int course_limit, int currenly_registered, int semester, ArrayList<String> prerequisites, int total_creds, int current_course_creds, String courseID) throws CourseFullException {//use getsemster and getprerequisites in main

        try {
            if (currenly_registered+1>course_limit) {
                throw new CourseFullException("Course " + courseID + " is already full.");
            }


            int flag = 0;
            if (total_creds + current_course_creds <= 20 && semester == this.semester) {
                flag = 0;

            } else {
                flag = 1;
            }
            int len = prerequisites.size();

            int checker = 0;
            for (String pre : prerequisites) {
                for (String completed_courses : this.completed_courses) {
                    if (completed_courses.equals(pre)) {
                        checker += 1;
                    }
                }
            }

            if (checker != len) {
                flag = 1;
                System.out.println("Prereq not met");
            }

            if (flag == 0) {
                enrolled_courses.add(courseID);
                System.out.println("Course has been added");
            } else {
                System.out.println("Not eligible for this course");
            }
        } catch(CourseFullException e) {
            System.out.println(e.getMessage());
        }
    }

    public void view_schedule(ArrayList<Course> all_courses){
        System.out.println("Schedule of courses: ");
        ArrayList<String> enrolled_courses=this.enrolled_courses;
        for(String enrolled_course: enrolled_courses){
            for(Course course:all_courses){
                if(course.getCourseID().equals(enrolled_course)){
                    System.out.println(course.getTitle()+"\n"+course.getSchedule()+"\n"+course.getLocation());
                }
            }
            System.out.println("-----------------");

        }
    }

    public void track_academic_progress(ArrayList<Student> all_students, ArrayList<Course> all_courses){
        int sem=this.getSemester();
        ArrayList<String> completed_courses=this.completed_courses;

        ArrayList<Integer> completed_courses_sem = new ArrayList<>(Arrays.asList());

        double total_sg=0;
        int total_sg_courses=0;

        for(String course:completed_courses){
            for(Course courses: all_courses){
                if(courses.getCourseID().equals(course)){
                    if(courses.getSemester()==sem){
                        total_sg+=courses.getGrade(this.getStudentId());
                        total_sg_courses+=1;

                    }
                }
            }
        }
        if(total_sg_courses!=0) {
            System.out.println("Current SGPA: " + total_sg / total_sg_courses);
            this.setS(total_sg / total_sg_courses);
        }
        else{
            System.out.println("Current SGPA: "+0);
        }

        double total_cg=0;
        int total_cg_courses=0;

        for(String course:completed_courses){
            for(Course courses: all_courses){
                if(courses.getCourseID().equals(course)){
//                        System.out.println("im here");
                        total_cg+=courses.getGrade(this.getStudentId());
                        total_cg_courses+=1;
                }
            }
        }

        if(total_cg_courses!=0) {
            System.out.println("Current CGPA: " + total_cg / total_cg_courses);
            this.setC(total_sg / total_sg_courses);
        }
        else{
            System.out.println("Current CGPA: "+0);
        }

    }

    public void drop_course(ArrayList<Course> all_courses) throws DropDeadlinePassedException {
        try {
            int k = -1;
            ArrayList<String> enrolled_courses = this.enrolled_courses;
            int len = enrolled_courses.size();
            System.out.print("Enter course code to drop:");
            String courseID = scanObj.nextLine();

            LocalDate drop_deadline = null;
            for (Course course : all_courses) {
                if (course.getCourseID().equals(courseID)) {
                    drop_deadline = course.getDrop_deadline();
                }
            }
            LocalDate currentDate = LocalDate.now();

            if (currentDate.isAfter(drop_deadline)) {
                throw new DropDeadlinePassedException("You cannot drop the course after the drop deadline (" + drop_deadline + ").");
            }

            for (int i = 0; i < len; i++) {
                if (enrolled_courses.get(i).equals(courseID)) {
                    k = i;
                }
            }
            if (k != -1) {
                enrolled_courses.remove(k);
                System.out.println("Selected course dropped");
            }
        }catch(DropDeadlinePassedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void submit_complaint(ArrayList<Complaint> all_complaint){
        System.out.print("Date of complaint(DD/MM/YYYY): ");
        String type = scanObj.nextLine();
        System.out.print("Description of complaint: ");
        String description = scanObj.nextLine();


        Complaint complaint = new Complaint(type, description);
        complaint.setStatus("Pending");

        complaint.setComplaintID(all_complaint.toArray().length+1);

        System.out.print("Complaint submitted with complaintID: ");
        System.out.println(all_complaint.toArray().length+1);

        all_complaint.add(complaint);
    }

    public void view_status_complaint(ArrayList<Complaint> all_complaint){
        System.out.print("Enter complaintID");
        int complaintID = scanObj.nextInt();
        scanObj.nextLine();

        for(Complaint complaint:all_complaint){
            if(complaint.getComplaintID()==complaintID){
                System.out.println("Status: "+ complaint.getStatus());
            }
        }
    }

    public void give_feedback(ArrayList<Course> all_courses,ArrayList<Feedback<?>> all_feedback){
        System.out.println("Enter courseID for which to give feedback: ");
        String courseID=scanObj.nextLine();

        ArrayList<String> completedCourses=this.getCompleted_courses();

        int flag=0;
        for(String course:completedCourses){
            if(course.equals(courseID)){
                flag=1;
            }
        }
        if(flag==0){
            System.out.println("You can only give feedback for courses that you have completed. ");
        }

        System.out.print("Do you want to give numeric feedback(1-5): Y or N: ");
        String ans1=scanObj.nextLine();

        if(ans1.equals("Y")){
            System.out.print("Enter rating(1-5): ");
            int rating=scanObj.nextInt();
            scanObj.nextLine();
            Feedback<Integer> new_feedback=new Feedback<>(this.getName(),rating,courseID);

            all_feedback.add(new_feedback);
        }
        System.out.print("Do you want to give textual feedback: Y or N: ");
        String ans2=scanObj.nextLine();

        if(ans2.equals("Y")){
            System.out.print("Enter textual feedback ");
            String rating=scanObj.nextLine();
            Feedback<String> new_feedback=new Feedback<>(this.getName(),rating,courseID);
            all_feedback.add(new_feedback);
        }
    }

    public String toString() {
        return "Student{" +
                "name='" + this.getName() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", studentId='" + studentId + '\'' +
                ", semester=" + semester +
                ", enrolledCourses=" + enrolled_courses +
                ", completedCourses=" + completed_courses+
                ", CGPA=" +c+
                ",SGPA="+s+
                '}';
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public ArrayList<String> getEnrolled_courses() {
        return enrolled_courses;
    }

    public void setEnrolled_courses(ArrayList<String> enrolled_courses) {
        this.enrolled_courses = enrolled_courses;
    }

    public ArrayList<String> getCompleted_courses() {
        return completed_courses;
    }

    public void setCompleted_courses(ArrayList<String> completed_courses) {
        this.completed_courses = completed_courses;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

}
