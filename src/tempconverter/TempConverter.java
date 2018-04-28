/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempconverter;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ejbal_000
 */
public class TempConverter extends Application {
    
    //create text area
    private TextArea ta = new TextArea();
    //create textfields
    private TextField convertedTempTf = new TextField();
    private TextField enterTempTf = new TextField();
    //create degree symbol
    String degree = "" + (char)176;
    
    @Override
    public void start(Stage primaryStage) {
      
        //Create a pane border for the root pane
        BorderPane pane = new BorderPane();
        
        //Create another border pane for the top.
        BorderPane paneUserInput = new BorderPane();
        //create Label
        Label enterTempLbl = new Label("Enter a temperature");
        // Even handler for input textfield.
        //This will only allows numbers to be entered
        // When non-numeric value is entered an error message in the text area will display
//         enterTempTf.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    ta.setText("Please enter numbers only.");
//                }else{
//                    ta.setText("");
//                }
//            }
//        });
        //set label to the left of the border
        paneUserInput.setLeft(enterTempLbl);
        //set textfield to the right of the border.
        paneUserInput.setRight(enterTempTf);
        //set borderpane color red
        paneUserInput.setStyle("-fx-border-color: red");
        //set paneUserInput BorderPane into the main border
        pane.setTop(paneUserInput);
        
        
        //BorderPane for the bottom
        BorderPane bottomPane = new BorderPane();
        //create Label for Converted temperature
        Label convertedTempLbl = new Label("Converted temperature");
        //create textfield for converted temperature
        //TextField convertedTempTf = new TextField();
        //set TextField not editable
        convertedTempTf.setEditable(false);
        //set positions for the label and textfield
        bottomPane.setLeft(convertedTempLbl);
        bottomPane.setRight(convertedTempTf);
        //set border line color red
        bottomPane.setStyle("-fx-border-color: red");
        //set the bottomPane bottom borderPane
        pane.setBottom(bottomPane);
        
        //create Vbox for the input scale
        VBox inputScaleVbox = new VBox(15);
        //create Label
        Label inputScaleLbl = new Label("Input Scale");
        //create radio buttons for the left side
        RadioButton inputCelcius = new RadioButton("Celcius");
        RadioButton inputFahrenheit = new RadioButton("Fahrenheit");
        RadioButton inputKelvin = new RadioButton("Kelvin");
       
        
        
        
        //set preference width for label and radio buttons
        inputScaleLbl.setPrefWidth(100);
        inputCelcius.setPrefWidth(100);
        inputFahrenheit.setPrefWidth(100);
        inputKelvin.setPrefWidth(100);
        //add UI controls into the vertical box and align in the center
        inputScaleVbox.getChildren().addAll(inputScaleLbl, inputCelcius, inputFahrenheit, inputKelvin);
        inputScaleVbox.setAlignment(Pos.CENTER);
        //set border line color red
        inputScaleVbox.setStyle("-fx-border-color: red");
        //set vbox to the left of the main border pane
        pane.setLeft(inputScaleVbox);
        
        //create Vbox for output scale
        VBox outputScaleVbox = new VBox(15);
        //create Label
        Label outputScaleLbl = new Label("Output Scale");
        //create radio buttons for the right side 
        RadioButton outputCelcius = new RadioButton("Celcius");
        RadioButton outputFahrenheit = new RadioButton("Fahrenheit");
        RadioButton outputKelvin = new RadioButton("Kelvin");
        
        
        //create toggle group to select only one radio button
        //This Toggle group is for input scale side.
        ToggleGroup tog = new ToggleGroup();
        inputCelcius.setToggleGroup(tog);
        inputFahrenheit.setToggleGroup(tog);
        inputKelvin.setToggleGroup(tog);
        //event handlers for the radio button
        //addlistener for the toggle property
        //this converts the input value when a button is selected.
        //This also detects invalid temperature that is entered
        tog.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                //Celcius conversion
                //celcius conversion to celcius
                if(inputCelcius.isSelected() && outputCelcius.isSelected()){
                    double output = Double.parseDouble(enterTempTf.getText());
                    if(output < -273.15){
                       ta.setText("Celcius must be greater than -273.15");
                       enterTempTf.setText("0");
                   }else{
                        ta.setText("");
                    }
                    convertedTempTf.setText(Double.toString(output) + degree + " C");
                }
                //fahreneheit conversion to celcius
                if(inputFahrenheit.isSelected() && outputCelcius.isSelected()){
                    double temp = Double.parseDouble(enterTempTf.getText());
                    if(temp < -459.67){
                        ta.setText("Fahrenheit must be greater than -459.67");
                        enterTempTf.setText("0");
                    }else{
                        ta.setText("");
                    }
                    double output = (temp - 32) * 5.0/9.0;
                    convertedTempTf.setText(Double.toString(output) + degree + " C");
                }
                //kelvin conversion to celcius
                if(inputKelvin.isSelected() && outputCelcius.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < 0){
                       ta.setText("Kelvin must be greater than 0");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   double output = temp - 273.15;
                   convertedTempTf.setText(Double.toString(output) + degree + " C");
                }
                //fahrenheit conversion
                //fahrenheit conversion to fahrenheit
                if(inputFahrenheit.isSelected() && outputFahrenheit.isSelected()){
                   double output = Double.parseDouble(enterTempTf.getText());
                   if(output < -459.67){
                       ta.setText("Celcius must be greater than -273.15");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   convertedTempTf.setText(Double.toString(output) +  " F");
                }
                //celcius conversion to fahrenheit
                if(inputCelcius.isSelected() && outputFahrenheit.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < -273.15){
                       ta.setText("Celcius must be greater than -273.15");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   double output = temp * 9.0/5.0 + 32;
                   convertedTempTf.setText(Double.toString(output) + degree +  " F");
                }
                //kelvin conversion to fahrenheit
                if(inputKelvin.isSelected() && outputFahrenheit.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < 0){
                       ta.setText("Kelvin must be greater than 0");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   double output = (temp * 9.0/5.0) - 459.67;
                   convertedTempTf.setText(Double.toString(output) + degree +  " F");
                }
                // kelvin conversion
                // kelvin conversion to kelvin
                if(inputKelvin.isSelected() && outputKelvin.isSelected()){
                   double output = Double.parseDouble(enterTempTf.getText());
                   if(output < 0){
                       ta.setText("Kelvin must be greater than 0");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   convertedTempTf.setText(Double.toString(output) + degree +  " K");
                }
                //Celcius converted to kelvin
                if(inputCelcius.isSelected() && outputKelvin.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < -273.15){
                       ta.setText("Celcius must be greater than -273.15");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   double output = temp + 273.15;
                   convertedTempTf.setText(Double.toString(output) + degree +  " K");
                }
                //fahrenheit conversion to kelvin
                if(inputFahrenheit.isSelected() && outputKelvin.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < -459.67){
                        ta.setText("Fahrenheit must be greater than -459.67");
                        enterTempTf.setText("0");
                        convertedTempTf.setText("");
                    }else{
                       ta.setText("");
                   }
                   double output = (temp + 459.67) * 5.0/9.0;
                   convertedTempTf.setText(Double.toString(output) + degree +  " K");
                }
            }
        });
        //create toggle group to select only one radio button
        //This Toggle group is for input scale side.
        ToggleGroup tog1 = new ToggleGroup();
        
        outputCelcius.setToggleGroup(tog1);
        outputFahrenheit.setToggleGroup(tog1);
        outputKelvin.setToggleGroup(tog1);
        //event handlers for the radio button
        //addlistener for the toggle property
        //this will convert the input value when one button is selected
        //Invalid temperatures are detected
        tog1.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                //Celcius conversion to celcius
                if(inputCelcius.isSelected() && outputCelcius.isSelected()){
                    double output = Double.parseDouble(enterTempTf.getText());
                    if(output < -273.15){
                       ta.setText("Celcius must be greater than -273.15");
                       enterTempTf.setText("0");
                   }
                    else{
                        ta.setText("");
                    }
                    convertedTempTf.setText(Double.toString(output) + degree + " C");
                    
                }
                //fahrenheit converted to Celcius
                if(inputFahrenheit.isSelected() && outputCelcius.isSelected()){
                    double temp = Double.parseDouble(enterTempTf.getText());
                    if(temp < -459.67){
                        ta.setText("Fahrenheit must be greater than -459.67");
                        enterTempTf.setText("0");
                    }
                    else{
                        ta.setText("");
                    }
                    double output = (temp - 32) * 5.0/9.0;
                    convertedTempTf.setText(Double.toString(output) + degree + " C");
                }
                //Kelvin converted to Celcius
                if(inputKelvin.isSelected() && outputCelcius.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < 0){
                       ta.setText("Kelvin must be greater than 0");
                       enterTempTf.setText("0");
                   }
                   else{
                       ta.setText("");
                   }
                   double output = temp - 273.15;
                   convertedTempTf.setText(Double.toString(output) + degree + " C");
                }
                //fahrenheit conversion to Fahrenheit
                if(inputFahrenheit.isSelected() && outputFahrenheit.isSelected()){
                   double output = Double.parseDouble(enterTempTf.getText());
                   if(output < -459.67){
                        ta.setText("Fahrenheit must be greater than -459.67");
                        enterTempTf.setText("0");
                    }
                   else{
                       ta.setText("");
                   }
                   convertedTempTf.setText(Double.toString(output) + degree +  " F");
                }
                //Celcius converted to Fahrenheit
                if(inputCelcius.isSelected() && outputFahrenheit.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < -273.15){
                       ta.setText("Celcius must be greater than -273.15");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   double output = temp * 9.0/5.0 + 32;
                   convertedTempTf.setText(Double.toString(output) + degree +  " F");
                }
                //Kelvin converted to Fahrenheit
                if(inputKelvin.isSelected() && outputFahrenheit.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < 0){
                       ta.setText("Kelvin must be greater than 0");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   double output = (temp * 9.0/5.0) - 459.67;
                   convertedTempTf.setText(Double.toString(output) + degree +  " F");
                }
                // kelvin conversion to Kelvin
                if(inputKelvin.isSelected() && outputKelvin.isSelected()){
                   double output = Double.parseDouble(enterTempTf.getText());
                   if(output < 0){
                       ta.setText("Kelvin must be greater than 0");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   convertedTempTf.setText(Double.toString(output) + degree + " K");
                }
                //Celcius converted to Kelvin
                if(inputCelcius.isSelected() && outputKelvin.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < -273.15){
                       ta.setText("Celcius must be greater than -273.15");
                       enterTempTf.setText("0");
                   }else{
                       ta.setText("");
                   }
                   double output = temp + 273.15;
                   convertedTempTf.setText(Double.toString(output) + degree + " K");
                }
                //Fahrenheit converted to Kelvin
                if(inputFahrenheit.isSelected() && outputKelvin.isSelected()){
                   double temp = Double.parseDouble(enterTempTf.getText());
                   if(temp < -459.67){
                        ta.setText("Fahrenheit must be greater than -459.67");
                        enterTempTf.setText("0");
                    }else{
                       ta.setText("");
                   }
                   double output = (temp + 459.67) * 5.0/9.0;
                   convertedTempTf.setText(Double.toString(output) + degree + " K");
                }
            }
        });
        
        
        
        //set preference width for label and radio buttons
        outputScaleLbl.setPrefWidth(100);
        outputCelcius.setPrefWidth(100);
        outputFahrenheit.setPrefWidth(100);
        outputKelvin.setPrefWidth(100);
        //add UI controls into the vertical box and align in the center
        outputScaleVbox.getChildren().addAll(outputScaleLbl, outputCelcius, outputFahrenheit, outputKelvin);
        outputScaleVbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 400, 200);
        //set Vbox border line color red
        outputScaleVbox.setStyle("-fx-border-color: red");
        //set output scale vbox to the right of the main border
        pane.setRight(outputScaleVbox);
        
        
        //set text area not editable
        ta.setEditable(false);
        //set text area within a scroll Pane
        ScrollPane scPane = new ScrollPane(ta);
        //set the border line for the Scroll Pane.
        scPane.setStyle("-fx-border-color: red");
        //set Text area to the center of the main border
        pane.setCenter(scPane);
        
        //Title
        primaryStage.setTitle("Temperature Conversion");
        //set scene
        primaryStage.setScene(scene);
        //show scene
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
