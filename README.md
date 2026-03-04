### Touch Target Size Experiment (MD3 Methodology)
![Android](https://img.shields.io/badge/platform-Android-green)
![Language](https://img.shields.io/badge/language-Java-orange)
![Version](https://img.shields.io/badge/version-v0.2.0-blue)

This Android application measures how touch target sizes affect user interactive experience and accuracy. 
This experiment compares different interactive UI element sizes and records completions times and misclicks. 

THe goal is to evaluate how smaller interface elements impact usability on mobile devices. 


### Overview
Participants complete a simple interaction task involving three UI controls
- Button
- Toggle Switch
- Radio Button

The controls appear at random positions on the screen for every trial.

The user must activate all three controls as quickly as possible.

The experiment currently compares two target sizes:

48dp (recommended Android touch target size)

32dp (smaller target size)

The application measures
- Completion time
- Number of trials
- Misclicks (touches outside the controls)
- Average time for each touch target 


### How the experiment works
1. User starts the experiment from the Start Screen
2. The 48dp condition begins
3. UI controls appear at randomized locations
4. The user activates all three controls
5. Multiple trials are performed
6. The experiment repeats with 32dp controls
7. Results are displayed including:
   - Total time
   - Misclicks 
   - Number of runs
   - Average time per touch target 


### Features
- Randomized UI element placement for each trial
- Multi-trial experiment system
- Misclick detection
- Automatic result calculation
- A very cool looking background
- Results screen showing experiment statistics


### Technologies used
- Java
- Android Studio
- Material Design Components 
- Android SDK 


## Credits 
This project was originally developed by Ibrahim Salman. 

The repository was extended and further developed by Pragyay Bharadwaj, adding additional functionality,
UI Improvements, and experiment tracking. 
