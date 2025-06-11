---
title: Cody Phelps ePortfolio
---

# Code Review
This code review showcases my ability to debug and look for security issues within the projects that I've seleted. Computer science professionals should be able to do this as it can improve: 
- Code Quality
- Help with Consistency
- Enhance Colaboration and Communication

<code> View my code review on youtube <a href="https://youtu.be/tBN6gzXDQrs">here</a></code>

## Artifact 1:
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
- <code> <a href="https://github.com/CodyPhelps93/CodyPhelps93.github.io/tree/Artifact1and2/Artificat-1-and-2/WeightTrackerAppCodyPhelps">Artifact 1 After enhancements</a></code>

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
I learned many different things while improving this applicaiton, such as brighter isn't always the best way to approach the colors of an application. Adding contrast can pull your attention to the areas of the applicaiton that you would like the user to notice. The orginal applicaiton's components being tightly wrapped into 1 main view made where the user had no navigation choice. The feedback that I recieved for this enhancement was that the background was to bright and my images that I used for the buttons were skewed. To fix this I redone the images to a different resolution that is shared by most phone, and I changed the theme of the application. Now the theme is not as heavy on the eyes and is pleasant to look at. While enhanceing the UI I wanted some features that required knowing what user was logged in so I incorporated a sharedPreference. This allowed me to save the current user logged in and to display it on the main menu. This is for users that may have multiple people on one device such as a tablet. The sharedPreference also allowed me to add more security to my SQL queries as before it would show all the data from that particular column because at that time we did not know what user was logged in. Adding the shared preferense also reminded me to include input validation for SQL injection so I added a new security layer by including this.

**Successfully demostrated outcomes:**
- Employ strategies for building collaborative environments that enable diverse audiences to support organizational decision making in the field of computer science.
- Demonstrate an ability to use well-founded and innovative techniques, skills and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry specific goals.
- Develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources

**Outcomes there were not met with this enhancement were:**
  - Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices.
  - Design, develop, and deliver professional-quality oral, written, and visual communications that are coherent, technically sound, and appropriately adapted to specific audiences and contexts
 
## Algorithms and Data Structure
    

