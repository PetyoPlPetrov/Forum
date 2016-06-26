package app;

import app.autentication.CurrentUser;
import app.dto.AnswerAddForm;
import app.dto.TopicAddForm;
import app.entity.Category;
import app.entity.Topic;
import app.service.AnswerService;
import app.service.CategoryService;
import app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PetyoPetrov on 27.05.2016 г..
 */
@Controller
public class TopicControler {
    @Autowired
    private TopicService topicService;
    @Autowired
    private AnswerService answerService;



    @Autowired
    private CategoryService categoryService;









    //topic
    @RequestMapping(value = "/categories/{categoryId}/topics/add", method = RequestMethod.GET)
    public String addTopicForm(@PathVariable Long categoryId, Model model, HttpServletRequest request) {
//        Object userIdAttrib = request.getSession().getAttribute("userId");
//        if (userIdAttrib == null) {
//            return "redirect:/";
//        }

        List<Category> categories = categoryService.getAll();
        Category current = categoryService.getById(categoryId);
        model.addAttribute("topicDto", new TopicAddForm());
        model.addAttribute("allCategories", categories);
        model.addAttribute("currentCategory", current);
        return "topics/add";
    }

    @RequestMapping(value = "/categories/{categoryId}/topics/add", method = RequestMethod.POST)
    public String createTopicProces(@ModelAttribute TopicAddForm topicDto, HttpServletRequest request, @PathVariable Long categoryId) throws Exception {
//        Object userIdAttrib = request.getSession().getAttribute("userId");
//        if (userIdAttrib == null) {
//            return "redirect:/";
//        }
//
//        int userId=  (int)userIdAttrib;
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        topicDto.setAuthorId(currentUser.getId());
        this.topicService.create(topicDto);

        return "redirect:/categories/" + categoryId;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String allCategories(Model m) throws Exception {
//        User user = userService.getUserBYIdANdPassword(userDto.getUsername(), Integer.parseInt(userDto.getPassword()));
//        if (user == null) {
//            throw new Exception("invalid username or pass");
//        }

        //request.getSession().setAttribute("userId", user.getId());
        List<Category> categories = categoryService.getAll();
        m.addAttribute("categories", categories);
        return "index";
    }



}
