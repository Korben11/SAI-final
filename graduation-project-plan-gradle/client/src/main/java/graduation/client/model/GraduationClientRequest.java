/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graduation.client.model;

/**
 *
 * @author 884294
 */
public class GraduationClientRequest {
    private int studentNumber;
       
    private String company;
    private String projectTitle;
    
    public GraduationClientRequest(int studentNumber, String company, String projectTitle){
        this.studentNumber = studentNumber;
        this.company = company;
        this.projectTitle = projectTitle;
    }
    
    public GraduationClientRequest(){
        this.studentNumber = 0;
        this.company = "unknown";
        this.projectTitle = "unknown";
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
    
    @Override
     public String toString(){
        return "stnr:"+ studentNumber + " comp:" + company + " proj:" + projectTitle;
    }
    
}
