package VO;

public class stu {
    private String StuId;
    private String StuName;
    private String StuSex;
    private int twtime;
    private int kqtime;
    private int qqtime;
    private double allgrade;
    // 抽取操作定义
    private double pi;// 抽取概率因子
    private int min;// 最小范围值

    public String getStuId() {
        return StuId;
    }

    public String getStuName() {
        return StuName;
    }

    public String getStuSex() {
        return StuSex;
    }

    public int gettwtime() {
        return twtime;
    }

    public int getkqtime() {
        return kqtime;
    }

    public int getqqtime() {
        return qqtime;
    }

    public double getallgrade() {
        return allgrade;
    }

    public void setStuId(String StuId) {
        this.StuId = StuId;
    }

    public void setStuName(String StuName) {
        this.StuName = StuName;
    }

    public void setStuSex(String StuSex) {
        this.StuSex = StuSex;
    }

    public void settwtime(int twtime) {
        this.twtime = twtime;
    }

    public void setkqtime(int kqtime) {
        this.kqtime = kqtime;
    }

    public void setqqtime(int qqtime) {
        this.qqtime = qqtime;
    }

    public void setallgrade(double allgrade) {
        this.allgrade = allgrade;
    }

    // 抽取操作方法
    public void setPi(double pi,double mark) {
        this.pi = pi*mark;
    }
    public void setPi(double pi) {
        this.pi=pi*100;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return (int)pi + min;
    }

}
