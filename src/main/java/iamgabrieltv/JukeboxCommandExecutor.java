package iamgabrieltv;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

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
  // Initialize the GUI
  Inventory gui = Bukkit.createInventory(player, 36, "Mobile Jukebox");

  // Add all the discs to the GUI
  gui.setItem(0, new ItemStack(Material.MUSIC_DISC_13));
  gui.setItem(1, new ItemStack(Material.MUSIC_DISC_CAT));
  gui.setItem(2, new ItemStack(Material.MUSIC_DISC_BLOCKS));
  gui.setItem(3, new ItemStack(Material.MUSIC_DISC_CHIRP));
  gui.setItem(4, new ItemStack(Material.MUSIC_DISC_FAR));
  gui.setItem(5, new ItemStack(Material.MUSIC_DISC_MALL));
  gui.setItem(6, new ItemStack(Material.MUSIC_DISC_MELLOHI));
  gui.setItem(7, new ItemStack(Material.MUSIC_DISC_STAL));
  gui.setItem(8, new ItemStack(Material.MUSIC_DISC_STRAD));
  gui.setItem(9, new ItemStack(Material.MUSIC_DISC_WARD));
  gui.setItem(10, new ItemStack(Material.MUSIC_DISC_11));
  gui.setItem(11, new ItemStack(Material.MUSIC_DISC_WAIT));
  gui.setItem(12, new ItemStack(Material.MUSIC_DISC_OTHERSIDE));
  gui.setItem(13, new ItemStack(Material.MUSIC_DISC_5));
  gui.setItem(14, new ItemStack(Material.MUSIC_DISC_PIGSTEP));
  gui.setItem(15, new ItemStack(Material.MUSIC_DISC_RELIC));

  // Initialize stop icon
  ItemStack stopBanner = new ItemStack(Material.RED_BANNER);
  BannerMeta stopMeta = (BannerMeta) stopBanner.getItemMeta();

  List<Pattern> patterns = new ArrayList<Pattern>();

  patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
  patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_BOTTOM));
  patterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));
  stopMeta.setPatterns(patterns);
  stopMeta.setDisplayName(ChatColor.RED + "Stop");

  stopBanner.setItemMeta(stopMeta);

  // Add the banner to the GUI
  gui.setItem(31, stopBanner);

  // Open the GUI for the player
  player.openInventory(gui);
 }
}
