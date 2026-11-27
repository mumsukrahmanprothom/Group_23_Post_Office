# Simulating_Operations_of_a_Post_Office

A simple JavaFX / FXML project for CSE213 Group 23, based on the User 7 (Post Office Manager) and User 8 (Postal Worker) goals in the supplied report.

## Files
- User 7 controllers and screens are grouped in `src/main/java/com/postoffice/controllers/user7/` and `src/main/resources/com/postoffice/fxml/user7/`.
- User 8 controllers and screens are grouped in `src/main/java/com/postoffice/controllers/user8/` and `src/main/resources/com/postoffice/fxml/user8/`.
- `src/main/resources/com/postoffice/fxml/main.fxml` is the role-selection screen.
- Each role has its own Goals menu under `src/main/resources/com/postoffice/fxml/common/`, available from its role screen and goal screens.
- `PostOfficeApp.java` provides a scene-switching utility.
- `Main.java` starts the application and handles scene navigation.

## Open in IntelliJ IDEA
1. Extract the ZIP.
2. Open the project folder containing `pom.xml` in IntelliJ IDEA.
3. Allow Maven to load dependencies (Java 21 required).
4. Run `Main`.
5. To edit a screen visually, open its `.fxml` file with Scene Builder.

The screens use JavaFX controls and have descriptive `fx:id` values. The table is an in-memory UI example; it does not save data to a database.
