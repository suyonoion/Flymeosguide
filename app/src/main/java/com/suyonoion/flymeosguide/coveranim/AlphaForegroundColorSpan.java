package com.suyonoion.flymeosguide.coveranim;

import android.graphics.Color;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

public class AlphaForegroundColorSpan extends ForegroundColorSpan
{
  private float mAlpha;

  public AlphaForegroundColorSpan(int paramInt)
  {
    super(paramInt);
  }

  public AlphaForegroundColorSpan(Parcel paramParcel)
  {
    super(paramParcel);
    this.mAlpha = paramParcel.readFloat();
  }

  private int getAlphaColor()
  {
    int i = getForegroundColor();
    return Color.argb((int)(255.0F * this.mAlpha), Color.red(i), Color.green(i), Color.blue(i));
  }

  public float getAlpha()
  {
    return this.mAlpha;
  }

  public void setAlpha(float paramFloat)
  {
    this.mAlpha = paramFloat;
  }

  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setColor(getAlphaColor());
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeFloat(this.mAlpha);
  }
}

/* Location:           E:\A. XPERIA M\3. THEMING\TOOL\Tutorial Decompile Classes.dex by Baca Yang Bener\dex2jar-0.0.9.8\dex2jar-0.0.9.8\coveranim_dex2jar.jar
 * Qualified Name:     com.ariv.notboringactionbar.AlphaForegroundColorSpan
 * JD-Core Version:    0.6.2
 */