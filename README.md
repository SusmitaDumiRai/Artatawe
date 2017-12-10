# Artatawe

Software Engineering Team 02 - Swansea University.

Ebay-like system for auctioning artwork on a standalone device. Allows registration of new users, creating, bidding and viewing past/present auctions for registered users. 
***
### Understanding this project
To the software engineering group that will get this project next semester, all the best from SE02. 

#### Logging onto the system
Currently there are 6 default registered users. These usernames allows access to the system, case senstitive.
* username
* buygymmem
* uglybackpack
* yaytesting
* finzfinz
* lolfan

#### Creating an auction
When the user decides to create an auction, the file path for the system will record the absolute file path for the photo(s). This can **cause errors for other people** as they may not have the same file path. The database provided will only have relative file paths set up however this is a word of warning. The specification says that the system runs on a standalone device therefore we did not address this problem.

#### Database tables and their schema
##### All data in this project is handled by SQL queries. All data is stored in the database.
* **Artwork** - ArtworkID (PK), Title, Description, Photo, NameOfCreator, ReservedPrice, DateEntered, BidsAllowed, TypeOfArtwork, Width, Height, Depth, MainMaterial, ExtraPhoto
  - Title and photo are unique.
* **User** - Username (PK), FirstName, Surname, PhoneNumber, Address, PostCode, LastLogin, ProfileImage
* **Auction** - AuctionID (PK, FK - Artwork), Seller (FK - User), WinningBid (FK - Bid), NumOfBidsLeft, AuctionComp, HighestBid
  - AuctionComp: 0 = false, 1 = true. SQLite sadly does not support boolean.
* **Bid** - BidID (PK), AuctionID (FK - Auction), Buyer (FK - User), BidAmount, DateAndTime
* **FavouriteUser** - (Username1 (FK - User), Username2 (FK - User)) - PK
  - Username1 favourites Username2.
* **Watching** - (AuctionID (FK - Auction), Username (FK - User)) - PK
  - Username is watching auction.

#### Random facts
* Date and time is saved in this format: dd/MM/yyyy HH:mm:ss.
* All (except login) fxml files load in the same window.
* SQLite will create the database if it cannot find the one. However it will be empty.
* [Errors where it cannot find FXML Files - make sure they are in the target folder as well.](https://youtu.be/bU4ovkVMWjY) 
* If the bid price becomes to high, it will standarise it. Example 3.0E10 - 30,000,000,000

#### Extension
* A user can watch/remove watch a certain auction. This remains anonymous however users can see how many users are watching that auction.
* A user can also edit their profile image through "My Profile" page.
*** 
### Maven
This project uses maven. This allows easy distribution of frameworks as you do not have to manually download relavent dependencies (e.g. sqlite). You can add more frameworks in pom.xml, simply google [framework name] maven dependency. 

When you first load this project up please allow maven to download. IDE will do all this for you, it has already been tested across NetBeans, IntelliJ and Eclipse. 

*** 
### Dependencies and JDK
* [SQLite](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.21.0)
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
***
### Compiling
Instructions to compile this project in command line - video demo will be provided.
1. Extract the zipped source.
2. Add new environmental variable called **JAVA_HOME** which points to your JDK e.g. D:\User\Documents\jdk1.8.0_121
3. [Install and extract maven](http://maven.apache.org/download.cgi)
4. Add maven to PATH e.g. D:\User\Documents\apache-maven-3.5.2\bin
5. Perform verification test - ```mvn --version```
6. Compile and run in directory - ```mvn exec:java```

***
### Useful links
* [Database](https://sqliteonline.com/) - Open the database and you can see all its content + perform SQL queries.
* Gary's lecture notes from CS-250
* [SQLite tutorial](http://www.sqlitetutorial.net/)
***
### Snapshots
![Login](https://i.gyazo.com/d5350315f1b9726dab4579eec1b0ef33.png "Login page")

![Browse users](https://i.gyazo.com/12cc9542437988d0d31e93aadf292efa.png "Browse users")

![Create auction](https://i.gyazo.com/66b308861e6512250c59c303dae4a8d4.png "Create auction")

![Auction information](https://i.gyazo.com/9c392070d2a1900d9a98f12614c75529.png "Auction info")
















