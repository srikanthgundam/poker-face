# Poker Face
Thie program reads input from a file and converts the specified hands into the name of the corresponding poker hand.

### The name of the hand will be one of:
    High card
    One pair
    Two pair
    Three of a kind
    Straight
    Flush
    Full house
    Four of a kind
    Straight flush
    Royal Flush

#### The full list of available poker hands is available here:
 http://en.wikipedia.org/wiki/List_of_poker_hands.

#### Technologies Used:
    Java 8
    Maven
    Junit

#### Build & Run the Program
  Clone the GIT project from https://github.com/srikanthgundam/poker-face.git

######  Build project by running: mvn clean install
######  Run the Project: java -jar target/poker-face-1.0-SNAPSHOT.jar <FilePath>
 
######  Example Pokerhands file is placed with in the package under src-test-resources path.
######  To run the program use below commands or you can provide new Pokerhands file path.
######  2. java -jar target/poker-face-1.0-SNAPSHOT.jar src\test\resources\PokerHands (Windows)
######  3. java -jar target/poker-face-1.0-SNAPSHOT.jar src/test/resources/PokerHands (Unix)
