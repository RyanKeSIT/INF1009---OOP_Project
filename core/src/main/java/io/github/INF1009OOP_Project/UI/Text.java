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
public class Text extends Entity{
	private String text;
	private Color textColor = Color.BLACK;
	private BitmapFont font;
	private float fontSize;

	
	
	public Text(float x, float y, float w, float h,String t, float fs, BitmapFont f){
		super();
        this.add(new Transform(x, y, w, h));
		this.setText(t);
		this.setFont(f);
		this.setFontSize(fs);
		
	}
	public Text(float x, float y, float w, float h,String t, float fs, Color color, BitmapFont f){
		super();
        this.add(new Transform(x, y, w, h));
		this.setText(t);
		this.setFont(f);
		this.setFontSize(fs);
		this.setTextColor(color);
	}
	public void draw(SpriteBatch sb) {
        Transform tr = get(Transform.class);
        font.getData().setScale(fontSize/14f);
		this.getFont().setColor(this.textColor);
		this.getFont().draw(sb, this.getText(), tr.getX(), tr.getY() + (tr.getHeight() + this.fontSize/2f)/2f, tr.getWidth(), Align.center, false);
	}
	
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
		this.fontSize = fontSize;
	}
}
