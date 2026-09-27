# Loot Stacker

This mod automatically stacks items in loot chests, preventing loot tables with too many entries from filling every chest slot and blocking subsequent loot from generating.

## Supported versions

| Minecraft | Mod loader | Java | Branch |
| --- | --- | --- | --- |
| 1.20.1 | Forge | 17 | `mc-1.20.1` |
| 1.21.1 | NeoForge | 21 | `mc-1.21.1` |

This is a standalone port of the loot-stacking feature from the latest PackCoreMod commit (`978a218`). If PackCoreMod (`scholarofcrimson`) is loaded at the same time, Loot Stacker disables its own mixin to prevent the feature from running twice.

## Server-side installation

For multiplayer, install the mod on the server only; clients do not need to install it. Single-player still requires the mod in the client installation because the client runs the integrated server.

## Build

Use Java 17 in this branch, then run `gradlew.bat build` from the project directory. The mod JAR is generated in `build/libs/`.

## Test loot tables

The test loot tables are automatically available in Gradle development runs (`gradlew.bat runClient` or `gradlew.bat runServer`) and are omitted from regular release JARs. To include them in a JAR, build with `-PincludeTestLoot=true`.

### Overflow test

`lootstacker:chests/overflow_test` generates 384 item drops: 64 each of six item types. With Loot Stacker enabled, these should fit into six chest slots. Without it, the drops can fill all 27 slots before the entire loot table is placed.

```mcfunction
/setblock ~2 ~ ~ minecraft:chest{LootTable:"lootstacker:chests/overflow_test",LootTableSeed:1L} replace
```

### Four items, 30 rolls

`lootstacker:chests/four_items_30_rolls` chooses from four item types for 30 rolls.

```mcfunction
/setblock ~2 ~ ~ minecraft:chest{LootTable:"lootstacker:chests/four_items_30_rolls",LootTableSeed:1L} replace
```

Place the chest in a development world (or a world with a datapack containing the test loot table) and open it to generate the loot.
