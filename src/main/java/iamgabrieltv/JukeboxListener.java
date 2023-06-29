package iamgabrieltv;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class JukeboxListener implements Listener {

 public File playersFile;

 public JukeboxListener(File playersFile) {
  this.playersFile = playersFile;
 }

 @EventHandler
 public void onInventoryClick(InventoryClickEvent event) {
  if (event.getView().getTitle().equals("Mobile Jukebox")) {
   event.setCancelled(true); // Cancel the event to prevent item moving

   ItemStack clickedItem = event.getCurrentItem();
   if (clickedItem != null && clickedItem.getType() != Material.AIR) {
    ItemMeta itemMeta = clickedItem.getItemMeta();
    if (itemMeta != null && itemMeta.hasDisplayName()) {
     String displayName = itemMeta.getDisplayName();

     if (displayName.startsWith(ChatColor.GREEN + "Play: ")) {
      Player player = (Player) event.getWhoClicked(); // Get the player who clicked the item
      stopPlayingDisc(player);
      String disc = displayName.substring("Play: ".length() + 2);
      // Call a method to start playing the disc
      startPlayingDisc(player, disc);
     } else if (displayName.equals(ChatColor.RED + "Stop")) {
      Player player = (Player) event.getWhoClicked(); // Get the player who clicked the item
      // Call a method to stop playing the disc
      stopPlayingDisc(player);
     }
    }
   }
  }
 }

 private void startPlayingDisc(Player player, String disc) {
  // Play the sound for the player
  player.playSound(player.getLocation(), Sound.valueOf(disc), 100, 1.0f);
 }

 private void stopPlayingDisc(Player player) {
  // Stop any currently playing sound for the player
  player.stopAllSounds();
 }
}
