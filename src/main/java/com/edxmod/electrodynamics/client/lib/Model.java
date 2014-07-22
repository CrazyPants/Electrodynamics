package com.edxmod.electrodynamics.client.lib;

import com.edxmod.electrodynamics.client.model.WrappedModel;

/**
 * @author dmillerw
 */
public enum Model {

    BARREL_WOOD("blocks/barrelWood", ".tcn"),
    HAND_CRANK("blocks/handCrank", ".obj"),
    HAMMER_MILL("blocks/hammerMill", ".obj"),
    KILN("blocks/kiln", ".obj"),
    KINETIC_CRANK("blocks/kineticCrank", ".obj"),
    METAL_PRESS("blocks/metalPress", ".obj"),
    TABLE_SIEVE("blocks/sieveTable", ".obj"),
    SINTERING_OVEN("blocks/sinteringOven", ".obj"),
    SPAWN_MARKER("blocks/skull", ".tcn"),
    TABLE_WOOD("blocks/basicTable", ".obj"),
    TABLE_STONE("blocks/smashingTable", ".obj"),
    WATER_MILL("blocks/mill", ".obj"),
    METAL_SHAFT("blocks/shaft", ".tcn"),
    COMPOST_WOOD("blocks/compostWood", ".tcn"),
    COMPOST_STONE("blocks/compostStone", ".tcn");

    private WrappedModel model;

    private Model(String path, String modelPrefix) {
        this.model = new WrappedModel(path + modelPrefix, path + ".png");
    }

    public void bindTexture() {
        model.bindTexture();
    }

    public void renderAll() {
        model.renderAll();
    }

    public void renderOnly(String... groupNames) {
        model.renderOnly(groupNames);
    }

    public void renderPart(String partName) {
        model.renderPart(partName);
    }

    public void renderAllExcept(String... excludedGroupNames) {
        model.renderAllExcept(excludedGroupNames);
    }
}
