package com.daoshengwanwu.android.work.service;


import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;


public class AuxiliaryService extends AccessibilityService {
    private static final String PRE_STR = "com.tencent.mm:id/";

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
                main();
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
                main();
                break;

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED: {
                main();
            } break;

            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                break;

            default:
                break;
        }
    }

    public void main() {
        AccessibilityNodeInfo rootNodeInfo = getRootInActiveWindow();
        if (rootNodeInfo == null) {
            Toast.makeText(this, "无法获取根节点信息", Toast.LENGTH_SHORT).show();
            return;
        }

        // 获取所有复选框
        // ad和a8分别对应删除复选框和复选框状态
        List<AccessibilityNodeInfo> afNodes =
                rootNodeInfo.findAccessibilityNodeInfosByViewId(
                        ResourceId.CHAT_LIST_VIEW_ITEM_CONTAINER);

        List<AccessibilityNodeInfo> a_Nodes =
                rootNodeInfo.findAccessibilityNodeInfosByViewId(ResourceId.CHAT_LIST_VIEW_CHECKBOX);
        int size = Math.min(afNodes.size(), a_Nodes.size());

        // 过去复选框列表并对其逐个点击选中
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                AccessibilityNodeInfo adNode = afNodes.get(i);
                AccessibilityNodeInfo a8Node = a_Nodes.get(i);
                if (!a8Node.isChecked()) {
                    performClick(adNode);
                } else {
                    break;
                }
            }
        }
    }

    //模拟点击事件
    public static void performClick(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }

        if (nodeInfo.isClickable()) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);

        } else {
            performClick(nodeInfo.getParent());

        }
    }

    @Override
    public void onInterrupt() { }

    private AccessibilityNodeInfo getChatPageListView(AccessibilityNodeInfo rootInfo) {
        List<AccessibilityNodeInfo> searchResult =
                rootInfo.findAccessibilityNodeInfosByViewId(ResourceId.CHAT_LIST_VIEW_CONTAINER);

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
        public static final String CHAT_LIST_VIEW_CHECKBOX = PRE_STR + "a_";
        public static final String CHAT_LIST_VIEW_ITEM_TIME = PRE_STR + "ag";
        public static final String CHAT_LIST_VIEW_CONTAINER = PRE_STR + "alc";
        public static final String CHAT_LIST_VIEW_ITEM_CONTAINER = PRE_STR + "af";
    }
}
