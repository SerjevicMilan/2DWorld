# 2D World Game Java Edition

A procedurally generated, tile-based 2D game written in Java.

---

## Features

- Procedural generation of rooms and hallways
- Room connectivity using **Kruskal’s algorithm** (Minimum Spanning Tree)
- Uses BFS to generate paths from enemy to player
- Fully tile-based graphical rendering
- Player movement and world interaction
- Save/load system using serialized coordinates
- Demo modes to visualize world and logic

---

## Rendering & Input

This project uses a tile engine provided by UC Berkeley's course:

- **TERenderer**: Provided by UC Berkeley for their tile engine assignment; draws a grid of `TETile` objects
- Relies on **StdDraw** (from the Princeton Standard Libraries) for low‑level drawing **and keyboard interaction**
- Tiles (walls, floors, player, coins, etc.) defined in `Tileset`
- Rendering and input loop via `TERenderer.drawFrame()` and `StdDraw.hasNextKeyTyped()` / `StdDraw.nextKeyTyped()`

---

## Saving & Loading

Game state is fully serialized via **coordinates**:

- Saves player position and seed
- Stored as structured text (not just input commands)
- On load, the world is reconstructed

---

## Getting Started

1. **Compile**
   ```bash
   javac -d out src/**/*.java
   ```
2. **Run** (e.g. main game demo)
   ```bash
   java -cp out demo.GameLoopDemo
   ```

## Project Structure

```
src/
  ├── core/        # World, Hallway, Coordinate, Main game loop
  ├── tileengine/  # `TETile`, `TERenderer`, `Tileset`
  └── utils/       # `FileUtils`, `RandomUtils`

tests/
  ├── CordinateTests.java
  └── WorldGenTests.java
```

---

## 🛠 Requirements

- Java 17+
- StdDraw library (bundled or on classpath) for rendering and keyboard input

---

## 📄 License

MIT