package com.mage.crm.service;

import com.mage.crm.dao.UserDao;
import com.mage.crm.model.UserModel;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.util.Base64Util;
import com.mage.crm.util.Md5Util;
import com.mage.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public UserModel userLogin(String userName, String userPwd){
        User user = userDao.queryUserByName(userName);
        AssertUtil.isTrue(null==user,"用户不存在");
        //加密过后的密码
        userPwd=Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(user.getUserPwd()),"用户名或密码错误");
        AssertUtil.isTrue("0".equals(user.getIsValid()),"用户已经被注销");
        return createUserModel(user);
    }
    public UserModel createUserModel(User user){
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUserName());
        userModel.setId(Base64Util.encode(user.getId()));
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    public void updatePwd(String id, String oldPassword, String newPassword, String confirmPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(id),"id不存在");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码与老密码不能相等");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"新密码与确认密码不一致");
        User user = userDao.queryUserById(Base64Util.decode(id));
        AssertUtil.isTrue(null==user,"用户不存在了");
        AssertUtil.isTrue("0".equals(user.getIsValid()),"用户已经被注销了");
        AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user.getUserPwd()),"原始密码错误");
        AssertUtil.isTrue(userDao.updatePwd(user.getId(),Md5Util.encode(newPassword))<1,"用户密码更新失败");
    }
    public  User queryUserById(String id){
        return  userDao.queryUserById(id);
    }

    public List<User> queryAllCustomerManager() {
        return userDao.queryAllCustomerManager();
    }
}
