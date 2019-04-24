package com.daoshengwanwu.android.work.service;


import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;


public class AuxiliaryService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
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

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED: {
                AccessibilityNodeInfo sourceNode = event.getSource();

                if (sourceNode.getClassName().equals("android.widget.CheckBox")) {
                    return;
                }

                AccessibilityNodeInfo rootInfo = getRootInActiveWindow();

                if (rootInfo == null) {
                    Toast.makeText(this, "无法获取到根节点信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<AccessibilityNodeInfo> rst = rootInfo.findAccessibilityNodeInfosByViewId(
                        ResourceId.CHAT_LIST_VIEW_CHECKBOX);

                if (rst == null || rst.size() <= 0) {
                    return;
                }

                for (AccessibilityNodeInfo info : rst) {
                    if (!info.isCheckable() || info.isChecked()) {
                        continue;
                    }

                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            } break;

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


    public static final class ResourceId {
        public static final String CHAT_LIST_VIEW_CHECKBOX = "com.tencent.mm:id/a_";
    }
}
