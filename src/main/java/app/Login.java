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
 * Created by PetyoPetrov on 30.05.2016 г..
 */
@Controller
public class Login {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String LoginForm(Model model) {

        model.addAttribute("userDto", new UserAddForm());
        return "/login";
    }





}
