package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import sample.Inheritance.Scheduler;
import sample.Models.Process;
import sample.Schedulers.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Controller {
    @FXML
    private TextFlow fileDisplayLabel;
    @FXML
    private Button importFileBtn;
    @FXML
    private TextField quantumTxt;
    @FXML
    private Label quantumLabel;
    @FXML
    private Button startBtn;
    @FXML
    private Button printBtn;
    @FXML
    private Label timeUnitLabel;
    @FXML
    private TextField timeUnitTxt;

    private Queue<Process> processList;
    private File file;
    private String data = "";
    private String textPrint = "";


    public void initialize(){
      processList = new LinkedList<>();
  }

    //Use path given by filechooser() to read file
    private void readFile(){
      if(file != null){
          try{
              BufferedReader in = new BufferedReader(new FileReader(file));
              String line = null;
              String text = "";
              //sacar read para inizializar la lista por button individual
              while ((line = in.readLine()) != null){
                  int burstTime = 0;
                  int priority = 0;
                  int arrivalTime = 0;
                  String name = "";

                  String[] data = line.split(" ");
                  burstTime = Integer.parseInt(data[1]);
                  priority = Integer.parseInt(data[2]);
                  arrivalTime = Integer.parseInt(data[3]);
                  name = data[0];
                  processList.add(new Process(burstTime,name,priority,arrivalTime));


                  text = text + line + System.lineSeparator();

              }
          } catch (IOException e) {
              e.printStackTrace();
          }

      }

  }

    //Get file path from the choosen from the UI
    @FXML
    private void fileChooser(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(new FXMLLoader(getClass().getResource("CPUSchedulerApp.fxml")).getRoot());
        if(file != null){

            quantumLabel.setVisible(true);
            quantumTxt.setVisible(true);
            timeUnitLabel.setVisible(true);
            timeUnitTxt.setVisible(true);
            startBtn.setVisible(true);
            quantumTxt.setText("4");
            timeUnitTxt.setText("2");
        }

    }

    //set data to display with spaces and print with separator for format .csv
    private void arrangeData(Scheduler scheduler , String name){
        scheduler.setTimeSlice(Integer.parseInt(timeUnitTxt.getText()));
        readFile();
        scheduler.run(processList);
        data += name + System.lineSeparator() + scheduler.getData() + System.lineSeparator() + System.lineSeparator();
        textPrint += name + "," + scheduler.print() + ",";
    }

    @FXML
    private void display(){
        String text = "";
        arrangeData(new FCFSSchedulerNon(),"FCFS");
        arrangeData(new NonPreemtiveSJFScheduler(),"NonPreemtiveSJFS");
        arrangeData(new PreemtiveSJFScheduler(),"PreemtiveSJFS");
        arrangeData(new NonPreemtivePriorityScheduler(),"NonPreemtivePriority");
        arrangeData(new PreemtivePriorityScheduler(), "PreemtivePriority");
        readFile();
        RoundRobin roundRobin = new RoundRobin();
        roundRobin.setTimeSlice(Integer.parseInt(timeUnitTxt.getText()));
        roundRobin.setQuantum(Integer.parseInt(quantumTxt.getText()));
        roundRobin.run(processList);
        text =  data +"RoundRobin"+ System.lineSeparator() + roundRobin.getData();
        textPrint = textPrint + "RoundRobin"+ "," + roundRobin.print();
        fileDisplayLabel.getChildren().add(new Text(text));
        printBtn.setVisible(true);
    }

    @FXML
    private void print(){
        try{
            PrintWriter printWriter = new PrintWriter("output.csv", "UTF-8");
            printWriter.print(textPrint);
            printWriter.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
