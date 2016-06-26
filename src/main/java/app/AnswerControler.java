package app;

import app.autentication.CurrentUser;
import app.dto.AnswerAddForm;
import app.entity.Answer;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.LongStream;

/**
 * Created by PetyoPetrov on 26.06.2016 г..
 */
@Controller
public class AnswerControler {

    @Autowired
    TopicService topicService;

    @Autowired
    AnswerService answerService;

    @RequestMapping(value = "/topics/{topicId}/{page}", method = RequestMethod.GET)
    public String addTopicAnswer(@PathVariable("topicId") Long topicId,
                                 @PathVariable("page") int page,
                                 Model model) throws Exception {
//
        Topic topic = topicService.getById(topicId);
        if (topic == null) {

            throw new Exception("no such a topic");
        }

        double countOfAnswers=answerService.getCount(topicId);
        List<Answer> answers=answerService.getAnswers(topicId,page);
        long[] pages= LongStream.range(1,(long)Math.ceil(countOfAnswers/2)+1).toArray();

        model.addAttribute("topic", topic);
        model.addAttribute("pages",pages);
        model.addAttribute("currentPage",page);
        model.addAttribute("answers",answers);


        return "allAnswersForTopic";


    }

    @RequestMapping(value = "/topic/{topicId}/addAnswer", method = RequestMethod.GET)
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
        return "addAnswer";

    }

    @RequestMapping(value = "/topic/{topicId}/addAnswer", method = RequestMethod.POST)
    public String addAnswerProcess( @PathVariable Long topicId, @ModelAttribute AnswerAddForm form) {
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

        return "redirect:/topics/"+topic.getId()+"/1";

    }
}
