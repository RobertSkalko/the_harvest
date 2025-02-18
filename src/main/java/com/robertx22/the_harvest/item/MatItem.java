package com.robertx22.the_harvest.item;

import com.robertx22.library_of_exile.localization.ExileTranslation;
import com.robertx22.library_of_exile.localization.ITranslated;
import com.robertx22.library_of_exile.localization.TranslationBuilder;
import com.robertx22.the_harvest.main.HarvestMain;
import net.minecraft.world.item.Item;

public class MatItem extends Item implements ITranslated {

    String name;

    public MatItem(String name) {
        super(new Properties().stacksTo(64));
        this.name = name;
    }

    @Override
    public TranslationBuilder createTranslationBuilder() {
        return TranslationBuilder.of(HarvestMain.MODID)
                .desc(ExileTranslation.item(this, name + " Gem of Entanglement"));
    }

    @Override
    public String GUID() {
        return "";
    }
}
