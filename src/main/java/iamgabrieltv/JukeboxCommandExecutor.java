package iamgabrieltv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class JukeboxCommandExecutor implements CommandExecutor {
 private File playersFile;

 public JukeboxCommandExecutor(File playersFile) {
  this.playersFile = playersFile;
 }

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

  // Read the playersFile JSON
  try (JsonReader reader = new JsonReader(new FileReader(playersFile))) {
   // Parse the JSON file into a JsonElement
   JsonElement jsonElement = JsonParser.parseReader(reader);

   // Get the player's name
   String playerName = player.getName();

   // Get the player's discs array from the JSON
   JsonArray playerDiscs = jsonElement.getAsJsonObject().getAsJsonArray(playerName);

   // Add the player's discs to the GUI
   for (int i = 0; i < playerDiscs.size(); i++) {
    String disc = playerDiscs.get(i).getAsString();
    Material material = Material.valueOf(disc);

    ItemStack discItem = new ItemStack(material);
    ItemMeta discMeta = discItem.getItemMeta();
    discMeta.setDisplayName(ChatColor.GREEN + "Play: " + disc); // Set display name for the disc item
    discItem.setItemMeta(discMeta);

    // Set the click event listener for the disc item
    discItem.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    ItemMeta itemMeta = discItem.getItemMeta();
    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    discItem.setItemMeta(itemMeta);

    gui.setItem(i, new ItemStack(material));
   }

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
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}
