package com.suyonoion.flymeosguide;

/**
 * Modified by Suyono on 4/11/2015.
 */

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ShaderEditor extends EditText
{
    public interface OnTextChangedListener
    {
        public void onTextChanged( String text );
    }

    public OnTextChangedListener onTextChangedListener = null;
    public int updateDelay = 1000;
    public int errorLine = 0;
    public boolean dirty = false;

    private static final int COLOR_ERROR = 0xffff47c5;
    private static final int COLOR_NUMBER = 0xff7ba212;
    private static final int COLOR_KEYWORD = 0xff399ed7;
    private static final int COLOR_BUILTIN = 0xffd79e39;
    private static final int COLOR_COMMENT = 0xff808080;

    private static final Pattern line = Pattern.compile(
            ".*\\n" );
    private static final Pattern numbers = Pattern.compile(
            "\\b(\\d*[.]?\\d+)\\b" );
    private static final Pattern keywords = Pattern.compile(
            "\\b(AbsListView|AbsListView.LayoutParams|AbsoluteLayout|AbsoluteLayout.LayoutParams|AbsSeekBar|AbsSpinner|ActionMenuView|ActionMenuView.LayoutParams|AdapterView|AdapterView.AdapterContextMenuInfo|AdapterViewAnimator|AdapterViewFlipper|AlphabetIndexer|AnalogClock|ArrayAdapter|AutoCompleteTextView|BaseAdapter|BaseExpandableListAdapter|Button|CalendarView|CheckBox|CheckedTextView|Chronometer|CompoundButton|CursorAdapter|CursorTreeAdapter|DatePicker|DialerFilter|DigitalClock|EdgeEffect|EditText|ExpandableListView|ExpandableListView.ExpandableListContextMenuInfo|Filter|Filter.FilterResults|FrameLayout|FrameLayout.LayoutParams|Gallery|Gallery.LayoutParams|GridLayout|GridLayout.Alignment|GridLayout.LayoutParams|GridLayout.Spec|GridView|HeaderViewListAdapter|HorizontalScrollView|ImageButton|ImageSwitcher|ImageView|LinearLayout|LinearLayout.LayoutParams|ListPopupWindow|ListView|ListView.FixedViewInfo|MediaController|MultiAutoCompleteTextView|MultiAutoCompleteTextView.CommaTokenizer|NumberPicker|OverScroller|PopupMenu|PopupWindow|ProgressBar|QuickContactBadge|RadioButton|RadioGroup|RadioGroup.LayoutParams|RatingBar|RelativeLayout|RelativeLayout.LayoutParams|RemoteViews|RemoteViewsService|ResourceCursorAdapter|ResourceCursorTreeAdapter|Scroller|ScrollView|SearchView|SeekBar|ShareActionProvider|SimpleAdapter|SimpleCursorAdapter|SimpleCursorTreeAdapter|SimpleExpandableListAdapter|SlidingDrawer|Space|Spinner|StackView|Switch|TabHost|TabHost.TabSpec|TableLayout|TableLayout.LayoutParams|TableRow|TableRow.LayoutParams|TabWidget|TextClock|TextSwitcher|TextView|TextView.SavedState|TimePicker|Toast|ToggleButton|Toolbar|Toolbar.LayoutParams|TwoLineListItem|VideoView|ViewAnimator|ViewFlipper|ViewSwitcher|ZoomButton|ZoomButtonsController|ZoomControls|Enums|fill_parent|horizontal|match_parent|vertical|wrap_content|" +
                    "fill_horizontal|right|true|false|top|left|suyonoion|bottom|center_vertical|android:textAppearance|clip_horizontal|center|fill_vertical|clip_vertical|right|center_horizontal" +
                    "android:paddingLeft|android:paddingRight)\\b" );
    private static final Pattern builtins = Pattern.compile(
            "\\b(android:layout_height|android:layout_weight|android:layout_width|android:orientation|android:text|android:baselineAligned|android:baselineAlignedChildIndex|android:divider|android:gravity|android:measureWithLargestChild|android:orientation|android:weightSum|" +
                    "xmlns:android|xmlns:tools|android:id|tools:context|" +
                    "android:padding|android:typeface|android:textSize|android:textColor|android:ellipsize|android:imeOptions|android:inputType|android:background|" +
                    "android:layout_width|android:layout_height|style|android:accessibilityLiveRegion|android:alpha|android:background|android:backgroundTint|android:backgroundTintMode|android:clickable|android:contentDescription|android:elevation|android:focusable|android:focusableInTouchMode|android:foreground||android:foregroundGravity|android:foregroundTint|android:foregroundTintMode|android:id|android:importantForAccessibility|android:labelFor|android:layoutMode|android:longClickable|android:measureAllChildren|android:minHeight|android:minWidth|android:nestedScrollingEnabled|android:onClick|android:outlineProvider|android:padding|android:paddingLeft|android:paddingTop|android:paddingRight|android:paddingBottom|android:paddingEnd|android:paddingStart|android:stateListAnimator|android:textAlignment|android:theme|android:touchscreenBlocksFocus|android:transitionGroup|android:transitionName|android:translationZ|android:visibility)\\b" );
    private static final Pattern comments = Pattern.compile(
            "/\\*(?:.|[\\n\\r])*?\\*/|//.*" );
    private static final Pattern trailingWhiteSpace = Pattern.compile(
            "[\\t ]+$",
            Pattern.MULTILINE );

    private final Handler updateHandler = new Handler();
    private final Runnable updateRunnable =
            new Runnable()
            {
                @Override
                public void run()
                {
                    Editable e = getText();

                    if( onTextChangedListener != null )
                        onTextChangedListener.onTextChanged( e.toString() );

                    highlightWithoutChange( e );
                }
            };
    private boolean modified = true;

    public ShaderEditor( Context context )
    {
        super( context );
        init();
    }

    public ShaderEditor( Context context, AttributeSet attrs )
    {
        super( context, attrs );
        init();
    }

    public void setTextHighlighted( CharSequence text )
    {
        cancelUpdate();

        errorLine = 0;
        dirty = false;

        modified = false;
        setText( highlight( new SpannableStringBuilder( text ) ) );
        modified = true;

        if( onTextChangedListener != null )
            onTextChangedListener.onTextChanged( text.toString() );
    }

    public String getCleanText()
    {
        return trailingWhiteSpace
                .matcher( getText() )
                .replaceAll( "" );
    }

    public void refresh()
    {
        highlightWithoutChange( getText() );
    }

    private void init()
    {
        setHorizontallyScrolling( false );

        setFilters( new InputFilter[]{
                new InputFilter()
                {
                    @Override
                    public CharSequence filter(
                            CharSequence source,
                            int start,
                            int end,
                            Spanned dest,
                            int dstart,
                            int dend )
                    {
                        if( modified &&
                                end-start == 1 &&
                                start < source.length() &&
                                dstart < dest.length() )
                        {
                            char c = source.charAt( start );

                            if( c == '\n' )
                                return autoIndent(
                                        source,
                                        start,
                                        end,
                                        dest,
                                        dstart,
                                        dend );
                        }

                        return source;
                    }
                } } );

        addTextChangedListener(
                new TextWatcher()
                {
                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count )
                    {
                    }

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after )
                    {
                    }

                    @Override
                    public void afterTextChanged( Editable e )
                    {
                        cancelUpdate();

                        if( !modified )
                            return;

                        dirty = true;
                        updateHandler.postDelayed(
                                updateRunnable,
                                updateDelay );
                    }
                } );
    }

    private void cancelUpdate()
    {
        updateHandler.removeCallbacks( updateRunnable );
    }

    private void highlightWithoutChange( Editable e )
    {
        modified = false;
        highlight( e );
        modified = true;
    }

    private Editable highlight( Editable e )
    {
        try
        {
            // don't use e.clearSpans() because it will remove
            // too much
            clearSpans( e );

            if( e.length() == 0 )
                return e;

            if( errorLine > 0 )
            {
                Matcher m = line.matcher( e );

                for( int n = errorLine;
                     n-- > 0 && m.find(); );

                e.setSpan(
                        new BackgroundColorSpan( COLOR_ERROR ),
                        m.start(),
                        m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
            }

            for( Matcher m = numbers.matcher( e );
                 m.find(); )
                e.setSpan(
                        new ForegroundColorSpan( COLOR_NUMBER ),
                        m.start(),
                        m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

            for( Matcher m = keywords.matcher( e );
                 m.find(); )
                e.setSpan(
                        new ForegroundColorSpan( COLOR_KEYWORD ),
                        m.start(),
                        m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

            for( Matcher m = builtins.matcher( e );
                 m.find(); )
                e.setSpan(
                        new ForegroundColorSpan( COLOR_BUILTIN ),
                        m.start(),
                        m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );

            for( Matcher m = comments.matcher( e );
                 m.find(); )
                e.setSpan(
                        new ForegroundColorSpan( COLOR_COMMENT ),
                        m.start(),
                        m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        }
        catch( Exception ex )
        {
        }

        return e;
    }

    private void clearSpans( Editable e )
    {
        // remove foreground color spans
        {
            ForegroundColorSpan spans[] = e.getSpans(
                    0,
                    e.length(),
                    ForegroundColorSpan.class );

            for( int n = spans.length; n-- > 0; )
                e.removeSpan( spans[n] );
        }

        // remove background color spans
        {
            BackgroundColorSpan spans[] = e.getSpans(
                    0,
                    e.length(),
                    BackgroundColorSpan.class );

            for( int n = spans.length; n-- > 0; )
                e.removeSpan( spans[n] );
        }
    }

    private CharSequence autoIndent(
            CharSequence source,
            int start,
            int end,
            Spanned dest,
            int dstart,
            int dend )
    {
        String indent = "";
        int istart = dstart-1;
        int iend = -1;

        // find start of this line
        boolean dataBefore = false;
        int pt = 0;

        for( ; istart > -1; --istart )
        {
            char c = dest.charAt( istart );

            if( c == '\n' )
                break;

            if( c != ' ' &&
                    c != '\t' )
            {
                if( !dataBefore )
                {
                    // indent always after those characters
                    if( c == '{' ||
                            c == '+' ||
                            c == '-' ||
                            c == '*' ||
                            c == '/' ||
                            c == '%' ||
                            c == '^' ||
                            c == '=' )
                        --pt;

                    dataBefore = true;
                }

                // parenthesis counter
                if( c == '(' )
                    --pt;
                else if( c == ')' )
                    ++pt;
            }
        }

        // copy indent of this line into the next
        if( istart > -1 )
        {
            char charAtCursor = dest.charAt( dstart );

            for( iend = ++istart;
                 iend < dend;
                 ++iend )
            {
                char c = dest.charAt( iend );

                // auto expand comments
                if( charAtCursor != '\n' &&
                        c == '/' &&
                        iend+1 < dend &&
                        dest.charAt( iend ) == c )
                {
                    iend += 2;
                    break;
                }

                if( c != ' ' &&
                        c != '\t' )
                    break;
            }

            indent += dest.subSequence( istart, iend );
        }

        // add new indent
        if( pt < 0 )
            indent += "\t";

        // append white space of previous line and new indent
        return source+indent;
    }
}
