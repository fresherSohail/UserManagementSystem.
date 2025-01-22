package UserManagement.Controller;

import UserManagement.entity.User;
import UserManagement.service.EmailSender;
import UserManagement.service.RegisterService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private EmailSender emailSender;

    @GetMapping("/registration")
    public String show() {
        return "registration";
    }

    @PostMapping("/processRegistrate")
    public String registerUser(@ModelAttribute("registrate") User user,
                                     @RequestParam("email") String email,
                                     @RequestParam("firstname") String name, Model model) throws MessagingException, IOException {
        if (registerService.createUser(user)) {
            emailSender.sendHtmlEmail(email, name);
            model.addAttribute("message", "User Registered Successfully");
            return "login";
        }else{
            return "redirect:/registerError";
        }
    }

    @GetMapping("/registerError")
    public String registerErrorPage(Model model) {
        model.addAttribute("message", "\"It looks like you may have already signed up.");
        return "status";
    }


//    @GetMapping("/dashboard")
//    public ModelAndView getDashboard(HttpSession session, HttpServletRequest request, Model model) {
//        ModelAndView mv = new ModelAndView();
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            mv.setViewName("Dashboard");
//            mv.addObject("username", user.getUsername());
//        } else {
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                String username = null;
//                for (Cookie cookie : cookies) {
//                    if ("JSESSIONID".equals(cookie.getName())) {
//                         username = cookie.getValue();
//                         model.addAttribute("username", username);
//                    }
//                }
//            }
//            mv.setViewName("LoginPage");
//        }
//        return mv;
//    }


}

