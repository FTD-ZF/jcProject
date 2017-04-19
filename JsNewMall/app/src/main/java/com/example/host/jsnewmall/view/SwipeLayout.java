package com.example.host.jsnewmall.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by host on 2017/3/21.
 */

public class SwipeLayout extends FrameLayout {

    private ViewGroup mBackView; // 后布局
    private ViewGroup mFrontView; // 前布局
    private int mHeight;
    private int mWidth;
    private int mRange;
    private ViewDragHelper mDragHelper; // 拖拽辅助类

    private Status status = Status.Close;
    public enum Status {
        Open, Close, Swiping;
    }

    OnSwipeListener onSwipeListener;

    public interface OnSwipeListener{

        void onOpen(SwipeLayout layout);// 打开了

        void onClose(SwipeLayout layout); // 关闭了

        void onStartOpen(SwipeLayout layout); // 将要打开

        void onStartClose(SwipeLayout layout); // 将要关闭
    }

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0 );
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // 1. 创建ViewDragHelper对象
        mDragHelper = ViewDragHelper.create(this, callback);
    }

    // 3. 重写事件回调
    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View view, int pointerId) {
            return true;
        }

        public int getViewHorizontalDragRange(View child) {
            return mRange; // > 0即可
        };

        // 修正水平方向的位置, 还未发生移动
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            // left 将要移动到的位置的建议值
            // dx 将要发生的变化量
            if(child == mFrontView){
                if(left < -mRange){
                    // 限定前布局的左边界
                    return -mRange;
                }else if (left > 0) {
                    // 限定前布局的右边界
                    return 0;
                }
            }else if (child == mBackView) {
                if(left < mWidth - mRange){
                    // 限定后布局的左边界
                    return mWidth - mRange;
                } else if (left > mWidth) {
                    // 限定后布局的右边界
                    return mWidth;
                }
            }
            return left;
        };

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            // 传递平移事件
            // dx 刚刚发生的水平方向变化量
            if(changedView == mFrontView){
                // 把前布局变化量转递给后布局
                mBackView.offsetLeftAndRight(dx);
            }else if (changedView == mBackView) {
                // 把后布局变化量转递给前布局
                mFrontView.offsetLeftAndRight(dx);
            }

            dispathDragEvent();

            invalidate();// 重绘界面
        };

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // xvel 向右+, 向左-
            // 控件被释放
            if(xvel == 0 && mFrontView.getLeft() < -mRange * 0.5f){
                open();
            }else if (xvel < 0) {
                open();
            }else {
                close();
            }

        };

    };

    /**
     * 更新状态, 监听回调
     */
    protected void dispathDragEvent() {

        // 保存上一个状态
        Status lastStatus = status;
        // 当前状态
        status = updateStatus();

        // 监听回调 (状态发生变化的时候执行)
        if(lastStatus != status && onSwipeListener != null){
            if(status == Status.Close){
                onSwipeListener.onClose(this);
            }else if (status == Status.Open) {
                onSwipeListener.onOpen(this);
            }else if (status == Status.Swiping) {
                if(lastStatus == Status.Close){
                    onSwipeListener.onStartOpen(this);
                }else if (lastStatus == Status.Open) {
                    onSwipeListener.onStartClose(this);
                }
            }
        }

    }

    // 根据前布局的位置获取当前的状态
    private Status updateStatus() {
        int left = mFrontView.getLeft();
        if(left == 0){
            return Status.Close;
        }else if (left == -mRange) {
            return Status.Open;
        }

        return Status.Swiping;
    }

    // 关闭
    public void close() {
        close(true);
    }

    public void close(boolean isSmooth){
        int finalLeft = 0;
        if(isSmooth){
            // 1. 触发平滑动画
            if(mDragHelper.smoothSlideViewTo(mFrontView, finalLeft, 0)){
                ViewCompat.postInvalidateOnAnimation(this);// 重绘界面 -> drawChild -> draw -> computeScroll
            }
        }else {
            layoutContent(false);
        }
    }

    // 打开
    public void open() {
        open(true);
    }

    public void open(boolean isSmooth){
        int finalLeft = -mRange;
        if(isSmooth) {
            // 1. 触发平滑动画
            if(mDragHelper.smoothSlideViewTo(mFrontView, finalLeft, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);// 重绘界面 -> drawChild -> draw -> computeScroll
            }
        } else {
            layoutContent(true);
        }
    }

    // 2. 维持动画的继续
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);// 重绘界面 -> drawChild -> draw -> computeScroll
        }

    }

    // 2. 转交拦截判断, 处理触摸事件
    public boolean onInterceptTouchEvent(android.view.MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        try {
            // 捕获多点触摸异常
            mDragHelper.processTouchEvent(event); // 交由mDragHelper处理触摸事件
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true; // 消费事件
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 获取控件宽高
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        // 获取拖拽范围
        mRange = mBackView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        layoutContent(false);

    }

    /**
     * 根据打开状态摆放内容
     * @param
     */
    private void layoutContent(boolean isOpen) {
        Rect frontRect = computeFrontRect(isOpen);
        // 摆放前布局内容位置, 指定左上右下
        mFrontView.layout(frontRect.left, frontRect.top, frontRect.right, frontRect.bottom);

        Rect backRect = computeBackRectViaFront(frontRect);
        // 摆放后布局内容位置, 指定左上右下
        mBackView.layout(backRect.left, backRect.top, backRect.right, backRect.bottom);

    }

    /**
     * 通过前布局得到后布局矩形
     * @param frontRect
     * @return
     */
    private Rect computeBackRectViaFront(Rect frontRect) {

        int left = frontRect.right;

        return new Rect(left, 0, left + mRange, 0 + mHeight);
    }

    /**
     * 计算前布局矩形
     * @param isOpen 打开状态
     * @return
     */
    private Rect computeFrontRect(boolean isOpen) {
        int left = 0;
        if(isOpen){
            left = -mRange;
        }
        return new Rect(left, 0, left + mWidth, 0 + mHeight);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mBackView = (ViewGroup) getChildAt(0);
        mFrontView = (ViewGroup) getChildAt(1);
    }
}
