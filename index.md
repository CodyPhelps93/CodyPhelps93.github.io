---
title: Cody Phelps ePortfolio
---

# Introduction
Hello, my name is Cody Phelps. This ePortfolio serves as a comprehensive showcase of my technical skills, professional growth, and readiness to contribute to the field of computer science. Throughout my coursework and capstone enhancements I’ve developed experiences in full-stack development, data structures, algorithms and database management. This has prepared me to excel in different roles as a software developer.

# Computer Science Journey
During my journey here I’ve created many different applications that have helped me to learn the fundamentals of computer science. I’ve been able to practice with data structures and algorithms as well as full stack development using different databases such as MongoDB and SQL. It has also taught me the importance of being able to translate requirements and explain them to a non-technical audience. <br/>
Throughout my course work in my capstone class, I’ve learned how I can take old code and refactor it and even transfer one application to another. By doing the code review in this course I was able to learn more about looking for errors in code and paying attention to the structure of code. It helps to point out mistakes and errors that may be overlooked or not even thought about. SNHU has allowed me to gain experience in team collaboration with discussions about issues that we were all having and gain solutions and insight into what was going on. While developing my android application I was able to gather information and requirements from different potential users and to communicate the technical requirements to a non-technical user. Throughout all these projects and course work and as I grew as a developer and found that not only did it help me in the Computer Science field but also in my problem-solving skills. I found that I can break problems down into smaller problems and create different solutions for those smaller problems. By building up the smaller solutions I’m able to tackle larger problems more efficiently. I've learned how to build databases and connect them to full-stack applications. I've even challenged myself by using different programs and frameworks not taught here at SNHU. From implementing Breath First Search (BFS) algorithms to search through data in a CSV file to building my first Blazor application featured in this ePortfolio, I've acquired the skills needed to become a successful software engineer or developer.

# Artifacts Review
In CS 499 I was tasked with enhancing artifacts in three different categories: **Software Design and Development**, **Algorithms and Data Structures**, and **Databases**. For Software Design and Development and Algorithms and Data Structures I chose to use my android application for tracking weight that I had previously built for my CS 360 class. The goal of this artifact was to show my skills in UI/UX design and building an algorithm for predicting weight using a regression algorithm. For my second artifact and my third enhancement I chose a project that was previously developed in Java. It uses a command line interface (CLI) to intake either monkeys or dogs and allow you to reserve them. For this artifact I decided to build a GUI using Blazer MAUI Hybrid and incorporate a MongoDB database. 

# Code Review
This code review showcases my ability to debug and look for security issues within the projects that I've seleted. Computer science professionals should be able to do this as it can improve: 
- Code Quality
- Help with Consistency
- Enhance Colaboration and Communication

<code> View my code review on youtube <a href="https://youtu.be/tBN6gzXDQrs">here</a></code>

## Artifact 1 Weight Tracking Application:
- **Briefly describe the artifact.** <br/>
  - An Android application for tracking weight developed from design to final product for my CS 360 class.
- **When was it created?** <br/>
  - Created during CS 360 Mobile Architecture and Development
- **Why did you select this item?** <br/>
  - This artifact is an android application that I developed from the design phase to the current product. This application demostrates my ability to build an application from scratch, showcasing ablilities such as:
     - Requirements research
     - UI/UX design
     - SQL Database usage

This artifact will serve as showing two enhancements the first being in 
```Software Engineering and Design```
and then
```Algorithms and data structures``` <br/>

- **Original Features:** 
  - Four views: Login, Create Account, Weight Tracking, SMS Permission
  - SQLite database for local data storage
  - Cluttered UI with navigation issues

You can see the code for before and after here:
- <code> <a href="https://github.com/CodyPhelps93/CS-360-Android-Development">Artifact 1 Before enhancements</a></code>
- <code> <a href="https://github.com/CodyPhelps93/CS-360-Enhanced">Artifact 1 After enhancements</a></code>

### Software Engineering and Design:
**What specific components of the artifact showcase your skills and abilities in software development?**
- Android development: Built a fully functional application with a SQLite database.
- UI/UX Design: Conducted research to inform design choices, ensuring intuitive navigation and themes.

**How did the enhancement improve the artifact?**
- Refactored UI: Replaced cluttered views with a streamlined layout; RecyclerView now occupies half the screen, with static buttons/TextViews at the bottom for easier data entry.
- Added Calorie Tracker: Displays calorie deficit and weight progress, enhancing user engagement.
- Improved Navigation: Reduced scrolling issues, making the app more intuitive, and added a Menu for view selection.
- SharedPreferences: Tracks logged-in users, improving security and personalization, especially for multi-user devices.
- Security Layer: added input validation to prevent SQL injection.

**What specific skills did you demonstrate in the enhancement?**
 - UI/UX design based on user feedback
 - Android app development and refactoring.
 - Secure database management

**Improvements Achieved:**
- Enhanced user experience with a cleaner more navigable interface.
- Increased functionality with calorie tracking.
- Improved security and user-specific data handling.

**Lessons Learned, Challenges, and Feedback** <br/><br/>
I learned many different things while improving this applicaiton, such as brighter isn't always the best way to approach the colors of an application. Adding contrast can pull your attention to the areas of the applicaiton that you would like the user to notice. The orginal applicaiton's components being tightly wrapped into 1 main view made where the user had no navigation choice. The feedback that I recieved for this enhancement was that the background was to bright and my images that I used for the buttons were skewed. To fix this I changed the images to a different resolution that is shared by most phone, and I changed the theme of the application. Now the theme is not as heavy on the eyes and is pleasant to look at. While enhanceing the UI I wanted some features that required knowing what user was logged in so I incorporated a sharedPreference. This allowed me to save the current user logged in and to display it on the main menu. This is for users that may have multiple people on one device such as a tablet. The sharedPreference also allowed me to add more security to my SQL queries as before it would show all the data from that particular column because at that time we did not know what user was logged in. Adding the shared preferense also reminded me to include input validation for SQL injection so I added a new security layer by including this.

**Successfully demostrated outcomes:**
- Employ strategies for building collaborative environments that enable diverse audiences to support organizational decision making in the field of computer science.
  - Shown by UI/UX design and changed made from feedback given.
  - SharedPreferences demostrates consideration for diverse users.
- Design, develop and deliver professional-quality oral, written, and visual communications that are coherent, technically sound and appropriately adapted to specific audiences
  - Completed by the refactoring of the UI and how users visual navigate the application.
  - Changing the theme to improve readablility and to help reduce eye strain.
- Demonstrate an ability to use well-founded and innovative techniques, skills and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry specific goals.
  - Calorie Tracker adds value by addressing other health releated goals for the user and aligns with other fitness/wellness applications.
  - Refactoring of the RecyclerView for better presentation of data.
- Develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources
  - Done by implementing a SQL injection prevention using input validation and a list of common injection patterns
  - SharedPreferences for user identification which allowed for user-specific data to be shown helping with privacy concerns. This also allowed for queries scoped to only the logged-in user which reduces data leakage.

**Outcomes there were not met with this enhancement were:**
  - Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices.
 
### Algorithms and Data Structure

**What specific components of the artifact showcase your skills and abilities in data structure and algorithms?**
- Prediction Algorithm: Developed a constant-time 0(1) algorithm to forecast future weights using a linear regresssion formula.
- Data Structure(List) Structured weight and date data in a list for efficient retrieval and manipulation from the SQLite database.
- Graphical Display: Enhanced the app to visualize predicted weight trends on a graph, showcasing the ability to translate algorithmic outputs into user-friendly formats.

**How did the enhancement improve the artifact?**
-  Users are now able to see predicted weights for 7 or 30 days away. This will allow the user to be more informed on their weight goals.

**What specific skills did you demonstrate in the enhancement?**
- Developed a prediction algorithm to forecast future weights based on user data, displayed on a graph.
- Structured data in a list for efficient retrieval and manipulation from SQLite database.
- Translate mathmatical formula to a working prediction model.

**Improvements Achieved:**
- Added a weight prediction algorithm using first and last weight entries and the days between them, that uses linear regression.
- Enhanced graphical display to show predicted weight trends, improving user insight into progress.

**Lessons Learned, Challenges, and Feedback** <br/><br/>
Developing the prediction algorithm for this artifact taught me to think creatively about gathering the necessary data for the formula used. The algorithm achieves constant-time comlexity 0(1) by referencing only the first and last data points in the list. This eliminates the need for loops and addtional data iterations. I had to consider how I would take the data from the database and store it so that I could use this data in the formula. Another challenge that I had is that the orginal artifact stored the date as a string in the database. This could have been refactored and stored and changed to hold a date instead of string but I found that this gave me an opputunity to parse this data and convert it using Java's DateTimeFormatter which is something that will be useful in future endeavors. 

**Successfully demostrated outcomes:**
- Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices (data structures and algorithms)
  - Done by implementing a linear regression algorithm for weight prediction.
- Develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources
  - Completed by introducing input validation on types. This was to make sure that the conversions were done and that the correct value was being passed. 

**Outcomes there were not met with this enhancement were:**
 - Design, develop, and deliver professional-quality oral, written, and visual communications that are coherent, technically sound, and appropriately adapted to specific audiences and contexts
- Employ strategies for building collaborative environments that enable diverse audiences to support organizational decision making in the field of computer science

## Artifact 2 IT-145 Grazioso Animal Intake
- **Briefly describe the artifact.**
  - This is an animal intake command line interface that is used to intake dogs or monkeys used for training and rescue.
- **When was it created**
  - This was created during my first year in IT-145.
- **Why did you select this item**
  - I selected this artifact because it gave me a chance to not only show off my C# ablilites but to also use BLazor MAUI Hybrid to build a GUI and to showcase:
    - Building a MongoDB database
    - Implementing it with .Net and blazor using nuget package.

I used this artifact for the final enhancement for ```databases```.
- **Original Features**
  - Intake Dog, or Monkey
  - Reserve Dog or Monkey
  - Print List of Avaliable Animals
  - Print List of All Animals
  - Animal Objects initialiy saved into a list
 
You can see the before and after code here:
- <code> <a href="https://github.com/CodyPhelps93/IT-145/tree/main">Artifact 2 before enhancements</a></code>
- <code> <a href="https://github.com/CodyPhelps93/IT-145-Enhanced">Artifact 2 after enhancements</a></code>

### Database
**What specific components of the artifact showcase your skills and abilities in software development?**
- C# using .Net framework with Blazor
- MongoDB implementation for animal data
- HTML with Bootstrap styling

**How did the enhancement improve the artifact?**
- This enhancement added a graphical user interface (GUI) with forms that auto-populate depending on the selected animal.
- Adds MongoDB to replace in-memory storage, this allows for more scalable solutions in the future.

**What specific skills did you demostrate in the enhancement?**
- UI Design
- C# and .NET
- MongoDB setup and queries with CRUD operations

**Improvements Achieved:**
- MongoDB successfuly intakes animals and allows for CRUD oeprations.
- Replace CLI with GUI for better visual usage.
- Replace questions with Forms for better data handling.

**Lessons Learned, Challeges, and Feedback**
This was a fun and challenging enhancment as I developed it using a framework I've never used before. I wanted to learn how Blazor worked and to see a different style of component based building. I wanted this to resemble a simple dashboard in which the user could easily intake and remove animals one a single screen. One of the challenges I faced was how can I get rid of some of the need for all the different user input. To solve this issue I decided it would be a good option to have the user have different options and to use a date selector when entering some of the data. This took out 99% of user typed input which helps to reduce any type of injection attacks and helps to cut down on user input validation. This also makes it easier on the user so they can just select the infomration that is needed.



