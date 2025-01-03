Welcome to Dungeon of Doom

The aim of this game is to pickup gold and exit the dungeon once you gain enough gold.

The commands that can be used are as follows:

MOVE X - This will move you in a specific direction, specified by either N for north, S for south, E for east and W for west, these are all replacing the X

PICKUP - This will pickup any gold on your current tile

LOOK - This will display the surrounding area around you

HELLO - This will display the required amount of Gold to exit

GOLD - This will display the amount of gold you have collected

QUIT - This will exit the game, if you are on an exit tile and have enough gold you will win otherwise you will lose


There is also a bot player that will be moving around the dungeon, it has multiple modes specified by integer inputs during setup.

0 = Random movement bot
1 = Player follower (This bot will look for the player and seek them out once they see the player)
9 = No bot

Any other inputs during setup will set the bot to type 9 (No bot)



To run the game you must open up a windows powershell and run the file using the following command replacing <filepath> with the filepath for your DungeonofDoom.jar file

java -jar <filepath>

For the game to run you must setup a folder in your C:\Users\<youruser> called DoDmaps, this will contain all the maps you wish to use.

When you run the game it will ask setup questions such as which map you would like to use and bot difficulty.

Maps are of the following format where # = a wall, . = a free space, G = gold and E = exit

name Maze of Mystery
win 1
##############################
#.#........#######...........#
#.####.#.#.............G.....#
#....#.#.#.#######...........#
#.##...#.#...................#
########.#####################
########....................E#
##############################


the first line of # specifies the player spawn radius, the player will randomly spawn within the length of the # at any level. 

The map does not need to be rectangular it can be of any shape, blank spaces are treated as walls


For this project I have implemented many elements of Object Oriented Program, I have seperated classes for the Map, Player and Bot along with a class for the Main file which implements all the game logic. The Bot class is extended from the Player due to many similar actions between the two classes.

