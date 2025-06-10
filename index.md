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
**Briefly describe the artifact. What is it? When was it created? Why did you select this item?** <br/>
This artifact is an android application that I developed from the design phase to the current product. It was created for my CS 360 class. The application is a weight tracking application that allows different users to login and track their weight. It uses a SQLite database to hold the data locally on the device. I selected this item because it was one of the first items that I built from the design phase all the way to the end product. This artifact will serve as showing two enhancements the first being in 
```Software Engineering and Design```
and then
```Algorithms and data structures```
This artifact orginally used 4 views: 
- Login View
- Create Account View
- Weight Tracking View
- SMS Permission View

This caused a clutter of different objects on the view and was not user friendly. This will take us to the first enhancement which is for software engineering and design.
You can see the code for before and after here:
- <code> <a href="https://github.com/CodyPhelps93/CS-360-Android-Development">Artifact 1 Before enhancements</a></code>
- <code> <a href="https://github.com/CodyPhelps93/CodyPhelps93.github.io/tree/Artifact1and2/Artificat-1-and-2/WeightTrackerAppCodyPhelps">Artifact 1 After enhancements</a></code>

## Software Engineering and Design:
**What specific components of the artifact showcase your skills and abilities in software development? How did the enhancement improve the artifact? What specific skills did you demonstrate in the enhancement?** <br/>
 It show cases my abilities building an android application and my UI/UX design choices. These choices involved a research phase where
I asked different people about my design choices and took that information into account when building the layout for the application. 
For this enhancement I chose to refactor the entire UI/UX of the application. This would take away the clutter and make the application more user friendly as well as adding a better sense of navigation. With this enhancement I decided to include
a calorie tracker so that the user had a way of seeing their progress in a different way. The calorie tracker view also takes the users calorie deficit and lets the user know how much weight they have either gained or loss. The old UI was also placed in just a recycler and a scroll view. Instead of just using the recycler's scroll feature this caused the whole screen to scroll once to much data was added. For the enhancement I decided it would be better if the Recycler only took half the screen up and the buttons and TextViews were static at the bottom half of the screen. This design fixes the issue of the user needing to scroll to add new information. The skills that I demostrated doing this enhancement would be my UI/UX design and android application building skills. I successfully showcase how I can take requirements and user review information and turn that into a fully functional product. <br/>
**What did you learn as you were creating it and improving it? What challenges did you face? How did you incorporate feedback as you made changes to the artifact? How was the artifact improved? Which course outcomes did you partially or fully meet with your enhancements? Which do you feel were not met?** <br/>
I learned many different things while improving this applicaiton, such as brighter isn't always the best way to approach the colors of an application. Adding contrast can pull your attention to the areas of the applicaiton that you would like the user to notice. The orginal applicaiton's components being tightly wrapped into 1 main view made where the user had no navigation choice. The feedback that I recieved for this enhancement was that the background was to bright and my images that I used for the buttons were skewed. To fix this I redone the images to a different resolution that is shared by most phone, and I changed the theme of the application. Now the theme is not as heavy on the eyes and is pleasant to look at. While enhanceing the UI I wanted some features that required knowing what user was logged in so I incorporated a sharedPreference. This allowed me to save the current user logged in and to display it on the main menu. This is for users that may have multiple people on one device such as a tablet. The sharedPreference also allowed me to add more security to my SQL queries as before it would show all the data from that particular column because at that time we did not know what user was logged in. Adding the shared preferense also reminded me to include input validation for SQL injection so I added a new security layer by including this.  With this I successfully demostrated that I could meet the following outcomes: 
- Employ strategies for building collaborative environments that enable diverse audiences to support organizational decision making in the field of computer science.
- Demonstrate an ability to use well-founded and innovative techniques, skills and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry specific goals.
- Develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources

  Outcomes there were not met with this enhancement were:
  - Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices.
  - Design, develop, and deliver professional-quality oral, written, and visual communications that are coherent, technically sound, and appropriately adapted to specific audiences and contexts
 
    ## Algorithms and Data Structure
    

