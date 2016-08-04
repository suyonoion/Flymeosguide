package com.suyonoion.flymeosguide.coveranim;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ParallaxImageView extends ImageView
{
  private int mCurrentTranslation;

  public ParallaxImageView(Context paramContext)
  {
    super(paramContext);
  }

  public ParallaxImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ParallaxImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void draw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.translate(0.0F, -this.mCurrentTranslation / 2);
    super.draw(paramCanvas);
    paramCanvas.restore();
  }

  public void setCurrentTranslation(int paramInt)
  {
    this.mCurrentTranslation = paramInt;
    invalidate();
  }
}

/* Location:           E:\A. XPERIA M\3. THEMING\TOOL\Tutorial Decompile Classes.dex by Baca Yang Bener\dex2jar-0.0.9.8\dex2jar-0.0.9.8\coveranim_dex2jar.jar
 * Qualified Name:     com.ariv.notboringactionbar.ParallaxImageView
 * JD-Core Version:    0.6.2
 */