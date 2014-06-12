package com.edxmod.electrodynamics.common.item.gas;

import com.edxmod.electrodynamics.api.EDXBlockHelper;
import net.minecraft.item.ItemBucket;

public class ItemBucketGas extends ItemBucket {

    public ItemBucketGas() {
        super(EDXBlockHelper.get("naturalGas"));
    }
}
