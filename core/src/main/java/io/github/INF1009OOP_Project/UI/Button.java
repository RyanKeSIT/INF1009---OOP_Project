package io.github.INF1009OOP_Project.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

import io.github.INF1009OOP_Project.Entities.*;
import io.github.INF1009OOP_Project.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Entities.Components.Transform;
//made minimal changes to implement new entity system
public class Button extends Entity{
	public static Texture white1x1 = new Texture("white1x1.png");
	private boolean isOutline = false;
	private String text;
	private Color backgroundColor;
	private Color textColor = Color.BLACK;
	private BitmapFont font;
	private float fontSize;

	
	
	public Button(float x, float y, float w, float h,String t, float fs, BitmapFont f, ClickEvent e){
		super();
        this.add(new Transform(x, y, w, h));
        this.add(new Clickable(e, this.get(Transform.class)));
		this.setText(t);
		this.setFont(f);
		this.setFontSize(fs);
		
	}
	public void draw(SpriteBatch sb) {
        Transform tr = get(Transform.class);
		sb.draw(white1x1, tr.getX(), tr.getY(), tr.getWidth(), tr.getHeight());
		this.getFont().setColor(this.textColor);
		this.getFont().draw(sb, this.getText(), tr.getX(), tr.getY() + (tr.getHeight() + this.fontSize/2f)/2f, tr.getWidth(), Align.center, false);
	}
	
	
	
	
	
	
	public boolean isOutline() {
		return isOutline;
	}	
	public void setOutline(boolean isOutline) {
		this.isOutline = isOutline;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getTextColor() {
		return textColor;
	}
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
		this.font.setColor(this.getTextColor());
	}
	public BitmapFont getFont() {
		return font;
	}
	public void setFont(BitmapFont font) {
		this.font = font;
	}
	public float getFontSize() {
		return fontSize;
	}
	public void setFontSize(float fontSize) {
		font.getData().setScale(fontSize/14f);
		this.fontSize = fontSize;
	}
}
