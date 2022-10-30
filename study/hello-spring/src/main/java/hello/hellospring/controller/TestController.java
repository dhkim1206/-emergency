package hello.hellospring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import hello.hellospring.model.Member;
import hello.hellospring.model.Project;
import hello.hellospring.model.User;
import hello.hellospring.repository.TestRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestRepository testRepository;
    @GetMapping("/info")
    public Object projectInfo(){
        Project project = new Project();
        project.id = "preword";
        project.name = "theking";
        return project;
    }
//    @GetMapping(path="/json.do")
//    public List<User> getAllUserNames(){
//        return testRepository.selectAll();
//    }
    @GetMapping(path="/json.do")
    public JSONObject selectInfo(Model model){
        List<User> users = testRepository.selectAll();
        JSONObject data = new JSONObject();
        data.put("member", users);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try{
            jsonStr = mapper.writeValueAsString(users);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

//        model.addAttribute("test", users);
//        System.out.println(model);
        return data;
    }
}
