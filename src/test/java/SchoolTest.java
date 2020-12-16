
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.InvalidAgeException;
import util.InvalidApplicantException;
import util.NotAPrincipalException;
import util.NotATeacherException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {

    static School school;

    //applicants and staff details can be created
    Person applicant1 = new Person("Johnson Clement", "male", "Ikorodu", "SS1", 14);
    Person applicant2 = new Person("Peter Goodness", "female", "Ajah", "SS1", 15);
    Person applicant3 = new Person("Anthony John", "male", "Ikeja", "SS2", 18);
    Person applicant4 = new Person("Joy Peace", "female", "Lekki", "SS1", 12);
    Person teacher1 = new Person("John Ebe", "male", "Police College", "teacher");
    Person teacher2 = new Person("Rebecca Favour", "femail", "Ojota", "teacher");
    Person nonTeacher1 = new Person("Marry Mac", "male", "Akure", "Admin Staff");
    Person schoolPrincipal = new Person("Einstein Joel", "male", "MarryLand", "principal");


    @BeforeAll
    public static void setup() {
        school = new School("Model Secondary School");
        school.setOffences(List.of("Stealing", "Cheating","Fighting"));

    }




    @Test
    @DisplayName("Can get the school name")
    public void getSchoolName() {
        assertEquals("Model Secondary School", school.getSchoolName());
    }


    @Test
    @DisplayName("Can get the school principal name")
    public void getSchoolPrincipal() {

        assertThrows(NotAPrincipalException.class, () -> school.setPrincipal(nonTeacher1));
        school.setPrincipal(schoolPrincipal);
        assertEquals("Einstein Joel", school.getSchoolPrincipal().getName());

    }


    @Test
    @DisplayName("Can only a person to list of teachers if and only if he is a teacher")
    public void getTeacher() {

        assertThrows(NotATeacherException.class, () -> school.addTeacher(nonTeacher1));
        school.addTeacher(teacher1);
        school.addTeacher(teacher2);


        List<String> expected = List.of("John Ebe", "Rebecca Favour");
        school.getTeacher().forEach(teacher -> {
            assertTrue(expected.contains(teacher.getName()));
        });
    }



    @Test
    @DisplayName("Can add and get nonAcademic Staff List")
    public void getNonAcademicStaffs() {

        school.addNonAcademicStaff(nonTeacher1);
        school.addNonAcademicStaff(schoolPrincipal);


        List<String> expected = List.of("Marry Mac", "Einstein Joel");
        school.getNonAcademicStaffs().forEach(nonAcademicStaffs -> {
            assertTrue(expected.contains(nonAcademicStaffs.getName()));
        });
    }


    @Test
    @DisplayName("Can add a person to applicant list")
    public void getApplicants() {

        school.addApplicant(applicant1);
        school.addApplicant(applicant2);

        List<String> expected = List.of("Johnson Clement", "Peter Goodness");
        school.getTeacher().forEach(applicant -> {
            assertTrue(expected.contains(applicant.getName()));
        });
    }


    @Test
    @DisplayName("Only Principal Can admit applicant base on age")
    public void admitApplicant() {

        assertFalse(school.admitApplicant(applicant1, teacher1));
        assertFalse(school.admitApplicant(applicant1, nonTeacher1));
        assertTrue(school.admitApplicant(applicant1, schoolPrincipal));

        //Applicant4 cannot be admitted because she is too young
        assertThrows(InvalidAgeException.class, () -> school.admitApplicant(applicant4, schoolPrincipal));
    }


//    @Test
//    public void getStudents() {
//
//        school.admitApplicant(applicant1, schoolPrincipal);
//        school.admitApplicant(applicant2, schoolPrincipal);
//        assertThrows(InvalidAgeException.class, () -> school.admitApplicant(applicant4, schoolPrincipal));
//
//        List<String> expected = List.of("Johnson Clement", "Peter Goodness");
//        school.getStudents().forEach(student -> {
//            assertTrue(expected.contains(student.getName()));
//        });
//    }


    @Test
    public void getOffences() {
        assertEquals(List.of("Stealing", "Cheating","Fighting"), school.getOffences());
    }


    @Test
    public void expelStudent() {

        school.admitApplicant(applicant1, schoolPrincipal);
        school.admitApplicant(applicant3, schoolPrincipal);
        assertThrows(InvalidAgeException.class, () -> school.admitApplicant(applicant4, schoolPrincipal));

        //Only principal can expel
        assertFalse(school.expelStudent(applicant3, teacher1, List.of("Fighting","Lateness","Cheating")));
        assertTrue(school.expelStudent(applicant3, schoolPrincipal, List.of("Fighting","Lateness","Cheating")));

        //A student cannot be expelled except he has committed two offences present in the offence list
        assertFalse(school.expelStudent(applicant1, schoolPrincipal, List.of("Lateness","Cheating", "Lying")));

        //Applicant3 is no longer a student, he cannot be expelled the second time.
        assertFalse(school.expelStudent(applicant3, schoolPrincipal, List.of("Fighting","Lateness","Cheating")));

        //Applicant4 is worthy of expulsion however, she is not a student in the first place.
        assertFalse(school.expelStudent(applicant4, schoolPrincipal, List.of("Fighting","Lateness","Cheating")));

    }

    @Test
    public void getStudentCourse() {

        school.admitApplicant(applicant3, schoolPrincipal);
        assertThrows(InvalidAgeException.class, () -> school.admitApplicant(applicant4, schoolPrincipal));
        school.setSS1Courses(List.of("Physics", "French", "Maths"));
        school.setSS2Courses(List.of("Chemistry", "English", "Maths"));
        school.setSS3Courses(List.of("Biology", "Yoruba", "Maths"));

        //Applicant3 is in SS2, he can take only SS2 courses
        assertEquals(List.of("Chemistry", "English", "Maths"), school.getStudentCourse(applicant3));
        assertNotEquals(List.of("Biology", "Yoruba", "Maths"), school.getStudentCourse(applicant3));

        //Applicant4 was not admitted, she cannot take any course
        assertEquals(List.of(""), school.getStudentCourse(applicant4));
    }

    @Test
    public void classes() {
    }

    @Test
    public void getSS1() {

        school.admitApplicant(applicant1, schoolPrincipal);
        school.admitApplicant(applicant2, schoolPrincipal);
        school.admitApplicant(applicant3, schoolPrincipal);
        assertThrows(InvalidAgeException.class, () -> school.admitApplicant(applicant4, schoolPrincipal));

        //SS1 class is empty because students have not been allocated to classes.
        assertEquals (List.of(), school.getSS1());

        school.classes(); // allocate each students to their respective classes
        assertEquals (List.of("Johnson Clement", "Peter Goodness" ), school.getSS1());
    }

    @Test
    public void getSS2() {

        school.admitApplicant(applicant1, schoolPrincipal);
        school.admitApplicant(applicant2, schoolPrincipal);
        school.admitApplicant(applicant3, schoolPrincipal);
        assertThrows(InvalidAgeException.class, () -> school.admitApplicant(applicant4, schoolPrincipal));

        school.classes();
        assertTrue(school.getSS2().contains("Anthony John"));
    }

    @Test()
    public void getSS3() {

        school.admitApplicant(applicant1, schoolPrincipal);
        school.admitApplicant(applicant2, schoolPrincipal);
        school.admitApplicant(applicant3, schoolPrincipal);
        assertThrows(InvalidAgeException.class, () -> school.admitApplicant(applicant4, schoolPrincipal));

        school.classes();
        assertEquals (List.of(), school.getSS3());

    }
}