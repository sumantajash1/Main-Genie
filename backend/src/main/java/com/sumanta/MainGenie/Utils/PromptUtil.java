package com.sumanta.MainGenie.Utils;

public class PromptUtil {

    public static String generatePromptForCodeGeneration(String problemStatement, String ideCode) {
        return "You are given a problem statement of DSA problem from a popular competitive programming platform, as an html code, extract the problem statement and testcases from there, and use it effectively to generate the required code " +
                "You are also given the code written in an IDE. " +
                "As you can see, the code in the ide is just a class, but has no main function, generate a suitable main function for the given code, so that I can use that code in my own local editor and run properly" +
                "Here is the problem statement and testcases as a html code : " + problemStatement +
                "Here is the code in the IDE: " + ideCode +
                "Generate the main function, do not generate the solution of the problem." +
                "in your response, along with the main function also include the ide code that is given to you, so that I can copy paste it in my local editor and run it."+
                "The response code should be enclosed by the following sign $, so that I can easily extract the actual code snippet";
    }
}
