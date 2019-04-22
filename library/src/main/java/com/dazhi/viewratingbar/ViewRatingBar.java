package com.dazhi.viewratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.Objects;

/**
 * 功能：评分条
 * 描述：
 * 作者：WangZezhi
 * 邮箱：wangzezhi528@163.com
 * 创建日期：2019/4/22 09:37
 * 修改日期：2019/4/22 09:37
 */
public class ViewRatingBar extends LinearLayout {
    // xml 属性定义
    private boolean starClickable=true; // 是否可点击, 默认可点击
    private int starCount; // 所有星数
    private float starSize; // 星的尺寸
    private float starSpace; //星之间的间隔
    private Drawable starImageEmpty;
    private Drawable starImageFill;
    // 外部可设置
    private OnRatingListener onRatingListener;
    //
    private int intCurrStarCount; // 当前星选中数


    public ViewRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        //
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewRatingBar);
        starClickable = ta.getBoolean(R.styleable.ViewRatingBar_starClickable, true);
        starCount = ta.getInteger(R.styleable.ViewRatingBar_starCount, 5);
        starSize = ta.getDimension(R.styleable.ViewRatingBar_starSize, 20F);
        starSpace = ta.getDimension(R.styleable.ViewRatingBar_starSpace, 20F);
        starImageEmpty = ta.getDrawable(R.styleable.ViewRatingBar_starImageEmpty);
        starImageFill = ta.getDrawable(R.styleable.ViewRatingBar_starImageFill);
        ta.recycle();
        //
        for (int i = 0; i < starCount; i++) {
            ImageView imageView = getStarImageView(context, i+1);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (starClickable) {
                        intCurrStarCount = indexOfChild(v) + 1;
                        setCurrStarCount(intCurrStarCount, true);
                        if (onRatingListener != null) {
                            onRatingListener.onRating(v, intCurrStarCount);
                        }
                    }
                }
            });
            addView(imageView);
        }
    }

    private ImageView getStarImageView(Context context, int index) {
        ImageView imageView = new ImageView(context);
        // 最后一颗星不添加间隔
        if(starCount!=index) {
            LayoutParams lp = new LayoutParams(Math.round(starSize), Math.round(starSize));
            lp.setMargins(0, 0, Math.round(starSpace), 0);
            imageView.setLayoutParams(lp);
        }
        imageView.setImageDrawable(starImageEmpty);
        return imageView;
    }


    /**
     * =======================================
     * 作者：WangZezhi  (2019/4/22  09:38)
     * 功能：改变监听
     * 描述：
     * =======================================
     */
    public interface OnRatingListener {
        void onRating(View currView, int intCurrStarCount);
    }


    /**
     * =======================================
     * 作者：WangZezhi  (2019/4/22  09:40)
     * 功能：
     * 描述：
     * =======================================
     */
    // 获得当前选中星数
    public int getCurrStarCount() {
        return intCurrStarCount;
    }

    public void setCurrStarCount(int mCurrStarCount, boolean animation) {
        mCurrStarCount = mCurrStarCount > this.starCount ? this.starCount : mCurrStarCount;
        mCurrStarCount = mCurrStarCount < 0 ? 0 : mCurrStarCount;
        for (int i = 0; i < mCurrStarCount; ++i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starImageFill);
            if (animation) {
                ScaleAnimation sa = new ScaleAnimation(0, 0, 1, 1);
                getChildAt(i).startAnimation(sa);
            }
        }
        for (int i = this.starCount - 1; i >= intCurrStarCount; --i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starImageEmpty);
        }
    }

    /**
     * =======================================
     * 作者：WangZezhi  (2019/4/22  10:08)
     * 功能：
     * 描述：
     * =======================================
     */
    public void setStarClickable(boolean starClickable) {
        this.starClickable = starClickable;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public void setStarSize(float starSize) {
        this.starSize = starSize;
    }

    public void setStarSpace(float starSpace) {
        this.starSpace = starSpace;
    }

    public void setStarImageEmpty(Drawable starImageEmpty) {
        this.starImageEmpty = starImageEmpty;
    }

    public void setStarImageFill(Drawable starImageFill) {
        this.starImageFill = starImageFill;
    }

    public void setOnRatingListener(OnRatingListener onRatingListener) {
        this.onRatingListener = onRatingListener;
    }


}
