LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libTouchVGCore
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/Users/hucanhui/Downloads/drawboard/Touchvg22/src/main/jni/addlog.py \
	/Users/hucanhui/Downloads/drawboard/Touchvg22/src/main/jni/Android.mk \
	/Users/hucanhui/Downloads/drawboard/Touchvg22/src/main/jni/Application.mk \
	/Users/hucanhui/Downloads/drawboard/Touchvg22/src/main/jni/build.sh \
	/Users/hucanhui/Downloads/drawboard/Touchvg22/src/main/jni/replacejstr.py \
	/Users/hucanhui/Downloads/drawboard/Touchvg22/src/main/jni/touchvg_java_wrap.cpp \

LOCAL_C_INCLUDES += /Users/hucanhui/Downloads/drawboard/Touchvg22/src/main/jni
LOCAL_C_INCLUDES += /Users/hucanhui/Downloads/drawboard/Touchvg22/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
