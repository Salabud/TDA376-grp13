
/**
 * Abstract base class for mouse tools used in the game interface.
 * Provides trigger flags and an execute method to be implemented by subclasses.
 */
package controller.mouseTool;

import model.datastructures.Position;
import model.world.World;

public abstract class MouseTool {
    /**
     * Indicates if the tool is triggered by a mouse press event.
     */
    protected boolean pressTriggered = false;
    /**
     * Indicates if the tool is triggered by a mouse click event.
     */
    protected boolean clickTriggered = false;
    /**
     * Indicates if the tool is triggered by a mouse drag event.
     */
    protected boolean draggedTriggered = false;
    /**
     * Indicates if the tool is triggered by a mouse release event.
     */
    protected boolean releaseTriggered = false;

    /**
     * Executes the tool's action at the given position in the world.
     * @param world the world to act upon
     * @param position the position where the action is performed
     */
    public void execute(World world, Position position){}

    /**
     * @return true if the tool is triggered by a mouse press event
     */
    public boolean isPressTriggered() {
        return pressTriggered;
    }

    /**
     * @return true if the tool is triggered by a mouse click event
     */
    public boolean isClickTriggered() {
        return clickTriggered;
    }

    /**
     * @return true if the tool is triggered by a mouse drag event
     */
    public boolean isDraggedTriggered() {
        return draggedTriggered;
    }

    /**
     * @return true if the tool is triggered by a mouse release event
     */
    public boolean isReleaseTriggered() {
        return releaseTriggered;
    }
}
