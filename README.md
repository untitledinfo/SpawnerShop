# SpawnerShop

Paper/Bukkit-compatible Java 21 spawner shop for 1.21.4+. The plugin uses only Bukkit/Paper APIs, so the same JAR works on Paper, Spigot, Purpur and Pufferfish. Vault is required; integrations listed in `plugin.yml` are detected without hard linking.

Build with `mvn package`; place `target/SpawnerShop.jar` plus Vault and an economy provider in `plugins/`. Configure shop entries in `plugins/SpawnerShop/shops.yml` and run `/spawnershop reload`.

Commands: `/spawnershop`, `/shopspawner`, `/shop`, plus `reload`, `open <player>`, `editor`, and `debug`. Left/right click buy/sell one; shift click buy/sell 64; the Sell All button scans player storage. Transactions are serialized per player, verified before economy changes, and logged asynchronously to SQLite.
