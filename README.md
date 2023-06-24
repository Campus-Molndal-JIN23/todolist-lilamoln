[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/MYVtI0hB)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=11359518)
# TDDProject

## Description

This project aims to build a simple TODO-app with support for multiple users. It can be used either locally with SQLite or MongoDB or with MongoDB atlas.
I've learned how to use GitHub CI, and gotten more practice with TDD, SQL, MongoDB, Junit5 and Mockito.

## Installation

Open in IDE, for example IntelliJ IDEA and run Main.java. Or build with Maven and run the jar-file.
See pom.xml for dependencies.

## Usage

The app is used through the command line. The user can create a new user, create a new todo, list all todos, mark a todo as done, delete a todo.
For MongoDB Atlas create a mongo.config file in root of project and insert:
```
cluster=YourCluster
connectionString=mongodb+srv://YourUrl
```
You can get your connectionstring from (MongoDB)(https://www.mongodb.com/), alternatively install MongoDB locally and use localhost.

It will fallback to localhost and standard port if no connectionstring is given.

## Credits

Third party resources that helped me complete this project:
* [junit jupiter 5](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter/)
* [mockito-core](https://mvnrepository.com/artifact/org.mockito/mockito-core/)
* [sqlite-jdbc](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/)
* [mongodb-driver-sync](https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync/)
* [sqlite-jdbc](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/)

Online resources:
* [mongodb documentation](https://www.mongodb.com/docs/) 
* [ChatGPT](https://chat.openai.com/) for troubleshooting and inspiration
* [Co-Pilot](https://copilot.github.com/) primarily for test-cases
## License

MIT License

Copyright (c) 2023 Åke Kristoffer Larsson

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Tests

Run tests with Maven or in IDE.
