# RealmEssentialsPro

RealmEssentialsPro is a Paper plugin for the usual essentials-style server features: homes, warps, teleport requests, spawn handling and a few utility commands.

Compared to the smaller `RealmCore` setup, this project is built for a newer Paper version and includes a bit more polish around GUI usage, first-join handling and configurable sounds and warmups.

## Included systems

- spawn and setspawn
- homes with list GUI
- warps with list GUI
- teleport requests
- heal, feed, fly and repair
- first-join spawn handling
- optional starter items on first join
- configurable teleport warmup and cancel-on-move behavior
- configurable success, error and click sounds

## Commands

- `/spawn`
- `/setspawn`
- `/sethome`
- `/home`
- `/delhome`
- `/homes`
- `/tpa`
- `/tpahere`
- `/tpaccept`
- `/tpdeny`
- `/setwarp`
- `/warp`
- `/delwarp`
- `/warps`
- `/heal`
- `/feed`
- `/fly`
- `/repair`
- `/rep`

## Configuration highlights

The current config covers:

- max homes
- teleport warmup and movement cancel behavior
- GUI sizes for homes and warps
- starter items on first join
- configurable sounds for success, error and button clicks

Configuration files live in:

- `src/main/resources/config.yml`
- `src/main/resources/messages.yml`

## Build

Requirements:

- Java 21
- Maven
- Paper API `1.21.4-R0.1-SNAPSHOT`

Build command:

```bash
mvn clean package
```

The plugin is packaged during the Maven build and the output ends up in `target/`.

## Notes

- storage is YAML-based
- permissions are defined directly in `plugin.yml`
- the project is structured as a self-contained Paper plugin rather than a network-wide proxy system
