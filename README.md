# Caddy Tracker

A Java Application for Managing Caddying Sessions.

## Overview

CaddyTracker is my attempt to keep track of summer job. I am tired of trying to build web-applications, but I wanted something that is useful to me. This application was born out of a desire to show off my Java skills, build a useful tool for myself, and learn how to develop desktop applications potentially for public use.
`Caddy Tracker` is currently a console-based Java application designed to track caddying jobs. It allows users to log loops, manage earnings, and persist data to a local text file for future reference. The program encapsulates date handling, loop (job) management, and file I/O operations within a modular structure.

It serves as a lightweight tool to track loops, providing quick access to financial summaries (Total Earnings, Average Income per session).

## Features

- Session Logging: Add new caddying loops with a name, date, and earnings amount.
- Data Persistence: Automatically saves entries to `loops.txt` and loads existing data upon startup.
- Financial Tracking:
    - Calculate Total Money earned across all sessions.
    - Calculate Average Money earned per session.
- History View: Display a formatted list of all saved caddying sessions.
- Date Formatting: Handles dates robustly with zero-padding (e.g., `05-01-2024`).
- File I/O: Uses `BufferedReader` and `BufferedWriter` for efficient text file handling.

## Architecture

The application follows a model where `Main.java` acts as the controller/menu, while `Utility.java` serves as the repository and logic manager holding the static data list.

### Class Structure

|Class|Responsibility|
|---|---|
|`Main.java`|Entry point. Handles the user interface, menu display, and input/output loops.|
|`Utility.java`|Core logic. Manages the static list of `Loop` objects, handles file reading/writing, and calculates totals/averages.|
|`Loop.java`|Domain model. Represents a single caddying session (Name, Date, Money).|
|`Date.java`|Helper class. Encapsulates year, month, and day for formatting purposes.|

## Getting Started

### Prerequisites

- Java JDK 8 or higher installed on your system.

### Installation

1. Ensure you have the source code files: `Main.java`, `Loop.java`, `Utility.java`, `Date.java`.
2. Ensure a file named `loops.txt` is present in the root directory (optional on first run, created automatically).

### Running the Application

1. Compile the source files:
    
    ```bash
    javac *.java
    ```
    
    _(Note: Ensure all files are in the same directory or configured with the correct package path `Java.Caddy`.)_
    
2. Execute the main class:
    
    ```bash
    java Main
    ```

## Future work

I want to implement a GUI to elevate the production value and make the application more user friendly. I also want to add more features such as; average money paid per person, weekly rolling average among other things. I also want to use a more secure storage solution, such as a database, for saving loop information.

## Usage Guide

Upon running the application, you will be presented with a numeric menu:

|Option|Action|Description|
|---|---|---|
|0|Quit|Exits the application.|
|1|Add Loop|Prompts for Name, Money, and Date. Automatically saves to file.|
|2|Display List|Shows all stored caddying sessions and their details.|
|3|Total Money|Calculates and displays the sum of all earnings.|
|4|Average Money|Calculates the mean earnings per session.|

## Data Storage

The application uses a file named `loops.txt` to persist data.

File Format: The file stores each entry on a new line using a hyphen-separated structure: `Name-Year-Month-Day-Money`

_Example Line:_ `John-2024-05-10-500`

The file is created automatically on the first `Add Loop` operation or upon startup if the file does not exist.

## Project Files

- `Main.java`: Application entry point and menu logic.
- `Utility.java`: Contains `ArrayList<Loop>`, file I/O logic, and calculation methods.
- `Loop.java`: Data structure for a single caddying entry.
- `Date.java`: Utility class for date manipulation and formatting.

