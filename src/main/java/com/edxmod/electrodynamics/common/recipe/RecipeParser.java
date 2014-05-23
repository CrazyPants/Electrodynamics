package com.edxmod.electrodynamics.common.recipe;

import com.edxmod.electrodynamics.api.util.RandomStack;
import com.google.gson.Gson;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dmillerw
 * Temporary class?
 */
public class RecipeParser {

	public static void dumpItems(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
		}

		FileWriter fileWriter = new FileWriter(file);

		List<String> keys = new ArrayList<String>();

		for (Object obj : GameData.getItemRegistry().getKeys()) {
			keys.add(obj.toString());
		}

		Collections.sort(keys);

		for (String str : keys) {
			fileWriter.write(str + '\n');
		}

		fileWriter.flush();
		fileWriter.close();
	}

	public static class ParsedRecipe {
		public boolean crash_on_fail;
		public Recipe[] recipes;

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("CRASH_ON_FAIL: " + crash_on_fail);
			sb.append("RECIPES: [");
			for (int i=0; i<recipes.length; i++) {
				Recipe output = recipes[i];
				sb.append(output.toString());
				if (i != recipes.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("]");
			return sb.toString();
		}
	}

	public static class Recipe {
		public String input;
		public int duration = 100;
		public Output[] outputs;

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("INPUT: " + input);
			sb.append("OUTPUT: [");
			for (int i=0; i<outputs.length; i++) {
				Output output = outputs[i];
				sb.append(output.toString());
				if (i != outputs.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("]");
			return sb.toString();
		}
	}

	public static class Output {
		public String item;
		public float chance;

		@Override
		public String toString() {
			return item + " : " + chance;
		}
	}

	public static void parseFile(File file) {
		try {
			FMLLog.info("[Electrodynamics] Parsing " + file.getName());
			ParsedRecipe recipe = new Gson().fromJson(new FileReader(file), ParsedRecipe.class);
			verifyParse(file.getName(), recipe);
		} catch (IOException ex) {
			FMLLog.warning("[Electrodynamics] Failed to parse " + file.getName());
		}
	}

	public static void verifyParse(String name, ParsedRecipe recipe) {
		for (Recipe recipe1 : recipe.recipes) {
			ItemStack input = getItem(recipe1.input);
			RandomStack[] output = new RandomStack[recipe1.outputs.length];

			for (int i=0; i<recipe1.outputs.length; i++) {
				ItemStack item = getItem(recipe1.outputs[i].item);
				float chance = recipe1.outputs[i].chance;
				if (chance < 0F) {
					chance = 0F;
				} else if (chance > 1F) {
					chance = 1F;
				}

				output[i] = new RandomStack(item, recipe1.outputs[i].chance);
			}

			if (recipe.crash_on_fail) {
				if (input == null) {
					throw new NullPointerException();
				} else {
					for (int i=0; i<output.length; i++) {
						if (output[i] == null || output[i].stack == null) {
							throw new NullPointerException();
						}
					}
				}
			}

			RecipeManager.INSTANCE.sieve.register(input, output, recipe1.duration);
		}

		int length = recipe.recipes.length;
		FMLLog.info("[Electrodynamics] Parsed " + name + ". Loaded " + length + (length > 1 ? " recipes" : " recipe"));
	}

	public static ItemStack getItem(String item) {
		ItemStack stack = null;
		String mod_id = "minecraft";
		String input_str = item;
		int meta = 0;

		if (input_str.contains(":")) {
			// has mod id
			mod_id = input_str.substring(0, input_str.indexOf(":"));
			input_str = input_str.substring(input_str.indexOf(":") + 1);
		}

		if (input_str.contains(";")) {
			meta = Integer.parseInt(input_str.substring(input_str.indexOf(";") + 1));
			input_str = input_str.substring(0, input_str.indexOf(";"));
		}

		stack = GameRegistry.findItemStack(mod_id, input_str, 1);

		if (stack != null) {
			stack.setItemDamage(meta);
		}

		return stack;
	}

}
