package com.sumanta.MainGenie.Utils;

public class PromptUtil {

    public static String generatePromptForCodeGeneration(String problemStatement, String ideCode) {
        return
                "You are a smart code generator, who generates code structures according to a DSA problem statement, but never generate solution.\n" +
                        "Here is the problem statement, problemStatemnt -" + problemStatement +
                        "and here is the default code snippet that is given in the website's ide, defaultIdeCode - " + ideCode + "(detect the language, and use that language for further use)\n" +
                        "As you can see, there is only a class and a function, no main function, so I cannot write a solution in the website and test it in my local machine.\n" +
                        "So, take the ide code snippet that is given, and use the context from the given problem statement\n" +
                        "And create a main function which will be compatible with the default IDE code, then rewrite the whole code again, so that I can just copy paste that into my local machine.\n" +
                        "Remember, never generate solution for the problem.\n" +
                        "for your reference I am giving an example (It is just an example, real problemStatement, and defaultIdeCode is given to you earlier) \n" +
                        "\n" +
                        "Problem statement - Given two numbers, x and y, add them and return the value\n" +
                        "Default Ide code - (I am using cpp for now, but it can bbe in any language, generate main function accordingly)\n" +
                        "\n" +
                        "Test cases - \n" +
                        "1. x=2, y=3, output = 5\n" +
                        "2. x=3, y=4, output = 7\n" +
                        "\n" +
                        "class Solution {\n" +
                        "\tpublic int addition(int x, int y) {\n" +
                        "\t\t// (keep this part empty, it is for me to write the solution)\n" +
                        "\t}\n" +
                        "}\n" +
                        "\n" +
                        "What response should you return ->\n" +
                        "\n" +
                        "#include<iostream> //for you to include proper libraries\n" +
                        "using namespace std; //for you to include proper libraries\n" +
                        "\n" +
                        "class Solution {\n" +
                        "\tpublic int addition(int x, int y) {\n" +
                        "\t\t// Blank space for writing the solution\n" +
                        "\t}\n" +
                        "}\n" +
                        "\n" +
                        "int main() {\n" +
                        "\tSolution solution = new Solution();\n" +
                        "\tcout << \"For input \" << 2 << \" and \" << 3 << \" the answer is : \" << solution.addition(2, 3);\n" +
                        "\tcout << \"For input \" << 3 << \" and \" << 4 << \" the answer is : \" << solution.addition(3, 4); \n" +
                        "}\n" +
                        "\n" +
                        "The above code is just for your reference of how you should respond, keep in mind, that the real problem statement and test cases and default ide code can and will be a lot different than this, generate the required code accordingly.\n" +
                        "\n" +
                        "Whatever you generate other than the code required, just comment it out. in your response there shouldn't be a single line which is not the part of the code and still not commented out";
    }
}
