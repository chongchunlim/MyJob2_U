package com.myjob2u.myjob2u;

public class PostNewJob {

    private String title,desc,salary,qualification,poster;

    public PostNewJob()
    {

    }

    public PostNewJob(String title,String desc,String salary,String qualification, String poster)
    {
        this.title=title;
        this.desc=desc;
        this.qualification=qualification;
        this.salary=salary;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
