package com.myjob2u.myjob2u;

public class BookmarkTakeJob {

String username,jobTitle;

    public BookmarkTakeJob() {
    }

    public BookmarkTakeJob(String username, String jobTitle) {
        this.username = username;
        this.jobTitle = jobTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
