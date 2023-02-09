package entity;

public class UserBean
{
    private String userID;
    private String email;
    private String name;
    private String password;
    private String activeCode; //账号激活码
    private String type;       //标注是管理员admin还是普通用户user
    private boolean activated; //账号是否激活

    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getActiveCode()
    {
        return activeCode;
    }
    public void setActiveCode(String activeCode)
    {
        this.activeCode = activeCode;
    }

    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public boolean isActivated()
    {
        return activated;
    }
    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }
}
