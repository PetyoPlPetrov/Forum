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

        List<Category> categories = categoryService.getAll();
        Category current = categoryService.getById(categoryId);
        model.addAttribute("topicDto", new TopicAddForm());
        model.addAttribute("allCategories", categories);
        model.addAttribute("currentCategory", current);
        return "topics/add";
    }

    @RequestMapping(value = "/categories/{categoryId}/topics/add", method = RequestMethod.POST)
    public String createTopicProces(@ModelAttribute TopicAddForm topicDto,
                                    @PathVariable Long categoryId) throws Exception {
//
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        topicDto.setAuthorId(currentUser.getId());
        this.topicService.create(topicDto);

        return "redirect:/categories/" + categoryId;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String allCategories(Model m) throws Exception {

        List<Category> categories = categoryService.getAll();
        m.addAttribute("categories", categories);
        return "index";
    }
    @RequestMapping(value = "/topics/{topicId}/confirm",method = RequestMethod.GET)
    public String confirmDeleting(@PathVariable Long topicId,Model model){
        Topic topic=this.topicService.getById(topicId);
        Category category=this.categoryService.getById(topic.getCategory().getId());
        model.addAttribute("topic",topic);
        model.addAttribute("category",category);

        return "topics/confirmDelete";

    }

    @RequestMapping(value = "/topics/{topicId}/delete",method = RequestMethod.GET)
    public String deleteTopic(@PathVariable Long topicId ){
        Topic topic=topicService.getById(topicId);
        Category currentCategory=topic.getCategory();



        this.topicService.delete(topic);
        return "redirect:/categories/" + currentCategory.getId();
    }
    @RequestMapping(value = "/topics/{topicId}/edit",method = RequestMethod.GET)
    public String editTopic(@PathVariable Long topicId,Model model ) throws Exception {

        Topic topic=topicService.getById(topicId);

        if(topic==null){
            throw new Exception("there is no such a topic");
        }
        List<Category> categories = categoryService.getAll();
        Category current = categoryService.getById(topic.getCategory().getId());
        model.addAttribute("topicDto", new TopicAddForm());
        model.addAttribute("allCategories", categories);
        model.addAttribute("currentCategory", current);



        return "topics/update";
    }
    @RequestMapping(value = "/topics/{topicId}/edit",method = RequestMethod.POST)
    public String editTopicProcessing(@ModelAttribute TopicAddForm topicDto,@PathVariable Long topicId){

        Topic topic=this.topicService.getById(topicId);
        topic.setBody(topicDto.getBody());
        topic.setTitle(topicDto.getTitle());
        Category newcategory=this.categoryService.getById(topicDto.getCategoryId());
        topic.setCategory(newcategory);
       this.topicService.update(topic);
        return "redirect:/categories/" + topic.getCategory().getId();
    }





}
