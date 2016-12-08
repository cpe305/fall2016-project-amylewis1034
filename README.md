# fall2016-project-amylewis1034

### Dad's Arcade
This project, Dad's Arcade, was developed by Amy Lewis at California Polytechnic State University, San Luis Obispo.

### Background
Dad's Arcade is a Java application including the games Space Invaders, Snake, and Breakout, a high score page, and options to easily navigate between them. It was created as a gift for my dad.

### Key Features
	1. Starting Menu
	2. Space Invaders
	3. Snake
	4. Breakout
	5. Help Menu
	6. High scores for all three games
	7. End Game menus to connect games back to arcade

### System Architecture
The system architecture is a 3 Tier Architecture:

	1. View - everything the user can see (menus, interactive games)
	2. Business Logic - coordinates the application, runs the game algorithms/logic 
	3. Data - database where high scores are stored (file system)

### Design Patterns

#### Observer Pattern
I'm most proud of how I implemented the Observer Pattern. The observer pattern defines a one-to-many dependency between objects so that one object changes state, all its dependents are notified and updated automatically. I used this to have my different games and menus be observers. I implemented the observer pattern before we learned about the State Pattern. The state patterns allows an object to alter its behavior when its internal state changes. The object will appear to change its class. Because I use the observer pattern to change the state of my program, I should be using the state pattern, but the observer pattern works as well.

#### Singleton Pattern
The Singleton Pattern was implemented in making the high score aspect of the arcade. The pattern ensures that there is only one file containing the high scores for each game. It also ensures that the file does not get overwritten and the scores do not get lost.

### Key Take-Aways
- Design patterns are helpful and worth the time to implement because they will save you time in the long run
- Implement a solid architecture and design patterns early on, because it is extremely difficult to go back and add them in
