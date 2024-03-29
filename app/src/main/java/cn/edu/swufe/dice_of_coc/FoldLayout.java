package cn.edu.swufe.dice_of_coc;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class FoldLayout extends LinearLayout implements View.OnClickListener {

    private boolean init;
    private int layoutId;
    private boolean isShow;
    private int contentHeight;
    private List<View> itemList;
    private LinearLayout content;
    private ValueAnimator showAnimator;
    private ValueAnimator hideAnimator;

    private OnItemClickListener mOnItemClickListener;

    public FoldLayout(Context context) {
        this(context, null);
    }

    public FoldLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FoldLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.FoldLayout,defStyleAttr,0);
        layoutId = ta.getResourceId(R.styleable.FoldLayout_layoutId,-1);
        Log.i("FoldLayout", "FoldLayout: laroutID="+layoutId);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        addDefaultLayout(context);
    }

    /**
     * 初始化Menu和Item容器
     * @param context
     */
    private void addDefaultLayout(Context context) {

        View defaultView = LayoutInflater.from(context).inflate(layoutId, this,true);
        defaultView.setOnClickListener(this);
        content = new LinearLayout(context);//此处的content是一个容器
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //content.setShowDividers(SHOW_DIVIDER_BEGINNING|SHOW_DIVIDER_MIDDLE);
        //content.setDividerDrawable(ContextCompat.getDrawable(context,R.drawable.item_divider));
        content.setOrientation(VERTICAL);
        addView(content,layoutParams);
    }

    @Override
    public void onClick(View v) {
        if(isShow) {
            hideItem();
        } else {
            showItem();         //defaultView的点击事件响应
        }
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//控制子控件的宽和高

        initAnimation();
    }

    /**
     * 动画
     */
    private void initAnimation() {

        contentHeight = content.getMeasuredHeight();
        //contentHeight = content.getMeasuredHeight();
        if(!init) {
            showAnimator = ValueAnimator.ofInt(0,contentHeight);
            showAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    LinearLayout.LayoutParams layoutParams = (LayoutParams) content.getLayoutParams();
                    layoutParams.height = (int) animation.getAnimatedValue();
                    content.setLayoutParams(layoutParams);
                }
            });

            hideAnimator = ValueAnimator.ofInt(contentHeight,0);
            hideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    LinearLayout.LayoutParams layoutParams = (LayoutParams) content.getLayoutParams();
                    layoutParams.height = (int) animation.getAnimatedValue();
                    content.setLayoutParams(layoutParams);
                }
            });
            init = true;
            hide();
        }
    }

    /**
     * 添加Item
     * @param views
     */
    public void addItemView(List<View> views) {
        this.itemList = views;
        for (int i = 0; i < views.size(); i++) {
            final int position = i;
            content.addView(views.get(i));
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null != mOnItemClickListener) {
                        mOnItemClickListener.onItemClick(v,position);//如果点击该item，响应点击事件，参数是position
                    }
                }
            });
        }
    }

    /**
     * 默认隐藏
     */
    public void hide() {
        LinearLayout.LayoutParams layoutParams = (LayoutParams) content.getLayoutParams();
        layoutParams.height = 0;
        content.setLayoutParams(layoutParams);
    }

    /**
     * 显示
     */
    public void showItem() {
        isShow = true;
        showAnimator.start();
    }

    /**
     * 隐藏
     */
    public void hideItem() {
        isShow = false;
        hideAnimator.start();
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
