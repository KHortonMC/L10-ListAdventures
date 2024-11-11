Welcome to List Adventures! A Space Shuttle Mini-Game

We've got the bare bones of a Space Shuttle supply collection game! Unfortunately, it's not very complete. To turn this dull game into an action packed adventure, we're going to need to follow a few steps.

PART I: (Steps 1-6) - models/GameLoop.java

The first issue we're running into, is that there's only one object getting spawned at a time! We're going to need to refactor that single GameObject debris into an ArrayList<> debris so we'll be able to have more than one of them at once!

Refactor all instances of this.debris into an ArrayList so there can be more than one debris on screen at a time! Follow the TODO: Steps as listed in ShuttleResupplyController.

PART II: (Steps 7-8) - controllers/ShuttleResupplyController.java

Update the UI. Now that we've got a decent simulation going, it's time to give the user some feedback on how they're doing. The first part, is to update the UI elements with data from the SpaceShuttle class.

Follow TODOs in ShuttleResupplyController for the pieces of information that need to be connected. (They've already been imported from the FXML for you!)

Hint: gameLoop has a getter for the space shuttle!

PART II: (Steps 9-12) - models/SpaceShuttle.java

Now that we have some cargo floating through space for pickup, we need to transfer any retrieved cargo into the Space Shuttle's inventory. For this part, follow the steps in SpaceShuttle, adding three new ArrayLists, one for medical supplies, one for food, and one for parts.

Follow the TODO comments to complete our space shuttle's cargo management system.

Once the ArrayLists have been filled with cargo, you'll need to iterate through the lists, adding the cargo contents to a results string to display at the end of the collection game.

