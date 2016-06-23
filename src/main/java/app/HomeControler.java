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
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
@Controller
public class HomeControler {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;


    //    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String index(Model m, HttpServletRequest request) throws Exception {
//        Object userIdAttrib = request.getSession().getAttribute("userId");
//        if (userIdAttrib == null) {
//            return "redirect:/login";
//        }
//        List<Category> categories = categoryService.getAll();
//        m.addAttribute("categories", categories);
//
//        return "index";
//    }
//
//
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String enterForm() {


        return "enter";

    }

}
