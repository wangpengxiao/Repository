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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tydic.android.usp.R;
  
public class OpenSelectPackageListViewPagerAdapter extends BaseAdapter{  

	private static String TAG = "OpenSelectPackageListViewPagerAdapter";
	
    private LayoutInflater inflater = null;  
    private ArrayList<Map<String, Object>> items = null;  

    private int PAGE_SIZE; // 每一屏幕显示   
    private int selectedPosition = -1;    
    public void setSelectedPosition(int position) {  
        selectedPosition = position;  
    }  
    
    public OpenSelectPackageListViewPagerAdapter(Context context, List<Map<String, Object>> list, int page, int pageSize) {  
        // LayoutInflater用来加载界面   
        inflater = LayoutInflater.from(context);  
        PAGE_SIZE = pageSize;
        items = new ArrayList<Map<String, Object>>();
		int i = page *PAGE_SIZE;
		int end = i + PAGE_SIZE;
		while ((i < list.size()) && (i < end)) {
			items.add(list.get(i));
			i++;
		}        
        
    }    
          
    @Override  
    public int getCount() {  
        return items.size();  
    }    
          
    @Override  
    public Object getItem(int position) {  
        return items.get(position);  
    }  
          
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
      
    // 保存每项中的控件的引用   
    class ViewHolder {  
        TextView ofrId;  
        TextView ofrName;
        TextView ofrDesc;
        TextView marketPrice;
        LinearLayout layout;  
    }  
          
    @Override    
    public View getView(int position, View convert, ViewGroup parent) {  
       ViewHolder holder;  
       if(convert == null)  
       {  
            // 调用LayoutInflater的inflate方法加载layout文件夹中的界面   
            convert = inflater.inflate(R.layout.open_select_package_gridview_item, null);  
            holder = new ViewHolder();  
            holder.ofrId = (TextView)convert.findViewById(R.id.open_select_package_gridView_ofr_id);
            holder.ofrName = (TextView)convert.findViewById(R.id.open_select_package_gridView_ofr_name);
            holder.ofrDesc = (TextView)convert.findViewById(R.id.open_select_package_gridView_ofr_desc);
            holder.marketPrice = (TextView)convert.findViewById(R.id.open_select_package_gridView_market_price);
            holder.layout = (LinearLayout)convert.findViewById(R.id.open_select_package_gridView_item);  
            
            holder.ofrId.setVisibility(View.GONE);
        	holder.ofrDesc.setVisibility(View.GONE);
            // 保存包含当前项控件的对象   
            convert.setTag(holder);  
        } else {  
            // 获取包含当前项控件的对象   
            holder = (ViewHolder)convert.getTag();  
        }  
        // 设置当前项的内容   
        //holder.text.setText(items.get(position));  
    	Map<String, Object> item = (HashMap<String, Object>) items.get(position);
    	holder.ofrId.setText(String.valueOf(item.get("ofr_id")));
    	String ofrName = String.valueOf(item.get("ofr_name"));
    	holder.ofrName.setText(ofrName);
    	holder.ofrDesc.setText(String.valueOf(item.get("ofr_desc")));
    	String marketPrice = "￥: " + String.valueOf(item.get("market_price")) + "元";
    	holder.marketPrice.setText(marketPrice);

        	
        // 设置选中效果   
            if(selectedPosition == position)  
            {  
            	//holder.layout.setSelected(true);
            	holder.layout.setBackgroundResource(R.drawable.item_selected_red_bg);
            	holder.ofrName.setTextColor(Color.rgb(255, 255, 255));
            	holder.ofrDesc.setTextColor(Color.rgb(255, 255, 255));
            	holder.marketPrice.setTextColor(Color.rgb(255, 255, 255));
            	holder.ofrId.setTextColor(Color.rgb(255, 255, 255));
            } else {
            	//holder.layout.setSelected(false);
            	holder.layout.setBackgroundResource(R.drawable.actionbar_common_btn_pressed);
            	holder.ofrName.setTextColor(Color.rgb(0, 0, 0));
            	holder.ofrDesc.setTextColor(Color.rgb(0, 0, 0));
            	holder.ofrId.setTextColor(Color.rgb(0, 0, 0));
            	//holder.marketPrice.setTextColor(Color.rgb(0, 0, 0));
            	holder.marketPrice.setTextColor(Color.RED);
            }  
            
            return convert;  
    }  
}  