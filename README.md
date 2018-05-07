# Prerequisites:
- Maven
- GIT
- JDK 8


# Steps to install and run the applicaiton on local machine:

- Clone the app with the following command :
```
git clone https://github.com/MoathIbrahem/expedia-task.git
```
- Build the project with the following command :
```
mvn clean package
```
- Start the application with the following commmand :
```
java -jar target/dependency/webapp-runner.jar --port 80 target/*.war
```
- Hit http://localhost to open the application

#

#### Heroku : [Heroku Link](https://expedia-task-moath-ibrahem.herokuapp.com)
#### Travis : [Travis Link](https://travis-ci.org/MoathIbrahem/expedia-task)
