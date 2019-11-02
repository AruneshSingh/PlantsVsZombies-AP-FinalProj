import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;


public class mainYardController {
    private Image clickedAndDragged;
    public void dragEnteredPane(MouseDragEvent mouseEvent){
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        if(paneElement.getImage() == null && clickedAndDragged != null){
            paneElement.setImage(clickedAndDragged);
            System.out.println("ifEnteredPassed");
        }
        System.out.println("RanEntered");
    }

    public void dragExitPane(MouseDragEvent mouseEvent){
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        if(paneElement.getImage() != null && clickedAndDragged != null){
            paneElement.setImage(null);
            System.out.println("ifExitPassed");
        }
        System.out.println("RanExit");
    }
    public void mouseClicked(MouseEvent mouseEvent){
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        switch (paneElement.getId()){
            case "sunflower":
                clickedAndDragged = (new Image("images/plants/sunflowerPlant.png"));
                break;
            case "peashooter":
                clickedAndDragged = (new Image("images/plants/peashooterPlant.png"));
                break;
            case "snowPeaShooter":
                clickedAndDragged = (new Image("images/plants/snowPeaPlant.png"));
                break;
            case "walnut":
                clickedAndDragged = (new Image("images/plants/walnutPlant.png"));
                break;
            case "cherrybomb":
                clickedAndDragged = (new Image("images/plants/cherryBombPlant.png"));
                break;

        }
        System.out.println("Clicked");
    }
}

