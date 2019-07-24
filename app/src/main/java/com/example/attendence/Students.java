package com.example.attendence;

public class Students {
    private String studenId;
    private String studentName;
    private String mcneeseId;

    public  Students(){

    }

    public Students(String studenId, String studentName, String mcneeseId) {
        this.studenId = studenId;
        this.studentName = studentName;
        this.mcneeseId = mcneeseId;
    }

    public String getStudenId() {
        return studenId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getMcneeseId() {
        return mcneeseId;
    }
}
