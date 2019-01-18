package com.fuhui.cas;

import com.fuhui.dao.cluster.UserInfoMapper;
import com.fuhui.entity.cluster.UserInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wwu on 2017/4/13.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoMapper userInfoMapper;


    @RequestMapping(value = "/html", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = userService.getUsername();
            System.out.println(name);
            UserInfo user = userInfoMapper.selectByParam(name);
            //创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            System.out.println("session==" + user);
            session.setAttribute("loginName", name);
            session.setAttribute("userId", user.getUserid());
            if (user != null) {
                String userCode = user.getUsertypecode();
                if (userCode.equals("01")) {
                    model.addAttribute("userName", user.getUsername());
                    return "appConfigList";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("errorInfo", "你没有权限!");
        return "error";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = userService.getUsername();
            UserInfo user = userInfoMapper.selectByParam(name);
            //创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            System.out.println("session==" + user);
            session.setAttribute("loginName", name);
            session.setAttribute("userId", user.getUserid());
            if (user != null) {
                String userCode = user.getUsertypecode();
                if (userCode.equals("01")) {
                    model.addAttribute("userName", user.getUsername());
                    return "index";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("errorInfo", "你没有权限!");
        return "error";
    }

}