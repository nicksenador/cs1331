import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        viewGameButton.disarm();

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
        Stage stage = new Stage();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(chessGame.getEvent());
        arrayList.add(chessGame.getSite());
        arrayList.add(chessGame.getDate());
        arrayList.add(chessGame.getWhite());
        arrayList.add(chessGame.getBlack());
        arrayList.add(chessGame.getResult());
        arrayList.add("");
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

        Scene scene = new Scene(listView);
        stage.setScene(scene);
        stage.setTitle(chessGame.getEvent());
        stage.show();
    }
}