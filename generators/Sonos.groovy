import com.blackbuild.openhab.generator.model.sonos.SonosPlayer
import templates.ItemType
import templates.SonosPlayerTemplate

into("items/sonos.items") { out ->

    config.all(SonosPlayer).each { rawPlayer ->
        def player = new SonosPlayerTemplate(rawPlayer)

        out.println "//-------------------------------------------- Sonos $player.parentGroup.label"

        out.println player.groupDefinition

        player.sonos.with {
            out.println createItem(ItemType.Player, "Control", "Sonos $player.parentGroup.label Steuerung", null, [player.fullName], null, "control")
            out.println createItem(ItemType.Dimmer, "Volume", "Sonos $player.parentGroup.label Lautstärke", null, [player.fullName], null, "volume")
            out.println createItem(ItemType.String, "Album", "Sonos $player.parentGroup.label Album", null, [player.fullName], null, "currentalbum")
            out.println createItem(ItemType.String, "Artist", "Sonos $player.parentGroup.label Interpret", null, [player.fullName], null, "currentartist")
            out.println createItem(ItemType.String, "Title", "Sonos $player.parentGroup.label Titel", null, [player.fullName], null, "currenttitle")

            out.println createItem(ItemType.Switch, "Save", "Sonos $player.parentGroup.label Save", null, [player.fullName], null, "save")
            out.println createItem(ItemType.Switch, "Restore", "Sonos $player.parentGroup.label Restore", null, [player.fullName], null, "restore")
        }

        player.homematic.with {
            out.println createItem(ItemType.String, "Speak", "Sonos $player.parentGroup.label Sprachausgabe", null, [player.fullName], null, "1#PLAY_TTS")
            out.println createItem(ItemType.String, "Duration", "Sonos $player.parentGroup.label Dauer", null, [player.fullName], null, "1#CURRENT_TRACK_DURATION")
            out.println createItem(ItemType.String, "Time", "Sonos $player.parentGroup.label Zeit", null, [player.fullName], null, "1#CURRENT_TRACK_RELATIVE_TIME")
        }

        out.println ""
    }
}


