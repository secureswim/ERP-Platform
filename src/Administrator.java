import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Administrator extends User {
    Scanner scanObj = new Scanner(System.in);
    private String adminID;

    public Administrator(String name, String email, String password, String adminID) {
        super(name, email, password);
        this.adminID = adminID;
    }

    public void view_courses(ArrayList<Course> all_courses) {
        for (int i = 0; i < all_courses.size(); i++) {
            System.out.println(all_courses.get(i).toString());
        }
    }

    public void add_course(ArrayList<Course> all_courses) {
        System.out.print("Enter semester:");
        int semester = scanObj.nextInt();
        scanObj.nextLine(); // Clear the buffer

        System.out.print("Enter course ID:");
        String courseID = scanObj.nextLine();

        System.out.print("Enter course title:");
        String title = scanObj.nextLine();

        System.out.print("Enter professor name:");
        String professor = scanObj.nextLine();

        System.out.print("Enter course credits:");
        int credits = scanObj.nextInt();
        scanObj.nextLine(); // Clear the buffer

        System.out.print("Enter course schedule:");
        String schedule = scanObj.nextLine();

        System.out.print("Enter course location:");
        String location =scanObj.nextLine();

        System.out.print("Enter number of prerequisites:");
        int numPrerequisites = scanObj.nextInt();
        scanObj.nextLine(); // Clear the buffer

        ArrayList<String> prerequisites = new ArrayList<>();
        for (int i = 0; i < numPrerequisites; i++) {
            System.out.println("Enter prerequisite " + (i + 1) + ":");
            String prerequisite = scanObj.nextLine();
            prerequisites.add(prerequisite);
        }

        System.out.print("Enter course limit:");
        int course_limit =scanObj.nextInt();
        scanObj.nextLine();

        System.out.print("Enter course drop deadline:");
        LocalDate drop_deadline = LocalDate.parse(scanObj.nextLine());

        Course new_course = new Course(semester, courseID, title, professor, credits, prerequisites, schedule, location,course_limit, drop_deadline);
        System.out.println("Course has been added");
        all_courses.add(new_course);
    }

    public void remove_course(ArrayList<Course> all_courses, String courseID) {
        int len = all_courses.size();
        for (int i = 0; i < len; i++) {
            if (all_courses.get(i).getCourseID().equals(courseID)) {
                all_courses.remove(i);
                i--;
                len--;
            }
        }
        System.out.println("Course has been removed");
    }

    public void view_students(ArrayList<Student> all_students) {
        for (int i = 0; i < all_students.size(); i++) {
            System.out.println(all_students.get(i).toString());
        }
    }

    public void update_student_records(ArrayList<Student> all_students) {
        System.out.print("Enter StudentId of whose record has to be updated");
        String studentID = scanObj.nextLine();
        System.out.println("Enter choice of what to update:");
        System.out.println("1. name ");
        System.out.println("2. email ");
        System.out.println("3. password ");
        System.out.println("4. studentID ");
        System.out.println("5. semester ");
        System.out.println("6. SGPA ");
        System.out.print("7. CGPA ");

        int c2 = scanObj.nextInt();
        scanObj.nextLine(); // Clear the buffer
        Student temp_student = null;
        for (Student student : all_students) {
            if (student.getStudentId().equals(studentID)) {
                temp_student = student;
                break;
            }
        }
        if (c2 == 1) {
            System.out.print("Enter new name");
            String new_name = scanObj.nextLine();
            temp_student.setName(new_name);
        }
        if (c2 == 2) {
            System.out.print("Enter new email");
            String new_email = scanObj.nextLine();
            temp_student.setEmail(new_email);
        }
        if (c2 == 3) {
            System.out.print("Enter new password");
            String new_password = scanObj.nextLine();
            temp_student.setPassword(new_password);
        }
        if (c2 == 4) {
            System.out.print("Enter new studentID");
            String new_studentID = scanObj.nextLine();
            temp_student.setStudentId(new_studentID);
        }
        if (c2 == 5) {
            System.out.print("Enter new semester");
            int new_semester = scanObj.nextInt();
            scanObj.nextLine(); // Clear the buffer
            temp_student.setSemester(new_semester);
        }
        if (c2 == 6) {
            System.out.print("Enter new SGPA");
            double new_sgpa = scanObj.nextDouble();
            scanObj.nextLine(); // Clear the buffer
            temp_student.setS(new_sgpa);
        }
        if (c2 == 7) {
            System.out.print("Enter new CGPA");
            double new_cgpa = scanObj.nextDouble();
            scanObj.nextLine(); // Clear the buffer
            temp_student.setC(new_cgpa);
        }
        System.out.println("Record updated, view to check. ");
    }

    public void change_professor(ArrayList<Professor> all_professors, ArrayList<Course> all_courses) {
        System.out.print("Enter professorID to be added to the course: ");
        String prof = scanObj.nextLine();
        System.out.print("Enter courseID: ");
        String cour = scanObj.nextLine();

        for (Professor professor : all_professors) {
            for (Course course : all_courses) {
                if (professor.getProfessorID().equals(prof) && course.getCourseID().equals(cour)) {
                    course.setProfessor(professor.getName());
                }
            }
        }
        System.out.println("Professor added to course given. ");
    }

    public void handle_complaint(ArrayList<Complaint> all_complaint) {
        System.out.print("How to filter the complaints: 1. Date 2. Status(Pending/Resolved) ");
        int cl=scanObj.nextInt();
        scanObj.nextLine();

        if(cl==1){
            System.out.print("Enter date for which complaints have to be displayed: ");
            String date=scanObj.nextLine();
            for (Complaint complaint : all_complaint) {
                if(complaint.getDate().equals(date)) {
                    System.out.println(complaint.toString());
                }
            }
            System.out.println("-----------------");
        }
        else{
            System.out.print("Enter status for which complaints have to be displayed: Pending or Resolved ");
            String status=scanObj.nextLine();
            for (Complaint complaint : all_complaint) {
                if(complaint.getStatus().equals(status)) {
                    System.out.println(complaint.toString());
                }
            }
            System.out.println("-----------------");
        }

        System.out.print("Do you wish to change complaint status? 1. Yes 2. No");
        int answer = scanObj.nextInt();

        scanObj.nextLine();
        if (answer == 1) {
            System.out.print("Enter complaintID to edit: ");
            int complaintID = scanObj.nextInt();
            scanObj.nextLine();

            System.out.print("Enter new status: ");
            String status = scanObj.nextLine();

            for (Complaint complaint : all_complaint) {
                if (complaint.getComplaintID()==complaintID) {
                    complaint.setStatus(status);
                }
            }
            System.out.println("Status changed, view to check. ");
        }
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
