package VO;

public class Users {
    private String id;
    private String password;
    private String name;
    private String tel;
    private String mail;

    public String getId()
    {
        return id;
    }
    public String getpassword()
    {
        return password;
    }
    public String getname()
    {
        return name;
    }
    public String gettel()
    {
        return tel;
    }
    public String getmail()
    {
        return mail;
    }

    public void setId(String id)
    {
        this.id=id;
    }
    public void setpassword(String password)
    {
        this.password=password;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    public void settel(String tel)
    {
        this.tel=tel;
    }
    public void setmail(String mail)
    {
        this.mail=mail;
    }
}
