How to run the code:
Open a terminal or command prompt and run the following command to clone the repository:
git clone https://github.com/secureswim/ERP-Platform.git
cd ERP-Platform

Open IntelliJ Idea or any other IDE and navigate to the directory where the project was cloned and open the project.

Run the Main.java file and proceed according to the menus given.

Assumptions

1. The system uses a console-based interface for user interaction.
2. Data is not persisted between runs; it's initialized in the main method.
3. Input validation is minimal; users are expected to input correct data types.
4. The system doesn't handle concurrent users or sessions.
5. Passwords are stored and compared in plain text (not secure for a real-world application).
6. The academic calendar is not explicitly modeled; courses are associated with semesters.

Object-Oriented Programming Concepts Applied
Classes
The system uses several classes to model different entities and functionalities:

User: Abstract base class for all users
Student, Professor, Administrator: Specific user types
Course: Represents academic courses
Complaint: Represents student complaints

Inheritance

The Student, Administrator, and Professor classes all extend the abstract User class, inheriting common attributes (name, email, password) and methods.

Polymorphism

Method overriding is used in subclasses. For example, the toString() method in the Student class overrides the default Object.toString().

Encapsulation

Private attributes are used in all classes (e.g., private String name in User class).
Public getter and setter methods are provided to access and modify these private attributes, controlling access to the internal state of objects.

Abstraction

The User class is declared as abstract, serving as a template for more specific user types.
The system abstracts complex operations into methods, such as register_for_courses() in the Student class, hiding the implementation details.

Generic Programming:

It is utilized in the Feedback class by introducing a type parameter <T> that allows the class to accept different types of feedback, whether numerical (e.g., Integer for ratings) or textual (e.g., String for comments). This flexibility is achieved by defining the feedback attribute with the generic type T, which can represent any data type depending on the context in which the class is used.

The give_feedback method further demonstrates the use of this generic approach by allowing users to input either numeric feedback (in the form of an integer) or textual feedback (as a string). The method creates instances of the Feedback class for both types of feedback, using Feedback<Integer> for numeric ratings and Feedback<String> for textual comments, and then stores these in a single ArrayList<Feedback<?>>. The wildcard <?> in ArrayList<Feedback<?>> allows the list to hold Feedback objects of any type, making it possible to store and manage both Integer and String feedback together.

Object Classes and Inheritance:

The TA (Teaching Assistant) class inherits from the Student class, demonstrating object-oriented principles of inheritance. By extending the Student class, TA inherits all its attributes and methods, such as name, email, studentId, and semester. The TA class also introduces its own specific attribute, assigned_courses, which tracks the courses a TA is responsible for.

Methods like view_enrolled_students and assign_grade are exclusive to the TA class, enabling TAs to perform tasks such as viewing students enrolled in assigned courses and assigning grades. By using inheritance, the TA class avoids duplicating common student-related functionality, while adding TA-specific features. This structure promotes code reuse and modularity.

Since the TA is also a student, they login first as a student then get an option to login as a TA and even utilise the other functionalities implemented for a student. Once logged in as a TA, the TA can access the functionalities given to a TA. If it is required to go back to student functionalities, the TA can logout as a TA.

Exception Handling:

In the code, custom exception handling is implemented by defining three user-defined exceptions: CourseFullException, InvalidLoginException, and DropDeadlinePassedException. Each of these classes extends Exception, allowing for specific error scenarios to be captured and handled more effectively. The constructors of these classes accept a custom error message, which can be used to provide meaningful feedback when an exception is thrown.

These exceptions are integrated into the main program using try-catch blocks. When an operation like course registration, login, or course drop is performed, a try block checks if the action can proceed. If an issue arises (e.g., the course is full, login details are incorrect, or the drop deadline has passed), the corresponding exception is thrown, and the catch block handles it, either by notifying the user of the specific issue. This structured approach improves the robustness of the code by ensuring that exceptional conditions are anticipated and managed gracefully.


This ERP system demonstrates a solid foundation in object-oriented programming principles. With further refinement and expansion, it could evolve into a more robust and feature-rich application.
