package com.yan.controller;

import com.yan.constant.loginRequire;
import com.yan.domain.User;
import com.yan.service.impl.UserServiceImpl;
import com.yan.util.Status;
import com.yan.util.StatusMsg;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Package ：com.yan.controller
 * Description：
 * date： 2019/1/15 下午9:59
 * author： Li KaiYan
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 检查邮件是否已经注册
     * @param email
     * @return
     */
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    public Status check_email(@Param("email") String email){
        Status msg = new Status();
        if(userService.check_email(email)){
            msg.setStatus(StatusMsg.EMAIL_USEABLE);
        }else{
            msg.setStatus(StatusMsg.EMAIL_EXIST);
        }
        return msg;
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    /**
     * 注册
     * @param email
     * @param password
     * @param username
     * @return
     */
    @RequestMapping(value = "/doReg", method = RequestMethod.POST)
    public Status doReg(@Param("email") String email, @Param("password") String password, @Param("username") String username){
        Status status = new Status();
        User user = new User(email, username, password);
        if(userService.register(user)){
            status.setStatus(StatusMsg.SEND_MAIL);
        }else{
            status.setStatus(StatusMsg.REG_ERROR);
        }
        return status;
    }

    /**
     * 激活账号
     * @param uid
     * @param code
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public Status doActive(String uid, String code){
        Status status = new Status();
        User user = new User();
        user.setUid(uid);
        user.setActive_code(code);
        if(userService.active(user)){
            status.setStatus(StatusMsg.ACTIVE_SUCCESS);
        }else{
            status.setStatus(StatusMsg.ACTIVE_FAILED);
        }
        return status;
    }

    /**
     * 跳转到登录页面,如果已经登录则跳转至商店
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user != null){
            ModelAndView mav = new ModelAndView("mall");
            mav.addObject("user", user);
            return new ModelAndView("mall");
        }else{
            return new ModelAndView("login");
        }
    }

    /**
     * 登录
     * @param email
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public Status doLogin(@Param("email") String email, @Param("password") String password, HttpSession session){
        Status status = new Status();
        User login = new User();
        login.setEmail(email);
        login.setPassword(password);
        User user = userService.login(login);
        if(user != null){
            if("0".equals(user.getStatus())){
                status.setStatus(StatusMsg.USER_NOT_ACTIVE);
            }else if("2".equals(user.getStatus())){
                status.setStatus(StatusMsg.USER_LOCKED);
            }else{
                session.setAttribute("user", user);
                status.setStatus(StatusMsg.LOGIN_SUCCESS);
            }
        }else{
            status.setStatus(StatusMsg.LOGIN_FAILED);
        }
        return status;
    }

    /**
     * 跳转至忘记密码页
     * @return
     */
    @RequestMapping(value = "/setPassword/forget", method = RequestMethod.GET)
    public ModelAndView forget_password(){
        return new ModelAndView("forgetPassword");
    }

    /**
     * 忘记密码（发送验证邮件）
     * @param email
     * @return
     */
    @RequestMapping(value = "/setPassword/setEmail", method = RequestMethod.GET)
    public Status setEmail(@Param("email") String email){
        Status status = new Status();
        if(!userService.check_email(email)){
            User user = new User();
            user.setEmail(email);
            userService.forget_password(user);
            status.setStatus(StatusMsg.RESET_PASSWORD);
        }else{
            status.setStatus(StatusMsg.EMAIL_NOT_REG + "/" + StatusMsg.USER_NOT_ACTIVE);
        }
        return status;
    }

    /**
     * 修改密码（发送验证邮件）
     * @param session
     * @return
     */
    @loginRequire
    @RequestMapping(value = "/setPassword/change", method = RequestMethod.GET)
    public Status change_password(HttpSession session){
        Status status = new Status();
        User user = (User)session.getAttribute("user");
        userService.change_password(user);
        status.setStatus(StatusMsg.RESET_PASSWORD);
        return status;
    }

    /**
     * 修改密码（检验验证码）
     * @param uid
     * @param code
     * @param session
     * @return
     */
    @RequestMapping(value = "/setPassword/verify", method = RequestMethod.GET)
    public ModelAndView verify(@RequestParam("uid") String uid, @RequestParam("code") String code, HttpSession session){
        Status status = new Status();
        User user = new User();
        user.setUid(uid);
        user.setActive_code(code);
        if(userService.verify_code(user)) {
            session.setAttribute("changePassword", uid);
            return new ModelAndView("setPassword");
        }
        return new ModelAndView("error");
    }

    /**
     * 修改密码（跳转设置密码页）
     * @param session
     * @return
     */
    @RequestMapping(value = "/setPassword/set", method = RequestMethod.GET)
    public ModelAndView setPasswordPage(HttpSession session){
        if(session.getAttribute("changePassword") != null){
            return new ModelAndView("setPassword");
        }else{
            return new ModelAndView("error");
        }
    }

    /**
     * 修改密码（更新密码）
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/setPassword/update", method = RequestMethod.POST)
    public Status updatePassword(@RequestParam("password") String password, HttpSession session){
        Status status = new Status();
        String uid = (String)session.getAttribute("changePassword");
        if(uid != null){
            User user = new User();
            user.setUid(uid);
            user.setPassword(password);
            if(userService.update(user) != null){
                session.removeAttribute("changePassword");
                status.setStatus(StatusMsg.CHANGE_SUCCESS);
            }else{
                status.setStatus(StatusMsg.CHANGE_FAILED);
            }
        }else{
            status.setStatus(StatusMsg.UNKNOW_ERROR);
        }
        return status;
    }

    @loginRequire
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public ModelAndView userInfo(){
        return new ModelAndView("personInfo");
    }

    /**
     * 获取用户详细信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @loginRequire
    public User getUserInfo(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user;
    }


    /**
     * 更新用户信息
     * @param username
     * @param session
     * @return
     */
    @RequestMapping(value = "/userinfo/update", method = RequestMethod.POST)
    @loginRequire
    public Status updateUserInfo(@RequestParam("username") String username, HttpSession session){
        Status status = new Status();
        User old_user = (User)session.getAttribute("user");
        old_user.setUsername(username);
        User user = userService.update(old_user);
        if(user != null){
            session.setAttribute("user", user);
            status.setStatus(StatusMsg.CHANGE_SUCCESS);
        }
        return status;
    }




}
