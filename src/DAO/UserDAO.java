package DAO;

import entity.UserBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DruidUtils;

import java.sql.SQLException;

public class UserDAO
{
    public void insertUser(UserBean userBean) throws SQLException  //用于用户注册，新增用户
    {
        String cmd = "insert into users(`userID`, `email`, `name`, `password`, `activeCode`, `type`, `activated`) " +
                     "values(?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        runner.update(cmd, userBean.getUserID(), userBean.getEmail(), userBean.getName(),
                userBean.getPassword(), userBean.getActiveCode(), userBean.getType(), userBean.isActivated());
    }
    public void activeEmail(String activeCode) throws SQLException //用于激活用户刚注册的账号
    {
        String cmd = "update users set `activated`=true where `activeCode`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        runner.update(cmd, activeCode);
    }
    public UserBean selectUserByName(String name) throws SQLException //根据用户名查找并返回用户
    {
        String cmd = "select * from users where `name`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return runner.query(cmd, new BeanHandler<UserBean>(UserBean.class), name);
    }
    public UserBean selectUserByEmail(String email) throws SQLException //根据email查找并返回用户
    {
        String cmd = "select * from users where `email`=?";
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        return runner.query(cmd, new BeanHandler<UserBean>(UserBean.class), email);
    }
}
