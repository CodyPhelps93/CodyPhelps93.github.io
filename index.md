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

## Software Engineering and Design:
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
- Demonstrate an ability to use well-founded and innovative techniques, skills and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry specific goals.
- Develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources

**Outcomes there were not met with this enhancement were:**
  - Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices.
  - Design, develop, and deliver professional-quality oral, written, and visual communications that are coherent, technically sound, and appropriately adapted to specific audiences and contexts
 
## Algorithms and Data Structure

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
- 

**Outcomes there were not met with this enhancement were:**
- 
    

