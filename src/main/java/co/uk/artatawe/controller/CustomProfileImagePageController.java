package co.uk.artatawe.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.scene.paint.Color;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.main.User;
import co.uk.artatawe.profileImage.Circle;
import co.uk.artatawe.profileImage.CustomProfileImage;
import co.uk.artatawe.profileImage.ParticleTrace;
import co.uk.artatawe.profileImage.SavedProfileImage;
import co.uk.artatawe.profileImage.StraightLine;

/**
 * The controller for the CustomProfieImagePage.fxml file, which creates a gui
 * that allows the custom drawing of a profile image.
 *
 * @author Adam Taylor (905448)
 * @author 908928 - Susmita
 * @version 1.0
 */
public class CustomProfileImagePageController implements Initializable {
    //The position that mouseX and Mouse Y are reset to.
    public static final double MOUSE_RESET_POSITION = -1;
    private double mouseX; //The last recorded x position of the mouse.
    private double mouseY; //The last recorded y position of the mouse.
    private User user; //The user the custom profile image is being created for.
    //The customProfileImage that is created.
    private CustomProfileImage customProfileImage;
    //The current particle trace being drawn.
    private ParticleTrace currentParticleTrace;
    //The pane the custom profile image page is attached to.   
    private Pane rootPane;
    private boolean registerPrevController; //true if prev controller was register, else false (from profile.)
    private String customDrawingFileLocation = ""; //location of the custom drawing created.

    //FXML generated attributes.

    @FXML
    private BorderPane root; //The border pane used to layout the page.

    @FXML
    private Canvas canvas; //The canvas the custom profile image is drawn on.

    @FXML
    private VBox options; //The v box used to layout the drawing options.

    @FXML
    private Label optionsTitle; //The title at the top of the options v box.

    //The toggle group that binds the options radio buttons.
    @FXML
    private ToggleGroup optionsToggleGroup;

    //The radio button that allows the user to draw straight lines.
    @FXML
    private RadioButton selectStraightLine;

    //The radio button that allows the user to draw particle traces.
    @FXML
    private RadioButton selectParticleTrace;

    //The title at the top of the page (border pane top box).
    @FXML
    private Label title;

    //The button which allows a user to return to the profile page.
    @FXML
    private Button back;

    /* The button which allows a user to save the image as their 
     * new profile image and return to the profile page.
     */
    @FXML
    private Button saveImage;

    /**
     * Creates custom profile image controller.
     *
     * @param rootPane               the pane attached.
     * @param registerPrevController true if controller was created from register controller.
     */
    public CustomProfileImagePageController(Pane rootPane, boolean registerPrevController) {
        this.rootPane = rootPane;
        this.registerPrevController = registerPrevController;
    }

    /**
     * Creates a custom profile image controller.
     *
     * @param user     The user the custom profile image is being created for.
     * @param rootPane The pane the custom profile image page is
     *                 attached to.
     */

    public CustomProfileImagePageController(User user, Pane rootPane, Boolean registerPrevController) {
        this.user = user;
        this.rootPane = rootPane;
        this.registerPrevController = registerPrevController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Reset the previous mouse position.
        mouseX = MOUSE_RESET_POSITION;
        mouseY = MOUSE_RESET_POSITION;
        customProfileImage = new CustomProfileImage(canvas.getHeight(),
                0, 0);
    }

    /**
     * Starts drawing if the mouse is pressed over the canvas,
     * if appropriate.
     *
     * @param event event.
     */
    @FXML
    public void onCanvasMousePressed(MouseEvent event) {
                /* If straight line radio button is selected,
                 * start drawing straight line.
		 */
        if (optionsToggleGroup.getSelectedToggle()
                == selectStraightLine) {
            drawStraightLine(event);
        } else if (optionsToggleGroup.getSelectedToggle()
                == selectParticleTrace) {
                        /* If particle trace radio buttion is selected,
                         * start drawing particle trace.
			 */
            canvas.getGraphicsContext2D().beginPath();
            canvas.getGraphicsContext2D().moveTo(event.getX(),
                    event.getY());
            Color colour = Color.BLACK;
            currentParticleTrace = new ParticleTrace(mouseX,
                    mouseY, colour);
            drawParticleTrace(event);
        }
    }

    /**
     * Draw when the mouse is dragged over the canvas, if appropriate.
     *
     * @param event event.
     */
    @FXML
    public void onCanvasMouseDragged(MouseEvent event) {
    	/* if particle trace radio button is selected,
    	* keep drawing particle trace.
	*/
        if (optionsToggleGroup.getSelectedToggle() == selectParticleTrace) {
            canvas.getGraphicsContext2D().moveTo(event.getX(), event.getY());
            drawParticleTrace(event);
        }
    }

    /**
     * Stops drawing when mouse is released, if appropriate.
     *
     * @param event event.
     */
    @FXML
    public void onCanvasMouseReleased(MouseEvent event) {
        //If straight line radio buttion is selected, draw a straight line.
        if (optionsToggleGroup.getSelectedToggle() == selectStraightLine) {
            drawStraightLine(event);
        } else if (optionsToggleGroup.getSelectedToggle()
                == selectParticleTrace) {
    		/* Else if particle trace radio button is selected, 
    		 * finish drawing particle trace.
    		 */
            canvas.getGraphicsContext2D().moveTo(event.getX(),
                    event.getY());
            customProfileImage.addComponent(currentParticleTrace);
            drawParticleTrace(event);
        }
    }

    /**
     * Saves the custom profile image when the "Save Image" button is pressed.
     *
     * @param event event.
     * @throws IOException Thrown if ProfilePage.fxml can't be loaded.
     */
    @FXML
    public void onSaveImageAction(ActionEvent event) throws IOException {

        //Get the absolute path of the project.
        File directory = new File("./");
        String path = directory.getAbsolutePath();
        String fileName;

        //Checks whether it came from profile or register prev controller.
        if (!registerPrevController) {

    	/* The filename of the image will be the users username followed by the
    	 * time it was created in milliseconds as a png file.
    	 */
            fileName = user.getUserName() + System.currentTimeMillis()
                    + ".png";

            convertToFile(fileName, path);

            //Update system and database with new profile image.
            user.setProfileImage(createSavedProfileImage(fileName));
            UserDatabaseManager u = new UserDatabaseManager();
            u.updateProfileImage(user,
                    "/co/uk/artatawe/profileImage/SavedProfileImages/"
                            + fileName);

        } else {
            //Create file name.
            fileName = System.currentTimeMillis() + ".png";

            convertToFile(fileName, path);

            createSavedProfileImage(fileName);

            //Set the relative path.
            customDrawingFileLocation = "/co/uk/artatawe/profileImage/SavedProfileImages/"
                    + fileName;


        }
        //Return to previous page.
        onBackAction(event);
    }

    /**
     * Convert custom profile image to file.
     *
     * @param path location of directory.
     */
    public void convertToFile(String fileName, String path) {

    	//Create path for File class as absolute path to project

        path = path.substring(0, path.length() - 2)
                + "/target/classes/co/uk/artatawe/profileImage/SavedProfileImages/"
                + fileName;

        //Create the file that will be saved.
        File file = new File(path);

        //If the file isn't null, try to save it.
        if (!(file == null)) {
            try {
                /* Convert a snapshot of the canvas to a buffered image
                 * and the write it to file.
                 */
                BufferedImage bImage = SwingFXUtils.fromFXImage(
                        canvas.snapshot(null, null), null);
                ImageIO.write(bImage, "png", file);
            } catch (Exception e) {
                /* If an exception is cause, print the error message
                 * on the console.
                 */
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Creates file relative to the project.
     * @param fileName name of file.
     * @return the profile image.
     */
    public SavedProfileImage createSavedProfileImage(String fileName) {
        //Create a new Saved Profile Image.
        return new SavedProfileImage(
                "co/uk/artatawe/profileImage/SavedProfileImages/"
                        + fileName);
    }

    /**
     * Returns to the controller page when the "Back" button is pressed.
     *
     * @param event event.
     * @throws IOException Thrown if ProfilePage.fxml can't be loaded.
     */
    @FXML
    public void onBackAction(ActionEvent event) throws IOException {
        //Returns to profile page.
        if (!registerPrevController) {
            ProfilePageController profilePageController = new ProfilePageController();
            profilePageController.setUsername(user.getUserName());
            profilePageController.setRootPane(rootPane);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/ProfilePage.fxml"));

            //Sets the controller manually.
            fxmlLoader.setController(profilePageController);
            //Puts the custom profile image page scene on the root pane.
            rootPane.getChildren().clear(); //clears the old scene
            rootPane.getChildren().add(fxmlLoader.load());
        //Return to register page.
        } else {
            RegisterController registerController = new RegisterController();
            //If user has created an image, get the file path.
            if (!customDrawingFileLocation.equals("")) {
                registerController.setAvatarImagePath(customDrawingFileLocation);
            }

            //Load up register page with the new saved profile image.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/RegisterUser.fxml"));
            fxmlLoader.setController(registerController);
            rootPane.getChildren().clear(); //clear old scene.
            rootPane.getChildren().add(fxmlLoader.load());
        }
    }

    /**
     * Draws a straight line.
     *
     * @param event event.
     */
    public void drawStraightLine(MouseEvent event) {
		/* If the mouses previous position was the reset position, set 
		 * the previous position to the current position.
		 */
        if ((mouseX == MOUSE_RESET_POSITION)
                && (mouseY == MOUSE_RESET_POSITION)) {
            mouseX = event.getX();
            mouseY = event.getY();
        } else {
			/* Else draw a straight line between the 
			 * previous position and current position of
			 * the mouse.
			 */
            Color lineColour = Color.BLACK;
            StraightLine s = new StraightLine(mouseX, mouseY,
                    event.getX(), event.getY(), lineColour);
            s.displayComponent(canvas);
            customProfileImage.addComponent(s);

            //Reset the previous mouse position.
            mouseX = MOUSE_RESET_POSITION;
            mouseY = MOUSE_RESET_POSITION;
        }
    }

    /**
     * Draws a new circle on the particle trace.
     *
     * @param event event.
     */
    public void drawParticleTrace(MouseEvent event) {
        //Creates a black circle at the current position of the mouse.
        Color lineColour = Color.BLACK;
        Circle circle = new Circle(event.getX(), event.getY(),
                lineColour, 5);
		
		/* Adds the circle to the particle trace and draws 
		 * it on the canvas.
		 */
        currentParticleTrace.addToTrace(circle);
        circle.displayComponent(canvas);
    }
}

