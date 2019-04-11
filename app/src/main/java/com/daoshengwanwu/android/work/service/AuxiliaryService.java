package com.daoshengwanwu.android.work.service;


import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;


public class AuxiliaryService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();

        AccessibilityNodeInfo rootInfo = getRootInActiveWindow();
        AccessibilityNodeInfo sourceInfo = event.getSource();

        if (rootInfo == null) {
            Toast.makeText(this, "无法获取到根节点信息", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "获取到根节点信息", Toast.LENGTH_SHORT).show();
        List<AccessibilityNodeInfo> searchResult = null;
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Toast.makeText(this, "检测到点击事件, 尝试执行长按操作", Toast.LENGTH_SHORT).show();
                searchResult = rootInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/nu");
                for (AccessibilityNodeInfo info : searchResult) {
                    if (info.isClickable()) {
                        info.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
                    }
                }
                break;

            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                break;

            case AccessibilityEvent.TYPE_ASSIST_READING_CONTEXT:
                break;

            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                break;

            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                break;

            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                break;

            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                break;

            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                break;

            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END:
                break;

            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START:
                break;

            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
                break;

            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED:
                break;

            case AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED:
                break;

            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                break;

            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                break;

            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                break;

            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                break;

            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                break;

            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY:
                break;

            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                break;

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                break;

            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                break;

            default:
                break;
        }
    }

    @Override
    public void onInterrupt() {
    }

    private AccessibilityNodeInfo getChatPageListView(AccessibilityNodeInfo rootInfo) {
        List<AccessibilityNodeInfo> searchResult =
                rootInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/alc");

        for (AccessibilityNodeInfo info : searchResult) {
            for (int i = 0; i < info.getChildCount(); i++) {
                if ("android.widget.ListView".equals(
                        info.getChild(i).getClassName().toString())) {

                    return info.getChild(i);
                }
            }
        }

        return null;
    }
}
