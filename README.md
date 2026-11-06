# Java-Game

A 2D side-scrolling platformer written in plain Java with AWT and Swing. It uses no game engine and no external libraries. I worked on it in April and May 2024.

## Status

Unfinished, and it does not run from this repo as it stands:

- **Images are missing.** The sprites and level images are loaded from the classpath, but they are not committed here, so the game fails at startup without them. The file names it expects are listed in `src/utilz/LoadSave.java`.
- **The level completed screen is switched on.** `lvlCompleted` in `src/gameStates/Playing.java` starts as `true` and nothing changes it. Starting a game goes straight to that screen and the player and enemies do not update.

## What is in the code

- Game loop on its own thread, with separate targets for updates (200 per second) and frames (120 per second)
- Game states for the menu and for playing, with keyboard and mouse input sent to the active state
- Player movement, jumping, gravity and tile collision
- A level built from an image file, with a camera that scrolls as the player nears the screen edge
- Background with clouds moving at different speeds
- Crab enemies that patrol, chase the player when they see them, and attack
- Health and damage for the player and enemies
- Menu, pause, game over and level completed screens
- Sound and volume buttons (interface only, the game has no audio code yet)

## Controls

| Key | Action |
|---|---|
| Enter | Start from the menu |
| A / D | Move left / right |
| Space | Jump |
| J | Attack |
| Esc | Pause, or return to the menu after game over |
| Mouse | Use the on-screen buttons |

## Project structure

| Package | Contents |
|---|---|
| `main` | Entry point (`MainClass`), game loop, window and panel |
| `gameStates` | Menu and playing states |
| `entities` | Player, enemies and enemy manager |
| `levels` | Level data and drawing |
| `inputs` | Keyboard and mouse listeners |
| `ui` | Buttons and overlays |
| `utilz` | Constants, collision helpers and image loading |

## Running it

Needs JDK 21. The repo is an Eclipse project.

1. Import it into Eclipse as an existing project.
2. Add the image files from `LoadSave.java` in a folder on the classpath.
3. Run `main.MainClass`.
