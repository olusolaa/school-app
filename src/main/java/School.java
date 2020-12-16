import util.InvalidAgeException;
import util.InvalidApplicantException;
import util.NotAPrincipalException;
import util.NotATeacherException;

import java.util.ArrayList;
import java.util.List;


public class School extends Classroom {


    //List all the fields present in school
    //*************************************
    private String schoolName;
    private static Person schoolPrincipal;
    private List<Person> students = new ArrayList<>();
    private List<Person> teachers = new ArrayList<>();
    private List<Person> nonAcademicStaffs = new ArrayList<>();
    private List<Person> applicants = new ArrayList<>();
    private List<String> offences = new ArrayList<>();

    private List<String> SS1 = new ArrayList<>();
    private List<String> SS2 = new ArrayList<>();
    private List<String> SS3 = new ArrayList<>();



    //Set and get the name of the school
    //**********************************
    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return this.schoolName;
    }


    //Set and get the staffs and applicants of the school the school
    //************************************************
    public void setPrincipal(Person staff){
        if(staff.getRole().equals("principal")){
            this.schoolPrincipal = staff;
        }else {
            throw new NotAPrincipalException();
        }
    }

    public Person getSchoolPrincipal() {
        return schoolPrincipal;
    }

    public void addTeacher(Person staff) {
        if(staff.getRole().equals("teacher")) {
            teachers.add(staff);
        } else {
            throw new NotATeacherException();
        }
    }

    public List<Person> getTeacher() {
        return teachers;
    }

    public void addNonAcademicStaff(Person staff) {
        if (staff.getRole().equals("nonTeacher"))
        nonAcademicStaffs.add(staff);
    }

    public List<Person> getNonAcademicStaffs() {
        return nonAcademicStaffs;
    }

    public void addApplicant(Person applicant) {
        if (applicant.getKlass().equals("SS1") || applicant.getKlass().equals("SS2") || applicant.getKlass().equals("SS2") ){
            this.applicants.add(applicant);
        }

    }

    public List<Person> getApplicants() {
        return applicants;
    }


    //Validate principal to check the admission status of an
    //applicant. If it's a success, add him to list of students
    //*********************************************************

    public boolean admitApplicant(Person applicant, Person staff) {
        if (staff.getRole().equals("principal")) {
            //System.out.println("principal validated.");
            if (applicant.getAge() >= 14 && applicant.getAge() <= 18) {
                students.add(applicant);
                //System.out.println("Congratulations, " + applicant.getName() + " has been admitted into " + getNameOfSchool());
                return true;
            } else {
                //System.out.println("Sorry " + applicant.getName() +" is not admitted she does not meet the age requirement" );
                throw new InvalidAgeException();
            }
        } else {
            //System.out.println("Access denied, only principal can admit an applicant");
            return false;
        }
    }

    public List<Person> getStudents() {
        return students;
    }

    //Set and get the offences that attracts expulsion
    //************************************************
    public void setOffences(List<String> offences) {
        this.offences = offences;
    }

    public List<String> getOffences() {
        return offences;
    }


    //Validate principal to check if a student has committed more
    //than one offence. If yes, remove him from list of students
    //***********************************************************
    public boolean expelStudent(Person student, Person staff, List<String> offences){

        if (students.contains(student)) {
            if (staff.getRole().equals("principal")) {
                String offenceRecord = "";
                System.out.println("Panel set for " + student.getName());
                for (String item : this.offences) {
                    if (offences.contains(item)) {
                        offenceRecord = (offenceRecord + " " + item).trim();
                    }
                }
                String[] offenceCommitted = offenceRecord.split(" ");
                if (offenceCommitted.length > 1) {
                    students.remove(student);
                    System.out.println(student.getName() + " has been expelled for " + offenceRecord.replace(" ", " and "));
                    return true;
                } else if (offenceCommitted.length == 1) {
                    System.out.println(student.getName() + " has committed an offence of " + offenceRecord + ". Invite " + student.getName() + "'s parents");
                    return false;
                } else {
                    return false;
                }
            } else {
                System.out.println("Access denied, only principal can expel students");
                return false;
            }

        }   else {
            System.out.println("Access denied, " + student.getName() + " is not our student");
            return false;
        }
    }


    //Determine the courses a particular student can take or a particular teacher can teach base on his class
    //*********************************************************************
    public List<String> getStudentCourse(Person student){
        if (students.contains(student)) {
            if (student.getKlass().equals("SS1")) {
                return getSS1Courses();
            } else if (student.getKlass().equals("SS2")) {
                return getSS2Courses();
            } else return getSS3Courses();
        }
        else return List.of("");
    }


    //Add each of the students to their classes
    //*****************************************
    public void classes(){
        for (Person student : this.students){
            if (student.getKlass().equals("SS1")){
                SS1.add(student.getName());
            }else if (student.getKlass().equals("SS2")){
                SS2.add(student.getName());
            }else if (student.getKlass().equals("SS3")){
                SS3.add(student.getName());
            }
        }
    }


    //Get the details of all the student present in a particular class
    //****************************************************************
    public List<String> getSS1() {
        return SS1;
    }

    public List<String> getSS2() {
        return SS2;
    }

    public List<String> getSS3() {
        return SS3;
    }


    //Determine the courses a particular teacher can teach base on his class
    //*********************************************************************
    public List<String> getTeacherCourseSet(Person teacher){
        if (teachers.contains(teacher)) {
            if (teacher.getKlass().equals("SS1")) {
                return getSS1Courses();
            } else if (teacher.getKlass().equals("SS2")) {
                return getSS2Courses();
            } else return getSS3Courses();
        }
        else return List.of("");
    }

}

