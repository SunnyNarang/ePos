package exodiasolutions.epos.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Sunny on 03-09-2017.
 */


public class CEditText extends EditText {


    private Context context;
    private AttributeSet attrs;
    private int defStyle;

    public CEditText(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public CEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        this.attrs=attrs;
        init();
    }

    public CEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        this.attrs=attrs;
        this.defStyle=defStyle;
        init();
    }

    private void init() {
        Typeface font= Typeface.createFromAsset(getContext().getAssets(), "fonts/JosefinSans-Bold.ttf");
        this.setTypeface(font);
    }
    @Override
    public void setTypeface(Typeface tf, int style) {
        tf= Typeface.createFromAsset(getContext().getAssets(), "fonts/JosefinSans-Bold.ttf");
        super.setTypeface(tf, style);
    }

    @Override
    public void setTypeface(Typeface tf) {
        tf= Typeface.createFromAsset(getContext().getAssets(), "fonts/JosefinSans-Bold.ttf");
        super.setTypeface(tf);
    }
}