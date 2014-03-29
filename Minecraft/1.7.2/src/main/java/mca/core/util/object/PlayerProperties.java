package mca.core.util.object;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import mca.core.MCA;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.radixshock.radixcore.logic.NBTHelper;

public class PlayerProperties implements IExtendedEntityProperties
{
	public static final transient String ID = "MCA_PlayerProperties";

	private transient EntityPlayer player;

	public boolean hasBaby;
	public boolean isEngaged;
	public boolean isMarried;
	public boolean hideSleepingTag;
	public boolean overwriteTestificates = true;
	public boolean displayMoodParticles = true;
	public boolean childrenGrowAutomatically = true;
	public boolean blockMarriageRequests;
	public boolean showNameTags = true;
	public boolean isMonarch;
	public List<String> blockList = new ArrayList<String>();
	public int spouseMCAId;
	public int heirId;
	public int stat_villagersExecuted;
	public int stat_villagersMadePeasants;
	public int stat_guardsMadeKnights;
	public int stat_wivesExecuted;
	public String customName;
	public String gender;
	public String genderPreference;

	public PlayerProperties(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt) 
	{
		for (Field field : this.getClass().getDeclaredFields())
		{
			try
			{
				final String fieldName = field.getName();
				final String fieldType = field.getType().toString();

				if (!Modifier.isTransient(field.getModifiers()))
				{
					if (fieldType.contains("String"))
					{
						nbt.setString(fieldName, (String)field.get(this));
					}

					else if (fieldType.contains("boolean"))
					{
						nbt.setBoolean(fieldName, Boolean.parseBoolean(field.get(this).toString()));
					}

					else if (fieldType.contains("double"))
					{
						nbt.setDouble(fieldName, Double.parseDouble(field.get(this).toString()));
					}

					else if (fieldType.contains("int"))
					{
						nbt.setInteger(fieldName, Integer.parseInt(field.get(this).toString()));
					}

					else if (fieldType.contains("float"))
					{
						nbt.setFloat(fieldName, Float.parseFloat(field.get(this).toString()));
					}

					else
					{
						MCA.getInstance().getLogger().log("Unsupported field type: " + fieldType + ". Field name: " + fieldName + ".");
					}
				}
			}

			catch (NullPointerException e)
			{
				continue;
			}

			catch (IllegalArgumentException e)
			{
				continue;
			}

			catch (IllegalAccessException e)
			{
				continue;
			}
		}
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) 
	{
		for (Field field : this.getClass().getDeclaredFields())
		{
			try
			{
				final String fieldName = field.getName();
				final String fieldType = field.getType().toString();

				if (!Modifier.isTransient(field.getModifiers()))
				{
					if (fieldType.contains("String"))
					{
						field.set(this, String.valueOf(nbt.getString(fieldName)));
					}

					else if (fieldType.contains("boolean"))
					{
						field.set(this, Boolean.valueOf(nbt.getBoolean(fieldName)));
					}

					else if (fieldType.contains("double"))
					{
						field.set(this, Double.valueOf(nbt.getDouble(fieldName)));
					}

					else if (fieldType.contains("int"))
					{
						field.set(this, Integer.valueOf(nbt.getInteger(fieldName)));
					}

					else if (fieldType.contains("float"))
					{
						field.set(this, Float.valueOf(nbt.getFloat(fieldName)));
					}
					
					else
					{
						MCA.getInstance().getLogger().log("Unsupported field type: " + fieldType + ". Field name: " + fieldName + ".");
					}
				}
			}

			catch (NullPointerException e)
			{
				continue;
			}

			catch (IllegalArgumentException e)
			{
				continue;
			}

			catch (IllegalAccessException e)
			{
				continue;
			}
		}
	}

	@Override
	public void init(Entity entity, World world) { }
}
