package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.base.CrmConstant;
import com.mage.crm.base.exceptions.ParamsException;
import com.mage.crm.model.MessageModel;
import com.mage.crm.model.UserModel;
import com.mage.crm.service.UserService;
import com.mage.crm.util.CookieUtil;
import com.mage.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;

    @RequestMapping("userLogin")
    @ResponseBody
    public MessageModel userLogin(String userName, String userPwd){
        MessageModel messageModel = new MessageModel();
        messageModel.setMsg(CrmConstant.OPS_SUCCESS_MSG);
        messageModel.setCode(CrmConstant.OPS_SUCCESS_CODE);
        try{
        UserModel userModel = userService.userLogin(userName, userPwd);
        messageModel.setResult(userModel);
            messageModel.setResult(userModel);
        }catch (ParamsException pe){
            pe.printStackTrace();
            messageModel.setCode(CrmConstant.LOGIN_FAILED_CODE);//登陆失败状态码305
            messageModel.setMsg(pe.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            messageModel.setCode(CrmConstant.LOGIN_FAILED_CODE);
            messageModel.setMsg(CrmConstant.LOGIN_FAILED_MSG);
        }
        return  messageModel;
    }
    @ResponseBody
    @RequestMapping("updatePwd")
    public MessageModel updatePwd(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        MessageModel messageModel = new MessageModel();
        String id = CookieUtil.getCookieValue(request, "id");
        try{
            userService.updatePwd(id,oldPassword,newPassword,confirmPassword);
            messageModel.setMsg("用户密码修改成功");
        }catch (ParamsException px){
            px.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
            messageModel.setMsg(px.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
            messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
        }
        return messageModel;
    }
    @ResponseBody
    @RequestMapping("queryAllCustomerManager")
    public List<User> queryAllCustomerManager(){
        return userService.queryAllCustomerManager();
    }
}
