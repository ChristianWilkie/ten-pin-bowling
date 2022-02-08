# Ten-pin Bowling Game
# Overview
CLI application implementation of a ten-pin bowling gameState.

## Problem Spec
Write a command line application that prompts its user for each individual roll in a one-player bowling gameState.
The application should inform its user of the gameState status after every input.

## Assumptions
1. Program will be running on a single PC where users take rolls entering their information (for instance a computer kiosk at a bowling alley)
2. Since the bowling alley would not want to spend a lot of money on each PC/kiosk,
   we can assume that the PC/kiosk would be a lower end, cheaper piece of hardware.
   As a simple reference a $35 Raspberry Pi 4 has 2 GB of RAM (ARM) or we could use a similarly size/price x86 computing platform.
   We will ignore embedded device options due to the cost/benefit ratio likely not to be worth it.
   We will also ignore cloud options (ex: kiosk connects to AWS to send/receive data from a bowling program hosted there) since it would
   probably be less reliable than a locally hosted option as it would add a dependency on network connectivity. Program could be adopted to
   a cloud environment if stakeholders felt they would benefit from it for the additional cost/complexity (ex: storing gameState history in a db or something)
3. Users will be playing in English - additional languages could be introduced later if this would be justified/useful based on talking with stakeholders.
4. Assume traditional scoring of ten-pin bowling (apparently there are different scoring mechanisms)

## Running the project

1. Use gradle to build it with `gradle jar`.
2. Run the jar with `java -jar ten-pin-bowling-1.0.0-SNAPSHOT.jar`.
3. Enter the number of pins for each roll as instructed by the program.
4. When you're done playing, type "exit" to exit the program.


## References
1. https://en.wikipedia.org/wiki/Ten-pin_bowling#Traditional_scoring
2. https://www.topendsports.com/sport/tenpin/scoring.htm
3. https://www.kidslearntobowl.com/how-to-keep-score/
4. Ten Pin Bowling scoring video: https://youtu.be/E2d8PizMe-8