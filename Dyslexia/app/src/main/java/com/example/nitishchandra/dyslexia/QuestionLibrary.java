package com.example.nitishchandra.dyslexia;



public class QuestionLibrary {

        private String mQuestions[] = {
                "kzjd",
                "mbsu",
                "ffwn",
                "ebgo"
        };


        private String mChoices[][] = {
                {"Orange", "Red", "Maroon"},
                {"Olive", "Grey", "Green"},
                {"Blue", "Black", "Brown"},
                {"Pink", "Magenta", "Purple"}
        };


        private String mCorrectAnswers[] = {"Red", "Green", "Blue", "Pink"};
        private int mColor []= {0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFF00FF};



        public String getQuestion(int a) {
            String question = mQuestions[a];
            return question;
        }


        public String getChoice1(int a) {
            String choice0 = mChoices[a][0];
            return choice0;
        }


        public String getChoice2(int a) {
            String choice1 = mChoices[a][1];
            return choice1;
        }

        public String getChoice3(int a) {
            String choice2 = mChoices[a][2];
            return choice2;
        }

        public String getCorrectAnswer(int a) {
            String answer = mCorrectAnswers[a];
            return answer;
        }
        public int getColor(int a)
        {
            int color = mColor[a];
            return color;
        }


    }

