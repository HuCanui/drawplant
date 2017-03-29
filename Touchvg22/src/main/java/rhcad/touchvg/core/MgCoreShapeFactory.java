/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package rhcad.touchvg.core;

public class MgCoreShapeFactory {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected MgCoreShapeFactory(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MgCoreShapeFactory obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        touchvgJNI.delete_MgCoreShapeFactory(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public MgCoreShapeFactory() {
    this(touchvgJNI.new_MgCoreShapeFactory(), true);
  }

  public MgBaseShape createShape(int type) {
    long cPtr = touchvgJNI.MgCoreShapeFactory_createShape(swigCPtr, this, type);
    return (cPtr == 0) ? null : new MgBaseShape(cPtr, false);
  }

}