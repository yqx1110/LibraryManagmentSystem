package yqx1110.LibraryManagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import yqx1110.LibraryManagement.entity.User;
import yqx1110.LibraryManagement.service.AdminService;
import yqx1110.LibraryManagement.service.ReaderService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class MainController {
    public static final String KEY_USER = "__user__";
    @Autowired
    AdminService adminService;

    @Autowired
    ReaderService readerService;

    @GetMapping(value = {"/"})
    public ModelAndView readerIndex(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user != null) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("user", user);
            return new ModelAndView("reader_index.html", model);
        }
        return new ModelAndView("redirect:/signin");
    }

    @GetMapping("/signin")
    public ModelAndView signin(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user != null) {
            return new ModelAndView("redirect:/profile");
        }
        return new ModelAndView("signin.html");
    }

    @PostMapping("/signin")
    public ModelAndView doLogin(
            @RequestParam("username") int uuid,
            @RequestParam("passwd") String passwd,
            @RequestParam("position") String position,
            HttpSession session
    ) {
        //position="admin", 管理员登录; 否则position="reader", 读者登录
        try {
            if (position.equals("admin")) {
                if (adminService.login(uuid, passwd) == 1) {
                    User user = adminService.getAdminById(uuid);
                    session.setAttribute(KEY_USER, user);
                    return new ModelAndView("redirect:/admin");
                }
            } else {
                if (readerService.login(uuid, passwd) == 1) {
                    User user = readerService.getReaderById(uuid);
                    session.setAttribute(KEY_USER, user);
                    return new ModelAndView("redirect:/");
                }
            }
        } catch (DataAccessException ignored) {
        }
        HashMap<String, Object> model = new HashMap<>();
        model.put("username", uuid);
        model.put("error", "用户名或密码错误");
        return new ModelAndView("signin.html", model);
    }

    @GetMapping("/signout")
    public String signout(HttpSession session) {
        session.removeAttribute(KEY_USER);
        return "redirect:/signin";
    }

    @RequestMapping("*")
    public String notFind() {
        return "404.jsp";
    }
}
