package com.nighthawk.spring_portfolio.mvc.terms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "Term.findByCourse", query = "SELECT * FROM ?1", resultClass = Term.class)
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Term", nullable = false)
    private String term;

    @Column(name = "Definition", nullable = false)
    private String definition;

    @Column(name = "Unit", nullable = false)
    private String unit;

    // Getters and setters
    public Term(String term, String definition, String unit) {
        this.term = term;
        this.definition = definition;
        this.unit = unit;
    }

    public static Term[] init() {
    
        // CSP terms
        Term[] terms = new Term[]{

            // CSP
            new Term("syntax error", "A mistake in typed code that violates the rules of the programming language. Typically, code with syntax errors will not run.", "csp"),
            new Term("logic error", "A mistake in an algorithm or program that causes it to behave unexpectedly or return the incorrect value.", "csp"),
            new Term("run-time error", "A mistake in a program that happens only when the program is actually run, such as a program attempting to access memory that does not exist.", "csp"),
            new Term("overflow error", "Error that results when the number of bits is not enough to represent the number (like a car’s odometer “rolling over”).", "csp"),
            new Term("bit", "A binary digit, either 0 or 1.", "csp"),
            new Term("byte", "A sequence of 8 bits.", "csp"),
            new Term("roundoff", "Error that results when the number of bits is not enough to represent the number with full precision.", "csp"),
            new Term("analog data", "Values that change smoothly, rather than in discrete intervals, over time. For example, the pitch and volume of a live concert.", "csp"),
            new Term("lossless", "Compressing data in a way that preserves all data away and allows full recovery of the original.", "csp"),
            new Term("lossy", "Compressing data in a way that discards some data and makes it impossible to recover the original.", "csp"),
            new Term("metadata", "Data about data, like descriptive information about a file or a row in a database.", "csp"),
            new Term("sequencing", "The sequential execution of steps in an algorithm or code in a program (like steps in a recipe).", "csp"),
            new Term("selection", "A Boolean condition to determine which of two paths are taken in an algorithm or program.", "csp"),
            new Term("iteration", "The repetition of steps in an algorithm or program for a certain amount of times or until a certain condition is met.", "csp"),
            new Term("linear search", "An algorithm that iterates through each item in a list until it finds the target value.", "csp"),
            new Term("binary search", "An algorithm that searches a sorted list for a value by repeatedly splitting the list in half.", "csp"),
            new Term("reasonable time", "A run time for an algorithm that doesn't increase faster than a polynomial function of the input size. An unreasonable run time would increase superpolynomially.", "csp"),
            new Term("heuristic", "A technique that helps an algorithm find a good solution in a hard problem (like always walking toward the north star when you are stuck in a forest).", "csp"),
            new Term("undecidable", "A problem that is so logically difficult, we can’t ever create an algorithm that would be able to answer \"yes or \"no\" for all inputs (like the halting problem).", "csp"),
            new Term("library", "A collection of procedures that are useful in creating programs.", "csp"),
            new Term("API", "Application Programming Interface, a library of procedures and a description of how to call each procedure.", "csp"),
            new Term("modularity", "The separation of a program into independent modules that are each responsible for one aspect of the program's functionality.", "csp"),
            new Term("traversal", "The iteration over the items in a list. A full traversal iterates over every item, while a partial traversal iterates over a subset of the items.", "csp"),
            new Term("computing device", "A physical device that can run a program, such as a computer, smart phone, or smart sensor.", "csp"),
            new Term("computer network", "A group of interconnected computing devices capable of sending or receiving data.", "csp"),
            new Term("bandwidth", "The maximum amount of data that can be sent in a fixed period of time over a network connection, typically measured in bits per second.", "csp"),
            new Term("protocol", "An agreed upon set of rules that specify the behavior of a system.", "csp"),
            new Term("scalability", "The ability of a system to adjust in scale to meet new demands.", "csp"),
            new Term("IP (Internet Protocol)", "The protocol that determines how to address nodes on the network (with IP addresses) and how to route data from one node to a destination node (using routers).", "csp"),
            new Term("TCP (Transmission Control Protocol)", "A data transport protocol that includes mechanisms for reliably transmitting packets to a destination.", "csp"),
            new Term("UDP (User Datagram Protocol)", "A lightweight data transport protocol with minimal error checking.", "csp"),
            new Term("World Wide Web", "A system of linked pages, media, and files, browsable over HTTP.", "csp"),
            new Term("HTTP (Hypertext Transfer Protocol)", "The protocol that powers the Web, used to request webpages from servers and submit form data to servers.", "csp"),
            new Term("parallel computing", "A computational model which splits a program into multiple tasks, some of which can be executed simultaneously.", "csp"),
            new Term("speedup", "The improvement in the amount of time a parallelized program takes to solve a problem, computed as the amount of time to complete the task sequentially divided by the amount of time to complete the task when run in parallel.", "csp"),
            new Term("distributed computing", "A computational model which uses multiple devices to run different parts of a program.", "csp"),
            new Term("digital divide", "The idea that some communities or populations have less access to computing than others, typically due to limitations of Internet speed or computer hardware access.", "csp"),
            new Term("crowdsourcing", "A model in which many online users combine efforts to help fund projects, generate ideas, or create goods or services (like Wikipedia).", "csp"),
            new Term("citizen science", "Crowdsourcing for science! The participation of volunteers from the public in a scientific research project (like collecting rain samples or counting butterflies).", "csp"),
            new Term("Creative Commons", "An alternative to copyright that allows people to declare how they want their artistic creations to be shared, remixed, used in noncommercial contexts, and how the policy should propagate with remixed versions.", "csp"),
            new Term("open access", "A policy that allows people to have access to documents (like research papers) for reading or data (like government datasets) for analysis.", "csp"),
            new Term("PII (Personally identifiable information)", "Information about an individual that can be used to uniquely identify them (directly or indirectly).", "csp"),
            new Term("multifactor authentication (MFA)", "A method of user authentication which requires the user to present multiple pieces of evidence in multiple categories (such as knowledge and possession).", "csp"),
            new Term("encryption", "The process of scrambling data to prevent unauthorized access.", "csp"),
        
            //CSA
            new Term("String(String str)", "Constructs a new String object that represents the same sequence of characters as str", "csa"),
            new Term ("int length()","Returns the number of characters in a String object","csa"),
            new Term ("String substring(int from, int to)","Returns the substring beginning at index from and ending at index to - 1","csa"),
            new Term ("String substring(int from)","Returns substring(from, length())","csa"),
            new Term ("int indexOf(String str)","Returns the index of the first occurrence of str; returns -1 if not found","csa"),
            new Term ("boolean equals(String other)","Returns true if this is equal to other; returns false otherwise","csa"),
            new Term ("int compareTo(String other)","Returns a value < 0 if this is less than other; returns zero if this is equal to other; returns a value > 0 if this is greater than other","csa"),
            new Term ("Integer(int value)","Constructs a new Integer object that represents the specified int value","csa"),
            new Term ("Integer.MIN_VALUE","The minimum value represented by an int or Integer","csa"),
            new Term ("Integer.MAX_VALUE","The maximum value represented by an int or Integer","csa"),
            new Term ("int intValue()","Returns the value of this Integer int","csa"),
            new Term ("Double(double value)","Constructs a new Double object that represents the specified double value","csa"),
            new Term ("double doubleValue()","Returns the value of this Double as a double","csa"),
            new Term ("static int abs(int x)","Returns the absolute value of an int value","csa"),
            new Term ("static double abs(double x)","Returns the absolute value of a double value","csa"),
            new Term ("static double pow(double base, double exponent)","Returns the value of the first parameter raised to the power of the second parameter","csa"),
            new Term ("static double sqrt(double x)","Returns the positive square root of a double value","csa"),
            new Term ("static double random()","Returns a double value greater than or equal to 0.0 and less than 1.0","csa"),
            new Term ("int size()","Returns the number of elements in the list","csa"),
            new Term ("boolean add(E obj)","Appends obj to end of list; returns true","csa"),
            new Term ("void add(int index, E obj)","Inserts obj at position index (0 <= index <= size), moving elements at position index and higher to the right (adds 1 to their indices) and adds 1 to size","csa"),
            new Term ("E get(int index)","Returns the element at position index in the list","csa"),
            new Term ("E set(int index, E obj)","Replaces the element at position index with obj; returns the element formerly at position index","csa"),
            new Term ("E remove(int index)","Removes element from position index, moving elements at position index + 1 and higher to the left (subtracts 1 from their indices) and subtracts 1 from size; returns the element formerly at position index","csa"),

            //Cyber
            new Term("Authentication", "Verifying a user's identity to grant access to a system or files using passwords, biometrics, or a combination.", "cyber"),
            new Term("Botnet", "A network of compromised computers used for various cyberattacks such as Bitcoin mining, spam, and DDoS attacks.", "cyber"),
            new Term("Data Breach", "Unauthorized access to a system resulting in exposure of sensitive data like credit card or Social Security numbers.", "cyber"),
            new Term("DDoS", "Overloading a website by bombarding it with requests from multiple sources, causing it to shut down temporarily.", "cyber"),
            new Term("Domain", "A group of interconnected computers and devices functioning as a single entity.", "cyber"),
            new Term("Encryption", "Scrambling data to protect it from unauthorized access.", "cyber"),
            new Term("Exploit", "A method used to attack a computer system, such as malicious commands or software.", "cyber"),
            new Term("Firewall", "Technology used to prevent unauthorized access to a network.", "cyber"),
            new Term("Phishing", "Tricking individuals into divulging sensitive information by posing as a legitimate entity.", "cyber"),
            new Term("Virus", "Malware that infects and alters data, spreading to other systems.", "cyber"),
            new Term("VPN", "A secure network connection that masks the user's IP address.", "cyber"),
            new Term("Cloud", "On-demand internet services for data storage and processing.", "cyber"),
            new Term("Software", "Instructions for a computer to perform tasks, stored on storage devices.", "cyber"),
            new Term("IP Address", "Numeric identifier for devices connected to the internet.", "cyber"),
            new Term("Multi-Factor Authentication", "Verification method requiring multiple credentials.", "cyber"),
            new Term("User Authentication", "Process of confirming a user's identity for access control.", "cyber"),
            new Term("Cyber Attack", "Attempt to breach a system's security.", "cyber"),
            new Term("Network", "Interconnected computers sharing resources and data.", "cyber"),
        };
        
        
        return terms;
    }

    public static void main(String[] args) {
        Term terms[] = init();
        for (Term term : terms) {
            System.out.println(term.getTerm());
        }
    }
}   
