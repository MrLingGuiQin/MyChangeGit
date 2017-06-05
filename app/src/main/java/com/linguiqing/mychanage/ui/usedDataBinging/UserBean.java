package com.linguiqing.mychanage.ui.usedDataBinging;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/6/4 0004
 * ***************************************
 */

public class UserBean {
    private String userName;
    private String job;
    private String age;

    public UserBean(String userName, String job, String age) {
        this.userName = userName;
        this.job = job;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
