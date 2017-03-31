package com.tydic.android.usp.activity;

import java.util.ArrayList;
import java.util.Arrays;

/**用于收集BasicActivity的子类状态，Activity启动则添加到集合，Activity销毁则从集合中删除。
 * 
 *
 */
public class ActivityStateList{
	
	/**Activity是否暂停
	 * @author zhangkune
	 *
	 */
	public static class ActivityState{
		public boolean isPause;
		public ActivityState(boolean isPause){
			this.isPause=isPause;
		}
		@Override
		public String toString() {
			return isPause+"";
		}
	}
	//ArrayList可以按顺序获取.保持最后一个元素则为栈顶Activity，Size为打开的Activity数目
	private static ArrayList<ActivityState> stateList = new ArrayList<ActivityState>();
	
	public static void addState(ActivityState activityState){
		stateList.add(activityState);
	}
	
	public static void delState(ActivityState activityState){
		stateList.remove(activityState);
	}
	
	public static ArrayList<ActivityState> getStateList(){
		return stateList;
	}
	
	public static String toSateString() {//打印所有activity 状态
		return Arrays.toString(stateList.toArray());
	}

}
