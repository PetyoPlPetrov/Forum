package app;

import app.autentication.CurrentUser;
import app.dto.AnswerAddForm;
import app.entity.Topic;
import app.service.AnswerService;
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

/**
 * Created by PetyoPetrov on 27.05.2016 г..
 */
@Controller
public class TopicControler {
    @Autowired
    private TopicService topicService;
    @Autowired
    private AnswerService answerService;


    @RequestMapping(value = "/topics/{topicId}", method = RequestMethod.GET)
    public String addTopicAnswer(@PathVariable Long topicId, Model model, HttpServletRequest request) throws Exception {
//        Object userIdAttrib = request.getSession().getAttribute("userId");
//        if (userIdAttrib == null) {
//            return "redirect:/";
//        }
        Topic topic = topicService.getById(topicId);
        if (topic == null) {

            throw new Exception("no such a topic");
        }
        model.addAttribute("topic", topic);
        return "topicone";


    }


    @RequestMapping(value = "/topic/{topicId}/addAns", method = RequestMethod.GET)
    public String addTopicAnsForm(Model model, HttpServletRequest request, @PathVariable Long topicId) throws Exception {
//        Object userIdAttrib = request.getSession().getAttribute("userId");
//        if (userIdAttrib == null) {
//            return "redirect:/";
//        }
        Topic topic = topicService.getById(topicId);
        if(topic==null){
            throw new Exception("there is no such a topic");
        }
        model.addAttribute("ansDto", new AnswerAddForm());
        model.addAttribute("topic",topic);
        return "addAns";

    }

    @RequestMapping(value = "/topic/{topicId}/addAns", method = RequestMethod.POST)
    public String addAnswerProcess(HttpServletRequest request, @PathVariable Long topicId, @ModelAttribute AnswerAddForm form) {
//        Object userIdAttrib = request.getSession().getAttribute("userId");
//        if (userIdAttrib == null) {
//            return "redirect:/";
//        }
//        Long userID = (Long)userIdAttrib;
        CurrentUser currentUser=(CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        form.setTopicId(topicId);
        form.setUserId(currentUser.getId());

        answerService.create(form);
        Topic topic=topicService.getById(topicId);

        return "redirect:/topics/"+topic.getId();

    }


}
