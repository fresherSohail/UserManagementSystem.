package UserManagement.Controller;

import UserManagement.entity.*;
import UserManagement.service.UserManageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserManageController {

    @Autowired
    private UserManageService userManageService;

    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session){
        User user1 = (User)session.getAttribute("user");
        model.addAttribute("loggedInUser", user1);
        return "HomePage";
    }

    @GetMapping("/aboutController")
    public String aboutController(Model model, HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        model.addAttribute("loggedInUser", user1);
        return "About";
    }

    @GetMapping("/profileController")
    public String profileController(Model model, HttpSession session){
        User user1 = (User)session.getAttribute("user");
        model.addAttribute("loggedInUser", user1);
        return "Profile";
    }

    @GetMapping("/getAllUserData")
    public String getAllUserData(Model model, HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        model.addAttribute("loggedInUser", user1);
        model.addAttribute("allUser", userManageService.getAllUser());
        return "getAllUser";
    }


    @GetMapping("/updateProcess/{id}")
    public String getUpdateUser(@PathVariable Long id, Model model) {
        User userToUpdate = userManageService.findById(id);
        model.addAttribute("user", userToUpdate);
        return "updateUser";
    }

    @PostMapping("/UpdatedUI/{id}")
    public ModelAndView updatedU(@ModelAttribute("UpdatedUI") User user, @PathVariable("id") Long id, HttpSession session, Model model) {
        if(user.getId() == null) {
            user.setId(id);
        }
        userManageService.updateUser(user, id);
        ModelAndView modelAV = new ModelAndView();
        modelAV.addObject("message", "Updated User: " + user.getId());
        User user1 = (User) session.getAttribute("user");
        modelAV.addObject("loggedInUser", user1);
        model.addAttribute("allUser", userManageService.getAllUser());
        modelAV.setViewName("getAllUser");
        return modelAV;
    }

    @PostMapping("/deleteProcess/{id}")
    public ModelAndView deleteUser(@PathVariable Long id, HttpSession session , Model model, User user){
        userManageService.deleteUser(id);
        ModelAndView modelAV = new ModelAndView();
        User user1 = (User)session.getAttribute("user");
        modelAV.addObject("loggedInUser", user1);
        modelAV.addObject("allUser", userManageService.getAllUser());
        modelAV.addObject("message", "Deleted SuccessFully: " + id);
        modelAV.setViewName("getAllUser");
        return modelAV;
    }

}
