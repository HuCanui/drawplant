/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package rhcad.touchvg.core;

public class GiPlayShapes {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected GiPlayShapes(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(GiPlayShapes obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        touchvgJNI.delete_GiPlayShapes(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setPlaying(GiPlaying value) {
    touchvgJNI.GiPlayShapes_playing_set(swigCPtr, this, GiPlaying.getCPtr(value), value);
  }

  public GiPlaying getPlaying() {
    long cPtr = touchvgJNI.GiPlayShapes_playing_get(swigCPtr, this);
    return (cPtr == 0) ? null : new GiPlaying(cPtr, false);
  }

  public void setPlayer(MgRecordShapes value) {
    touchvgJNI.GiPlayShapes_player_set(swigCPtr, this, MgRecordShapes.getCPtr(value), value);
  }

  public MgRecordShapes getPlayer() {
    long cPtr = touchvgJNI.GiPlayShapes_player_get(swigCPtr, this);
    return (cPtr == 0) ? null : new MgRecordShapes(cPtr, false);
  }

  public GiPlayShapes() {
    this(touchvgJNI.new_GiPlayShapes(), true);
  }

}
