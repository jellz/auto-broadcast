# auto-broadcast
Simple auto message broadcasting plugin for (Paper) Spigot  
(I just made this in a couple of hours for a server and thought it might be useful for some people here)

### Features
- **Simple, easy-to-use configuration**
- Reload config command
- **Coloured broadcasts**
- Random order
- Multi-line supported
- **Sounds**

### Usage
Currently, the plugin is not on Spigot. You can download the latest version of 
the plugin from the [Releases page](https://github.com/jellz/auto-broadcast/releases).  
  
Just install it on your server like any other plugin. You can edit the config after the plugin generates it.  
  
This is the default config:  
```yaml
broadcasts-enabled: true
broadcast-interval: 3600 # ticks between each broadcast (default: 3 minutes)
broadcasts:
  test:
    sound: BLOCK_NOTE_BLOCK_PLING # see https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html for a list of sounds, leave blank for no sound
    messages:
      - '&6Test broadcast!'
  nosound:
    messages:
      - ' '
      - '&bThis broadcast takes up 3 lines!'
      - ' '
```

Reload the config with `/reloadbroadcasts`.
  
### Ideas
- PlaceholderAPI support
