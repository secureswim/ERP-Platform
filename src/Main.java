import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class Main{
    static ArrayList<Administrator> all_admins = new ArrayList<>();
    static ArrayList<Student> all_students = new ArrayList<>();
    static ArrayList<Professor> all_professors = new ArrayList<>();
    static ArrayList<Course> all_courses = new ArrayList<>();
    static ArrayList<Complaint> all_complaint = new ArrayList<>();
    static ArrayList<Feedback<?>> all_feedback = new ArrayList<>();
    static ArrayList<TA> all_ta = new ArrayList<>();

    static Scanner scanObj = new Scanner(System.in);

    static public void exit(){
        System.exit(0);
    }

    public static void main(String args[]) {
        Administrator admin1 = new Administrator("xoxo", "xoxo@gmail.com", "admin1212", "admin1");

        ArrayList<String> profcourses1 = new ArrayList<>(Arrays.asList("CSE101"));
        ArrayList<String> profcourses2 = new ArrayList<>(Arrays.asList("CSE201"));

        Professor prof1 = new Professor("Anuj Grover", "anujgrover@gmail.com", "anujisthebest", "prof1", profcourses1);
        Professor prof2 = new Professor("Debarka Sengupta", "deb@gmail.com", "debisthebest", "prof2", profcourses2);

        ArrayList<String> enrolledCourses1 = new ArrayList<>(Arrays.asList("CSE212"));
        ArrayList<String> completedCourses1 = new ArrayList<>(Arrays.asList("CSE101", "CSE201","CSE102"));

        ArrayList<String> enrolledCourses2 = new ArrayList<>(Arrays.asList());
        ArrayList<String> completedCourses2 = new ArrayList<>(Arrays.asList("CSE101", "COM201"));

        ArrayList<String> enrolledCourses3 = new ArrayList<>(Arrays.asList("CSE101"));
        ArrayList<String> completedCourses3 = new ArrayList<>(Arrays.asList());

        ArrayList<String> prerequisites_courses1 = new ArrayList<>(Arrays.asList());
        ArrayList<String> prerequisites_courses2 = new ArrayList<>(Arrays.asList("CSE101","CSE102"));
        ArrayList<String> prerequisites_courses3 = new ArrayList<>(Arrays.asList("CSE101"));
        ArrayList<String> prerequisites_courses4 = new ArrayList<>(Arrays.asList("CSE104"));
        ArrayList<String> prerequisites_courses5 = new ArrayList<>(Arrays.asList());


        Student stud1 = new Student("Siddharth", "siddharth@gmail.com", "sidisthebest", "2023522", 3,enrolledCourses1,completedCourses1, 9.0, 9.0);
        Student stud2 = new Student("Sagar", "sagar@gmail.com", "sagaristhebest", "2023458", 2,enrolledCourses2,completedCourses2, 8.0, 7.5);
        Student stud3 = new Student("Aditi", "aditi@gmail.com", "aditiisthebest", "2023038", 1,enrolledCourses3,completedCourses3, 0, 0);


        Course course1 = new Course(1, "CSE101", "Introduction to Programming", "Anuj Grover", 4, prerequisites_courses1, "8-9 every Monday","LHC 101",10, LocalDate.of(2023, 10, 15));
        Course course2 = new Course(3, "CSE201", "Advanced Programming", "Debarka Sengupta", 4, prerequisites_courses2, "3-4 every Monday","LHC 301",10,LocalDate.of(2024, 10, 15));
        Course course3 = new Course(2, "CSE102", "DSA", "Tammam Tillo", 4, prerequisites_courses3, "9-10 every Thursday","LHC 201",10,LocalDate.of(2024, 10, 15));
        Course course4 = new Course(3, "CSE212", "Operating Systems", "Vivek Kumar", 4, prerequisites_courses4, "10-11 every Wednesday","RND A006",10,LocalDate.of(2024, 10, 15));
        Course course5 = new Course(1, "COM101", "Communication", "Anuj Grover", 4, prerequisites_courses5, "8-9 every Tuesday","C12",0,LocalDate.of(2024, 10, 15));

        Complaint complaint1= new Complaint("20/09/2024","Clash in schedule");
        complaint1.setStatus("Pending");
        complaint1.setComplaintID(1);

        ArrayList<String> assigned_courses_ta1 = new ArrayList<>(Arrays.asList("CSE101"));


        TA ta1 = new TA("Siddharth", "siddharth@gmail.com", "sidisthebest", "2023522", 3,enrolledCourses1,completedCourses1, 9.0, 9.0,assigned_courses_ta1);
        all_ta.add(ta1);
        all_courses.add(course1);
        all_courses.add(course2);
        all_courses.add(course3);
        all_courses.add(course4);
        all_courses.add(course5);
        all_professors.add(prof1);
        all_professors.add(prof2);
        all_students.add(stud1);
        all_students.add(stud2);
        all_admins.add(admin1);
        all_complaint.add(complaint1);
        all_students.add(stud3);

            int choice;

        do {

            System.out.println("Welcome to ERP: ");
            System.out.println("Login as: ");
            System.out.println("1. Student/TA ");
            System.out.println("2. Professor ");
            System.out.println("3. Administrator ");
            System.out.print("4. Exit ERP ");
            choice = scanObj.nextInt();

            scanObj.nextLine();

            try {
                if (choice == 1) {
                    int flag = 0;
                    System.out.print("Enter email: ");
                    String email = scanObj.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanObj.nextLine();
                    Student current_user = null;
                    if (flag == 0) {
                        for (Student student : all_students) {
                            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                                current_user = student;
                                System.out.println("Logged in");
                                System.out.println("-----------------");
                                break;
                            }
                        }

                        if (current_user == null) {
                            throw new InvalidLoginException("InvalidLoginException:Incorrect Student credentials.");
                        }
                        int c = 0;
                        do {
                            System.out.println("Enter choice of what to do:");
                            System.out.println("1. View Available courses ");
                            System.out.println("2. Register for courses");
                            System.out.println("3. View Schedule");
                            System.out.println("4. Track Academic Progress");
                            System.out.println("5. Drop Courses ");
                            System.out.println("6. Submit Complaints");
                            System.out.println("7. Give Feedback");
                            System.out.println("8. Login as TA");
                            System.out.print("9. Logout:  ");

                            c = scanObj.nextInt();
                            ;
                            scanObj.nextLine();

                            if (c == 1) {
                                current_user.view_available_courses(all_courses);
                                System.out.println("-----------------");
                            }
                            if (c == 2) {
                                Course current_course = null;
                                System.out.print("Enter courseID to register for: ");
                                String courseID = scanObj.nextLine();
                                for (Course course : all_courses) {
                                    if (course.getCourseID().equals(courseID)) {
                                        current_course = course;
                                        break;
                                    }
                                }

//                                System.out.println(current_course.getCourseID());

                                int course_limit=current_course.getCourse_limit();

                                int currently_enrolled=0;

                                for(Student student:all_students){
                                    for(String course: student.getEnrolled_courses()){
                                        if(course.equals(courseID)){
                                            currently_enrolled+=1;
                                        }
                                    }
                                }


                                int semester = current_course.getSemester();
                                int total_credits = 0;
                                ArrayList<String> enrolled_courses = current_user.getEnrolled_courses();

                                for (String courseId : enrolled_courses) {
                                    Course temp_course = null;
                                    for (Course course : all_courses) {
                                        if (course.getCourseID().equals(courseID)) {
                                            temp_course = course;
                                            break;
                                        }
                                    }
                                    total_credits += temp_course.getCredits();
                                }

                                ArrayList<String> prerequisites = current_course.getPrerequisites();

                                current_user.register_for_courses(course_limit, currently_enrolled, semester, prerequisites, total_credits, current_course.getCredits(), courseID);
                                System.out.println("-----------------");
                            }
                            if (c == 3) {
                                current_user.view_schedule(all_courses);
                                System.out.println("-----------------");
                            }

                            if (c == 4) {
                                current_user.track_academic_progress(all_students, all_courses);
                                System.out.println("-----------------");

                            }

                            if (c == 5) {
                                current_user.drop_course(all_courses);
                                System.out.println("-----------------");
                            }

                            if (c == 6) {
                                System.out.println("Enter what to do: ");
                                System.out.println("1. Register a complaint ");
                                System.out.print("2. Check Status of a complaint ");
                                int c1 = scanObj.nextInt();
                                if (c1 == 1) {
                                    current_user.submit_complaint(all_complaint);
                                } else {
                                    current_user.view_status_complaint(all_complaint);
                                }
                                System.out.println("-----------------");
                            }

                            if (c == 7) {
                                current_user.give_feedback(all_courses, all_feedback);
                                System.out.println("-----------------");
                            }
                            if (c == 8) {
                                int g = 0;
                                TA temp_user = null;
                                for (TA ta : all_ta) {
                                    if (ta.getEmail().equals(email) && ta.getPassword().equals(password)) {
                                        temp_user = ta;
                                        System.out.println("Logged in as TA. ");
                                        System.out.println("-----------------");
                                        g = 1;
                                        break;
                                    }
                                }
                                if (g != 1) {
                                    System.out.println("You are not a TA. ");
                                    System.out.println("-----------------");
                                }
                                else {
                                    int l;
                                    do {
                                        System.out.println("Enter choice of what to do: ");
                                        System.out.println("1. View enrolled students ");
                                        System.out.println("2. Assign Grades ");
                                        System.out.print("3. Logout as TA ");

                                        l = scanObj.nextInt();
                                        scanObj.nextLine();
                                        if (l == 1) {
                                            temp_user.view_enrolled_students(all_students, all_courses);
                                        }
                                        if (l == 2) {
                                            temp_user.assign_grade(all_students, all_courses);
                                            System.out.println("-----------------");
                                        }
                                    } while (l != 3);
                                }
                            }
                        } while (c != 9);

                    }
                    System.out.println("Logging out");
                    System.out.println("-----------------");
                } else if (choice == 3) {
                    Administrator current_user = null;
                    System.out.print("Enter email: ");
                    String email = scanObj.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanObj.nextLine();
                    for (Administrator admin : all_admins) {
                        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                            System.out.println("Logged in");
                            System.out.println("-----------------");
                            current_user = admin;
                            break;
                        }
                    }
                    if (current_user == null) {
                        throw new InvalidLoginException("InvalidLoginException:Incorrect Administrator credentials.");
                    }
                    int c;
                    do {
                        System.out.println("Enter choice of what to do:");
                        System.out.println("1. Manage Course Catalog ");
                        System.out.println("2. Manage Student Records ");
                        System.out.println("3. Assign Professor to courses ");
                        System.out.println("4. Handle Complaints ");
                        System.out.print("5. Logout ");

                        c = scanObj.nextInt();
                        if (c == 1) {
                            System.out.println("-----------------");
                            System.out.println("Enter choice of what to do:");
                            System.out.println("1. View Courses ");
                            System.out.println("2. Add courses ");
                            System.out.print("3. Delete courses ");

                            int c1 = scanObj.nextInt();
                            scanObj.nextLine();

                            if (c1 == 1) {
                                current_user.view_courses(all_courses);
                                System.out.println("-----------------");
                            } else if (c1 == 2) {
                                current_user.add_course(all_courses);
                                System.out.println("-----------------");
                            } else {
                                System.out.print("Enter courseID to remove:");
                                String courseID = scanObj.nextLine();
                                current_user.remove_course(all_courses, courseID);
                                System.out.println("-----------------");
                            }
                        } else if (c == 2) {
                            System.out.println("Enter choice of what to do:");
                            System.out.println("1. View students ");
                            System.out.print("2. Update student records ");

                            int c1 = scanObj.nextInt();

                            if (c1 == 1) {
                                current_user.view_students(all_students);
                                System.out.println("-----------------");
                            } else {
                                current_user.update_student_records(all_students);
                                System.out.println("-----------------");
                            }
                        } else if (c == 3) {
                            current_user.change_professor(all_professors, all_courses);
                            System.out.println("-----------------");
                        } else if (c == 4) {
                            current_user.handle_complaint(all_complaint);
                            System.out.println("-----------------");
                        }
                    } while (c != 5);
                    System.out.println("Logging out");
                    System.out.println("-----------------");
                } else if(choice==2){
                    Professor current_user = null;
                    System.out.print("Enter email: ");
                    String email = scanObj.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanObj.nextLine();
                    for (Professor professor : all_professors) {
                        if (professor.getEmail().equals(email) && professor.getPassword().equals(password)) {
                            current_user = professor;
                            System.out.println("Logged in");
                            System.out.println("-----------------");
                            break;
                        }
                    }
                    if (current_user == null) {
                        throw new InvalidLoginException("InvalidLoginException:Incorrect Professor credentials.");
                    }

                    int c;
                    do {
                        System.out.println("Enter choice of what to do:");
                        System.out.println("1. View or edit Course Details ");
                        System.out.println("2. View enrolled students in a course ");
                        System.out.println("3. Assign Grades ");
                        System.out.println("4. View course feedback  ");
                        System.out.println("5. Assign TA  ");
                        System.out.print("6. Logout: ");
                        c = scanObj.nextInt();
                        if (c == 1) {
                            current_user.manage_courses(all_courses);
                            System.out.println("-----------------");
                        } else if (c == 2) {
                            current_user.view_enrolled_students(all_students, all_courses);
                            System.out.println("-----------------");
                        } else if (c == 3) {
                            current_user.assign_grade(all_students, all_courses);
                        } else if (c == 4) {
                            current_user.view_feedback(all_feedback);
                        } else if(c==5){
                            current_user.assign_ta(all_students,all_ta);
                            System.out.println("-----------------");
                        }
                    } while (c != 6);
                    System.out.println("Logging out");
                    System.out.println("-----------------");
                }
            }catch (InvalidLoginException e) {
                System.out.println(e.getMessage());
            } catch (CourseFullException | DropDeadlinePassedException e) {
                throw new RuntimeException(e);
            }
        }while(choice!=4);
        System.out.println("Exiting ERP");

        System.out.println("-----------------");
    }
}
