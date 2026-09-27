# Loot Stacker — Minecraft Forge 1.20.1

独立移植自 PackCoreMod 最新提交 `978a218` 的战利品堆叠功能。战利品表填充容器前，会把物品与标签完全相同、且可堆叠的物品合并；若检测到 `scholarofcrimson`（PackCoreMod）已加载，本模组的 Mixin 会自动跳过，避免重复生效。

## 构建

使用 Java 17，在本目录运行 `gradlew.bat build`。产物位于 `build/libs/`。

## 测试用箱子

测试战利品表 `lootstacker:chests/overflow_test` 每次产生 384 个物品（六种各 64 个）。它只会自动加入 Gradle 的开发运行：`gradlew.bat runClient` 或 `gradlew.bat runServer`；普通 `build` 产物不包含该测试表。

在开发运行或包含该战利品表的数据包的世界中，执行：

```mcfunction
/setblock ~2 ~ ~ minecraft:chest{LootTable:"lootstacker:chests/overflow_test",LootTableSeed:1L} replace
```

打开箱子触发生成。启用本模组时预期六种物品各占一格；未启用时，超过箱子 27 格容量的部分无法放入。若要把测试表临时打入 JAR，可在构建时加 `-PincludeTestLoot=true`。

另有 `lootstacker:chests/four_items_30_rolls` 测试表：4 种物品共 roll 30 次。生成指令：

```mcfunction
/setblock ~2 ~ ~ minecraft:chest{LootTable:"lootstacker:chests/four_items_30_rolls",LootTableSeed:1L} replace
```

## English Description

This mod automatically stacks items in loot chests, preventing loot tables with too many entries from filling every chest slot and blocking subsequent loot from generating.

## 服务端安装

多人游戏只需将模组安装在服务器上，客户端无需安装。单人游戏的集成服务器由客户端启动，因此单人游戏仍需在客户端安装模组。
