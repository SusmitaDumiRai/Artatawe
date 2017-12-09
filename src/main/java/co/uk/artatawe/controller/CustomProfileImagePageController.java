package co.uk.artatawe.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import co.uk.artatawe.main.Main;
import co.uk.artatawe.main.User;
import co.uk.artatawe.profileImage.Circle;
import co.uk.artatawe.profileImage.CustomProfileImage;
import co.uk.artatawe.profileImage.StraightLine;

public class CustomProfileImagePageController  implements Initializable{
    private double mouseX;
    private double mouseY;

    @FXML
    private BorderPane root;

    @FXML
    private Canvas canvas;

    @FXML
    private VBox options;

    @FXML
    private Label optionsTitle;
    
    @FXML
    private ToggleGroup optionsToggleGroup;
    
    @FXML
    private RadioButton selectStraightLine;

    @FXML
    private RadioButton selectParticleTrace;
    
    @FXML
    private Label title;

    @FXML
    private Button back;

    @FXML
    private Button saveImage;
    
    private static Stage primaryStage;
    
    private User user;
    
    private CustomProfileImage customProfileImage;
	
	public CustomProfileImagePageController(User user) {
		this.user = user;
		customProfileImage = new CustomProfileImage(0, 0, 0);
	}

	@Override
    public void initialize(URL location, ResourceBundle resources) {
		mouseX = -1;
		mouseY = -1;
    }
	
	@FXML
	public void onCanvasMousePressed(MouseEvent event) {
		if (optionsToggleGroup.getSelectedToggle() == selectStraightLine) {
			drawStraightLine(event);
		} else if (optionsToggleGroup.getSelectedToggle() == selectParticleTrace) {
			canvas.getGraphicsContext2D().beginPath();
			canvas.getGraphicsContext2D().moveTo(event.getX(), event.getY());
			drawParticleTrace(event);
		}
	} 
	
    @FXML
    public void onCanvasMouseDragged(MouseEvent event) {
	    	if (optionsToggleGroup.getSelectedToggle() == selectParticleTrace) {
	    		canvas.getGraphicsContext2D().moveTo(event.getX(), event.getY());
	    		drawParticleTrace(event);	
			}
    }
    
    @FXML
    public void onCanvasMouseReleased(MouseEvent event) {
    	if (optionsToggleGroup.getSelectedToggle() == selectStraightLine) {
			drawStraightLine(event);
    	}
    }
    

    @FXML
    public void onSaveImageAction(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	
    	fileChooser.getExtensionFilters().addAll(
    		new ExtensionFilter("PNG Files", "*.png"),
    		new ExtensionFilter("All Files", "*.*"));
    	
    	File directory = new File("./");    	
    	
    	fileChooser.setInitialDirectory(new File(directory.getAbsolutePath() 
    			+ "/target/classes/co/uk/artatawe/profileImage/SavedProfileImages"));
    	
    	File file = fileChooser.showSaveDialog(getPrimaryStage());
    	
    	if (!(file == null)) {
    		try {
    			BufferedImage bImage = SwingFXUtils.fromFXImage(canvas.snapshot(null, null), null);
    			ImageIO.write(bImage, "png", file);
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    		}
    		
    	}
    	
    	
    }
	
	public void drawStraightLine(MouseEvent event) {
		if ((mouseX == -1) && (mouseY == -1)) {
			mouseX = event.getX();
			mouseY = event.getY();
		} else {
			Color lineColour = Color.BLACK;
			StraightLine s = new StraightLine(mouseX, mouseY, event.getX(), event.getY(), lineColour);
			s.displayComponent(canvas);
			mouseX = -1;
			mouseY = -1;
		}
	}
	
	public void drawParticleTrace(MouseEvent event) {
		Color lineColour = Color.BLACK;
		Circle c = new Circle(event.getX(), event.getY(), lineColour, 5);
		c.displayComponent(canvas);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		CustomProfileImagePageController.primaryStage = primaryStage;
	}
}

