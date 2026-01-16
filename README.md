# Escape the Lab
https://youtu.be/y0nZYe-WSYo 

Escape the lab is a JavaFX-based educational "escape the room" game designed to test students' knowledge of science-related concepts taught during their college studies. This interactive game guids players through four labs, each requiring the application of physics and chemistry to solve puzzles through lab form and escape. Simply download the project, open it in your prefered IDE and run! Make sure all dependencies are installed. This will vary depending on the operating system used.

## Project Objectives
- Reinforce key scientific concepts in a fun, gamified environment.
- Simulate a mini-escape room challenge integrated with course material.

## Concept and Workflow Behind the Application Logic 
Each lab in the game introduces a scenerio grounded in real scientific principles. The game's architecture is mainly based on the MVC (Model-View-Controller) pattern:
    - Model: Represents Data structures like inventory items and substances.
    - View: responsible for displaying visual components and animations of each lab.
    - Controller: processes interactions and updates the game state.
    
The game logic flows as follows:
    1. The player interacts with GUI elements (buttons, sliders, textfields, draggable items).
    2. The controller interprets the interaction and modifies the model or transitions to another game state.
    3. The View or UI updates accordingly.

## Needs Addressed and Functionalities Developed
### Needs Addressed
- Students benefit from a review of important concepts through active learning.
- Offers an engaging platform to revisit college science topics.
- Encourages collaborative development among students

### Key Functionalities
- *Login system* with modifiable credentials.
- *Language support* for English and French.
- *Inventory system* to manage and use items in puzzles.
- *Scene transitions* between labs and interface states.
- *Physics and chemistry based puzzles*, reflecting actual course content.
- *Help hints*, dialogies, and feedback to guid the player.
- *Victory and loss conditions*, such as health depletion, lab success or death screen.

## Game Walkthrough
### Login
To login, input the username "user" and password "pass" to enter into the start screen. It's possible to change the default password in the parameters from the start screen.

### Start Screen
In the top left of the screen, there is a setting menu. On the right hand side of the screen, you can change the prefered language to french or english.

#### Settings
There is the choice to turn all sounds off my clicking on the submenu. You can also change your password using the second sub-menu. Change the username using the third sub-menu. Finally, you can quit the game.

### Labs
In each lab, you are presented with science-related problems. On the top of the right side is the life count, denoted by 3 hearts or less. Below these heart are the 6 allotted inventory spots. Finally, at the bottom, there is a help button that will give you a hint on what to do when you feel stuck.
To use an item, simply click on it and then click again on the area on which you would like to use the item.

### Lab 1: Circuit Lab
1. By clicking on the door, you can see that it is too heavy.
2. Click on the panel which will display an incomplete circuit, an led, and a little sticky note which gives you more information.
3. Go back to the main scene and click on the already broken glass to make the robot fall.
4. Collect the resistors from its head.
5. Apply the V = IR formula from electricity and magnetism classto select the correct resistor and light the LED to open the door.
6. Go back to the panel and put the correct resistor (160 ohm).
7. The led will light up and the door should open.

### Lab 2: Spring Lab
1. In the sofa, there are three springs.
2. On the shelves, there are two objects.
3. Light up the hidden part of the room by clicking on the lamp.
4. A strange entity appears. It will give you a third item.
5. Now, the lab can start. Click on the side table.
6. There is a resonance frequency ω written on the wall.
7. You must use the correct formula learnt in mechanics to use the right object mass and spring combination to oscillate (Correct spring: 200 N/m, correct item: 4kg)

### Lab 3: Flame Lab
1. Click on the right hand drawer and open the little doors.
2. Take the bunsen burner.
3. Go back and click on the left drawer. A little disk is peaking out the bottom.
4. Go back and click on the microscope. Add the lens to it.
5. Collect all chemicals, you should have four.
6. Go back to the right hand drawer and add the bunsen burner on top of it.
7. Pour each substance into the tubes but be careful, you only have three tubes and four chemicals...
8. Now, burn each substance, upon adding the tube to the flame, the flame will turn to a different color.
9. Click on the flame to collect it.
10. Repeat steps 8-9.
11. Go back and click on the door to add the flame to the allotted spot. (The correct flame is red, LiCl).

### Lab 4: Acid Neutralization Lab
1. Click onn the flower to see the context of the lab.
2. Use the slider to input the pH of the HCl on the floor.
3. Press on the eyes of the flower to enter your answer. If the right answer is inputed then a substance of NaOH will go in the player's inventory.
4. Press the go back arrow when done.
5. Press on the substances in the tree to collect them in the inventory.
6. Press on the house to zoom into it.
7. Press on the substances in the house to collect them in your inventory and go back.
8. Drag and drop the desired substances onto the acid floor to neutralize it (correct substances: NaOH and CaCO3).
9. Press the door to escape the lab.

## Challenges Met:
### Team collaboration
- Synchronizing contributions through Git was challenging when multiple members were pushing simultaneously.
- Compromise was often necessary to align coding styles, llogic structures, and design consistency across labs.
- We overcame this by daily merge reviews and communication through task boards and messaging apps.

### Technical Challenges
- Implementing consistent dialogue systems and death/respawn mechanisms across labs.
- Handling JavaFX animation, particularly for simulating spring oscillations in Lab 2.
- Ensuring modular logic that kept UI and game logic separate (adhering to MVC).

## Lessons Learned
- Effective team communication is essential, especially in tim-sensitive and shared projects.
- Version control systems like Git require discipline but are invaluable when used correctly.
- Animations and real-time interactions in JavaFX can be powerful yet tricky without careful planning.
- Building reusable components (e.g., inventory, dialogue system, help system) saves time and keeps code dry.
- Consistent architecture and naming conventions make integration smoother across different modules developed by team members.
    

