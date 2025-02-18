package com.robertx22.the_harvest.capability;

import com.google.gson.JsonSyntaxException;
import com.robertx22.library_of_exile.utils.LoadSave;
import com.robertx22.the_harvest.main.HarvestMain;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HarvestEntityCap implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public LivingEntity en;

    public static final ResourceLocation RESOURCE = new ResourceLocation(HarvestMain.MODID, "entity");
    public static Capability<HarvestEntityCap> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
    });

    transient final LazyOptional<HarvestEntityCap> supp = LazyOptional.of(() -> this);

    public HarvestEntityCap(LivingEntity en) {
        this.en = en;
    }

    public static HarvestEntityCap get(LivingEntity entity) {
        return entity.getCapability(INSTANCE).orElse(new HarvestEntityCap(entity));
    }

    public HarvestEntityData data = new HarvestEntityData();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == INSTANCE) {
            return supp.cast();
        }
        return LazyOptional.empty();

    }

    @Override
    public CompoundTag serializeNBT() {
        var nbt = new CompoundTag();

        try {
            LoadSave.Save(data, nbt, "data");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        try {
            this.data = LoadSave.loadOrBlank(HarvestEntityData.class, new HarvestEntityData(), nbt, "data", new HarvestEntityData());


        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

    }
}
