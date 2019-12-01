package FXMLcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class existingPlayerMenuController implements Initializable {
    private boolean selectedButton = false;
    private Pane selectedPane;

    @FXML
    public Pane player1, player2, player3, player4;

    private String name;

    public int readFile(String filename) {
        BufferedReader in;
        int lv;
        try {
            in = new BufferedReader(new FileReader("src/savedFiles/"  + filename));

            String temp;
            temp = in.readLine();
            temp = in.readLine();
            temp = in.readLine();
            lv = Integer.parseInt(temp);
            return lv;

        }
        catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void setList() {

        File dir = new File("src/savedFiles");
        String[] savedGames = dir.list();
        int len;
        if(savedGames == null)
            len = 0;
        else
            len = savedGames.length;
        ArrayList<Pane> panes = new ArrayList<>();
        panes.add(player1);
        panes.add(player2);
        panes.add(player3);
        panes.add(player4);

        for (int i = 0; i < Math.min(4, len); i++) {
            ((Label) panes.get(i).getChildren().get(0)).setText(savedGames[i].replace(".txt",""));
            ((Label) panes.get(i).getChildren().get(1)).setText(Integer.toString(readFile(savedGames[i])));
        }

    }


    public void playerHoverIn(MouseEvent mouseEvent){
        if(!selectedButton) {
            try {
                Pane button = (Pane) mouseEvent.getSource();
                ColorAdjust colorAdjust = new ColorAdjust();
                Color color = new Color(1, 1, 1, 0.5);
                colorAdjust.setBrightness(0.5);
                button.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            catch (ClassCastException e){

            }
        }
    }
    public void playerHoverOut(MouseEvent mouseEvent){
        if(!selectedButton) {
            try {
                Pane button = (Pane) mouseEvent.getSource();
                button.setBackground(null);
            }
            catch (ClassCastException e){

            }
        }
    }
    public void playerSelection(MouseEvent mouseEvent) throws IOException {
        selectedButton = true;
        Pane button = (Pane) mouseEvent.getSource();
        if(selectedPane!=null) {
            selectedPane.setEffect(null);
        }
        selectedPane = button;
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);
        button.setEffect(colorAdjust);
        name = ((Label)button.getChildren().get(0)).getText();
    }
    public void startHoverIn(MouseEvent mouseEvent){
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/startPlankSelected.png"));
    }
    public void startHoverOut(MouseEvent mouseEvent){
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/startPlank.png"));
    }
    public void startClicked(MouseEvent mouseEvent) throws  IOException {
        if(selectedButton) {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            String source = "src/savedFiles/" + name + ".txt";
            String dest = "src/gameFiles/currentUser.txt";
            File user = new File(dest);
            user.createNewFile();
            List<String> lines = Files.readAllLines(Paths.get(source), StandardCharsets.UTF_8);
            Files.write(Paths.get(dest), lines, StandardCharsets.UTF_8);
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainYard.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    public void exitButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setList();
    }
}
