# RealmEssentialsPro

RealmEssentialsPro is a premium-style Paper plugin designed as a portfolio and marketplace-ready resource.

## Features

- Spawn system (`/spawn`, `/setspawn`)
- Homes system (`/sethome`, `/home`, `/delhome`, `/homes` GUI)
- Warp system (`/setwarp`, `/warp`, `/delwarp`, `/warps` GUI)
- Teleport requests (`/tpa`, `/tpahere`, `/tpaccept`, `/tpdeny`)
- Utility commands (`/heal`, `/feed`, `/fly`, `/repair`)
- Configurable messages, sounds, cooldown-like warmup behavior
- YAML persistence for homes, warps, spawn and player first-join state

## Build

Requires:
- Java 21
- Maven

Build with:

```bash
mvn clean package
```

The output jar will be inside `target/`.

## Suggested BuiltByBit positioning

Title idea:
`RealmEssentialsPro | Homes, Warps, TPA, GUI`

Short pitch:
A clean premium-style essentials core for Paper servers with homes, warps, teleport requests, spawn and admin utilities.

## Git quick start

```bash
git init
git add .
git commit -m "Initial commit - RealmEssentialsPro"
git branch -M main
git remote add origin YOUR_GITHUB_REPO_URL
git push -u origin main
```
