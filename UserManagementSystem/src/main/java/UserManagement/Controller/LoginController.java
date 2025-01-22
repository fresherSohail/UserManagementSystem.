package UserManagement.Controller;

import UserManagement.entity.Login;
import UserManagement.entity.User;
import UserManagement.service.UserManageService;
import UserManagement.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserManageService userManageService;

    @RequestMapping("/login")
    public String showForm() {
        return "login";
    }

    @PostMapping("/processform")
    public String processLogin(@ModelAttribute("processform") Login login, Model model, HttpSession session) {
        if (loginService.loginProcess(login)) {
            User loggedInUser = loginService.findByUsername(login.getUsername());
            session.setAttribute("user", loggedInUser);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("allUser", userManageService.getAllUser());
            return "HomePage";
        } else {
            return "login";
        }
    }

    @GetMapping("/logoutController")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

}
