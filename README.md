<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
<!--
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
-->


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
  </a>

  <h3 align="center">Tic Tac Toe Game</h3>

  <p align="center">
    CSE_535_Project_2
    <br />
    <br />
    <a href="https://youtu.be/EC9uGuIxjCg">View Demo Video</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#file-structure">File Structure</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

This project presents a mobile Tic-Tac-Toe game featuring both AI-vs-human and player-vs-player (PvP) modes, developed for Android platform. The AI opponent uses the Minimax algorithm with alpha-beta pruning, allowing efficient decision-making even on resource-limited devices. The game includes adjustable AI difficulty levels, a PvP mode on one device and with Bluetooth multiplayer, and logs game history locally, storing outcomes and difficulty levels.

### Introduction
Mobile gaming offers unique challenges, particularly in implementing efficient AI algorithms under constrained system resources. This project focuses on building a mobile Tic-Tac-Toe game for Android, supporting both AI-vs-human and player-vs-player (PvP) modes.

In the AI mode, a human player can challenge a computer opponent driven by the Minimax algorithm with alpha-beta pruning, which allows the AI to make optimal moves while minimizing unnecessary calculations. With three difficulty levels (Easy, Medium, Hard), the game can accommodate players of different skill levels.

In addition to the AI mode, the game offers a PvP mode, where two players can compete on a single device or over Bluetooth, enabling multiplayer interaction across two devices. The app also stores game results (game time and the winner) and difficulty levels locally, allowing players to review their history and assess their performance.


<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

This section briefly describe what techenologies are used to build the project.

* [![Kotlin][Kotlinlang.org]][Kotlin-url]
* [![Android][Android]][Android-url]
* [![RoomDB][RoomDB]][RoomDB-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

Users must install the application mentioned below to set up the local environment.

* Android Studio (Android SDK 34)

### Installation

This section gives an instruction of how to run the project on local machine with Android Studio emulator.

1. Clone the repo
   ```sh
   git clone https://github.com/Max851010/CSE_535_Project_2.git
   ```

2. Activate an emulator
    * Open Android Studio
    * Click "Device Manager" at the right side bar
    * Create or activate a device

3. Sync Project with Gradle Files

4. Run the app


<p align="right">(<a href="#readme-top">back to top</a>)</p>



## File Structure

This project follows the Model-View-Controller (MVC) architecture using the Jetpack Compose framework for a modular, maintainable, and responsive UI design.

- Model: All data-related operations are managed within the `database/` and `viewModels/` folders, where Room entities (`Entities.kt`) and DAOs (`HistoryDao.kt`, `SettingsDao.kt`) represent the data layer, while DataBaseViewModel.kt and TicTacToeViewModel.kt handle data interactions and business logic.

- View: The UI components, layouts, and themes are organized in the `ui/` folder. Screens like `GameScreen.kt`, `PastGameScreen.kt`, and `SettingsScreen.kt` present data to the user, with styling managed in the `theme/` subfolder.

- Controller: Navigation and user flow are controlled by NavGraph.kt, which defines the routes between screens and manages transitions within the app.

The project files are organized within `/app/src/main/java/com/example/cse_535_project_2_jet` as follows:

```shell
.
├── MainActivity.kt
├── components
│   └── DateConverters.kt
├── database
│   ├── Entities.kt          # Defines the Settings and Histories database entities
│   ├── GameDatabase.kt      # Configures Room Database and provides instance
│   ├── HistoryDao.kt        # Data Access Object (DAO) for history table operations
│   ├── MyAutoMigrationSpec.kt # Manages database migrations
│   └── SettingsDao.kt       # DAO for settings table operations
├── navigation
│   └── NavGraph.kt          # Handles app navigation with Jetpack Navigation Component
├── ui
│   ├── components
│   │   └── TicTacToeViewFactory.kt  # Factory for rendering Tic-Tac-Toe board
│   ├── screens
│   │   ├── GameScreen.kt     # Main game screen
│   │   ├── MainScreen.kt     # Home screen, entry point
│   │   ├── PastGameScreen.kt # Displays a list of past games
│   │   └── SettingsScreen.kt # User settings and configurations screen
│   └── theme
│       ├── Color.kt          # App color scheme
│       ├── Theme.kt          # Theme configurations
│       └── Type.kt           # Text styles and font configurations
└── viewModels
    ├── DataBaseViewModel.kt  # ViewModel for database interactions
    └── TicTacToeViewModel.kt # ViewModel containing all game logic for Tic-Tac-Toe
```

### Screens
This project is built using the Jetpack Compose framework. The main entry point is MainActivity.kt, which loads MainScreen.kt as the home screen. From there, the app is structured into three primary screens:

- `GameScreen.kt`: The main gameplay interface for Tic-Tac-Toe.
- `PastGameScreen.kt`: Displays a history of past games with details.
- `SettingsScreen.kt`: Provides configuration options for users.

Navigation between these screens is managed by `NavGraph.kt`, ensuring smooth transitions.

### RoomDB
The Room database setup is located in the `database/` folder, with all CRUD operations facilitated through DAOs and managed via `DataBaseViewModel.kt`. The database includes two primary tables, defined in `Entities.kt`:

- `settings`: Stores user configuration settings.
- `histories`: Logs past games with relevant game details.

Data access is handled by `SettingsDao.kt` and `HistoryDao.kt`, which provide methods for interacting with the database in a structured, efficient manner.

### Game Logic
All the game logic for Tic-Tac-Toe is implemented in `TicTacToeViewModel.kt`. This includes game state management, turn-based logic, win-checking algorithms, and any updates required during gameplay.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/demopic.jpg
[Kotlinlang.org]: https://img.shields.io/badge/Kotlin-563D7C?style=for-the-badge&logo=kotlin&logoColor=white
[Kotlin-url]: https://kotlinlang.org/
[Android]: https://img.shields.io/badge/Android-35495E?style=for-the-badge&logo=android&logoColor=4FC08D
[Android-url]: https://www.android.com/
[RoomDB]: https://img.shields.io/badge/Roomdb-20232A?style=for-the-badge&logo=android&logoColor=4FC08D
[RoomDB-url]: https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase