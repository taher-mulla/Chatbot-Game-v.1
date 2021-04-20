package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Random;

public class Controller {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TextArea mainTextArea;
    @FXML
    private TextField messageArea;

    private int botP,userP;
    private String botS="Bot's points --> ", userS="Users points are -->";
    private TextField botPoints = new TextField(botS+botP);
    private TextField userPoints = new TextField(userS+userP);
    private VBox vBox = new VBox(botPoints,userPoints);

    boolean convoStarted = false;
    boolean pizza = false;
        //If it's the Bot's turn(i.e. the bot is answering and user is giving the character name) this value will be 0, and for the user it will be 1
    int turn = 0;
    String charGiven = "";
    String message;
    String[] affermative = {"yes","y","okay","ok","ya"};
    String[] negative = {"no","nah","i dont want to"};
    String[] character = {"CHICO","COOPER","GRIFFIN","EDWARDS","EDWARDS","JASON","GWEN","ELAINE","VENKMAN","DEPUTY CLARK","GENERAL GREY","TROY"};

    public void initialize(){

        addToTextArea('b',"Hi, lets play a game");
        botP=0;
        userP=0;

    }

    public void addToTextArea(char who, String addText){
        if(who == 'b'){
            mainTextArea.appendText("\n" + "[Bot]-> " + addText);
        }else{
            mainTextArea.appendText("\n" + "[You]-> " + addText);
        }
    }

    public void sendButton() {
        System.out.println("SEND CLICKED");
        if (!messageArea.getText().isEmpty()) {
            addToTextArea('y', messageArea.getText());

            if (!convoStarted) {
                System.out.println("CONVO JUST STARTED");
                if (yes()) {
                    convoStarted=true;
                    borderPane.setTop(vBox);
                    addToTextArea('b', "Okay so the game is based on points, if I cannot answer a question you get a point and " +
                            "if you can't answer then I get a point\n" + "             Give me a movie character and i will tell you which movie it is from" +
                            "\n             Okay, so you go first give me a charactor name fro any movie");
                }else{
                    addToTextArea('b', "I did't get you pls explain");
                }

            }
                //THIS PART I HAVE MADE FOR FUTURE WORK, TO INTEGRATE NPL USING OpenNPL IN THIS TO ORDER A PIZZA
            /*else if(yes() && pizza){
                System.out.println("ASKED FOR PIZZA AND IN yes()");
                    addToTextArea('b', "So, what kind of pizza do you want");

            }else if(no() && pizza){
                System.out.println("ASKED FOR PIZZA AND IN no()");
                charGiven = character[new Random().nextInt(character.length)];
                addToTextArea('b',"Okay then tell me which movie "+charGiven+ " is from?");
//                askedChar = true;

            }*/

            else if(turn == 1){
                if(!charGiven.isBlank()){
                    System.out.println("CHARACTER IS GIVEN IN THE if");
                    if(checkMovieCorrect()){
                        message = "Correct!!... you get a point";
                        userP++;
                    }else{
                        message ="Wrong...";
                        botP++;
                    }

                    userPoints.setText(userS+userP);
                    botPoints.setText(botS+botP);
                    turn = 0;
                    addToTextArea('b', message+"..... Now give me a character");
                }
            }else if(turn ==0){
                System.out.println("IN FINAL else()");
                if (!characterExixts()) {
                    message = "Okay I give up, you get a point, I don't know this character....";
                    turn = 1;
                    userP++;
                }else{
                    botP++;
                    message = "HaHa, I get a point....";
                    turn = 1;
                }

                userPoints.setText(userS+userP);
                botPoints.setText(botS+botP);
                charGiven = character[new Random().nextInt(character.length)];
                addToTextArea('b',message+"Okay now tell me which movie "+charGiven+ " is from?");
//                pizza = true;
            }

        }else{
            addToTextArea('b', "Noooo.... Enter something at least :(");
        }
    }



    private boolean checkMovieCorrect(){
        System.out.println("CHECKING IF MOVIE IS CORRECT");
        String movieName = messageArea.getText();
        messageArea.clear();
        boolean correctMovie = false;
        Path movieChar = FileSystems.getDefault().getPath("cornell movie-dialogs corpus", "movie_characters_metadata.txt");
        try (BufferedReader fileReader = Files.newBufferedReader(movieChar)) {
            String line;
            String[] parts;
            while ((line = fileReader.readLine()) != null) {
                parts = line.split(";");
                if (charGiven.toLowerCase(Locale.ROOT).equals(parts[1]) &&
                        parts[3].toLowerCase(Locale.ROOT).equals(movieName.toLowerCase(Locale.ROOT))){
                    addToTextArea('b', movieName+ " is from "+parts[3]);
                    correctMovie=true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if(correctMovie)
            return true;
        else
            return false;
    }

    //THIS FUNCTION CHECKS IF THE GIVEN CHAR NAME IS THERE IN THE FILE AND PRINTS ALL MATCHES
    //IF IT IS THEN RETURNS true ELSE false
    private boolean characterExixts(){
        String charName = messageArea.getText();
        messageArea.clear();
        boolean hasChar = false;
        //addToTextArea('y',charName);
        Path movieChar = FileSystems.getDefault().getPath("cornell movie-dialogs corpus", "movie_characters_metadata.txt");
        try (BufferedReader fileReader = Files.newBufferedReader(movieChar)) {
            System.out.println("IN characterExixts() TRY BLOCK");
            String line;
            String[] parts;
            while ((line = fileReader.readLine()) != null) {
                parts = line.split(";");
                System.out.println(parts[1].toLowerCase(Locale.ROOT)+" ------------- "+charName.toLowerCase(Locale.ROOT));
                if (parts[1].toLowerCase(Locale.ROOT).equals(charName.toLowerCase(Locale.ROOT))){
                    addToTextArea('b', charName+ " is from "+parts[3]);
                    hasChar=true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if(hasChar)
            return true;
        else
            return false;
    }


        //CHECKS IF THE GIVEN MESSAGE equals ANY OF THE PRE-DETERMINED NO ANSWERS
    /*private boolean no(){
        for(int i=0; i< negative.length; i++){
//            System.out.println(affermative[i] + " ---- " + messageArea.getText().toLowerCase(Locale.ROOT));
            if(negative[i].equals(messageArea.getText().toLowerCase(Locale.ROOT))) {
                messageArea.clear();
                return true;
            }
        }
        return false;
    }*/



        //CHECKS IF THE GIVEN MESSAGE equals ANY OF THE PRE-DETERMINED AFERMATIVE ANSWERS
    private boolean yes(){
        for(int i=0; i< affermative.length; i++){
//            System.out.println(affermative[i] + " ---- " + messageArea.getText().toLowerCase(Locale.ROOT));
            if(affermative[i].equals(messageArea.getText().toLowerCase(Locale.ROOT))) {
                messageArea.clear();
                return true;
            }
        }
        return false;
    }
}
