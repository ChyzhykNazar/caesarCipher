package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

class CaesarCipher {
    public static String encrypt(String alphabet, String text, int shift) {
        int index = 0;
        boolean upperCase;
        String result = new String();
        char[] ch = alphabet.toCharArray();
        for(int i = 0; i < text.length(); i++){
            for(int j = 0; j < alphabet.length(); j++){
                upperCase = Character.isUpperCase(text.charAt(i));
                char temp = Character.toLowerCase(text.charAt(i));
                int code = temp;
                if(ch[j] == temp){
                    try{
                        if(j + shift >= alphabet.length()){
                            index = (j + shift) - alphabet.length();
                            System.out.println("Alphabet: " + ch[j]);
                            System.out.println("Text: " + temp);
                            System.out.println("Shift: "+ ch[index]);
                        }else {
                            index = (j + shift);
                            System.out.println("Alphabet: " + ch[j]);
                            System.out.println("Text: " + temp);
                            System.out.println("Shift: "+ ch[index]);
                        }
                        if(upperCase == true){
                            result = result + Character.toTitleCase(ch[index]);
                            //ch[index] = Character.toTitleCase(ch[index]);
                        }else{
                            result = result + ch[index];
                        }
                        j = alphabet.length();
                    }catch (ArrayIndexOutOfBoundsException ex){
                        ex.getMessage();
                    }
                }else if(temp == ' '){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 45){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 39){
                    result = result + temp;
                    j = alphabet.length();
                }
            }
        }
        return result;
    }
    public static String decipher(String alphabet, String text, int shift) {
        int index = 0;
        boolean upperCase;
        String result = new String();
        char[] ch = alphabet.toCharArray();
        for(int i = 0; i < text.length(); i++){
            for(int j = 0; j < alphabet.length(); j++){
                upperCase = Character.isUpperCase(text.charAt(i));
                char temp = Character.toLowerCase(text.charAt(i));
                int code = temp;
                if(ch[j] == temp){
                    try{
                        if(j - shift < 0){
                            index = (j - shift) + alphabet.length();
                            System.out.println("Alphabet: " + ch[j]);
                            System.out.println("Text: " + temp);
                            System.out.println("Shift: "+ ch[index]);
                        }else {
                            index = (j - shift);
                            System.out.println("Alphabet: " + ch[j]);
                            System.out.println("Text: " + temp);
                            System.out.println("Shift: "+ ch[index]);
                        }
                        if(upperCase == true){
                            result = result + Character.toTitleCase(ch[index]);
                        }else{
                            result = result + ch[index];
                        }
                        j = alphabet.length();
                    }catch (ArrayIndexOutOfBoundsException ex){
                        ex.getMessage();
                    }
                }else if(temp == ' '){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 45){
                    result = result + temp;
                    j = alphabet.length();
                }else if(code == 39){
                    result = result + temp;
                    j = alphabet.length();
                }
            }
        }
        return result;
    }
}

public class Controller {

    @FXML
    private Button encrypt_btn;

    @FXML
    private TextArea input_textarea;

    @FXML
    private Label output;

    @FXML
    private Button decipher_btn;

    @FXML
    private ChoiceBox<String> shiftChoiceBox;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @FXML
    void initialize() {
        String English = "abcdefghijklmnopqrstuvwxyz";
        String Ukrainian = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
        languageChoiceBox.getItems().add("Українська");
        languageChoiceBox.getItems().add("English");
        languageChoiceBox.setValue("Українська мова");

        languageChoiceBox.setOnAction(actionEvent -> {
            shiftChoiceBox.getItems().clear();
            if(languageChoiceBox.getValue() == "Українська"){
                for(int i = 0; i < 34; i++){
                    shiftChoiceBox.getItems().add("" + i + "");
                }
            }else{
                for(int i = 0; i < 27; i++){
                    shiftChoiceBox.getItems().add("" + i + "");
                }
            }
        });

        encrypt_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String shift = String.valueOf(shiftChoiceBox.getValue());
            System.out.println(shift);
            if(languageChoiceBox.getValue() == "Українська"){
                String outputText = CaesarCipher.encrypt(Ukrainian,inputText,Integer.parseInt(shift));
                System.out.println(outputText);
                output.setText(outputText);
            }else{
                String outputText = CaesarCipher.encrypt(English,inputText,Integer.parseInt(shift));
                System.out.println(outputText);
                output.setText(outputText);
            }

        });

        decipher_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String shift = String.valueOf(shiftChoiceBox.getValue());
            System.out.println(shift);
            if(languageChoiceBox.getValue() == "Українська"){
                String outputText = CaesarCipher.decipher(Ukrainian,inputText,Integer.parseInt(shift));
                System.out.println(outputText);
                output.setText(outputText);
            }else{
                String outputText = CaesarCipher.decipher(English,inputText,Integer.parseInt(shift));
                System.out.println(outputText);
                output.setText(outputText);
            }
        });

    }
}
