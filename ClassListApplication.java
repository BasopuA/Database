/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classlistapplication;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import za.ac.tut.bl.StudentManagerDB;
import za.ac.tut.entity.Student;

/**
 *
 * @author Alex
 */
public class ClassListApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException 
    {
        String url="jdbc:derby://localhost:1527/StudentDb; create=true";
        String username = "Anele";
        String password = "12345";
        StudentManagerDB database = new StudentManagerDB(url, username, password);
        int studNum,age,numComputers;
        String name,surname;
        double percentage;
        char gender;
        Scanner input = new Scanner(System.in);
        Student student = null;
                
        
        int options = promptUser();
        while(options != 17)
        {
            switch (options) 
            {
                case 1:
                    System.out.println("Enter student number");
                    studNum = input.nextInt();
                    System.out.println("enter student name");
                    name = input.next();
                    System.out.println("enter studant surname");
                    surname = input.next();
                    System.out.println("Enter student gender");
                    gender = input.next().charAt(0);
                    System.out.println("Enter student age");
                    age = input.nextInt();
                    System.out.println("Enter student devices");
                    numComputers = input.nextInt();
                    System.out.println("Enter student percentage");
                    percentage = input.nextDouble();
                    
                    student = new Student(studNum, name, surname, gender, age, numComputers, percentage);
                    boolean added = database.addStudent(student);
                    if(added)
                    {
                        System.out.println("Student is Successfuly added");
                    }
                    else
                    {
                        System.out.println("student is not added");
                    }
                    
                break;
                case 2:
                    System.out.println("Enter student number");
                    studNum = input.nextInt();
                    boolean deleted = database.deleteStudent(studNum);
                    if(deleted)
                    {
                        System.out.println("Student is Successfuly deleted");
                    }
                    else
                    {
                        System.out.println("student is not deleted");
                    }
                    
                break;
                case 3:
                    System.out.println("Enter student number");
                    studNum = input.nextInt();
                    System.out.println("Enter student percentage");
                    percentage = input.nextDouble();
                    
                    student = database.getStudent(studNum);
                    
                    if(student != null)
                    {
                        student.setPercentMark(percentage);
                    }
                    boolean changeMarks =database.updateStudent(student);
                    
                    if(changeMarks)
                    {
                        System.out.println("Student marks is Successfuly updated");
                    }
                    else
                    {
                        System.out.println("student marks is not updated");
                    }
                    
                break;
                case 4:
                    System.out.println("Enter student number");
                    studNum = input.nextInt();
                    student = database.getStudent(studNum);
                    System.out.println(student);
                break;
                case 5:
                    List<Student> students = database.getAllStudent();
                    System.out.println("===============student list========================");
                    for (Student student1 : students) 
                    {   
                        System.out.println(student1);
                    }
                    System.out.println("=======================================");
                break;
                case 6:
                    int highest = database.getHighestMark();
                    System.out.println("Highest Mark: "+highest+"%");
                break;
                case 7:
                    int lowest = database.getLowestTestMark();
                    System.out.println("Lowest Mark: "+lowest+"%");
                break;
                case 8:
                    int devices = database.getCount();
                    System.out.println("Number of devices: "+devices);
                break;
                case 9:
                    int numPass = database.getNumPassed();
                    System.out.println("Number of student pass: "+numPass);
                break;
                case 10:
                    int numFail = database.getNumFailed();
                    System.out.println("Number of student fail: "+numFail);
                break;
                case 11:
                    System.out.println("Enter student gender");
                    gender = input.next().charAt(0);
                    List<Student> listPassGender = database.determinePassedGender(gender);
                    System.out.println("=====Pass List of choosen gender===============");
                    for (Student student1 : listPassGender) 
                    {
                        System.out.println(student1);  
                    }
                    System.out.println("\"=====Fail List of choosen gender===============");
                break;
                case 12:
                    System.out.println("Enter student gender");
                    gender = input.next().charAt(0);
                    List<Student> listFailGender = database.determineFailedGender(gender);
                    System.out.println("=====Pass List of choosen gender===============");
                    for (Student student1 : listFailGender) 
                    {
                        System.out.println(student1);  
                    }
                    System.out.println("=======================================");
                break;
                case 13:
                    int avgMark = database.getAverageTestMark();
                    System.out.println("Test average percentage: "+avgMark+"%");
                break;
                case 14:
                    int ageAverage = database.getAvgAge();
                    System.out.println("Age  Average: "+ageAverage);
                break;
                case 15:
                    List<Student> passList = database.deteminePassList();
                    for (Student student1 : passList) 
                    {
                        System.out.println(student1);
                    }
                break;
                case 16:
                    
                break;
                
                
                
                default:
                    System.out.println("INVALID ");
            }
            options = promptUser();
        }
        
        
    }
    private static int promptUser()
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("===========================================================\n"
                + "Select one of the following options\n"
                + "1 - add student\n "
                + "2 - delete student\n "
                + "3 - update student\n "
                + "4 - get student\n "
                + "5 - get all student\n "
                + "6 - lowest mark\n "
                + "7 - highest mark\n"
                + "8 - total number of devices\n "
                + "9 - passed students\n "
                + "10 - failed students\n "
                + "11- gender  passed list\n "
                + "12 -  gender failed list\n "
                + "13 - mark average\n "
                + "14 - age average\n"
                + "15 - Pass List\n"
                + "16 - Fail List\n "
                + "17 - EXIT\n  "
                + "Your options: ");
        int options = input.nextInt();
        
        return options;
    }
}
