package java2.asignment;


import java.time.LocalDate;

public class ClassRoom {

    private String name;

    private String classRoom;

    private int schoolYear;

    public ClassRoom() {

    }

    public ClassRoom(String name, String classRoom, int yearSchool) throws Exception{
        this.name = name;
        this.classRoom = classRoom;
        this.setSchoolYear(yearSchool);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        if(schoolYear >= 2000 && schoolYear <= 2023){
            this.schoolYear = schoolYear;
        }
        else{
            LocalDate today = LocalDate.now();
           this.schoolYear = today.getYear();
        }
    }
}
