/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package rhcad.touchvg.core;

public class MgCommand {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected MgCommand(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MgCommand obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        touchvgJNI.delete_MgCommand(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected void swigDirectorDisconnect() {
    swigCMemOwn = false;
    delete();
  }

  public void swigReleaseOwnership() {
    swigCMemOwn = false;
    touchvgJNI.MgCommand_change_ownership(this, swigCPtr, false);
  }

  public void swigTakeOwnership() {
    swigCMemOwn = true;
    touchvgJNI.MgCommand_change_ownership(this, swigCPtr, true);
  }

  public MgCommand(String name) {
    this(touchvgJNI.new_MgCommand(name), true);
    touchvgJNI.MgCommand_director_connect(this, swigCPtr, swigCMemOwn, true);
  }

  public void release() {
    touchvgJNI.MgCommand_release(swigCPtr, this);
  }

  public boolean cancel(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_cancel(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_cancelSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean initialize(MgMotion sender, MgStorage s) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_initialize(swigCPtr, this, MgMotion.getCPtr(sender), sender, MgStorage.getCPtr(s), s) : touchvgJNI.MgCommand_initializeSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender, MgStorage.getCPtr(s), s);
  }

  public boolean backStep(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_backStep(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_backStepSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean draw(MgMotion sender, GiGraphics gs) {
    return touchvgJNI.MgCommand_draw(swigCPtr, this, MgMotion.getCPtr(sender), sender, GiGraphics.getCPtr(gs), gs);
  }

  public boolean gatherShapes(MgMotion sender, MgShapes shapes) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_gatherShapes(swigCPtr, this, MgMotion.getCPtr(sender), sender, MgShapes.getCPtr(shapes), shapes) : touchvgJNI.MgCommand_gatherShapesSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender, MgShapes.getCPtr(shapes), shapes);
  }

  public boolean click(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_click(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_clickSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean doubleClick(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_doubleClick(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_doubleClickSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean longPress(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_longPress(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_longPressSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean touchBegan(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_touchBegan(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_touchBeganSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean touchMoved(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_touchMoved(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_touchMovedSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean touchEnded(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_touchEnded(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_touchEndedSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean mouseHover(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_mouseHover(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_mouseHoverSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean twoFingersMove(MgMotion sender) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_twoFingersMove(swigCPtr, this, MgMotion.getCPtr(sender), sender) : touchvgJNI.MgCommand_twoFingersMoveSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender);
  }

  public boolean isDrawingCommand() {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_isDrawingCommand(swigCPtr, this) : touchvgJNI.MgCommand_isDrawingCommandSwigExplicitMgCommand(swigCPtr, this);
  }

  public boolean isFloatingCommand() {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_isFloatingCommand(swigCPtr, this) : touchvgJNI.MgCommand_isFloatingCommandSwigExplicitMgCommand(swigCPtr, this);
  }

  public boolean doContextAction(MgMotion sender, int action) {
    return (getClass() == MgCommand.class) ? touchvgJNI.MgCommand_doContextAction(swigCPtr, this, MgMotion.getCPtr(sender), sender, action) : touchvgJNI.MgCommand_doContextActionSwigExplicitMgCommand(swigCPtr, this, MgMotion.getCPtr(sender), sender, action);
  }

}
