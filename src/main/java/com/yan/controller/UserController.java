package com.yan.controller;

import com.yan.domain.User;
import com.yan.service.UserService;
import com.yan.util.Status;
import com.yan.util.StatusMsg;
import com.yan.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Package ：com.yan.controller
 * Description：
 * date： 2019/1/15 下午9:59
 * author： Li KaiYan
 */
@RestController(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check_email", method = RequestMethod.GET)
    public Status check_email(String email){
        Status msg = new Status();
        if(userService.check_email(email)){
            msg.setStatus(StatusMsg.EMAIL_USEABLE);
        }else{
            msg.setStatus(StatusMsg.EMAIL_EXIST);
        }
        return msg;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/doReg", method = RequestMethod.POST)
    public Status doReg(String email, String password, String username){
        Status status = new Status();
        User user = new User(email, username, password);
        if(Utils.isEmail(email) && userService.register(user)){
            status.setStatus(StatusMsg.SEND_MAIL);
        }else{
            status.setStatus(StatusMsg.REG_ERROR);
        }
        return status;
    }

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpSession session){
        if(session.getAttribute("user") != null){
            return new ModelAndView("mall");
        }else{
            return new ModelAndView("login");
        }
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public Status doLogin(String email, String password, HttpSession session){
        Status status = new Status();
        User login = new User();
        login.setEmail(email);
        login.setPassword(password);
        User user = userService.login(login);
        if(user != null){
            session.setAttribute("user", user);
            status.setStatus(StatusMsg.LOGIN_SUCCESS);
        }else{
            status.setStatus(StatusMsg.LOGIN_FAILED);
        }
        return status;
    }


}
