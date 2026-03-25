package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
//made minimal changes to implement new entity system
public class ModeCheckbox extends Button{

	
	private boolean isChecked = false;
	
	public ModeCheckbox(float x, float y, float w, float h,String t, float fs, BitmapFont f){
		super(x,y,w,h,t,fs,f,null);
        
		this.add(new Clickable(new ClickEvent() {
				@Override
				public void onClick(){
					ModeCheckbox.this.toggle();
				}
			}, this.get(Transform.class))
		);
		
		setChecked(false);
		
		
	}
	
	
	
	public void toggle() {
		
		setChecked(!checked());
		
		
	}
	
	public void setChecked(boolean c) {
		isChecked = c;
		if(isChecked) {
			background = white1x1;
			this.setTextColor(Color.BLACK);
		}else {
			background = gray1x1;
			this.setTextColor(Color.WHITE);
		}
	}
	public boolean checked() {
		return isChecked;
	}
	
	
}
