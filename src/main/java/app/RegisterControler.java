package app;

import app.dto.UserAddForm;
import app.entity.Category;
import app.entity.User;
import app.service.CategoryService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PetyoPetrov on 01.06.2016 г..
 */
@Controller
public class RegisterControler {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String RegisterForm(Model model) {

        model.addAttribute("userDto", new UserAddForm());
        return "user/register1";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String RegisterFormProcesing(@ModelAttribute UserAddForm userAddForm) {

        this.userService.create(userAddForm);
        return "redirect:/login";
    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String registerProces(Model m) throws Exception {
//        m.addAttribute("userDto",new UserAddForm());
//
//        return "/register";
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String registerProcesForm(Model m, @ModelAttribute UserAddForm userDto, HttpServletRequest request) throws Exception {
//
//        userService.create(userDto);
//        User user = userService.getUserBYIdANdPassword(userDto.getUsername(),(userDto.getPassword()));
//        if (user == null) {
//            throw new Exception("invalid username or pass");
//        }
//
//        request.getSession().setAttribute("userId", user.getId());
//        List<Category> categories = categoryService.getAll();
//        m.addAttribute("categories", categories);
//        return "index";
//    }
}
