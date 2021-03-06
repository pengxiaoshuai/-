package com.xms.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决gridview在scrollview中显示不全的问题
 * @author 彭其煊
 * @version 1.0
 * @date 2017.2.24
 */

public class MGridView extends GridView {

	public boolean hasScrollBar = true;

	/**
	 * @param context
	 */
	public MGridView(Context context) {
		this(context, null);
	}

	public MGridView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public MGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = heightMeasureSpec;
		if (hasScrollBar) {
			expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
					MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

}
