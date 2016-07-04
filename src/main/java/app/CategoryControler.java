package app;

import app.autentication.CurrentUser;
import app.dto.AnswerAddForm;
import app.dto.CategoryAddForm;
import app.dto.TopicAddForm;
import app.dto.UserAddForm;
import app.entity.Authority;
import app.entity.Category;
import app.entity.Role;
import app.entity.Topic;
import app.service.AnswerService;
import app.service.CategoryService;
import app.service.TopicService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by PetyoPetrov on 25.05.2016 г..
 */
@Controller
@PreAuthorize("isAuthenticated()")
public class CategoryControler {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TopicService topicservice;

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String getInfo(@PathVariable Long id, Model model) throws Exception {

        Category category = this.categoryService.getById(id);

        if (category == null) {
            throw new Exception("category does not exist");
        }
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();

        boolean hasPermission = category.getAuthorities().stream()
                .anyMatch(categoryy ->
                        Arrays.stream(currentUser.getRole())
                                .anyMatch(role -> categoryy.getName().equalsIgnoreCase(role.toString())));
        if (!hasPermission) {
            throw new Exception("You are not an admin");
        }
        boolean isAdmin = Stream.of(currentUser.getRole()).anyMatch(userRole -> userRole.name().
                equalsIgnoreCase("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("category", category);
        return "categoryone";
    }


}
