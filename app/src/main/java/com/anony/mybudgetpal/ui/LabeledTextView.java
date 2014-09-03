package com.anony.mybudgetpal.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anony.mybudgetpal.R;

/**
 * TODO: document your custom view class.
 */
public class LabeledTextView extends RelativeLayout {
    EditText m_textView;

    private enum InputType {
        STRING(0),
        INTEGER(1),
        DATE(2);

        private int m_code;

        InputType(int code){
            m_code = code;
        }

        int getCode(){
            return m_code;
        }
    }

    public LabeledTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public LabeledTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LabeledTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Get the views we care about from the layout.
        LayoutInflater.from(context).inflate(R.layout.view_labeled_text_children, this, true);
        TextView labelView = (TextView)findViewById(R.id.labeledText_label);
        m_textView  = (EditText)findViewById(R.id.labeledText_input);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LabeledTextView, defStyle, 0);

        // Set the keyboard input style.
        int inputType = a.getInt(R.styleable.LabeledTextView_type, InputType.STRING.getCode());
        if (inputType == InputType.STRING.getCode()) {
            m_textView.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
        }
        else if (inputType == InputType.INTEGER.getCode()) {
            m_textView.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        else if (inputType == InputType.DATE.getCode()) {
            m_textView.setInputType(android.text.InputType.TYPE_CLASS_DATETIME | android.text.InputType.TYPE_DATETIME_VARIATION_DATE);
        }

        // Set the view's label.
        labelView.setText(a.getString(R.styleable.LabeledTextView_label));

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public String getText(){
        return m_textView.getText().toString();
    }

    public int getInt(){
        return Integer.valueOf(getText());
    }

    public double getDouble(){
        return Double.valueOf(getText());
    }
}
