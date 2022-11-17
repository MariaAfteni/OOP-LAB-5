import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

import static java.lang.System.out;

public class Simulation {
    public void Simulate() throws InterruptedException {
        {

            University u = new University();
            Course c1 = new Course();
            Course c2 = new Course();
            Course c3 = new Course();
            Assignment a1 = new Assignment();
            Assignment a2 = new Assignment();
            Assignment a3 = new Assignment();
            Student s1 = new Student();
            Student s2 = new Student();
            Student s3 = new Student();
            Midterm m = new Midterm();
            Exam e = new Exam();
            int total_students = u.total_students();
            String course1, course2, course3;
            String assignment1, assignment2, assignment3;
            String s1name, s1surname, s2name, s2surname, s3name, s3surname, status;

            out.print("\n======================================");
            out.print("\nCourses this semester");
            course1 = c1.list_courses();
            course2 = c2.list_courses();
            if (Objects.equals(course1, course2))
            {course2 = c2.list_courses();}
            course3 = c3.list_courses();
            if (Objects.equals(course1, course3) || Objects.equals(course2, course3)){course3 = c3.list_courses();}
            out.print("\n======================================");
            ArrayList<String> courses = new ArrayList<>();
            courses.add(course1); courses.add(course2); courses.add(course3);

            Date date = new Date();
            int weekday = 1, week = 1, w = 0, p = 0, grade;
            ArrayList<Integer> grade1 = new ArrayList<>();
            ArrayList<Integer> grade2 = new ArrayList<>();
            ArrayList<Integer> grade3 = new ArrayList<>();
            ArrayList<Integer> mid1 = new ArrayList<>();
            ArrayList<Integer> mid2 = new ArrayList<>();
            ArrayList<Integer> mid3 = new ArrayList<>();
            ArrayList<Integer> exam = new ArrayList<>();

            s1surname = s1.Surname();
            s1name = s1.Name();
            s2surname = s2.Surname();
            s2name = s2.Name();
            s3surname = s3.Surname();
            s3name = s3.Name();

            while(true){
                if (w == 0){
                    out.print("\nWeek " + week);}
                out.print("\n--------------------------------------");
                weekday = date.display_day(weekday);
                int present = u.students_present(total_students, weekday);
                w++;
                if (p<5){
                    if (week % 2 != 0 && week % 5 != 0){
                        out.print("\nTotal students present today: " + present);

                        assignment1 = a1.add_assignment(course1);
                        a1.submitted_by(assignment1, present, total_students);
                        out.print("\n");
                        assignment2 = a2.add_assignment(course2);
                        a2.submitted_by(assignment2, present, total_students);
                        out.print("\n");
                        assignment3 = a3.add_assignment(course3);
                        a3.submitted_by(assignment3, present, total_students);
                        out.print("\n");
                        out.print("\n------------------------------------------");
                        out.print("\n|          Submit Log                    |");
                        out.print("\n------------------------------------------");

                        out.print("\n#" + s1surname + " " + s1name);
                        out.print("\n - " + course1);
                        grade = a1.grade();
                        grade1.add(grade);
                        out.print("\n - " + course2);
                        grade = a2.grade();
                        grade1.add(grade);
                        out.print("\n - " + course3);
                        grade = a3.grade();
                        grade1.add(grade);
                        out.print("\n#" + s2surname + " " + s2name);
                        out.print("\n - " + course1);
                        grade = a1.grade();
                        grade2.add(grade);
                        out.print("\n - " + course2);
                        grade = a2.grade();
                        grade2.add(grade);
                        out.print("\n - " + course3);
                        grade = a3.grade();
                        grade2.add(grade);
                        out.print("\n#" + s3surname + " " + s3name);
                        out.print("\n - " + course1);
                        grade = a1.grade();
                        grade3.add(grade);
                        out.print("\n - " + course2);
                        grade = a2.grade();
                        grade3.add(grade);
                        out.print("\n - " + course3);
                        grade = a3.grade();
                        grade3.add(grade);
                    }
                    else if(week % 2 == 0){
                        out.print("\n!!! MIDTERMS WEEK!!");
                        out.print("\nTotal students present today: " + present);
                        Collections.shuffle(courses, new Random(1));
                        out.print("\n -> " + courses.get(0));
                        out.print("\n#" + s1surname + " " + s1name);
                        grade = m.grade_midterm(grade1);
                        mid1.add(grade);
                        status = m.status();
                        m.display(grade, status);
                        out.print("\n#" + s2surname + " " + s2name);
                        grade = m.grade_midterm(grade2);
                        mid2.add(grade);
                        status = m.status();
                        m.display(grade, status);
                        out.print("\n#" + s3surname + " " + s3name);
                        grade = m.grade_midterm(grade3);
                        mid3.add(grade);
                        status = m.status();
                        m.display(grade, status);
                        p = 5;
                    }
                    else{
                        out.print("\n!!! EXAMS WEEK !!!");
                        out.print("\nTotal students present today: " + present);
                        Collections.shuffle(courses, new Random(1));
                        out.print("\n -> " + courses.get(0));
                        out.print("\n#" + s1surname + " " + s1name);
                        grade = e.grade_exam(mid1);
                        exam.add(grade);
                        out.print("\n#" + s2surname + " " + s2name);
                        grade = e.grade_exam(mid2);
                        exam.add(grade);
                        out.print("\n#" + s3surname + " " + s3name);
                        grade = e.grade_exam(mid3);
                        exam.add(grade);
                        e.avg(exam, courses.get(0));
                        p = 5;
                        if (w == 5){
                            out.print("\n===========================================");
                            out.print("\n         END of the semester");
                            out.print("\n===========================================");
                            Thread.sleep(1000);
                            out.print("\n===========================================");
                            out.print("\n      START of the new semester");
                            out.print("\n===========================================");
                            out.print("\n======================================");
                            out.print("\nCourses this semester");
                            course1 = c1.list_courses();
                            course2 = c2.list_courses();
                            if (Objects.equals(course1, course2)){course2 = c2.list_courses();}
                            course3 = c3.list_courses();
                            if (Objects.equals(course1, course3) || Objects.equals(course2, course3)){course3 = c3.list_courses();}
                            out.print("\n======================================");
                            courses.clear();
                            courses.add(course1); courses.add(course2); courses.add(course3);

                            grade1.clear();
                            grade2.clear();
                            grade3.clear();
                            mid1.clear();
                            mid2.clear();
                            mid3.clear();
                            exam.clear();


                            s1surname = s1.Surname();
                            s1name = s1.Name();
                            s2surname = s2.Surname();
                            s2name = s2.Name();
                            s3surname = s3.Surname();
                            s3name = s3.Name();
                            week = 0;
                        }
                    }
                    p++;} else { p = 0;}

                if (w > 5){
                    week ++;
                    w = 0;
                }
                Thread.sleep(3500);
            }
        }
    }
}
