# Product Scraper
This application has the objective to retrieve all products from Kolonial.no website.
###Initializing the application
Before start with the process be sure to be inside the Project folder.

Build the Spring Boot Project with Maven

    mvn install

Then run Spring Boot app with java -jar command

    java -jar target/product.scraper-0.0.1-SNAPSHOT.jar

Or run Spring Boot app with Maven

    mvn spring-boot:run

#### After application initialize
After Spring Boot start all processes the scraper will start.
It can be checked seeing the log with the message:

    [   scheduling-1] c.k.product.scraper.service.Fetcher      : Product Scraper started.

After the scrap finish it will log the message:

    [   scheduling-1] c.k.product.scraper.service.Fetcher      : Product Scraper started.

All data retrieved from the website is saved in the database.

The scheduler for the scrapper is configure to run from 4 to 4 hours.

#### Accessing the data
The data saved in the database can be accessed in the Browser.

To check it put the url

    http://localhost:8080/products
    
