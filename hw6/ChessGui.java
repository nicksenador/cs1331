import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The purpose of this class is to create a JavaFX GUI for dispalying chess
 * games populated by the ChessDb clas.
 *
 * @author nsenador
 * @version 1
 */
public class ChessGui extends Application {

    @Override
    public void start(Stage stage) {
        ChessDb chessDb = new ChessDb();
        TableView<ChessGame> table = new TableView<>();
        ObservableList<ChessGame> chessGames = FXCollections
            .observableList(chessDb.getGames());
        table.setItems(chessGames);

        TableColumn<ChessGame, String> eventColumn = new TableColumn("Event");
        TableColumn<ChessGame, String> siteColumn = new TableColumn("Site");
        TableColumn<ChessGame, String> dateColumn = new TableColumn("Date");
        TableColumn<ChessGame, String> whiteColumn = new TableColumn("White");
        TableColumn<ChessGame, String> blackColumn = new TableColumn("Black");
        TableColumn<ChessGame, String> resultColumn = new TableColumn("Result");

        eventColumn.setCellValueFactory(new PropertyValueFactory("event"));
        siteColumn.setCellValueFactory(new PropertyValueFactory("site"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        whiteColumn.setCellValueFactory(new PropertyValueFactory("white"));
        blackColumn.setCellValueFactory(new PropertyValueFactory("black"));
        resultColumn.setCellValueFactory(new PropertyValueFactory("result"));

        table.getColumns().setAll(eventColumn, siteColumn, dateColumn,
            whiteColumn, blackColumn, resultColumn);

        Button viewGameButton = new Button("View Game");
        viewGameButton.setOnAction((event) -> {
                ChessGame chessGame = table.getSelectionModel()
                    .getSelectedItem();
                if (chessGame != null) {
                    createChessGameDialogBox(chessGame);
                }
            });
        viewGameButton.disableProperty()
            .bind(Bindings.isNull(table.getSelectionModel()
            .selectedItemProperty()));

        Button dismissButton = new Button("Dismiss");
        dismissButton.setOnAction((event) -> System.exit(0));

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(viewGameButton, dismissButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, buttonHBox);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Chess DB GUI");
        stage.show();
    }

    private void createChessGameDialogBox(ChessGame chessGame) {
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 1;
        try {
            while (true) {
                arrayList.add(chessGame.getMove(i));
                i++;
            }
        } catch (Exception e) {
            i = 0;
        }
        ListView<String> listView = new ListView<>();
        listView.setItems(FXCollections.observableList(arrayList));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(chessGame.getEvent());
        alert.setHeaderText(String.format("%s%n%s%n%s%n%s%n%s",
            chessGame.getSite(), chessGame.getDate(), chessGame.getWhite(),
            chessGame.getBlack(), chessGame.getResult()));
        alert.setGraphic(listView);
        alert.showAndWait();
    }
}