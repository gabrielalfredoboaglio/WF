<div align="right">
  
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/) [![Version](https://badge.fury.io/gh/tterb%2FHyde.svg)](https://badge.fury.io/gh/tterb%2FHyde)  [![GitHub pull requests](https://img.shields.io/github/issues-pr/cdnjs/cdnjs.svg?style=flat)]()  [![PR's Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat)](http://makeapullrequest.com)  [![GitHub contributors](https://img.shields.io/github/contributors/cdnjs/cdnjs.svg?style=flat)]()

</div>
<div align="center">
<br>
<img align="center" src="https://i.ibb.co/yV7xfLH/index.jpg">
<br>
<h3>A lot of gyms, whether they're big or small, all seem to have the same problem: they can't give their trainers and clients a system to help them create workout routines and track their progress. But that's where WorldFit comes in! <br><br> A complete solution that can help any gym improve their service to their clients in an easy and convenient way.</h3>

<p>WorldFit is an innovative software platform designed specifically for gyms and fitness centers. Its primary function is to provide a comprehensive system for creating and tracking workout routines for gym members, but it does much more than that. With WorldFit, you can keep track of all your clients' biometric data, including their weight, height, body mass index, and other essential information. This information is used to create personalized workout routines that are tailored to each individual's needs and fitness level.
<br><br>
With WorldFit, gym trainers can easily create and manage workout routines for their clients, ensuring that they are doing the right exercises in the correct order, and tracking their progress along the way. This helps clients stay motivated, as they can see their progress and celebrate their successes. Additionally, WorldFit makes it easy to communicate with clients, sending them reminders about their workouts, and providing them with the tools they need to stay on track.
<br><br>
In conclusion, WorldFit is a must-have solution for any gym looking to improve its service and provide its clients with a personalized, high-quality fitness experience. With its advanced features and intuitive interface, WorldFit can help you create a workout routine that is tailored to your clients' unique needs, track their progress, and ultimately help them achieve their fitness goals.
</p
<br>
<p align="center" ><img width="100px" align="center"src="https://media.tenor.com/xAiM7ejIt3wAAAAC/gym-workout.gif"> <img width="100px" align="center"src="https://media.tenor.com/nm8TTgQyVFwAAAAM/workout-pull.gif"></p> 
<p align="center" ></p>

<hr>
<br><br>

</div>
<div align="center">
<img style="width:700px;" src="https://i.ibb.co/t8nBk50/1.png">
<img style="width:700px;"  src="https://i.ibb.co/kc8bDRv/Screen-Shot-2023-04-21-at-03-41-10.png">
<br><br>
<img style="width:350px;" src="https://i.ibb.co/V21knYK/Screen-Shot-2023-04-21-at-03-34-22.png">
<img style="width:350px;" src="https://i.ibb.co/VxhN0Z2/Screen-Shot-2023-04-21-at-03-38-00.png">

<h3 align="center"><b>You can check out our webpage here:</b></h3>
<h3 align="center"><a href="https://worldfit.site" target="_blank" rel="noopener noreferrer"> :woman_cartwheeling: World Fit :weight_lifting:</a></h3>
<p  align="center"><a href="https://drive.google.com/file/d/1ZkK3e7O97BbAfoZLvHUCO_4H1VGIrdq2/view" target="_blank" rel="noopener noreferrer"> <img src="https://img.shields.io/badge/Video Preview%20-%23FF0000.svg?&style=for-the-badge&logo=YouTube&logoColor=white"/></a></p>
</div>
<hr>
<br><br>

## ✔ Run the proyect 📋

1 - Install [Docker](https://https://docs.docker.com/get-docker/) and [Docker-Compose](https://docs.docker.com/compose/install/)

2 - Clone this repo and move into the downloaded folder.

3 - In the same path, run the command **`docker-compose up -d`**.

4 - Thats all! you can go to http://localhost:4200 in your browser and see the project running, with your own db.

<br>

## ✔ BackEnd

#### 👉🏻 Task List ✅

- Development of REST API for WorlFit with Java and Spring-Boot.
- Postgre Database.
- Creating filters with regular expressions and JPA/Hibernate rules.
- Role based securize architecture.
- Users validation with JWT.
- Dockerize and orchestrate containers with automatic renewal of SSL certificates.
- Deployed on EC2 instance of AWS through nginx reverse proxy.

#### 👉🏻 Code Standards 📜

- Keep in mind rules from [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).
- Code must be in English.
- The controllers should finish with suffix "Controller". Example: UserController.
- The services should finish with suffix "Service". Example: UserService.
- The repositories should finish with suffix "Repository". Example: UserRepository.
- The implementations of interfaces should finish with suffix "Impl". Example: UserServiceImpl.
- The DTOs should finish with suffix "Dto". Example: UserDto, UserRequestDto.
- Usage of DTOs is a must. Can have DTOs for request and response.
- Package names are in singular.
- The names of attributes/fields from Java classes must be written using camel case. Example: firstName.
- The name of columns in the entities must be written using underscore and uppercase. Example: FIRST_NAME. The name of the tables is always in plural, but the entity name should be in singular.
- Exceptions should be handled by an implementation of ControllerAdvice.
- Messages to user can't be hardcoded them should be handled. Some refs [here](https://looksok.wordpress.com/2014/07/05/string-externalization-in-spring-3-1-with-messagesource-no-web-xml/) and [here](https://zetcode.com/spring/messagesource/).
- If you add a new endpoint, make sure to set the role access for it in the WebSecurity class.

#### 👉🏻 Built with 🛠️

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)

#### 👉🏻 Developer 👨🏻‍💻

| <img src="https://avatars.githubusercontent.com/u/109044049?v=4" width=50> |
| :------------------------------------------------------------------------: |
|                            **Gabriel Boaglio**                             |

<a href="https://www.linkedin.com/in/gabriel-boaglio/"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white"/>
<br>

## ✔ FrontEnd

#### 👉🏻 Task List ✅

- Development of front-end reactive Angular app.
- Implementation of security architecture with JWT interceptors.
- Connection with REST API.
- Role based views and actions.
- Deployed in Firebase hosting.

#### 👉🏻 Code Standards 📜

- Keep in mind rules from [Angular Style Guide](https://angular.io/guide/styleguide) and [TypeScript Style Guide](https://github.com/basarat/typescript-book/blob/master/docs/styleguide/styleguide.md).
- Code must be in english or spanish.
- The components should finish with suffix "Component". Example: UserComponent.
- The services should finish with suffix "Service". Example: UserService.
- The interfaces should finish with suffix "Interface". Example: UserServiceInterface.
- The models should finish with suffix "Model". Example: UserModel.
- Usage of interfaces is a must. Can have interfaces for request and response.
- The names of attributes/fields from TypeScript classes must be written using camel case. Example: firstName.
- The name of variables should be descriptive and meaningful.
- Use let and const instead of var.
- Use async/await instead of Promise.then().
- Exceptions should be handled by the ErrorHandler class or a custom implementation of it.
- Messages to user can't be hardcoded, they should be handled by using an implementation of the TranslateService.
- If you add a new route, make sure to set the role access for it in the RouteGuard class.

#### 👉🏻 Built with 🛠️

![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
![MUI](https://img.shields.io/badge/MUI-%230081CB.svg?style=for-the-badge&logo=mui&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-039BE5?style=for-the-badge&logo=Firebase&logoColor=white)
