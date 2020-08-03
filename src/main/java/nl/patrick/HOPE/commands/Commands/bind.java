package nl.patrick.HOPE.commands.Commands;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import nl.patrick.HOPE.Hope;
import nl.patrick.HOPE.Managers.MessageManager;
import nl.patrick.HOPE.Module.Module;
import nl.patrick.HOPE.commands.Command;
import org.lwjgl.input.Keyboard;

public class bind extends Command {
    public bind() {
        super("bind", new String[]{"t", "toggle"});
    }
    private boolean watching;
    Keyboard key;
    @Override
    public void onCommand(String[] args) {
        if(args.length > 2){
            try{
                for(Module m: Hope.moduleManager.getModules()) {
                    if (m.getName().equalsIgnoreCase(args[1])) {
                        Hope.SETTINGS_MANAGER.updateSettings();
                        watching = true;
                        MessageManager.sendMessagePrefix("press the key u want to bind");
                        try {
                            m.settings.setSetting("keybind", Keyboard.getKeyIndex(args[2].toUpperCase() + ""));
                            MessageManager.sendMessagePrefix(m.getName() + " is now binded to " + Keyboard.getKeyIndex(args[2].toUpperCase() + ""));
                        } catch (Exception e) {
                            MessageManager.sendMessagePrefix(m.getName() + "something went wrong");

                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
