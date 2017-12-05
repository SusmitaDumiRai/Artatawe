package co.uk.artatawe.gui;

import co.uk.artatawe.sample.Main;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class to test browsing auction.
 * Probably redundant
 *
 * @author 908928
 */
public class BrowseAuctions extends Application {



    //OUTDATED. WARNING WHEN USING.
    @FXML
    private TilePane artworkTilePane;

    @FXML
    private ScrollPane artworkScrollPane;

    Stage stage;
	
    @Override
    public void start(Stage primaryStage) throws Exception {
          stage = primaryStage;
         ScrollPane root = artworkScrollPane;
         TilePane tile = artworkTilePane;
       artworkScrollPane.setStyle("-fx-background-color: DAE6F3;");
        artworkTilePane.setPadding(new Insets(15, 15, 15, 15));
        artworkTilePane.setHgap(15);

          String path = "src/main/java/co/uk/artatawe/artworkpictures";

          File folder = new File(path);
          File[] listOfFiles = folder.listFiles();

          for (final File file : listOfFiles) {
              ImageView imageView;
              imageView = createImageView(file);
              artworkTilePane.getChildren().addAll(imageView);
          }


        artworkScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        artworkScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        artworkScrollPane.setFitToWidth(true);
        artworkScrollPane.setContent(artworkTilePane);

          primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
          primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
          Scene scene = new Scene(artworkScrollPane);
          primaryStage.setScene(scene);
          primaryStage.show();

      }

    private ImageView createImageView(final File imageFile) {
        // DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
        // The last two arguments are: preserveRatio, and use smooth (slower)
        // resizing

        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if (mouseEvent.getClickCount() == 2) {
                            try {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: BLACK");
                                imageView.setFitHeight(stage.getHeight() - 10);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: BLACK");
                                Stage newStage = new Stage();
                                newStage.setWidth(stage.getWidth());
                                newStage.setHeight(stage.getHeight());
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane, Color.BLACK);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;

    }


    public static void main(String[] args) {
        launch(args);

    }

}
