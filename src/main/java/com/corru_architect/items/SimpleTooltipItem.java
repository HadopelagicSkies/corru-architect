package com.corru_architect.items;

import com.corru_architect.CorruArchitect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleTooltipItem extends Item {
    public SimpleTooltipItem(Settings settings) {
        super(settings);
    }

    private List<String> tooltipText = new ArrayList<>();
    private List<Identifier> tooltipIdentifier = new ArrayList<>();
    private Formatting tooltipColor = Formatting.WHITE;
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!tooltipIdentifier.isEmpty()){
            for (Identifier text : tooltipIdentifier) {
                tooltip.add(Text.translatable(text.toTranslationKey()).formatted(tooltipColor).setStyle(Style.EMPTY.withFont(CorruArchitect.FONT_ID).withColor(tooltipColor)));
            }
        }else if(!tooltipText.isEmpty()){
                for (String text : tooltipText) {
                    tooltip.add(Text.translatable(text).formatted(tooltipColor).setStyle(Style.EMPTY.withFont(CorruArchitect.FONT_ID).withColor(tooltipColor)));
                }
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public void setTooltipDetails(Formatting tooltipColor, String... tooltipText ){
        this.tooltipText = Arrays.stream(tooltipText).toList();
        this.tooltipColor = tooltipColor;
    }

    public void setTooltipDetails(Formatting tooltipColor, Identifier... tooltipIdentifier ){
        this.tooltipIdentifier = Arrays.stream(tooltipIdentifier).toList();
        this.tooltipColor = tooltipColor;
    }
}
