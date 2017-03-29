/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package rhcad.touchvg.core;

public class MgEllipse extends MgBaseRect {
  private transient long swigCPtr;

  protected MgEllipse(long cPtr, boolean cMemoryOwn) {
    super(touchvgJNI.MgEllipse_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MgEllipse obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        touchvgJNI.delete_MgEllipse(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public MgEllipse() {
    this(touchvgJNI.new_MgEllipse(), true);
  }

  public static MgEllipse create() {
    long cPtr = touchvgJNI.MgEllipse_create();
    return (cPtr == 0) ? null : new MgEllipse(cPtr, false);
  }

  public static int Type() {
    return touchvgJNI.MgEllipse_Type();
  }

  public static MgEllipse cast(MgBaseShape obj) {
    long cPtr = touchvgJNI.MgEllipse_cast(MgBaseShape.getCPtr(obj), obj);
    return (cPtr == 0) ? null : new MgEllipse(cPtr, false);
  }

  public static MgEllipse fromHandle(int h) {
    long cPtr = touchvgJNI.MgEllipse_fromHandle(h);
    return (cPtr == 0) ? null : new MgEllipse(cPtr, false);
  }

  public MgObject clone() {
    long cPtr = touchvgJNI.MgEllipse_clone(swigCPtr, this);
    return (cPtr == 0) ? null : new MgObject(cPtr, false);
  }

  public void copy(MgObject src) {
    touchvgJNI.MgEllipse_copy(swigCPtr, this, MgObject.getCPtr(src), src);
  }

  public void release() {
    touchvgJNI.MgEllipse_release(swigCPtr, this);
  }

  public boolean equals(MgObject src) {
    return touchvgJNI.MgEllipse_equals(swigCPtr, this, MgObject.getCPtr(src), src);
  }

  public int getType() {
    return touchvgJNI.MgEllipse_getType(swigCPtr, this);
  }

  public boolean isKindOf(int type) {
    return touchvgJNI.MgEllipse_isKindOf(swigCPtr, this, type);
  }

  public Box2d getExtent() {
    return new Box2d(touchvgJNI.MgEllipse_getExtent(swigCPtr, this), true);
  }

  public void update() {
    touchvgJNI.MgEllipse_update(swigCPtr, this);
  }

  public void transform(Matrix2d mat) {
    touchvgJNI.MgEllipse_transform(swigCPtr, this, Matrix2d.getCPtr(mat), mat);
  }

  public void clear() {
    touchvgJNI.MgEllipse_clear(swigCPtr, this);
  }

  public void clearCachedData() {
    touchvgJNI.MgEllipse_clearCachedData(swigCPtr, this);
  }

  public int getPointCount() {
    return touchvgJNI.MgEllipse_getPointCount(swigCPtr, this);
  }

  public Point2d getPoint(int index) {
    return new Point2d(touchvgJNI.MgEllipse_getPoint(swigCPtr, this, index), true);
  }

  public void setPoint(int index, Point2d pt) {
    touchvgJNI.MgEllipse_setPoint(swigCPtr, this, index, Point2d.getCPtr(pt), pt);
  }

  public boolean isClosed() {
    return touchvgJNI.MgEllipse_isClosed(swigCPtr, this);
  }

  public boolean hitTestBox(Box2d rect) {
    return touchvgJNI.MgEllipse_hitTestBox(swigCPtr, this, Box2d.getCPtr(rect), rect);
  }

  public boolean draw(int mode, GiGraphics gs, GiContext ctx, int segment) {
    return touchvgJNI.MgEllipse_draw(swigCPtr, this, mode, GiGraphics.getCPtr(gs), gs, GiContext.getCPtr(ctx), ctx, segment);
  }

  public void output(MgPath path) {
    touchvgJNI.MgEllipse_output(swigCPtr, this, MgPath.getCPtr(path), path);
  }

  public boolean save(MgStorage s) {
    return touchvgJNI.MgEllipse_save(swigCPtr, this, MgStorage.getCPtr(s), s);
  }

  public boolean load(MgShapeFactory factory, MgStorage s) {
    return touchvgJNI.MgEllipse_load(swigCPtr, this, MgShapeFactory.getCPtr(factory), factory, MgStorage.getCPtr(s), s);
  }

  public int getHandleCount() {
    return touchvgJNI.MgEllipse_getHandleCount(swigCPtr, this);
  }

  public Point2d getHandlePoint(int index) {
    return new Point2d(touchvgJNI.MgEllipse_getHandlePoint(swigCPtr, this, index), true);
  }

  public boolean setHandlePoint(int index, Point2d pt, float tol) {
    return touchvgJNI.MgEllipse_setHandlePoint(swigCPtr, this, index, Point2d.getCPtr(pt), pt, tol);
  }

  public boolean isHandleFixed(int index) {
    return touchvgJNI.MgEllipse_isHandleFixed(swigCPtr, this, index);
  }

  public int getHandleType(int index) {
    return touchvgJNI.MgEllipse_getHandleType(swigCPtr, this, index);
  }

  public boolean offset(Vector2d vec, int segment) {
    return touchvgJNI.MgEllipse_offset(swigCPtr, this, Vector2d.getCPtr(vec), vec, segment);
  }

  public float hitTest(Point2d pt, float tol, MgHitResult res) {
    return touchvgJNI.MgEllipse_hitTest(swigCPtr, this, Point2d.getCPtr(pt), pt, tol, MgHitResult.getCPtr(res), res);
  }

  public float getRadiusX() {
    return touchvgJNI.MgEllipse_getRadiusX(swigCPtr, this);
  }

  public float getRadiusY() {
    return touchvgJNI.MgEllipse_getRadiusY(swigCPtr, this);
  }

  public void setRadius(float rx, float ry) {
    touchvgJNI.MgEllipse_setRadius__SWIG_0(swigCPtr, this, rx, ry);
  }

  public void setRadius(float rx) {
    touchvgJNI.MgEllipse_setRadius__SWIG_1(swigCPtr, this, rx);
  }

  public boolean setCircle(Point2d center, float radius) {
    return touchvgJNI.MgEllipse_setCircle(swigCPtr, this, Point2d.getCPtr(center), center, radius);
  }

  public boolean setCircle2P(Point2d start, Point2d end) {
    return touchvgJNI.MgEllipse_setCircle2P(swigCPtr, this, Point2d.getCPtr(start), start, Point2d.getCPtr(end), end);
  }

  public boolean setCircle3P(Point2d start, Point2d point, Point2d end) {
    return touchvgJNI.MgEllipse_setCircle3P(swigCPtr, this, Point2d.getCPtr(start), start, Point2d.getCPtr(point), point, Point2d.getCPtr(end), end);
  }

  public boolean isCircle() {
    return touchvgJNI.MgEllipse_isCircle__SWIG_0(swigCPtr, this);
  }

  public static boolean isCircle(MgBaseShape sp) {
    return touchvgJNI.MgEllipse_isCircle__SWIG_1(MgBaseShape.getCPtr(sp), sp);
  }

  public static int crossCircle(Point2d pt1, Point2d pt2, MgBaseShape sp1, MgBaseShape sp2, Point2d hitpt) {
    return touchvgJNI.MgEllipse_crossCircle__SWIG_0(Point2d.getCPtr(pt1), pt1, Point2d.getCPtr(pt2), pt2, MgBaseShape.getCPtr(sp1), sp1, MgBaseShape.getCPtr(sp2), sp2, Point2d.getCPtr(hitpt), hitpt);
  }

  public static int crossCircle(Point2d pt1, Point2d pt2, MgBaseShape sp) {
    return touchvgJNI.MgEllipse_crossCircle__SWIG_1(Point2d.getCPtr(pt1), pt1, Point2d.getCPtr(pt2), pt2, MgBaseShape.getCPtr(sp), sp);
  }

}
