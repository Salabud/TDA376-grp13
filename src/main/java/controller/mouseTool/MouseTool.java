package controller.mouseTool;

import model.datastructures.Position;
import model.world.World;

public abstract class MouseTool {
    protected boolean pressTriggered = false;
    protected boolean clickTriggered = false;
    protected boolean draggedTriggered = false;
    protected boolean releaseTriggered = false;

    public void execute(World world, Position position){}

    public boolean isPressTriggered() {
        return pressTriggered;
    }

    public boolean isClickTriggered() {
        return clickTriggered;
    }

    public boolean isDraggedTriggered() {
        return draggedTriggered;
    }

    public boolean isReleaseTriggered() {
        return releaseTriggered;
    }
}
