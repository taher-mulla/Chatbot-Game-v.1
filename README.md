# Chatbot v.1

![Project Image](ChatbotPhase1NoAudio.gif)

> This is a rudimentary chatbot game using Java, it is based on conditional logic i.e. 'if' statements. This phase does not use any NPL.

---

## Table of Contents

- [Description](#description)
- [Working](#working)
- [Improvements](#improvements)
- [Data Source](#data-source)
- [License](#license)
- [Author Info](#author-info)

---

## Description

This application is a game between the user and a bot. It is based on conditional statements, however, the program will accept different ways of affirmative statements, etc. I am using a database that has the movie name and character name in a .txt file. The questions and answers are taken from here.

#### Technologies

- JavaIO
- JavaFx
- JavaNIO
- Basic Java

[Back To The Top](#chatbot-java-v1)

---

## Working

#### Data Base

  The file is a .txt file with involves 9,035 characters from 617 movies. 
  - character metadata included:
  	  - gender (for 3,774 characters)
	  - position on movie credits (3,321 characters)

  [Data File](Project/cornell movie-dialogs corpus/movie_characters_metadata.txt)

  -Data Pre-processing 
	
  The original delimiter used was ' +++$+++ '. This delimiter could not be used as the split function uses regular expressions, and could not accept this. The delimiter was changed to ';'

#### User Interface 
  The user interface is made using JavaFX. The top-level is a BorderPane, we use the top, center, and bottom of this view in the interface. 

  - Top

	  The top used and created in the controller, is the only part of the interface that has not been coded in the fxml file. It is used to keep the score and has a VBox in it for the user and bot scores. The TextFields are updated every time the score changes through the controller. 

  - Center 

	  The TextArea here is used for the conversation messages, it is appended here every time the bot or used sends a message.

  - Bottom

 	  Here a BorderPane is used with a TextField and  Button. 

#### Code
  The working is done in the [controller](Project/src/sample/Controller.java). Most of the events are triggered by sending a message(send button), from here various functions are called.
  
  Pls look at these 3 [files](Project/src/sample/) for the code. 

[Back To The Top](#chatbot-Java-v1)

---

## Improvements

The user experience can be further improved. This can be done by increasing the accepted affirmative and negative statements. Further, we can also have multiple statements that the bot uses for correct or wrong answers in an array, this can then be randomly be used to create a better conversation. Lastly, the UI can be improved to include shortcuts like 'Enter Button' to send. 

[Back To The Top](#Chatbot-Java-v1)

---

## Data Source

The data was taken from Kaggle and can be found [here.](https://www.kaggle.com/fungusamongus/chatbot-data)

[Back To The Top](#Chatbot-Java-v1)

---

## License

MIT License

Copyright (c) [2017] [Taher H Mulla]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

[Back To The Top](#Chatbot-Java-v1)

---

## Author Info

 - [Linkdin](www.linkedin.com/in/taher-mulla-8b9546136) 

 - [GitHub](https://github.com/taher-mulla)

 - Email - taher.mulla@gmail.co

[Back To The Top](#chatbot-java-v1)
