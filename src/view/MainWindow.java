/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.GestionListaEnMemoria;
import model.Film;


/**
 *
 * @author idoia
 */
public class MainWindow extends Application {

    private final TableView<Film> table = new TableView<>();

    final HBox hb = new HBox();
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        
        ObservableList<Film> data = GestionListaEnMemoria.cargarDatos();
        
        stage.setTitle("Datuen Taula");
        stage.setWidth(450);
        stage.setHeight(550);
        final Label label = new Label("Films");
        label.setFont(new Font("Arial", 20));
        
        table.setEditable(true);
        
        TableColumn<Film, String> nameCol =
            new TableColumn<>("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
            new PropertyValueFactory<>("firstName"));
        nameCol.setCellFactory(TextFieldTableCell.<Film>forTableColumn());
        nameCol.setOnEditCommit(
            (TableColumn.CellEditEvent<Film, String> t) -> {
            ((Film) t.getTableView().getItems().get(
            t.getTablePosition().getRow())
            ).setFirstName(t.getNewValue());
            });
        
        TableColumn<Film, String> genreCol =
            new TableColumn<>("Genre");
        genreCol.setMinWidth(100);
        genreCol.setCellValueFactory(
            new PropertyValueFactory<>("lastName"));
        genreCol.setCellFactory(TextFieldTableCell.<Film>forTableColumn());
        genreCol.setOnEditCommit(
            (TableColumn.CellEditEvent<Film, String> t) -> {
            ((Film) t.getTableView().getItems().get(
            t.getTablePosition().getRow())
            ).setLastName(t.getNewValue());
            });
        
        TableColumn<Film, String> dirCol = new TableColumn<>("Director");
        dirCol.setMinWidth(200);
        dirCol.setCellValueFactory(
        new PropertyValueFactory<>("email"));
        dirCol.setCellFactory(TextFieldTableCell.<Film>forTableColumn());
        dirCol.setOnEditCommit(
            (TableColumn.CellEditEvent<Film, String> t) -> {
                ((Film) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setEmail(t.getNewValue());
            });
        table.setItems(data);
        table.getColumns().addAll(nameCol, genreCol, dirCol);
        final TextField addName = new TextField();
        addName.setPromptText("name");
        addName.setMaxWidth(nameCol.getPrefWidth());
        final TextField addGenre = new TextField();
        addGenre.setMaxWidth(genreCol.getPrefWidth());
        addGenre.setPromptText("genre");
        final TextField addDir = new TextField();
        addDir.setMaxWidth(dirCol.getPrefWidth());
        addDir.setPromptText("director");
       
        final Button addButton = new Button("Add");        
        addButton.setOnAction((ActionEvent e) -> {
            Film p = new Film(
                addName.getText(),
                addGenre.getText(),
                addDir.getText());
            data.add(p);
            
            addName.clear();
            addGenre.clear();
            addDir.clear();
        });
        
        final Button removeButton = new Button("Delete");        
        removeButton.setOnAction((ActionEvent e) -> {
            Film film = table.getSelectionModel().getSelectedItem();    
            data.remove(film);
        });
        
        hb.getChildren().addAll(addName, addGenre, addDir, addButton, removeButton);
        hb.setSpacing(3);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
