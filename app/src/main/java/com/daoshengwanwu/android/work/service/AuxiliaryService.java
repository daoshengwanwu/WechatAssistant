package com.daoshengwanwu.android.work.service;


import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;


public class AuxiliaryService extends AccessibilityService {
    private static final String TAG = "AuxiliaryService";


    public AuxiliaryService() { }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int a;
        int eventType = event.getEventType();

        AccessibilityNodeInfo rootInfo = getRootInActiveWindow();
        AccessibilityNodeInfo sourceInfo = event.getSource();

        if (rootInfo == null) {
            return;
        }

        Log.d(TAG, "onAccessibilityEvent: changed" + rootInfo.getPackageName());
        List<AccessibilityNodeInfo> rst = rootInfo.findAccessibilityNodeInfosByText("取消");
        if (rst.size() > 0) {
            Log.d(TAG, "onAccessibilityEvent: 找到取消置顶，getPackageName: " + rootInfo.getPackageName()
            + "eventType: " + eventType);
        }
        
        rst = rootInfo.findAccessibilityNodeInfosByText("复制");
        if (rst.size() > 0) {
            Log.d(TAG, "onAccessibilityEvent: 找到复制" + rootInfo.getPackageName()
                    + "eventType: " + eventType);
        }
        
        rst = rootInfo.findAccessibilityNodeInfosByText("邮件发送");
        if (rst.size() > 0) {
            Log.d(TAG, "onAccessibilityEvent: 找到邮件发送" + rootInfo.getPackageName()
                    + "eventType: " + eventType);
        }
        

        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                if (!"com.tencent.mm".equals(event.getPackageName().toString())) {
                    break;
                }

                if (rootInfo == null) {
                    break;
                }

                List<AccessibilityNodeInfo> searchResult =
                        rootInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/alc");

                AccessibilityNodeInfo listViewNode;

                for (AccessibilityNodeInfo info : searchResult) {
                    for (int i = 0; i < info.getChildCount(); i++) {
                        if ("android.widget.ListView".equals(
                                info.getChild(i).getClassName().toString())) {

                            listViewNode = info.getChild(i);
                            Log.d(TAG, "onAccessibilityEvent: 找到listView");
                        }
                    }
                }


                break;

            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_ASSIST_READING_CONTEXT:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                a = 20;

                break;

            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                a = 20;

                break;

            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                a = 10;
                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:

                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                a = 20;

                break;

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                a = 20;
                break;

            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                a = 20;
                break;

            default: break;
        }
    }


    @Override
    public void onInterrupt() { }
}
