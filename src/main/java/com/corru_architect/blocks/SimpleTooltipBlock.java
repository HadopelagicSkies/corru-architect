package com.corru_architect.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SimpleTooltipBlock extends Block {
    public SimpleTooltipBlock(Settings settings) {
        super(settings);
    }

    private String tooltipText = "";
    private Formatting tooltipColor = Formatting.WHITE;
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(!tooltipText.isEmpty())
            tooltip.add(Text.translatable(tooltipText).formatted(tooltipColor));
        super.appendTooltip(stack, context, tooltip, type);
    }

    public void setTooltipDetails(String tooltipText, Formatting tooltipColor){
        this.tooltipText = tooltipText;
        this.tooltipColor = tooltipColor;
    }
}
