/*
  Nearly all the code used in this file originates
  from Botania made by <Vazkii>. It has been altered
  to work for, and distributed as part of,
  the Runology mod created by <codycoolwaffle>.
  
  Runology is Open Source and distributed under a
  Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
  http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_US
  
  Check out the corresponding Github's here:
  https://github.com/Vazkii/Botania
  https://github.com/codycoolwaffle/Runology
 */
package ccw.wafflekingdom.runology.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import javax.annotation.Nonnull;

import ccw.wafflekingdom.runology.api.RunologyAPI;
import ccw.wafflekingdom.runology.api.tome.TomeEntry;
import ccw.wafflekingdom.runology.common.Runology;
import ccw.wafflekingdom.runology.common.core.helper.ItemNBTHelper;
import ccw.wafflekingdom.runology.common.lib.LibGuiIDs;
import ccw.wafflekingdom.runology.common.lib.LibItemNames;
import ccw.wafflekingdom.runology.common.lib.LibMisc;

public class ItemRunicTome extends ItemMod
{
	//private static final String TAG_KNOWLEDGE_PREFIX = "knowledge.";//TODO add module
	private static final String TAG_FORCED_MESSAGE = "forcedMessage";
	private static final String TAG_QUEUE_TICKS = "queueTicks";
	private boolean skipSound = true;//TODO add sounds
	
	public ItemRunicTome()
	{
		super(LibItemNames.RUNIC_TOME);
		setMaxStackSize(1);
	}
	
	@Nonnull
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand,
	                                  EnumFacing side, float par8, float par9, float par10)
	{
		/*if(player.isSneaking()) {
			Block block = world.getBlockState(pos).getBlock();
			
			if(block != null) {
				if(block instanceof ITomeable) {
					ItemStack stack = player.getHeldItem(hand);
					TomeEntry entry = ((ITomeable) block).getEntry(world, pos, player, stack);
					if(entry != null /*&& isKnowledgeUnlocked(stack, entry.getKnowledgeType())*//*) //TODO uncomment when module is added
					{
						Runology.proxy.setEntryToOpen(entry);
						
						openBook(player, stack, world, false);
						return EnumActionResult.SUCCESS;
					}
				} else if(world.isRemote) {
					RayTraceResult mop = new RayTraceResult(new Vec3d(par8, par9, par10), side, pos);
					return Runology.proxy.openWikiPage(world, block, mop) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
				}
			}
		}*///TODO uncomment when wiki lookup is added
		
		return EnumActionResult.PASS;
	}
	
	/*@Override
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> list) {
		if(isInCreativeTab(tab)) {
			list.add(new ItemStack(this));
			ItemStack creative = new ItemStack(this);
			for(String s : RunologyAPI.knowledgeTypes.keySet()) {
				KnowledgeType type = RunologyAPI.knowledgeTypes.get(s);
				unlockKnowledge(creative, type);
			}
			list.add(creative);
		}
	}*///TODO uncomment when module is added
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, World world, List<String> stacks,
	                           ITooltipFlag flags)
	{
		/*if(GuiScreen.isShiftKeyDown())
		{
			String edition = TextFormatting.GOLD + I18n.format("runologymisc.edition", getEdition());
			if(!edition.isEmpty())
				stacks.add(edition);
			
			List<KnowledgeType> typesKnown = new ArrayList<>();
			for(String s : RunologyAPI.knowledgeTypes.keySet()) {
				KnowledgeType type = RunologyAPI.knowledgeTypes.get(s);
				if(isKnowledgeUnlocked(par1ItemStack, type))
					typesKnown.add(type);
			}
			
			String format = typesKnown.size() == 1 ? "runologymisc.knowledgeTypesSingular" : "runologymisc.knowledgeTypesPlural";
			addStringToTooltip(I18n.format(format, typesKnown.size()), stacks);
			
			for(KnowledgeType type : typesKnown)
				addStringToTooltip(" \u2022 " + I18n.format(type.getUnlocalizedName()), stacks);
			
		} else addStringToTooltip(I18n.format("runologymisc.shiftinfo"), stacks);*///TODO uncomment when module is added
	}
	
	private void addStringToTooltip(String s, List<String> tooltip)
	{
		tooltip.add(s.replaceAll("&", "\u00a7"));
	}
	
	@SideOnly(Side.CLIENT)
	public static String getEdition()
	{
		String version = LibMisc.BUILD;
		int build = version.contains("GRADLE") ? 0 : Integer.parseInt(version); return "null";
		//return build == 0 ? I18n.format("runologymisc.devEdition") : MathHelper.numberToOrdinal(build);
	}
	
	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
	                                                @Nonnull EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand); String force = getForcedPage(stack);
		if(force != null && !force.isEmpty())
		{
			TomeEntry entry = getEntryFromForce(stack); if(entry != null)
			Runology.proxy.setEntryToOpen(entry);
		else
			player.sendMessage(new TextComponentTranslation("botaniamisc.cantOpen")
					                   .setStyle(new Style().setColor(TextFormatting.RED)));
			setForcedPage(stack, "");
		}
		
		openTome(player, stack, world, skipSound);
		//skipSound = false;//TODO uncomment when sounds are added
		
		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
	}
	
	public static void openTome(EntityPlayer player, ItemStack stack, World world,
	                            boolean skipSound)
	{
		//ITome t = (ITome) stack.getItem();
		
		/*if(!t.isKnowledgeUnlocked(stack, RunologyAPI.relicKnowledge) && t.isKnowledgeUnlocked(stack, BotaniaAPI.elvenKnowledge))
			for(ItemStack rstack : ItemDice.relicStacks) {
				Item item = rstack.getItem();
				if(PlayerHelper.hasItem(player, s -> s != null && s.getItem() == item)) {
					t.unlockKnowledge(stack, RunologyAPI.relicKnowledge);
					break;
				}
			}*///TODO uncomment when legends are added
		
		Runology.proxy.setTomeStack(stack); Runology.LOGGER.info(Runology.instance.toString());
		Runology.LOGGER.info(LibGuiIDs.TOME); Runology.LOGGER.info(world.toString());
		player.openGui(Runology.instance, LibGuiIDs.TOME, world, 0, 0, 0);
		//if(!world.isRemote) {
		//	if(!skipSound)
		//		world.playSound(null, player.posX, player.posY, player.posZ, ModSounds.lexiconOpen, SoundCategory.PLAYERS, 0.5F, 1F);
		//	PlayerHelper.grantCriterion((EntityPlayerMP) player, new ResourceLocation(LibMisc.MOD_ID, "main/lexicon_use"), "code_triggered");
		//}//TODO uncomment when sounds are added
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int idk, boolean something)
	{
		int ticks = getQueueTicks(stack); if(ticks > 0 && entity instanceof EntityPlayer)
	{
		//skipSound = ticks < 5;//TODO uncomment when sounds are added
		if(ticks == 1)
			onItemRightClick(world, (EntityPlayer) entity, EnumHand.MAIN_HAND);
		
		setQueueTicks(stack, ticks - 1);
	}
	}
	
	/*@Nonnull
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.UNCOMMON;
	}
	
	@Override
	public boolean isKnowledgeUnlocked(ItemStack stack, KnowledgeType knowledge) {
		return knowledge.autoUnlock || ItemNBTHelper.getBoolean(stack, TAG_KNOWLEDGE_PREFIX + knowledge.id, false);
	}
	
	@Override
	public void unlockKnowledge(ItemStack stack, KnowledgeType knowledge) {
		ItemNBTHelper.setBoolean(stack, TAG_KNOWLEDGE_PREFIX + knowledge.id, true);
	}*///TODO uncomment when module and legends are added
	
	public static void setForcedPage(ItemStack stack, String forced)
	{
		ItemNBTHelper.setString(stack, TAG_FORCED_MESSAGE, forced);
	}
	
	public static String getForcedPage(ItemStack stack)
	{
		return ItemNBTHelper.getString(stack, TAG_FORCED_MESSAGE, "");
	}
	
	private static TomeEntry getEntryFromForce(ItemStack stack)
	{
		String force = getForcedPage(stack);
		
		for(TomeEntry entry : RunologyAPI.getEntries())
			if(entry.getUnlocalizedName().equals(force))
				if(entry
				   != null /*&& ((ItemRunicTome) stack.getItem()).isKnowledgeUnlocked(stack, entry.getKnowledgeType())*/)//Uncomment when module is added
					return entry;
		
		return null;
	}
	
	public static int getQueueTicks(ItemStack stack)
	{
		return ItemNBTHelper.getInt(stack, TAG_QUEUE_TICKS, 0);
	}
	
	public static void setQueueTicks(ItemStack stack, int ticks)
	{
		ItemNBTHelper.setInt(stack, TAG_QUEUE_TICKS, ticks);
	}
	
	public static String getTitle(ItemStack stack)
	{
		String title = ModItems.runicTome.getItemStackDisplayName(ItemStack.EMPTY);
		if(!stack.isEmpty())
			title = stack.getDisplayName();
		
		String akashicTomeNBT = "akashictome:displayName";
		title = ItemNBTHelper.getString(stack, akashicTomeNBT, title); return title;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0,
		                                           new ModelResourceLocation(getRegistryName(),
		                                                                     "inventory"));
	}
}