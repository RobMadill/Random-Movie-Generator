package randmoviegen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Random;

public class randMovieController {
	private ObservableList<ObservableList> movieList = FXCollections.observableArrayList();
	@FXML
	private VBox top;
	@FXML
	private Label welcomeLbl, optionLbl;
	@FXML
	private Label randChoice;
	@FXML
	private TextField userIn;
	@FXML
	private ListView<String> displayUserIn;
	@FXML
	private Button enter, remove, random;

	@FXML
	public void initialize() {
		userIn.requestFocus();
		if (displayUserIn.getItems().isEmpty()) {
			remove.setDisable(true);
		}
		enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<String> tempList = FXCollections.observableArrayList(userIn.getText());
				movieList.add(tempList);
				for (String i : tempList) {
					displayUserIn.getItems().add(i);
				}
				if (!displayUserIn.getItems().isEmpty()) {
					remove.setDisable(false);
				}
				userIn.setText("");
				userIn.requestFocus();
			}
		});
		random.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Random r = new Random();
				int randomIndex = r.nextInt(movieList.size());
				randChoice.setText(movieList.get(randomIndex).toString().replace("[", "").replace("]", ""));
			}
		});
		remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int userSelectionRemove = displayUserIn.getSelectionModel().getSelectedIndex();
				displayUserIn.getItems().remove(userSelectionRemove);
				movieList.remove(userSelectionRemove);
			}
		});
	}
}