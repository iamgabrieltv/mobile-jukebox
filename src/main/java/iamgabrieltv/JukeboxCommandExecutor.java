package iamgabrieltv;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class JukeboxCommandExecutor implements CommandExecutor {
 @Override
 public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
  if (sender instanceof Player) {
   Player player = (Player) sender;

   // Open the GUI for the player
   openJukeboxGUI(player);
  } else {
   sender.sendMessage("This command can only be executed by a player.");
  }

  return true;
 }

 private void openJukeboxGUI(Player player) {
  Inventory gui = Bukkit.createInventory(player, 18, "Mobile Jukebox");

  // TODO: Add items to the GUI
  gui.setItem(0, new ItemStack(Material.MUSIC_DISC_13));

  player.openInventory(gui);
 }
}
