/*
 * wcy.tydic
 */
package com.tydic.android.usp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tydic.android.usp.R;
  
public class OpenSelectNumberListViewPagerAdapter extends BaseAdapter{  
      
    private LayoutInflater inflater = null;  
    private List<Map<String, Object>> items = null;  

    private int PAGE_SIZE; // 每一屏幕显示   
    private int selectedPosition = -1;    
    public void setSelectedPosition(int position) {  
        selectedPosition = position;  
    }  
    
    public OpenSelectNumberListViewPagerAdapter(Context context, List<Map<String, Object>> arraylist, int page, int pageSize) {  
        // TODO Auto-generated constructor stub   
        // LayoutInflater用来加载界面   
        inflater = LayoutInflater.from(context);  
        PAGE_SIZE = pageSize;
        items = new ArrayList<Map<String, Object>>();
		int i = page *PAGE_SIZE;
		int end = i + PAGE_SIZE;
		while ((i < arraylist.size()) && (i < end)) {
			items.add(arraylist.get(i));
			i++;
		}        
        
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
        TextView accNbr;  
        TextView fee;
        LinearLayout layout;  
    }  
          
    @Override    
    public View getView(int position, View convert, ViewGroup parent) {  
       // TODO Auto-generated method stub   
       ViewHolder holder;  
       if(convert == null)  
       {  
            // 调用LayoutInflater的inflate方法加载layout文件夹中的界面   
            convert = inflater.inflate(R.layout.open_select_number_gridview_item, null);  
            holder = new ViewHolder();  
            holder.accNbr = (TextView)convert.findViewById(R.id.open_select_number_gridView_accNbr);
            holder.fee = (TextView)convert.findViewById(R.id.open_select_number_gridView_fee);
            holder.layout = (LinearLayout)convert.findViewById(R.id.open_select_number_gridView_item);  
            // 保存包含当前项控件的对象   
            convert.setTag(holder);  
        } else {  
            // 获取包含当前项控件的对象   
            holder = (ViewHolder)convert.getTag();  
        }  
        // 设置当前项的内容   
        //holder.text.setText(items.get(position));  
        Map<String, Object> item = (HashMap<String, Object>) items.get(position);
        holder.accNbr.setText(String.valueOf(item.get("acc_nbr")));
        String feeText = "预存话费 " + String.valueOf(item.get("fee")) + " 元";
        int start = feeText.indexOf("预存话费") + 4;
        int end = feeText.indexOf("元");
        SpannableStringBuilder style=new SpannableStringBuilder(feeText);      
        style.setSpan(new ForegroundColorSpan(Color.RED),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);      
        holder.fee.setText(style);  
        
        //holder.fee.setText(feeText);
        	
        // 设置选中效果   
            if(selectedPosition == position)  
            {  
            	//holder.layout.setSelected(true);
            	holder.layout.setBackgroundResource(R.drawable.item_selected_red_bg);
            	holder.accNbr.setTextColor(Color.rgb(255, 255, 255));
            	//holder.fee.setTextColor(Color.rgb(255, 255, 255));
                SpannableStringBuilder stylePressed = new SpannableStringBuilder(feeText);      
                stylePressed.setSpan(new ForegroundColorSpan(Color.WHITE),0,end+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);      
                holder.fee.setText(stylePressed);  
            } else {
            	//holder.layout.setSelected(false);
            	holder.layout.setBackgroundResource(R.drawable.actionbar_common_btn_pressed);
            	holder.accNbr.setTextColor(Color.rgb(0, 0, 0));
            	holder.fee.setTextColor(Color.rgb(0, 0, 0));
            }  
            
            return convert;  
    }  
}  