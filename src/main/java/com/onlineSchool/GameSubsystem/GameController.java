package com.onlineSchool.GameSubsystem;
 
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlineSchool.AccountSubsystem.Account;
import com.onlineSchool.CourseSubsystem.Course;
import com.onlineSchool.CourseSubsystem.CourseRepository;
 
@Controller
public class GameController {
   
    @Autowired
    GameRepository gameRepository;
    
    @Autowired
    CommentRepository commentRepository;
       
    @Autowired
    CourseRepository courseRepository;
   
    ArrayList<Question> questions = new ArrayList<Question>();
    private boolean step1 = false , step2 = false;
    Game game = new Game();
    Game currGame = new Game();
    int index = 0;
    
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
        	mav.setViewName("initializeGame");
            mav.addObject("error","Game with the same name already exists please try again!");
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
    	question.setGame(game);
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
    	question.setGame(game);
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
    	question.setGame(game);
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
    	question.setGame(game);
    	questions.add(question);
    	game.setQuestions(questions);
        gameRepository.save(game);
        questions = new ArrayList<Question>(); 
        return "redirect:/thome";
    }
    
    @RequestMapping("/playGame")
    ModelAndView playGame(@RequestParam("gameName")String gameName,ModelAndView mav){
    gameName = gameName.substring(1, gameName.length());
    currGame = gameRepository.findOne(gameName);
    
    if((currGame.getQuestions().get(0)) instanceof TrueOrFalse)
     { 
    	mav.setViewName("PlayTF");
     }
   // else
   // {
   //   mav.setViewName("MCQplay");
   // }
   mav.addObject("game", currGame);
   mav.addObject("question", currGame.getQuestions().get(index++));
   return mav;
    }
    
    /* for playing T/F Game and Swapping questions */
    @RequestMapping("/check")
    ModelAndView playTF(ModelAndView mav)
    {
     mav.setViewName("PlayTF");	
     if(index == currGame.getQuestions().size())
     {
    	 mav.setViewName("redirect:/Congratulations");
     }
     else{
      mav.addObject("game", currGame);
      mav.addObject("question", currGame.getQuestions().get(index++));
     }
     return mav;
    }
    
    /* for playing MCQ Game and Swapping questions */
    @RequestMapping("/Check")
    ModelAndView playMCQ(ModelAndView mav)
    {
    	mav.setViewName("MCQplay");	
     if(index == currGame.getQuestions().size())
     {
    	 mav.setViewName("redirect:/Congratulations");
     }
     else{
    	 mav.addObject("game", currGame);
    	 mav.addObject("question", currGame.getQuestions().get(index++));
     }
     return mav;
    }
    
    @RequestMapping("/Congratulations")
    ModelAndView congratulations(ModelAndView mav)
    {
    	mav.setViewName("Congratulations");
   	    mav.addObject("game", currGame);
   	   index = 0;
   	   currGame = new Game();
       return mav;
    }
    
     /* Request Edit page to edit some game */
    @RequestMapping("/Edit")
	public ModelAndView edit(@RequestParam("gameName")String gameName , ModelAndView mav) {
    	Game game = gameRepository.findOne(gameName);
    	mav.setViewName("EditGame");
		mav.addObject("game", game);
		return mav;
	}
    
    /* apply the changes of editing in the system */
    @RequestMapping("/Done")
    String saveNewGame(@RequestParam("game") Game game, @RequestParam("gameName")String gameName)
    {
//      if(game.getTeacher().getEmail().equals(email))
// 	   {
        Game gam = gameRepository.findOne(gameName);
        gameRepository.delete(gam);
        gameRepository.save(game);
//     }
		return "redirect:/thome";
    }
    
    /* Cancel game selected by a teacher who owns it */
    @RequestMapping("/Cancel")
    String cancelGame(@RequestParam("gameName")String gameName)
    {
//    if(game.getTeacher().getEmail().equals(email))
//	   {
        Game game = gameRepository.findOne(gameName);	
        gameRepository.delete(game);
//     }
       return "redirect:/thome";
    }

    /* Copy game selected by teacher except who owns it */
    @RequestMapping("/CopyGame")
    ModelAndView copyGame(@RequestParam("gameName")String gameName ,ModelAndView mav)
    {
    	String email = new String();
    	Game copiedGame = new Game();       
    	Game game = gameRepository.findOne(gameName);
    	String gamename = new String(); 
    	List<Question> questions = game.getQuestions();
    	List<Question> cQuestions = new ArrayList<Question>();
    	if(Character.isUpperCase(gameName.charAt(0))) 
    		gamename = gameName.substring(0, 1).toLowerCase() + gameName.substring(1);
    	else
    		 {gamename = gameName.substring(0, 1).toUpperCase() + gameName.substring(1);}	
    	
    //	if(!game.getTeacher().getEmail().equals(email) && (accountRepository.findOne(email) instanceof Teacher))
    //	 {
    	    copiedGame.setGameName(gamename +".");
    	    copiedGame.setCourse(game.getCourse());
    	    for(int i = 0 ; i < questions.size() ;i++)
    	    	{
    	    	 TrueOrFalse ques = new TrueOrFalse();
    	    	 ques.setGame(copiedGame);
    	    	 ques.setHeader(questions.get(i).getHeader());
    	    	 ques.setAnswer(((TrueOrFalse) questions.get(i)).getAnswer());
    	    	 cQuestions.add(ques);
    	    	}
    	    copiedGame.setQuestions(cQuestions);
   // 		copiedGame.setTeacher((Teacher)accountRepository.findOne(email));
    		gameRepository.save(copiedGame);
    //	 }
    	mav.setViewName("redirect:/thome");	
    	return mav;
    }
	
    @RequestMapping("/addComment")
    void addComment(@RequestParam("text") String text, 
    				@RequestParam("account") Account account,
    				@RequestParam("game") Game game)
    {
    	Comment comment = new Comment();
    	comment.setText(text);
    	comment.setAccount(account);
    	comment.setGame(game);
    	commentRepository.save(comment);
    }
    
  
}
