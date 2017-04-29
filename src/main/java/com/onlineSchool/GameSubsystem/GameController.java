package com.onlineSchool.GameSubsystem;
 
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
import com.onlineSchool.CourseSubsystem.Course;
import com.onlineSchool.CourseSubsystem.CourseRepository;
 
@Controller
public class GameController {
   
    @Autowired
    GameRepository gameRepository;
       
    @Autowired
    CourseRepository courseRepository;
   
    ArrayList<Question> questions = new ArrayList<Question>();
    private boolean step1 = false , step2 = false;
    Game game = new Game();
   
    
    @RequestMapping("/intialize")
    ModelAndView initializeGame(ModelAndView mav){
        List<Course> courses = courseRepository.findAll();
        mav.setViewName("initializeGame");
        mav.addObject("courses",courses);
        step1 = true;
        return mav;
    }
   
    /*  takes game name and what course it belongs and its type */
    @RequestMapping("/addQuestions")   
    ModelAndView redirect(@RequestParam("gameName")String gameName,
    		                      @RequestParam("courseName")String courseName,     
                                  @RequestParam("Type")String type,
                                   ModelAndView mav ) {        
       
        if(!step1){
            mav.setViewName("intializeGame");
            List<Course> courses = courseRepository.findAll();
            mav.addObject("courses",courses);
            return mav;
        }
        step1 = false;
       
        if(gameRepository.exists(gameName)){
            mav.addObject("error","Game with the same name already exists please try again!");
            mav.setViewName("initializeGame");
            List<Course> courses = courseRepository.findAll();
            mav.addObject("courses",courses);
            return mav;
        }
       
        game.setCourse(courseRepository.findOne(courseName));
        game.setGameName(gameName);
       
        if(type.equals("T/F")){
            mav.setViewName("TFView");
            step2 = true;
            return mav;
        }
        
        else if(type.equals("MCQ")){
            mav.setViewName("MCQ");
            step2 = true;
            return mav;
        }    
        else{
            //RunCode RUNCODE = new RunCode();
            mav.setViewName("RUNCode");
            mav.addObject("questions",null);
            step2 = true;
            return mav;
        }
    }
    
    /*  adds more questions of T/F type */
    @RequestMapping("/validGame")  
    ModelAndView addQuestion(@RequestParam("Header") String header,
    		                  @RequestParam("Answer") String answer, ModelAndView mav)
    {
    	TrueOrFalse question = new TrueOrFalse();
    	question.setHeader(header);
    	question.setAnswer(answer);
    	questions.add(question);
        mav.setViewName("TFView");
    	return mav;
    }
   
    /* creates game of type T/F type */
    @RequestMapping("/createGame")
    String createGame(@RequestParam("Header") String header,
                      @RequestParam("Answer") String answer){
       
        if(!step2){
            return "redirect:/initialize";
        }
        step2 = false;
        TrueOrFalse question = new TrueOrFalse();
    	question.setHeader(header);
    	question.setAnswer(answer);
    	questions.add(question);
        game.setQuestions(questions);
        gameRepository.save(game);
        questions = new ArrayList<Question>(); 
        return "redirect:/thome";
    }
   
    
    /* adds more questions of MCQ type */
    @RequestMapping("/validgame")
    ModelAndView addquestion(@RequestParam("Header") String header,
    		                  @RequestParam("correctChoice") String answer,
    		                  @RequestParam("Choice1") String choice1,
    		                  @RequestParam("Choice2") String choice2,
    		                  @RequestParam("Choice3") String choice3,
    		                  @RequestParam("Choice4") String choice4, ModelAndView mav)
    {
    	MultipleChoice question = new MultipleChoice();
    	question.setHeader(header);
    	question.setCorrectChoice(answer);
    	ArrayList<String>answers = new ArrayList<String>();
    	answers.add(choice1);
    	answers.add(choice2);
    	answers.add(choice3);
    	answers.add(choice4);
    	question.setChoices(answers);
    	questions.add(question);
        mav.setViewName("MCQ");
    	return mav;
    }
    
    /* creates game of type MCQ type */
    @RequestMapping("/creategame")
    String creategame(@RequestParam("Header") String header,
                           @RequestParam("correctChoice") String answer,
                           @RequestParam("Choice1") String choice1,
                           @RequestParam("Choice2") String choice2,
                           @RequestParam("Choice3") String choice3,
                           @RequestParam("Choice4") String choice4)
    {
       
        if(!step2){
            return "redirect:/intialize";
        }
        step2 = false;
        MultipleChoice question = new MultipleChoice();
    	question.setHeader(header);
    	question.setCorrectChoice(answer);
    	ArrayList<String>answers = new ArrayList<String>();
    	answers.add(choice1);
    	answers.add(choice2);
    	answers.add(choice3);
    	answers.add(choice4);
    	question.setChoices(answers);
    	questions.add(question);
    	game.setQuestions(questions);
        gameRepository.save(game);
        questions = new ArrayList<Question>(); 
        return "redirect:/thome";
    }
    
    @RequestMapping("/playGame")
    ModelAndView playGame(@RequestParam("game")Game game,ModelAndView mav){
       
        return mav;
    }
   
 
}