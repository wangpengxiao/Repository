/*
 * wcy.tydic
 */
package com.tydic.android.usp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tydic.android.usp.R;
  
public class OpenSelectPackageListAdapter extends BaseAdapter{  
      
    private LayoutInflater inflater = null;  
    private ArrayList<Map<String, String>> items = null;  

          
    private int selectedPosition = -1;    
    public void setSelectedPosition(int position) {  
        selectedPosition = position;  
    }  
    
    public OpenSelectPackageListAdapter(Context context, ArrayList<Map<String, String>> arraylist) {  
        // TODO Auto-generated constructor stub   
        // LayoutInflater用来加载界面   
        inflater = LayoutInflater.from(context);  
        // 保存适配器中的每项的文字信息   
        this.items = arraylist;  

    }    
          
    @Override  
    public int getCount() {  
        // TODO Auto-generated method stub   
        return items.size();  
    }    
          
    @Override  
    public Object getItem(int position) {  
        // TODO Auto-generated method stub   
        return items.get(position);  
    }  
          
    @Override  
    public long getItemId(int position) {  
        // TODO Auto-generated method stub   
        return position;  
    }  
      
 // 保存每项中的控件的引用   
    class ViewHolder {  
        TextView ofrId;  
        TextView ofrName;
        TextView ofrDes;
        TextView marketPrice;
        LinearLayout layout;  
    }  
          
    @Override    
    public View getView(int position, View convert, ViewGroup parent) {  
       // TODO Auto-generated method stub   
       ViewHolder holder;  
       if(convert == null)  
       {  
            // 调用LayoutInflater的inflate方法加载layout文件夹中的界面   
            convert = inflater.inflate(R.layout.open_select_package_gridview_item, null);  
            holder = new ViewHolder();  
            //holder.title = (TextView)convert.findViewById(R.id.open_select_package_gridView_title);
            //holder.brief = (TextView)convert.findViewById(R.id.open_select_package_gridView_brief);
            holder.layout = (LinearLayout)convert.findViewById(R.id.open_select_package_gridView_item);  
            // 保存包含当前项控件的对象   
            convert.setTag(holder);  
        } else {  
            // 获取包含当前项控件的对象   
            holder = (ViewHolder)convert.getTag();  
        }  
        // 设置当前项的内容   
        //holder.text.setText(items.get(position));  
        	Map<String, String> item = (HashMap<String, String>) items.get(position);
        	//holder.title.setText(String.valueOf(item.get("TITLE")));
        	//holder.brief.setText(String.valueOf(item.get("BRIEF")));
        	
        // 设置选中效果   
            if(selectedPosition == position)  
            {  
            	//holder.layout.setSelected(true);
            	holder.layout.setBackgroundResource(R.drawable.item_selected_bg);
            	//holder.title.setTextColor(Color.rgb(255, 255, 255));
            	//holder.brief.setTextColor(Color.rgb(255, 255, 255));
            } else {
            	//holder.layout.setSelected(false);
            	holder.layout.setBackgroundResource(R.drawable.actionbar_common_btn_pressed);
            	//holder.title.setTextColor(Color.rgb(0, 0, 0));
            	//holder.brief.setTextColor(Color.rgb(0, 0, 0));
            }  
            
            return convert;  
    }  
}  