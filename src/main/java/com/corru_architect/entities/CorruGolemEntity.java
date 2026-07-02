package com.corru_architect.entities;

import com.corru_architect.Humors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class CorruGolemEntity extends MobEntity {

    private static final TrackedData<Humors> HUMOR1 = DataTracker.registerData(CorruGolemEntity.class, Humors.HUMORS_TRACKED_DATA_HANDLER);
    private static final TrackedData<Humors> HUMOR2 = DataTracker.registerData(CorruGolemEntity.class, Humors.HUMORS_TRACKED_DATA_HANDLER);
    private static final TrackedData<Humors> HUMOR3 = DataTracker.registerData(CorruGolemEntity.class, Humors.HUMORS_TRACKED_DATA_HANDLER);

    public CorruGolemEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(HUMOR1,Humors.NONE);
        builder.add(HUMOR2, Humors.NONE);
        builder.add(HUMOR3,Humors.NONE);
    }

    public void setHumor1(Humors humor){
        this.dataTracker.set(HUMOR1,humor);
    }
    public Humors getHumor1(){
        return this.dataTracker.get(HUMOR1);
    }

    public void setHumor2(Humors humor){
        this.dataTracker.set(HUMOR2,humor);
    }
    public Humors getHumor2(){
        return this.dataTracker.get(HUMOR2);
    }

    public void setHumor3(Humors humor){
        this.dataTracker.set(HUMOR3,humor);
    }
    public Humors getHumor3(){
        return this.dataTracker.get(HUMOR3);
    }

    public static DefaultAttributeContainer createGolemAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 20.0F).add(EntityAttributes.FOLLOW_RANGE, 24.0).build();
    }
}
