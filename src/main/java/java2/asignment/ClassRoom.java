package java2.asignment;


import java.time.LocalDate;

public class ClassRoom {

    private String name;

    private String classRoom;

    private int yearSchool;

    public ClassRoom() {

    }

    public ClassRoom(String name, String classRoom, int yearSchool) throws Exception{
        this.name = name;
        this.classRoom = classRoom;
        this.setYearSchool(yearSchool);
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

    public int getYearSchool() {
        return yearSchool;
    }

    public void setYearSchool(int yearSchool) {
        if(yearSchool >= 2000 && yearSchool <= 2023){
            this.yearSchool = yearSchool;
        }
        else{
            LocalDate today = LocalDate.now();
           this.yearSchool = today.getYear();
        }
    }
}
