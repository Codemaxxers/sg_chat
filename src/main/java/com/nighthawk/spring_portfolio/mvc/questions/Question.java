package com.nighthawk.spring_portfolio.mvc.questions;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "Question.findByCourse", query = "SELECT * FROM ?1", resultClass = Question.class)
public class Question {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Question", nullable = false)
    private String question;

    @Column(name = "Answer 1", nullable = false)
    private String answer1;

    @Column(name = "Answer 2", nullable = false)
    private String answer2;

    @Column(name = "Answer 3", nullable = false)
    private String answer3;

    @Column(name = "Answer 4", nullable = false)
    private String answer4;

    @Column(name = "Correct Answer", nullable = false)
    private Integer correctAnswer;

    @Column(name = "Difficulty", nullable = false)
    private Integer difficulty;

    @Column(name = "Unit", nullable = false)
    private String unit;

    @Column(name = "Points", nullable = false)
    private Integer points;

    // Getters and setters
    public Question(String question, String answer1, String answer2, String answer3, String answer4, Integer correctAnswer, Integer difficulty, String unit, Integer points) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.unit = unit;
        this.points = points;
    }

    public static Question[] init() {
        Question[] questions = new Question[30];
    
        // CSP Questions
        questions[0] = new Question("What is an example of binary data?", "22222", "111000", "303030300", "00600903", 1, 1, "csp", 40);
        questions[1] = new Question("Which of the following is a characteristic of a good password?", "Simple", "Easily guessable", "Complex and unique", "Short", 3, 2, "csp", 35);
        questions[2] = new Question("What is an example of a lossless compression algorithm?", "MP3", "JPEG", "GIF", "ZIP", 4, 2, "csp", 30);
        questions[3] = new Question("What does the acronym 'URL' stand for?", "Uniform Resource Locator", "Universal Record Locator", "Unique Resource Link", "Universal Resource Locator", 1, 1, "csp", 40);
        questions[4] = new Question("Which of the following is NOT a programming language?", "Python", "Java", "CSS", "HTML", 3, 1, "csp", 45);
        questions[5] = new Question("What is the purpose of HTML?", "Styling web pages", "Creating dynamic content", "Defining the structure of web pages", "Handling server-side logic", 3, 1, "csp", 45);
        questions[6] = new Question("What is the main function of a firewall?", "To block all incoming traffic", "To filter network traffic", "To speed up internet connection", "To install updates automatically", 2, 2, "csp", 30);
        questions[7] = new Question("What is an example of symmetric encryption?", "RSA", "AES", "Diffie-Hellman", "SHA-256", 2, 2, "csp", 35);
        questions[8] = new Question("What is the purpose of a DNS?", "To convert IP addresses to domain names", "To convert domain names to IP addresses", "To encrypt data transmission", "To protect against malware", 2, 1, "csp", 40);
        questions[9] = new Question("Which of the following is NOT a type of network topology?", "Ring", "Bus", "Mesh", "Point", 4, 1, "csp", 45);
        questions[10] = new Question("What is the role of a router in a network?", "To connect devices within the same network", "To connect multiple networks together", "To protect against viruses", "To host websites", 2, 2, "csp", 35);
        questions[11] = new Question("Which of the following is an example of a DNS record type?", "FTP", "MX", "HTTP", "SSL", 2, 2, "csp", 30);
        questions[12] = new Question("What does the acronym 'HTTP' stand for?", "Hypertext Transfer Protocol", "Hyperlink Text Transfer Protocol", "High Transfer Technology Protocol", "Home Text Terminal Protocol", 1, 1, "csp", 40);
        questions[13] = new Question("What is the purpose of an IP address?", "To identify a specific website", "To identify a specific device on a network", "To encrypt internet traffic", "To manage email communication", 2, 1, "csp", 45);
        questions[14] = new Question("Which of the following is NOT a characteristic of a good algorithm?", "Efficiency", "Completeness", "Uniqueness", "Correctness", 3, 2, "csp", 35);
    
        // CSA Questions
        questions[15] = new Question("Which of the following is NOT an object-oriented programming language?", "Java", "Python", "C", "C++", 3, 1, "csa", 45);
        questions[16] = new Question("What is an example of a data structure?", "ArrayList", "Math", "Scanner", "String", 1, 1, "csa", 40);
        questions[17] = new Question("What does the '++' operator do in Java?", "Increment by 1", "Decrement by 1", "Multiply by 2", "Divide by 2", 1, 2, "csa", 35);
        questions[18] = new Question("Which keyword is used to define a method in Java?", "func", "method", "def", "public", 4, 1, "csa", 45);
        questions[19] = new Question("What is the result of 7 % 3 in Java?", "3", "2", "1", "0", 3, 2, "csa", 30);
        questions[20] = new Question("What is the purpose of the 'this' keyword in Java?", "To refer to the current instance of the class", "To indicate the end of a method", "To print text to the console", "To create a new object", 1, 2, "csa", 35);
        questions[21] = new Question("What is the output of 'System.out.println(5 == 5)?' in Java?", "True", "False", "5", "Error", 1, 1, "csa", 40);
        questions[22] = new Question("Which of the following is an example of a primitive data type in Java?", "String", "Array", "Integer", "Boolean", 4, 1, "csa", 45);
        questions[23] = new Question("What does the 'new' keyword do in Java?", "Delete an object", "Create a new object", "Declare a variable", "Define a method", 2, 2, "csa", 30);
        questions[24] = new Question("What is the purpose of inheritance in object-oriented programming?", "To create multiple instances of a class", "To allow one class to acquire properties and behavior of another class", "To perform mathematical operations", "To store data temporarily", 2, 2, "csa", 35);
        questions[25] = new Question("Which data structure uses the 'first-in, first-out' (FIFO) principle?", "Stack", "Queue", "Linked List", "Tree", 2, 1, "csa", 40);
        questions[26] = new Question("What is the purpose of the 'super' keyword in Java?", "To start a loop", "To call the superclass constructor", "To print output to the console", "To delete an object", 2, 2, "csa", 35);
        questions[27] = new Question("What does the 'extends' keyword indicate in Java?", "Inheritance", "Composition", "Initialization", "Delegation", 1, 1, "csa", 45);
        questions[28] = new Question("What is the main advantage of using an interface in Java?", "To define a contract for implementing classes", "To perform input/output operations", "To handle exceptions", "To represent a set of related data", 1, 2, "csa", 30);
        questions[29] = new Question("Which of the following is NOT a Java keyword?", "class", "method", "break", "integer", 4, 1, "csa", 45);
    
        return questions;
    }

    public static void main(String[] args) {
        Question questions[] = init();
        for (Question question : questions) {
            System.out.println(question.getQuestion());
        }
    }
}   
