import java.util.ArrayList;
import java.util.List;

public abstract class Classroom {

    private List<String> SS1Courses = new ArrayList<>();
    private List<String> SS2Courses = new ArrayList<>();
    private List<String> SS3Courses = new ArrayList<>();


    //Get the courses done in each classes
    //************************************
    public List<String> getSS1Courses() {
        return SS1Courses;
    }

    public List<String> getSS2Courses() {
        return SS2Courses;
    }

    public List<String> getSS3Courses() {
        return SS3Courses;
    }


    //Set the courses done in each classes
    //************************************
    public void setSS1Courses(List<String> SS1Courses) {
        this.SS1Courses = SS1Courses;
    }

    public void setSS2Courses(List<String> SS2Courses) {
        this.SS2Courses = SS2Courses;
    }

    public void setSS3Courses(List<String> SS3Courses) {
        this.SS3Courses = SS3Courses;
    }

}

