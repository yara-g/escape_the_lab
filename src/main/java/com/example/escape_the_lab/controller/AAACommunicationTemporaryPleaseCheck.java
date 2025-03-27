package com.example.escape_the_lab.controller;

import javafx.scene.control.Button;

import java.util.List;

public class AAACommunicationTemporaryPleaseCheck {
    // Olivia: Ty Yara <3 <3 <3
    //// How my Item Inventory system work:
    // Add Items in your class. Add an Item called placeHolder (copy this:)
    // Item placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");
    // add a private Item chosenItem;
    // Initialize your scene with chosenItem = placeHolder;
    // Now, set up mouse click actions for the imageviews on your scene, for example,
    // An action for a spring that could be picked up will include at least spring.setVisible(false);
    // And inventory.addItem(springTool).
    // Set an action for your item: exampleItem.getImageview().setOnMouseClicked(e -> {
    // chosenItem = exampleItem;})
    // Add this method, and add more else if statements.
    // private void useItem(MouseEvent event) {
    //        if (chosenItem != null && chosenItem.equals(exampleItem)) {
    //            exampleItem.setMouseTransparent(false);
    //            exampleItem.setVisible(true);
    //            chosenItem = placeHolder;
    //            inventory.removeItem(exampleItem);
    //            overlay.updateInventory();
    //        }
    //    }
    // Then set on action for where you want to trigger (use) the item.
    // examplePlace.setOnMouseClicked(e -> {
    //            useItem(e);
    //        });





    // Olivia:
    //// What I understand of inventory system:
    // I draw every single possible item that could be stored in inventory, then add each in my class as Item.
    // In my own lab, get my own Inventory, and add the item when the user interacts with the game correctly, ex: click something collectable.
    // Whenever I do that, or use an item, update inventory with my overlay.
    //// Yara: Yes that's exactly it.


    // Yara:
    //// Inventory/Lives Overlay
    // Make sure to add the inventory image to all of your scenes
    // You must update the overlay via overlay.updateInventory() whenever you add/remove an item
    // Yes this is stupid but guess what, i cant code.


    // Olivia:
    // I added gameFail.png and gameFailF.png for when the user dies. Whoever do the lives count and death
    // put a stackpane including gameFail.png and goBack.png for English, and a stackpane gameFailF.png
    // and retourner.png for French, OR tell me where to do so I'll add it.

    // The imgView for going back from a zoomed scene to your main room is called back.png, just assign
    // setOnMouseClicked to the imgView if you wish, OR again ask me to set all the visuals after you
    // finish coding.


    //// How does the language system work?
    // IN YOUR OWN LAB, CREATE A GAME CONTROLLER OR COPY THESE 2 LINES.
    // GameController controller = new GameController();
    // boolean languageSystem = controller.language;

    // DEPENDING ON THE BOOLEAN, USE LISTS OF DIFFERENT LANGUAGE IMAGES.
    // monologues.addAll(List.of(img1, img2, img3, img4));
    // monologuesF.addAll(List.of(img1F, img2F, img3F, img4F));
    // List<ImageView> monologuesLanguageSystem = new ArrayList<>();

    // if (l) {
    // monologuesLanguageSystem.clear();
    // monologuesLanguageSystem = monologues;
    // } else if (!l) {
    // monologuesLanguageSystem.clear();
    // monologuesLanguageSystem = monologuesF;
    // }

    //// Test it out with a button:
    //Button b = new Button();
    //    stackPane.getChildren().add(b);
    //    b.setOnAction(e -> {
    //    stackPane.getChildren().add(monologuesLanguageSystem.get(0));
    //});
}
