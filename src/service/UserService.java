package service;

import DAO.UserDAO;
import entity.UserBean;
import utils.UidUtils;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

public class UserService
{
    UserDAO userDAO = new UserDAO();

    public boolean register(UserBean userBean)  //账号注册
    {
        try {
            userBean.setUserID(UidUtils.getUid());
            userBean.setType("user");
            userBean.setActivated(false);
            userDAO.insertUser(userBean);
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public UserBean login(UserBean requestUserBean) throws LoginException  //账号登录
    {
        UserBean selectedUserBean = null;
        try
        {
            if (requestUserBean.getName() != null)
                selectedUserBean = userDAO.selectUserByName(requestUserBean.getName());
            else if (requestUserBean.getEmail() != null)
                selectedUserBean = userDAO.selectUserByEmail(requestUserBean.getEmail());
            if (selectedUserBean == null)
                throw new LoginException("帐号不存在!");
            else if (! selectedUserBean.getPassword().equals(requestUserBean.getPassword()))
                throw new LoginException("帐号或密码错误!");
            else if (!selectedUserBean.isActivated())
                throw new LoginException("账号邮箱未激活!");
            return selectedUserBean;
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new LoginException("登录失败，请重试!");
        }
    }
    public boolean validateEmail(String email)  //验证邮箱是否已被注册
    {
        if (email == null) return false;
        UserBean userBean;
        try
        {
            userBean = userDAO.selectUserByEmail(email);
            return userBean == null;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean validateName(String name)  //验证用户名是否已被注册
    {
        if (name == null) return false;
        UserBean userBean;
        try
        {
            userBean = userDAO.selectUserByName(name);
            return userBean == null;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean activeEmail(String activeCode)  //激活账号
    {
        try{
            userDAO.activeEmail(activeCode);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
