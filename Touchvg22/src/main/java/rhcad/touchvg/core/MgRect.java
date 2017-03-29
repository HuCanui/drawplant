/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package rhcad.touchvg.core;

public class MgRect extends MgBaseRect {
  private transient long swigCPtr;

  protected MgRect(long cPtr, boolean cMemoryOwn) {
    super(touchvgJNI.MgRect_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MgRect obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        touchvgJNI.delete_MgRect(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public MgRect() {
    this(touchvgJNI.new_MgRect(), true);
  }

  public static MgRect create() {
    long cPtr = touchvgJNI.MgRect_create();
    return (cPtr == 0) ? null : new MgRect(cPtr, false);
  }

  public static int Type() {
    return touchvgJNI.MgRect_Type();
  }

  public static MgRect cast(MgBaseShape obj) {
    long cPtr = touchvgJNI.MgRect_cast(MgBaseShape.getCPtr(obj), obj);
    return (cPtr == 0) ? null : new MgRect(cPtr, false);
  }

  public static MgRect fromHandle(int h) {
    long cPtr = touchvgJNI.MgRect_fromHandle(h);
    return (cPtr == 0) ? null : new MgRect(cPtr, false);
  }

  public MgObject clone() {
    long cPtr = touchvgJNI.MgRect_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new MgObject(cPtr, false);
  }

  public void copy(MgObject src) {
    touchvgJNI.MgRect_copy(swigCPtr, this, MgObject.getCPtr(src), src);
  }

  public void release() {
    touchvgJNI.MgRect_release(swigCPtr, this);
  }

  public boolean equals(MgObject src) {
    return touchvgJNI.MgRect_equals(swigCPtr, this, MgObject.getCPtr(src), src);
  }

  public int getType() {
    return touchvgJNI.MgRect_getType(swigCPtr, this);
  }

  public boolean isKindOf(int type) {
    return touchvgJNI.MgRect_isKindOf(swigCPtr, this, type);
  }

  public Box2d getExtent() {
    return new Box2d(touchvgJNI.MgRect_getExtent(swigCPtr, this), true);
  }

  public void update() {
    touchvgJNI.MgRect_update(swigCPtr, this);
  }

  public void transform(Matrix2d mat) {
    touchvgJNI.MgRect_transform(swigCPtr, this, Matrix2d.getCPtr(mat), mat);
  }

  public void clear() {
    touchvgJNI.MgRect_clear(swigCPtr, this);
  }

  public void clearCachedData() {
    touchvgJNI.MgRect_clearCachedData(swigCPtr, this);
  }

  public int getPointCount() {
    return touchvgJNI.MgRect_getPointCount(swigCPtr, this);
  }

  public Point2d getPoint(int index) {
    return new Point2d(touchvgJNI.MgRect_getPoint(swigCPtr, this, index), true);
  }

  public void setPoint(int index, Point2d pt) {
    touchvgJNI.MgRect_setPoint(swigCPtr, this, index, Point2d.getCPtr(pt), pt);
  }

  public boolean isClosed() {
    return touchvgJNI.MgRect_isClosed(swigCPtr, this);
  }

  public boolean hitTestBox(Box2d rect) {
    return touchvgJNI.MgRect_hitTestBox(swigCPtr, this, Box2d.getCPtr(rect), rect);
  }

  public boolean draw(int mode, GiGraphics gs, GiContext ctx, int segment) {
    return touchvgJNI.MgRect_draw(swigCPtr, this, mode, GiGraphics.getCPtr(gs), gs, GiContext.getCPtr(ctx), ctx, segment);
  }

  public void output(MgPath path) {
    touchvgJNI.MgRect_output(swigCPtr, this, MgPath.getCPtr(path), path);
  }

  public boolean save(MgStorage s) {
    return touchvgJNI.MgRect_save(swigCPtr, this, MgStorage.getCPtr(s), s);
  }

  public boolean load(MgShapeFactory factory, MgStorage s) {
    return touchvgJNI.MgRect_load(swigCPtr, this, MgShapeFactory.getCPtr(factory), factory, MgStorage.getCPtr(s), s);
  }

  public int getHandleCount() {
    return touchvgJNI.MgRect_getHandleCount(swigCPtr, this);
  }

  public Point2d getHandlePoint(int index) {
    return new Point2d(touchvgJNI.MgRect_getHandlePoint(swigCPtr, this, index), true);
  }

  public boolean setHandlePoint(int index, Point2d pt, float tol) {
    return touchvgJNI.MgRect_setHandlePoint(swigCPtr, this, index, Point2d.getCPtr(pt), pt, tol);
  }

  public boolean isHandleFixed(int index) {
    return touchvgJNI.MgRect_isHandleFixed(swigCPtr, this, index);
  }

  public int getHandleType(int index) {
    return touchvgJNI.MgRect_getHandleType(swigCPtr, this, index);
  }

  public boolean offset(Vector2d vec, int segment) {
    return touchvgJNI.MgRect_offset(swigCPtr, this, Vector2d.getCPtr(vec), vec, segment);
  }

  public float hitTest(Point2d pt, float tol, MgHitResult res) {
    return touchvgJNI.MgRect_hitTest(swigCPtr, this, Point2d.getCPtr(pt), pt, tol, MgHitResult.getCPtr(res), res);
  }

}
