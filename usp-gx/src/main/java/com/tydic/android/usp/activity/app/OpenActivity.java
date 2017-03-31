package com.tydic.android.usp.activity.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.tydic.android.rest.RestParameters;
import com.tydic.android.rest.api.ApiError;
import com.tydic.android.rest.api.RestApiListener;
import com.tydic.android.usp.R;
import com.tydic.android.usp.activity.BasicActivity;
import com.tydic.android.usp.ui.config.MenuLeftConfig;
import com.tydic.android.usp.ui.constant.Constants;
import com.tydic.android.usp.uss.ApiUrls;
import com.tydic.android.usp.uss.JsonToBeanUtils;
import com.tydic.android.usp.uss.ObjectToRestParamUtils;
import com.tydic.android.usp.uss.request.CheckCustInfoRequest;
import com.tydic.android.usp.uss.request.CodeListRequest;
import com.tydic.android.usp.uss.request.SaleOpenOrderSubmitRequest;
import com.tydic.android.usp.uss.request.SaleSelectNumberRequest;
import com.tydic.android.usp.uss.request.SaleSelectedCodeRequest;
import com.tydic.android.usp.uss.response.CodeListResponse;
import com.tydic.android.usp.uss.response.CustomerVerifyResponse;
import com.tydic.android.usp.uss.response.OperLoginResponse;
import com.tydic.android.usp.uss.response.SaleOpenResponse;
import com.tydic.android.usp.uss.response.SaleSelectNumberResponse;
import com.tydic.android.usp.uss.response.SaleSelectedCodeResponse;
import com.tydic.android.usp.util.AppUtil;
import com.tydic.android.usp.util.OpenLeftButtonListAdapter;
import com.tydic.android.usp.util.OpenListAdapter;
import com.tydic.android.usp.util.OpenSelectNumberListViewPagerAdapter;
import com.tydic.android.usp.util.OpenSelectPackageListAdapter;
import com.tydic.android.usp.util.OpenSelectPackageListViewPagerAdapter;
import com.tydic.android.usp.util.StringUtils;
import com.tydic.android.usp.util.TitleBarUtils;
import com.tydic.android.usp.util.ViewPagerAdapter;

public class OpenActivity extends BasicActivity {

	// mask window
	private static Animation fadeIn;
	private static Animation fadeOut;
	private RelativeLayout maskLayout;

	// edit window
	private PopupWindow editWindow;
	private LinearLayout editView;

	// finish window
	private PopupWindow finishWindow;
	private LinearLayout finishView;

	// 左侧按钮list
	private ListView openButtonListView;
	private OpenLeftButtonListAdapter openLeftButtonListAdapter;

	// 右侧页
	private Animation slideInRight;
	private Animation slideInLeft;
	private Animation slideOutRight;
	private Animation slideOutLeft;
	private ViewFlipper openLaoutRightPages;
	private RelativeLayout openLaoutRightPage1;
	private RelativeLayout openLaoutRightPage2;
	private RelativeLayout openLaoutRightPage3;
	private RelativeLayout openLaoutRightPage4;
	private RelativeLayout openLaoutRightPage5;
	private Button page1NextStep;
	private Button page2PreStep;
	private Button page2NextStep;
	private Button page3PreStep;
	private Button page3NextStep;
	private Button page4PreStep;
	private Button page4NextStep;
	private Button page5PreStep;
	private Button page5FinishStep;
	private Button finishReturn;
	private Button finishCommit;
	private Button finishNewBusi;
	private Button finishExit;
	// page1控件
	private Button page1TeleTypeTitle;
	private TextView page1TeleTypeValue;
	private Button page1NumberDefineTitle;
	private TextView page1NumberDefineValue;
	private Button page1PrePaidTitle;
	private TextView page1PrePaidValue;
	private Button page1NumberTypeTitle;
	private TextView page1NumberTypeValue;

	private Button page1Searching;

	private Map<String, List<Map<String, Object>>> codeListData;

	// 电信类型窗口 适配器 数据
	private LinearLayout openPage1TeleTypeListWindow;
	private ListView openPage1TeleTypeList;
	private List<Map<String, Object>> openPageTeleTypeListData;
	private OpenListAdapter openListAdapter_Page1TeleType;

	// 号码自定义窗口 适配器 数据
	private LinearLayout openNumberDefineListWindow;
	private ListView openNumberDefineList;
	private List<Map<String, Object>> openNumberDefineListData;
	private OpenListAdapter openListAdapter_NumberDefine;

	// 预存话费窗口 适配器 数据
	private LinearLayout openPrePaidListWindow;
	private ListView openPrePaidList;
	private List<Map<String, Object>> openPrePaidListData;
	private OpenListAdapter openListAdapter_PrePaid;

	// 靓号类型窗口 适配器 数据
	private LinearLayout openNumberTypeListWindow;
	private ListView openNumberTypeList;
	private List<Map<String, Object>> openNumberTypeListData;
	private OpenListAdapter openListAdapter_NumberType;

	// 选择号码
	private LinearLayout openSelectNumberGrid01;
	private GridView openSelectNumberGridView01;
	private List<Map<String, Object>> openSelectNumberListData = new ArrayList<Map<String, Object>>();
	private GridView openSelectNumberList;

	private ViewPager openSelectNumberViewPager;
	private OpenSelectNumberListViewPagerAdapter adapter11111;
	private ViewPagerAdapter openSelectNumberViewPagerAdapter;
	private OpenSelectNumberListViewPagerAdapter lastSelectedItemAdapter = null;
	private List<GridView> openSelectNumberGridViewLists = new ArrayList<GridView>();
	private TextView page1SelectedPhone;

	// page2控件
	// private Button page2TeleTypeTitle;
	// private TextView page2TeleTypeValue;
	private Button page2PackageTypeTitle;
	private TextView page2PackageTypeValue;
	private Button page2MonthConsumptionTitle;
	private TextView page2MonthConsumptionValue;
	private Button page2MonthCallTitle;
	private TextView page2MonthCallValue;
	private Button page2MonthNetTitle;
	private TextView page2MonthNetValue;

	private EditText page2SearchingOfrName;
	private Button page2Searching;
	private TextView page2SelectedPackage;

	// 电信类型窗口 适配器 数据
	private LinearLayout openPage2TeleTypeListWindow;
	private ListView openPage2TeleTypeList;
	// private ArrayList<Map<String, Object>> openPage2TeleTypeListData;
	private OpenListAdapter openListAdapter_Page2TeleType;

	// 套餐类型窗口 适配器 数据
	private LinearLayout openPackageTypeListWindow;
	private ListView openPackageTypeList;
	private List<Map<String, Object>> openPackageTypeListData;
	private OpenListAdapter openListAdapter_PackageType;

	// 月消费金额窗口 适配器 数据
	private LinearLayout openMonthConsumptionListWindow;
	private ListView openMonthConsumptionList;
	private List<Map<String, Object>> openMonthConsumptionListData;
	private OpenListAdapter openListAdapter_MonthConsumption;

	// 月通话时长窗口 适配器 数据
	private LinearLayout openMonthCallListWindow;
	private ListView openMonthCallList;
	private List<Map<String, Object>> openMonthCallListData;
	private OpenListAdapter openListAdapter_MonthCall;

	// 月上网流量窗口 适配器 数据
	private LinearLayout openMonthNetListWindow;
	private ListView openMonthNetList;
	private List<Map<String, Object>> openMonthNetListData;
	private OpenListAdapter openListAdapter_MonthNet;

	// 选择套餐
	private LinearLayout openSelectPackageGrid01;
	private GridView openSelectPackageGridView01;
	private List<Map<String, Object>> openSelectPackageListData = new ArrayList<Map<String, Object>>();
	private GridView openSelectPackageList;
	private OpenSelectPackageListAdapter openSelectPackageListAdapter;

	// 是否参加合约计划进行选择
	private RelativeLayout page2NextStepSelectWindow;
	private LinearLayout page2NextStepSelect_line;
	private Button page2NextStepActionOk;
	private Button page2NextStepActionNo;

	// 客户校验
	private CustomerVerifyResponse customerVerifyResponse;
	private Boolean restResult;

	private ViewPager openSelectPackageViewPager;
	// private OpenSelectNumberListViewPagerAdapter adapter11111;
	private ViewPagerAdapter openSelectPackageViewPagerAdapter;
	private OpenSelectPackageListViewPagerAdapter lastSelectedPackageItemAdapter = null;
	private List<GridView> openSelectPackageGridViewLists = new ArrayList<GridView>();
	// page3控件
	private Button page3ActionSelectTitle;
	private TextView page3ActionSelectResult;
	private LinearLayout actionSelectListWindow;
	private ListView actionSelectList;
	private ArrayList<Map<String, Object>> actionSelectListData;
	private OpenListAdapter openListAdapter_actionSelect;
	private View page3PhonePlan;
	int Page3PhonePlanFlag = 0;
	private ImageView Page3SelectActionOnOffView;

	// page4控件
	private EditText page4CertNumberValue;
	private Button page4CertNumberValidate;// 省份验证按钮

	private EditText page4CertExpDate;
	private EditText page4CertAddrValue;
	private EditText page4UserNameValue;
	private TextView page4UserSexValue;
	private TextView page4UserBirthdayValue;
	private EditText page4UserZipcodeValue;
	private EditText page4UserEmailValue;
	private EditText page4CommentsValue;
	private EditText page4ContactNameValue;
	private EditText page4ContactTelValue;
	private EditText page4ContactAddrValue;
	private TextView page4PostSelectValue;
	private EditText page4PostTypeValue;
	private EditText page4PostInfoValue;

	private TextView page4_cust_name;
	private TextView page4_cust_addr;
	private TextView page4_cust_sex;
	private TextView page4_cust_cert_exp;
	// private TextView page4_cust_birthday;
	private TextView page4_cust_zipcode;
	private TextView page4_cust_email;
	private TextView page4_contact_name;
	private TextView page4_contact_tel;
	private TextView page4_contact_addr;
	private Button page4CertTypeButton;
	private TextView page4CertType;
	private LinearLayout openIdTypeListWindow;
	private ListView openIdTypeList;
	private List<Map<String, Object>> openIdTypeListData;
	private OpenListAdapter openListAdapter_IdType;
	private int posIdTypeSelected = 0;

	private Button page4PayTypeButton;
	private TextView page4PayType;
	private LinearLayout openPayTypeListWindow;
	private ListView openPayTypeList;
	private List<Map<String, Object>> openPayTypeListData;
	private OpenListAdapter openListAdapter_PayType;
	private int posPayTypeSelected = 0;

	private Button page4CustSexButton;
	private TextView page4CustSex;
	private LinearLayout openCustSexListWindow;
	private ListView openCustSexList;
	private List<Map<String, Object>> openCustSexListData;
	private OpenListAdapter openListAdapter_CustSex;
	private int posCustSexSelected = 0;

	// page5控件
	private EditText page5DevIdValue;
	private EditText page5DevTelValue;
	private EditText page5ChannelValue;
	private EditText page5CreditValue;
	private EditText page5OperValue;
	private TextView page5IdTypeValue;
	private EditText page5IdNumberValue;
	private EditText page5CommentsValue;
	private EditText page5DevName;
	private EditText page5ChannelNameValue;

	private SaleOpenOrderSubmitRequest saleOpenOrderSubmitRequest;

	// finish
	private RelativeLayout openFinishWindow;
	private ViewFlipper openFinishFlipper;
	private TextView openFinishMsg;
	private TextView openFInishHeader;

	private static String TAG = "OpenActivity";

	@Override
	protected void setlayout() {
		Log.i(getClass().getName(), "OpenActivity.onCreate");

		// set anim
		fadeIn = new AlphaAnimation(0, 1);
		// fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(200);

		fadeOut = new AlphaAnimation(1, 0);
		// fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setDuration(200);

		setContentView(R.layout.usp_open_main); // main page

		initNavigation();
		TitleBarUtils.setTitle(titleBar, "开户");

		maskLayout = ((RelativeLayout) this.findViewById(R.id.mask_layout1));

	}

	@Override
	protected void setlayoutBackGround() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void findView() {

		openButtonListView = (ListView) this.findViewById(R.id.open_left_button_listView);
		createOpenLeftButtonList();

		slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
		slideOutLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
		slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
		slideOutRight = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);

		openLaoutRightPages = ((ViewFlipper) this.findViewById(R.id.open_layout_right_content));
		setOpenLaoutRightPagesAnimRightToLeft();

		openLaoutRightPage1 = ((RelativeLayout) this.findViewById(R.id.open_layout_right_page1));
		openLaoutRightPage2 = ((RelativeLayout) this.findViewById(R.id.open_layout_right_page2));
		openLaoutRightPage3 = ((RelativeLayout) this.findViewById(R.id.open_layout_right_page3));
		openLaoutRightPage4 = ((RelativeLayout) this.findViewById(R.id.open_layout_right_page4));
		openLaoutRightPage5 = ((RelativeLayout) this.findViewById(R.id.open_layout_right_page5));

		page1NextStep = ((Button) this.findViewById(R.id.open_layout_right_page1_next_step));
		page2PreStep = ((Button) this.findViewById(R.id.open_layout_right_page2_pre_step));
		page2NextStep = ((Button) this.findViewById(R.id.open_layout_right_page2_next_step));
		page3PreStep = ((Button) this.findViewById(R.id.open_layout_right_page3_pre_step));
		page3NextStep = ((Button) this.findViewById(R.id.open_layout_right_page3_next_step));
		page4PreStep = ((Button) this.findViewById(R.id.open_layout_right_page4_pre_step));
		page4NextStep = ((Button) this.findViewById(R.id.open_layout_right_page4_next_step));
		page5PreStep = ((Button) this.findViewById(R.id.open_layout_right_page5_pre_step));
		page5FinishStep = ((Button) this.findViewById(R.id.open_layout_right_page5_next_step));

		// 1、处理选号码页面的控件
		page1TeleTypeTitle = ((Button) this.findViewById(R.id.open_layout_right_page1_tele_type_title));
		page1NumberDefineTitle = ((Button) this.findViewById(R.id.open_layout_right_page1_number_define_title));
		page1PrePaidTitle = ((Button) this.findViewById(R.id.open_layout_right_page1_prepaid_title));
		page1NumberTypeTitle = ((Button) this.findViewById(R.id.open_layout_right_page1_number_type_title));

		page1TeleTypeValue = ((TextView) this.findViewById(R.id.open_layout_right_page1_tele_type_value));
		page1NumberDefineValue = ((TextView) this.findViewById(R.id.open_layout_right_page1_number_define_value));
		page1PrePaidValue = ((TextView) this.findViewById(R.id.open_layout_right_page1_prepaid_value));
		page1NumberTypeValue = ((TextView) this.findViewById(R.id.open_layout_right_page1_number_type_value));

		page1Searching = ((Button) this.findViewById(R.id.open_layout_right_page1_searching));

		editView = (LinearLayout) mLayoutInflater.inflate(R.layout.edit_window, null);
		// 电信类型选择窗口
		openPage1TeleTypeListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_tele_type_page1_window, null);
		openPage1TeleTypeList = (ListView) openPage1TeleTypeListWindow.findViewById(R.id.open_tele_type_page1_list);
		// 号码自定义选择窗口
		openNumberDefineListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_number_define_window, null);
		openNumberDefineList = (ListView) openNumberDefineListWindow.findViewById(R.id.open_number_define_list);
		// 预存话费选择窗口
		openPrePaidListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_pre_paid_window, null);
		openPrePaidList = (ListView) openPrePaidListWindow.findViewById(R.id.open_pre_paid_list);
		// 靓号类型选择窗口
		openNumberTypeListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_number_type_window, null);
		openNumberTypeList = (ListView) openNumberTypeListWindow.findViewById(R.id.open_number_type_list);

		// 选择号码grid
		// openSelectNumberGrid01 =
		// (LinearLayout)mLayoutInflater.inflate(R.layout.open_select_number_grid,
		// null);
		// openSelectNumberGridView01 =
		// (GridView)openSelectNumberGrid01.findViewById(R.id.open_select_number_gridView);
		// openSelectNumberGridView01.setColumnWidth(openSelectNumberGrid01.getWidth()/4);
		// openLaoutRightPage1.addView(openSelectNumberGrid01);

		openSelectNumberViewPager = (ViewPager) findViewById(R.id.open_select_number_viewpager);

		// 2、处理选产品页面的控件
		// page2TeleTypeTitle = ((Button)
		// this.findViewById(R.id.open_layout_right_page2_tele_type_title));
		page2PackageTypeTitle = ((Button) this.findViewById(R.id.open_layout_right_page2_package_type_title));
		page2MonthConsumptionTitle = ((Button) this.findViewById(R.id.open_layout_right_page2_month_consumption_title));
		page2MonthCallTitle = ((Button) this.findViewById(R.id.open_layout_right_page2_month_call_title));
		page2MonthNetTitle = ((Button) this.findViewById(R.id.open_layout_right_page2_month_net_title));

		// page2TeleTypeValue = ((TextView)
		// this.findViewById(R.id.open_layout_right_page2_tele_type_value));
		page2PackageTypeValue = ((TextView) this.findViewById(R.id.open_layout_right_page2_package_type_value));
		page2MonthConsumptionValue = ((TextView) this.findViewById(R.id.open_layout_right_page2_month_consumption_value));
		page2MonthCallValue = ((TextView) this.findViewById(R.id.open_layout_right_page2_month_call_value));
		page2MonthNetValue = ((TextView) this.findViewById(R.id.open_layout_right_page2_month_net_value));

		page2SearchingOfrName = (EditText) this.findViewById(R.id.open_layout_right_page2_search_ofr_name);
		page2Searching = ((Button) this.findViewById(R.id.open_layout_right_page2_search_btn));

		editView = (LinearLayout) mLayoutInflater.inflate(R.layout.edit_window, null);
		// 电信类型选择窗口
		openPage2TeleTypeListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_tele_type_page2_window, null);
		openPage2TeleTypeList = (ListView) openPage2TeleTypeListWindow.findViewById(R.id.open_tele_type_page2_list);
		// 套餐类型选择窗口
		openPackageTypeListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_page2_package_type_window, null);
		openPackageTypeList = (ListView) openPackageTypeListWindow.findViewById(R.id.open_page2_package_type_list);
		// 月消费金额选择窗口
		openMonthConsumptionListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_page2_month_consumption_window, null);
		openMonthConsumptionList = (ListView) openMonthConsumptionListWindow.findViewById(R.id.open_page2_month_consumption_list);
		// 月通话时长选择窗口
		openMonthCallListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_page2_month_call_window, null);
		openMonthCallList = (ListView) openMonthCallListWindow.findViewById(R.id.open_page2_month_call_list);
		// 月上网流量选择窗口
		openMonthNetListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_page2_month_net_window, null);
		openMonthNetList = (ListView) openMonthNetListWindow.findViewById(R.id.open_page2_month_net_list);

		// 选择套餐grid
		// openSelectPackageGrid01 =
		// (LinearLayout)mLayoutInflater.inflate(R.layout.open_select_package_grid,
		// null);
		// openSelectPackageGridView01 =
		// (GridView)openSelectPackageGrid01.findViewById(R.id.open_select_package_gridView);
		// openSelectPackageGridView01.setColumnWidth(openSelectPackageGrid01.getWidth()/4);
		// openLaoutRightPage2.addView(openSelectPackageGrid01);

		openSelectPackageViewPager = (ViewPager) findViewById(R.id.open_select_package_viewpager);

		page2NextStepSelectWindow = (RelativeLayout) mLayoutInflater.inflate(R.layout.page2_next_step_select, null);
		page2NextStepActionOk = ((Button) page2NextStepSelectWindow.findViewById(R.id.page2_next_step_action_ok));
		page2NextStepActionNo = ((Button) page2NextStepSelectWindow.findViewById(R.id.page2_next_step_action_no));

		// openLaoutRightPages.setPersistentDrawingCache(ViewGroup.PERSISTENT_ALL_CACHES);
		page3ActionSelectTitle = ((Button) this.findViewById(R.id.open_layout_right_page3_action_choice_title));
		page3ActionSelectResult = (TextView) this.findViewById(R.id.open_layout_right_page3_action_choice_value);
		actionSelectListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_number_define_window, null);
		actionSelectList = (ListView) actionSelectListWindow.findViewById(R.id.open_number_define_list);
		page3PhonePlan = findViewById(R.id.open_layout_right_page3_phone_plan);
		Page3SelectActionOnOffView = (ImageView) findViewById(R.id.open_layout_right_page3_action_choice_on_off);

		// page4
		page4CertNumberValue = (EditText) this.findViewById(R.id.open_layout_right_page4_cert_number_value);
		page4CertNumberValidate = (Button) this.findViewById(R.id.open_layout_right_page4_cert_number_validate);
		page4_cust_name = (TextView) this.findViewById(R.id.open_layout_right_page4_user_name_value);
		page4_cust_addr = (TextView) this.findViewById(R.id.open_layout_right_page4_cert_addr_value);
		page4_cust_sex = (TextView) this.findViewById(R.id.open_layout_right_page4_user_sex_value);
		page4_cust_cert_exp = (TextView) this.findViewById(R.id.open_layout_right_page4_cert_exp_date);
		// page4_cust_birthday = (TextView)
		// this.findViewById(R.id.open_layout_right_page4_user_birthday_value);
		page4_cust_zipcode = (TextView) this.findViewById(R.id.open_layout_right_page4_user_zipcode_value);
		page4_cust_email = (TextView) this.findViewById(R.id.open_layout_right_page4_user_email_value);
		page4_contact_name = (TextView) this.findViewById(R.id.open_layout_right_page4_contact_name_value);
		page4_contact_tel = (TextView) this.findViewById(R.id.open_layout_right_page4_contact_tel_value);
		page4_contact_addr = (TextView) this.findViewById(R.id.open_layout_right_page4_contact_addr_value);
		page4CertTypeButton = (Button) this.findViewById(R.id.open_layout_right_page4_cert_type_title);
		page4CertType = (TextView) this.findViewById(R.id.open_layout_right_page4_cert_type_value);
		openIdTypeListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_number_define_window, null);
		openIdTypeList = (ListView) openIdTypeListWindow.findViewById(R.id.open_number_define_list);

		page4PayTypeButton = (Button) this.findViewById(R.id.open_layout_right_page4_pay_type_title);
		page4PayType = (TextView) this.findViewById(R.id.open_layout_right_page4_pay_type_value);
		openPayTypeListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_number_define_window, null);
		openPayTypeList = (ListView) openPayTypeListWindow.findViewById(R.id.open_number_define_list);

		page4CustSexButton = (Button) this.findViewById(R.id.open_layout_right_page4_user_sex_title);
		page4CustSex = (TextView) this.findViewById(R.id.open_layout_right_page4_user_sex_value);
		openCustSexListWindow = (LinearLayout) mLayoutInflater.inflate(R.layout.open_number_define_window, null);
		openCustSexList = (ListView) openCustSexListWindow.findViewById(R.id.open_number_define_list);

		// finish
		finishView = (LinearLayout) mLayoutInflater.inflate(R.layout.finish_window, null);
		openFinishWindow = (RelativeLayout) mLayoutInflater.inflate(R.layout.open_finish_window, null);
		finishReturn = (Button) openFinishWindow.findViewById(R.id.open_layout_right_finish_return);
		finishCommit = (Button) openFinishWindow.findViewById(R.id.open_layout_right_finish_commit);
		finishNewBusi = (Button) openFinishWindow.findViewById(R.id.open_layout_right_finish_newbusi);
		finishExit = (Button) openFinishWindow.findViewById(R.id.open_layout_right_finish_exit);
		openFinishFlipper = ((ViewFlipper) openFinishWindow.findViewById(R.id.open_layout_right_finish_flipper));
		finishView.addView(openFinishWindow);
		openFinishFlipper.setDisplayedChild(0);

		page1SelectedPhone = (TextView) this.findViewById(R.id.open_layout_right_page1_selected_phone);
		page2SelectedPackage = (TextView) this.findViewById(R.id.open_layout_right_page2_selected_package);
		// page4CertTypeValue = (TextView)
		// this.findViewById(R.id.open_layout_right_page4_cert_type_value);
		page4CertExpDate = (EditText) this.findViewById(R.id.open_layout_right_page4_cert_exp_date);
		page4CertAddrValue = (EditText) this.findViewById(R.id.open_layout_right_page4_cert_addr_value);
		page4UserNameValue = (EditText) this.findViewById(R.id.open_layout_right_page4_user_name_value);
		page4UserSexValue = (TextView) this.findViewById(R.id.open_layout_right_page4_user_sex_value);
		// page4UserBirthdayValue = (TextView)
		// this.findViewById(R.id.open_layout_right_page4_user_birthday_value);
		page4UserZipcodeValue = (EditText) this.findViewById(R.id.open_layout_right_page4_user_zipcode_value);
		page4UserEmailValue = (EditText) this.findViewById(R.id.open_layout_right_page4_user_email_value);
		page4CommentsValue = (EditText) this.findViewById(R.id.open_layout_right_page4_comments_value);
		page4ContactNameValue = (EditText) this.findViewById(R.id.open_layout_right_page4_contact_name_value);
		page4ContactTelValue = (EditText) this.findViewById(R.id.open_layout_right_page4_contact_tel_value);
		page4ContactAddrValue = (EditText) this.findViewById(R.id.open_layout_right_page4_contact_addr_value);
		page4PostSelectValue = (TextView) this.findViewById(R.id.open_layout_right_page4_post_select_value);
		page4PostTypeValue = (EditText) this.findViewById(R.id.open_layout_right_page4_post_type_value);
		page4PostInfoValue = (EditText) this.findViewById(R.id.open_layout_right_page4_post_info_value);

		// page5
		page5DevIdValue = (EditText) this.findViewById(R.id.open_layout_right_page5_dev_id_value);
		page5DevTelValue = (EditText) this.findViewById(R.id.open_layout_right_page5_dev_tel_value);
		page5ChannelValue = (EditText) this.findViewById(R.id.open_layout_right_page5_channel_value);
		page5CreditValue = (EditText) this.findViewById(R.id.open_layout_right_page5_credit_value);
		page5OperValue = (EditText) this.findViewById(R.id.open_layout_right_page5_oper_value);
		page5IdTypeValue = (TextView) this.findViewById(R.id.open_layout_right_page5_id_type_value);
		page5IdNumberValue = (EditText) this.findViewById(R.id.open_layout_right_page5_id_number_value);
		page5CommentsValue = (EditText) this.findViewById(R.id.open_layout_right_page5_comments_value);
		TextView title_show = (TextView) findViewById(R.id.open_layout_right_page2_selected_package);
		openFinishMsg = (TextView) openFinishWindow.findViewById(R.id.open_layout_right_finish_text);
		openFInishHeader = (TextView) openFinishWindow.findViewById(R.id.open_layout_finish_header);

		OperLoginResponse operLoginInfo = (OperLoginResponse) appCache.getAsObject("operLoginResponse");
		page5DevName = (EditText) this.findViewById(R.id.open_layout_right_page5_dev_value);
		page5ChannelNameValue = (EditText) this.findViewById(R.id.open_layout_right_page5_channel_name_value);
		page5DevName.setText(operLoginInfo.getOperName());
		page5DevIdValue.setText(operLoginInfo.getLoginCode());

		saleOpenOrderSubmitRequest = new SaleOpenOrderSubmitRequest();
	}

	@Override
	protected void setListener() {

		// 主页按钮的点击事件
		commonBtnHome.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				returnHome();
			}
		});

		page1TeleTypeTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openPage1TeleTypeListWindow);
				} else if (editView.getChildAt(0) != openPage1TeleTypeListWindow) {
					editView.removeAllViews();
					editView.addView(openPage1TeleTypeListWindow);
					showToastMsg("add openTelePage1TypeListWindow");
				}

				showEditWindow(v);
			}
		});

		page1NumberDefineTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openNumberDefineListWindow);
				} else if (editView.getChildAt(0) != openNumberDefineListWindow) {
					editView.removeAllViews();
					editView.addView(openNumberDefineListWindow);
					showToastMsg("add openNumberDefineListWindow");
				}

				showEditWindow(v);
			}
		});

		page1PrePaidTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openPrePaidListWindow);
				} else if (editView.getChildAt(0) != openPrePaidListWindow) {
					editView.removeAllViews();
					editView.addView(openPrePaidListWindow);
					showToastMsg("add openPrePaidListWindow");
				}

				showEditWindow(v);

			}
		});

		page1NumberTypeTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openNumberTypeListWindow);
				} else if (editView.getChildAt(0) != openNumberTypeListWindow) {
					editView.removeAllViews();
					editView.addView(openNumberTypeListWindow);
					showToastMsg("add openNumberTypeListWindow");
				}

				showEditWindow(v);
			}
		});

		page1NextStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				String accNbr = page1SelectedPhone.getText().toString();
				if (StringUtils.isBlank(accNbr) || accNbr.equals("未选择号码")) {
					showToastMsg("请先选择号码！");
					return;
				}
				getSelectPackageList();

				setOpenLaoutRightPagesAnimRightToLeft();
				openLaoutRightPages.setDisplayedChild(1);
				// open02.requestFocus();
				openLeftButtonListAdapter.setSelectedPosition(1);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
			}
		});

		page1Searching.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String teleType = page1TeleTypeValue.getText().toString();
				if (teleType == null || teleType.equals(""))
					showToastMsg("请选择电信类型！");
				getSelectNumberList();
			}
		});

		page2PreStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				openLeftButtonListAdapter.setSelectedPosition(0);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimLeftToRight();
				openLaoutRightPages.setDisplayedChild(0);
				// open01.requestFocus();
			}
		});

		page2NextStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * openLeftButtonListAdapter.setSelectedPosition(2);
				 * openLeftButtonListAdapter.notifyDataSetInvalidated();
				 * setOpenLaoutRightPagesAnimRightToLeft();
				 * openLaoutRightPages.setDisplayedChild(2);
				 */
				// open03.requestFocus();

				if (editView.getChildCount() == 0) {
					editView.addView(page2NextStepSelectWindow);
				} else if (editView.getChildAt(0) != page2NextStepSelectWindow) {

					editView.removeAllViews();
					editView.addView(page2NextStepSelectWindow);
				}

				showEditWindow(v, 3);
			}
		});
		page2NextStepActionOk.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openLeftButtonListAdapter.setSelectedPosition(2);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimRightToLeft();
				openLaoutRightPages.setDisplayedChild(2);
				editWindow.dismiss();
				// page3ActionSelectResult.setText("参加");
				Page3PhonePlanFlag = 1;
				openListAdapter_actionSelect.setSelectedPosition(0);
				page3PhonePlan.setVisibility(View.VISIBLE);
				Page3SelectActionOnOffView.setImageLevel(1);
			}
		});
		page2NextStepActionNo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openLeftButtonListAdapter.setSelectedPosition(3);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimRightToLeft();
				openLaoutRightPages.setDisplayedChild(3);
				editWindow.dismiss();
				// page3ActionSelectResult.setText("不参加");
				Page3PhonePlanFlag = 0;
				openListAdapter_actionSelect.setSelectedPosition(1);
				page3PhonePlan.setVisibility(View.INVISIBLE);
				Page3SelectActionOnOffView.setImageLevel(0);
			}
		});

		page2Searching.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				getSelectPackageList();
			}
		});

		/*
		 * page3ActionSelectTitle.setOnClickListener(new
		 * Button.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * if (editView.getChildCount() == 0) {
		 * editView.addView(actionSelectListWindow); } else if
		 * (editView.getChildAt(0) != actionSelectListWindow) {
		 * editView.removeAllViews(); editView.addView(actionSelectListWindow);
		 * showToastMsg("add actionSelectListWindow"); }
		 * 
		 * showEditWindow(v); } });
		 */
		Page3SelectActionOnOffView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Page3PhonePlanFlag == 0) {
					Page3SelectActionOnOffView.setImageLevel(1);
					Page3PhonePlanFlag = 1;
					page3PhonePlan.setVisibility(View.VISIBLE);
				} else {
					Page3SelectActionOnOffView.setImageLevel(0);
					Page3PhonePlanFlag = 0;
					page3PhonePlan.setVisibility(View.INVISIBLE);
				}
			}
		});

		page3PreStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				openLeftButtonListAdapter.setSelectedPosition(1);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimLeftToRight();
				openLaoutRightPages.setDisplayedChild(1);
				// open02.requestFocus();
			}
		});

		page3NextStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				openLeftButtonListAdapter.setSelectedPosition(3);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimRightToLeft();
				openLaoutRightPages.setDisplayedChild(3);
				// open04.requestFocus();
			}
		});

		page4PreStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				openLeftButtonListAdapter.setSelectedPosition(2);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimLeftToRight();
				openLaoutRightPages.setDisplayedChild(2);
				// open03.requestFocus();
			}
		});

		page4NextStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				openLeftButtonListAdapter.setSelectedPosition(4);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimRightToLeft();
				openLaoutRightPages.setDisplayedChild(4);
				// open05.requestFocus();
			}
		});

		page4CertTypeButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openIdTypeListWindow);
				} else if (editView.getChildAt(0) != openIdTypeListWindow) {
					editView.removeAllViews();
					editView.addView(openIdTypeListWindow);
					showToastMsg("add openIdTypeListWindow");
				}

				showEditWindow(v);
			}
		});

		page4PayTypeButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openPayTypeListWindow);
				} else if (editView.getChildAt(0) != openPayTypeListWindow) {
					editView.removeAllViews();
					editView.addView(openPayTypeListWindow);
					showToastMsg("add openPayTypeListWindow");
				}

				showEditWindow(v);
			}
		});

		page4CustSexButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openCustSexListWindow);
				} else if (editView.getChildAt(0) != openCustSexListWindow) {
					editView.removeAllViews();
					editView.addView(openCustSexListWindow);
					showToastMsg("add openCustSexListWindow");
				}

				showEditWindow(v);
			}
		});

		page5PreStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				openLeftButtonListAdapter.setSelectedPosition(3);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				setOpenLaoutRightPagesAnimLeftToRight();
				openLaoutRightPages.setDisplayedChild(3);

				// open04.requestFocus();
			}
		});

		page5FinishStep.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				showFinishWindow(v);
				// List<Map<String, Object>> listItems = new
				// ArrayList<Map<String, Object>>();
				String msg = "";
				String detailed = "";

				saleOpenOrderSubmitRequest.setIdType(openIdTypeListData.get(posIdTypeSelected).get("ID").toString());
				saleOpenOrderSubmitRequest.setIdNumber(page4CertNumberValue.getText().toString());
				saleOpenOrderSubmitRequest.setAuthEndDate(page4CertExpDate.getText().toString());
				saleOpenOrderSubmitRequest.setAuthAdress(page4CertAddrValue.getText().toString());
				saleOpenOrderSubmitRequest.setCustomerName(page4UserNameValue.getText().toString());
				saleOpenOrderSubmitRequest.setCustSex(openCustSexListData.get(posCustSexSelected).get("ID").toString());
				// born_date String N 出生日期
				// saleOpenOrderSubmitRequest.setCustPost(page4UserZipcodeValue.getText().toString());
				// saleOpenOrderSubmitRequest.setCustEmail(page4UserEmailValue.getText().toString());
				saleOpenOrderSubmitRequest.setRemarkDesc(page4CommentsValue.getText().toString());
				// saleOpenOrderSubmitRequest.setContactName(page4ContactNameValue.getText().toString());
				// saleOpenOrderSubmitRequest.setContactPhone(page4ContactTelValue.getText().toString());
				// saleOpenOrderSubmitRequest.setContactAdress(page4ContactAddrValue.getText().toString());
				saleOpenOrderSubmitRequest.setDevolopName(page5DevName.getText().toString());
				saleOpenOrderSubmitRequest.setDevolopPost(page5DevIdValue.getText().toString());
				saleOpenOrderSubmitRequest.setDevolopPhone(page5DevTelValue.getText().toString());
				saleOpenOrderSubmitRequest.setDevolopChannelId(page5ChannelValue.getText().toString());
				saleOpenOrderSubmitRequest.setDevolopChannelName(page5ChannelNameValue.getText().toString());
				saleOpenOrderSubmitRequest.setCreditFirst(page5CreditValue.getText().toString());
				saleOpenOrderSubmitRequest.setHandlerName(page5OperValue.getText().toString());
				saleOpenOrderSubmitRequest.setHandlerIdType("02");
				saleOpenOrderSubmitRequest.setHandlerIdNumber(page5IdNumberValue.getText().toString());
				saleOpenOrderSubmitRequest.setHandlerRemarkDesc(page5CommentsValue.getText().toString());
				saleOpenOrderSubmitRequest.setTeleType(page1TeleTypeValue.getText().toString());
				saleOpenOrderSubmitRequest.setPaymentType(openPayTypeListData.get(posPayTypeSelected).get("ID").toString());
				saleOpenOrderSubmitRequest.setJsessionid(appCache.getAsString("jsessionid"));

				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getIdType())) {
					msg += "     [证件类型]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getIdNumber())) {
					msg += "     [证件号码]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getAuthEndDate())) {
					msg += "     [证件到期时间]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getAuthAdress())) {
					msg += "     [证件地址]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getCustomerName())) {
					msg += "     [客户名称]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getCustSex())) {
					msg += "     [客户性别]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getDevolopName())) {
					msg += "     [发展人名称]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getDevolopPost())) {
					msg += "     [发展人编码]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getDevolopChannelId())) {
					msg += "     [发展渠道编码]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getDevolopChannelName())) {
					msg += "     [发展渠道名称]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getCreditFirst())) {
					msg += "     [初始信用度]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getAccNbr())) {
					msg += "     [设备号码]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getAccNbrFee())) {
					msg += "     [号预存]\n";
				}
				if (StringUtils.isBlank(saleOpenOrderSubmitRequest.getOfrId())) {
					msg += "     [套餐ID]\n";
				}
				if (saleOpenOrderSubmitRequest.getTeleType().equals("2G") && StringUtils.isBlank(saleOpenOrderSubmitRequest.getPaymentType())) {
					msg += "     [付费类型ID]\n";
				}

				if (msg.length() > 0) {
					openFInishHeader.setText("校验信息");
					openFinishMsg.setText("尊敬的用户：\n     您有几处必填项未填，请返回重写 ！\n\n" + msg);
					finishCommit.setClickable(false);
				} else {
					openFInishHeader.setText("配置清单");
					detailed += "     [设备号码]  -  " + saleOpenOrderSubmitRequest.getAccNbr() + "\n";
					detailed += "     [套餐名称]  -  " + saleOpenOrderSubmitRequest.getOfrInfo() + "\n";
					detailed += "     [客户名称]  -  " + saleOpenOrderSubmitRequest.getCustomerName() + "\n";
					detailed += "     [客户性别]  -  " + (saleOpenOrderSubmitRequest.getCustSex().equals("1") ? "男性" : "女性") + "\n";
					detailed += "     [证件号码]  -  " + saleOpenOrderSubmitRequest.getIdNumber() + "\n";
					detailed += "     [证件地址]  -  " + saleOpenOrderSubmitRequest.getAuthAdress() + "\n";
					detailed += "     [初始信用]  -  " + saleOpenOrderSubmitRequest.getCreditFirst() + "\n";
					openFinishMsg.setText("尊敬的用户：\n     请再次核对数据后提交订单 ！\n\n" + detailed);
					finishCommit.setClickable(true);
				}
			}
		});

		finishReturn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				finishWindow.dismiss();
			}
		});

		// 提交订单按钮
		finishCommit.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (startRestOrderSubmit(saleOpenOrderSubmitRequest)) { // success
					openFinishFlipper.setDisplayedChild(1);
					// openFinishMsg.setText("\n\n\n   订单提交成功！");
					// showToastMsg("订单提交成功！");
				} else {// fail
					openFinishMsg.setText("\n\n\n   订单提交失败！");
					// showToastMsg("订单提交失败！");
				}
			}
		});

		finishNewBusi.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 清空内容...
				openFinishFlipper.setDisplayedChild(0);
				openLeftButtonListAdapter.setSelectedPosition(0);
				openLeftButtonListAdapter.notifyDataSetInvalidated();
				openLaoutRightPages.setDisplayedChild(0);
				finishWindow.dismiss();

			}
		});

		finishExit.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				finishWindow.dismiss();
				// 回到主页
				returnHome();
			}
		});

		// 处理选产品页面按钮事件
		/*
		 * page2TeleTypeTitle.setOnClickListener(new Button.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * if (editView.getChildCount() == 0) {
		 * editView.addView(openPage2TeleTypeListWindow); } else if
		 * (editView.getChildAt(0) != openPage2TeleTypeListWindow) {
		 * editView.removeAllViews();
		 * editView.addView(openPage2TeleTypeListWindow);
		 * showToastMsg("add openTeleTypeListWindow"); }
		 * 
		 * showEditWindow(v); } });
		 */

		page2PackageTypeTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openPackageTypeListWindow);
				} else if (editView.getChildAt(0) != openPackageTypeListWindow) {
					editView.removeAllViews();
					editView.addView(openPackageTypeListWindow);
					showToastMsg("add openPackageTypeListWindow");
				}

				showEditWindow(v);
			}
		});

		page2MonthConsumptionTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openMonthConsumptionListWindow);
				} else if (editView.getChildAt(0) != openMonthConsumptionListWindow) {
					editView.removeAllViews();
					editView.addView(openMonthConsumptionListWindow);
					showToastMsg("add openMonthConsumptionListWindow");
				}

				showEditWindow(v);

			}
		});

		page2MonthCallTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openMonthCallListWindow);
				} else if (editView.getChildAt(0) != openMonthCallListWindow) {
					editView.removeAllViews();
					editView.addView(openMonthCallListWindow);
					showToastMsg("add openMonthCallListWindow");
				}

				showEditWindow(v);
			}
		});

		page2MonthNetTitle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (editView.getChildCount() == 0) {
					editView.addView(openMonthNetListWindow);
				} else if (editView.getChildAt(0) != openMonthNetListWindow) {
					editView.removeAllViews();
					editView.addView(openMonthNetListWindow);
					showToastMsg("add openMonthNetListWindow");
				}

				showEditWindow(v);
			}
		});

		// page4页面
		// 客戶资料校验
		page4CertNumberValidate.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				CheckCustInfoRequest checkCustInfoRequest = new CheckCustInfoRequest();

				checkCustInfoRequest.setJsessionid(appCache.getAsString("jsessionid"));
				OperLoginResponse operLogin = (OperLoginResponse) appCache.getAsObject("operLoginResponse");
				checkCustInfoRequest.setOperId(operLogin.getOperId());
				checkCustInfoRequest.setIdType(openIdTypeListData.get(posIdTypeSelected).get("ID").toString());
				String idNumber = page4CertNumberValue.getText().toString();
				if (StringUtils.isBlank(idNumber)) {
					showToastMsg("请输入证件号码！");
					return;
				}
				checkCustInfoRequest.setIdNumber(idNumber);
				/*
				 * if (checkCustInfoRequest.getIdType().equals("02")) { if
				 * (IdcardUtils.validateCard(idNumber)) {
				 * checkCustInfoRequest.setIdNumber(idNumber); } else {
				 * showToastMsg("身份证号码有误,请检查后重新输入!"); return; } }
				 */
				checkCustInfoRequest.setChannelType("101");
				checkCustInfoRequest.setAccessType("01");
				checkCustInfoRequest.setOperDept("100");
				checkCustInfoRequest.setCity("591");
				checkCustInfoRequest.setDistrict("591");
				checkCustInfoRequest.setProvince("59");

				// 开始调用客户资料校验接口
				startRestCheckCustInfo(checkCustInfoRequest);
			}
		});

		// page5提交

	}

	@Override
	protected void prepareData() {

		prepareData(true, new DataTask() {

			@Override
			public void onPreExecute() {
				showProgressDialog();
			}

			@Override
			public boolean doInBackground() {

				boolean flag = false;

				flag = queryTeleTypeList();
				if (!flag) {
					return false;
				}

				flag = queryNumberDefineList();
				if (!flag) {
					return false;
				}

				flag = queryPrepaidList();
				if (!flag) {
					return false;
				}

				flag = queryNumberTypeList();
				if (!flag) {
					return false;
				}

				flag = querySelectNumberList(true);
				if (!flag) {
					return false;
				}

				flag = queryPackageTypeList();
				if (!flag) {
					return false;
				}

				flag = queryMonthConsumptionList();
				if (!flag) {
					return false;
				}

				flag = queryMonthCallList();
				if (!flag) {
					return false;
				}

				flag = queryMonthNetList();
				if (!flag) {
					return false;
				}

				flag = querySelectPackageList(true);
				if (!flag) {
					return false;
				}
				flag = setActionSelectList();
				if (!flag) {
					return false;
				}
				flag = queryIdTypeList();
				if (!flag) {
					return false;
				}
				flag = queryPayTypeList();
				if (!flag) {
					return false;
				}
				flag = queryCustSexList();
				if (!flag) {
					return false;
				}

				return true;
			}

			@Override
			public void showErr() {
				showToastMsg("获取数据失败");
			}

			@Override
			public void showData() {

				createOpenTeleType1List();
				createOpenNumberDefineList();
				createQueryPrepaidList();
				createQuetyNumberTypeList();
				createQuetySelectNumberList();
				// createOpenList();

				// 准备选产品页面的数据
				// createOpenTeleType2List();
				createOpenPackageTypeList();
				createOpenMonthConsumptionList();
				createOpenMonthCallList();
				createOpenMonthNetList();

				createQuerySelectPackageList();
				createActionSelectList();
				createIdTypeList();
				createPayTypeList();
				createCustSexList();
			}
		});

	}

	private void createOpenLeftButtonList() {

		// 获取左侧菜单的配置
		ArrayList<Map<String, Object>> mList = MenuLeftConfig.getOpenLeftMenuMap();

		openLeftButtonListAdapter = new OpenLeftButtonListAdapter(this, mList);
		openLeftButtonListAdapter.setSelectedPosition(0);
		openButtonListView.setAdapter(openLeftButtonListAdapter);
		openButtonListView.setClickable(true);
		openButtonListView.setFocusable(true);

		openButtonListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openButtonListView.getItemAtPosition(arg2);

				setOpenLaoutRightPagesAnimRightToLeft();

				switch (arg2) {
				case 0:
					if (openLeftButtonListAdapter.getSelectedPosition() != arg2) {
						openLeftButtonListAdapter.setSelectedPosition(arg2);
						openLeftButtonListAdapter.notifyDataSetInvalidated();
						openLaoutRightPages.setDisplayedChild(0);

					}
					break;
				case 1:
					if (openLeftButtonListAdapter.getSelectedPosition() != arg2) {
						String accNbr = page1SelectedPhone.getText().toString();
						if (StringUtils.isBlank(accNbr) || accNbr.equals("未选择号码")) {
							showToastMsg("请先选择号码！");
							return;
						}
						getSelectPackageList();
						openLeftButtonListAdapter.setSelectedPosition(arg2);
						openLeftButtonListAdapter.notifyDataSetInvalidated();
						openLaoutRightPages.setDisplayedChild(1);

					}
					break;
				case 2:
					if (openLeftButtonListAdapter.getSelectedPosition() != arg2) {
						openLeftButtonListAdapter.setSelectedPosition(arg2);
						openLeftButtonListAdapter.notifyDataSetInvalidated();
						openLaoutRightPages.setDisplayedChild(2);

					}
					break;
				case 3:
					if (openLeftButtonListAdapter.getSelectedPosition() != arg2) {
						openLeftButtonListAdapter.setSelectedPosition(arg2);
						openLeftButtonListAdapter.notifyDataSetInvalidated();
						openLaoutRightPages.setDisplayedChild(3);

					}
					break;
				case 4:
					if (openLeftButtonListAdapter.getSelectedPosition() != arg2) {
						openLeftButtonListAdapter.setSelectedPosition(arg2);
						openLeftButtonListAdapter.notifyDataSetInvalidated();
						openLaoutRightPages.setDisplayedChild(4);

					}
					break;
				default:
					break;
				}

			}
		});

	}

	private void createOpenTeleType1List() {
		openListAdapter_Page1TeleType = new OpenListAdapter(this, openPageTeleTypeListData);

		openListAdapter_Page1TeleType.setSelectedPosition(0);
		openPage1TeleTypeList.setAdapter(openListAdapter_Page1TeleType);

		// openTeleTypeList.setItemsCanFocus(true);

		openPage1TeleTypeList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openPage1TeleTypeList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page1TeleTypeValue.setText(title);

				openListAdapter_Page1TeleType.setSelectedPosition(arg2);
				openListAdapter_Page1TeleType.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});
	}

	private void createOpenNumberDefineList() {

		openListAdapter_NumberDefine = new OpenListAdapter(this, openNumberDefineListData);

		openListAdapter_NumberDefine.setSelectedPosition(0);
		openNumberDefineList.setAdapter(openListAdapter_NumberDefine);

		// openNumberDefineList.setItemsCanFocus(true);

		openNumberDefineList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openNumberDefineList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page1NumberDefineValue.setText(title);

				openListAdapter_NumberDefine.setSelectedPosition(arg2);
				openListAdapter_NumberDefine.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});

	}

	private void createActionSelectList() {

		openListAdapter_actionSelect = new OpenListAdapter(this, actionSelectListData);
		Log.i("LIST", "list_size=" + openListAdapter_actionSelect.getCount());

		openListAdapter_actionSelect.setSelectedPosition(1);
		actionSelectList.setAdapter(openListAdapter_actionSelect);

		actionSelectList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) actionSelectList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page3ActionSelectResult.setText(title);

				Page3PhonePlanFlag = (Integer) item.get("flag");
				if (Page3PhonePlanFlag == 0) {
					page3PhonePlan.setVisibility(View.INVISIBLE);
				} else {
					page3PhonePlan.setVisibility(View.VISIBLE);
				}

				openListAdapter_actionSelect.setSelectedPosition(arg2);
				openListAdapter_actionSelect.notifyDataSetInvalidated();
				editWindow.dismiss();
			}
		});
	}

	private void createQueryPrepaidList() {

		openListAdapter_PrePaid = new OpenListAdapter(this, openPrePaidListData);

		openListAdapter_PrePaid.setSelectedPosition(0);
		openPrePaidList.setAdapter(openListAdapter_PrePaid);

		openPrePaidList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openPrePaidList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page1PrePaidValue.setText(title);

				openListAdapter_PrePaid.setSelectedPosition(arg2);
				openListAdapter_PrePaid.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});

	}

	private void createQuetyNumberTypeList() {

		openListAdapter_NumberType = new OpenListAdapter(this, openNumberTypeListData);

		openListAdapter_NumberType.setSelectedPosition(0);
		openNumberTypeList.setAdapter(openListAdapter_NumberType);

		openNumberTypeList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openNumberTypeList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page1NumberTypeValue.setText(title);

				openListAdapter_NumberType.setSelectedPosition(arg2);
				openListAdapter_NumberType.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});
	}

	private void createQuetySelectNumberList() {

		// TODO

		openSelectNumberGridViewLists.clear();

		int heightItemPx = com.tydic.android.usp.util.AppUtil.dp2Px(this, Constants.VIEWPAGER_GRIDITEM_HEIGHT);
		// int rows =(int) Math.floor(openSelectNumberViewPager.getHeight() /
		// heightItemPx);
		// other height: 25+40*3+25+25+40 : 115+40*3
		int rows = (int) Math.floor((openLaoutRightPages.getHeight() - AppUtil.dp2Px(this, 235)) / heightItemPx);

		final int pageSize = rows * 4;
		final int PageCount = (int) Math.ceil((openSelectNumberListData.size() / (float) pageSize));
		// showToastMsg("PageCount=" + String.valueOf(PageCount) + "pageSize=" +
		// String.valueOf(pageSize));

		Log.i(TAG, "heightItemPx: " + heightItemPx);
		Log.i(TAG, "openSelectNumberViewPager.getHeight(): " + openSelectNumberViewPager.getHeight());
		Log.i(TAG, "号码每页行数: " + rows);
		Log.i(TAG, "号码一共有: " + PageCount);

		for (int i = 0; i < PageCount; i++) {
			// GridView gv = new GridView(this);
			final GridView gv = (GridView) mLayoutInflater.inflate(R.layout.viewpager_gridview, null);
			final OpenSelectNumberListViewPagerAdapter adapter_gv = new OpenSelectNumberListViewPagerAdapter(this, openSelectNumberListData, i, pageSize);
			gv.setAdapter(adapter_gv);
			gv.setGravity(Gravity.CENTER);
			gv.setClickable(true);
			gv.setFocusable(true);
			gv.setNumColumns(4);
			gv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
					Map<String, Object> item = (HashMap<String, Object>) gv.getItemAtPosition(arg2);

					String accNbr = String.valueOf(item.get("acc_nbr"));
					String accNbrFee = String.valueOf(item.get("fee"));
					TextView title_show = (TextView) findViewById(R.id.open_layout_right_page1_selected_phone);
					title_show.setText(accNbr);

					String teleType = page1TeleTypeValue.getText().toString();
					if (teleType.equals("2G")) {
						page4PayType.setVisibility(View.VISIBLE);
					} else {
						page4PayType.setVisibility(View.INVISIBLE);
					}

					saleOpenOrderSubmitRequest.setAccNbr(accNbr);
					saleOpenOrderSubmitRequest.setAccNbrFee(accNbrFee);

					adapter_gv.setSelectedPosition(arg2);

					if (lastSelectedItemAdapter == null) {
						lastSelectedItemAdapter = adapter_gv;
					} else if (adapter_gv != lastSelectedItemAdapter) {
						lastSelectedItemAdapter.setSelectedPosition(-1);
						lastSelectedItemAdapter.notifyDataSetInvalidated();
						lastSelectedItemAdapter = adapter_gv;
					}

					adapter_gv.notifyDataSetInvalidated();

				}
			});

			openSelectNumberGridViewLists.add(gv);

		}

		openSelectNumberViewPagerAdapter = new ViewPagerAdapter(this, openSelectNumberGridViewLists);
		openSelectNumberViewPager.setAdapter(openSelectNumberViewPagerAdapter);

	}

	/*
	 * private void createOpenTeleType2List() { openListAdapter_Page2TeleType =
	 * new OpenListAdapter(this, openPageTeleTypeListData);
	 * 
	 * openListAdapter_Page2TeleType.setSelectedPosition(0);
	 * openPage2TeleTypeList.setAdapter(openListAdapter_Page2TeleType);
	 * 
	 * // openTeleTypeList.setItemsCanFocus(true);
	 * 
	 * openPage2TeleTypeList.setOnItemClickListener(new OnItemClickListener() {
	 * 
	 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int
	 * arg2, long arg3) { Log.i(getClass().getName(), "position=" + arg2 + " " +
	 * arg3); Map<String, Object> item = (HashMap<String, Object>)
	 * openPage2TeleTypeList.getItemAtPosition(arg2); String title =
	 * String.valueOf(item.get("TITLE")); page2TeleTypeValue.setText(title);
	 * 
	 * openListAdapter_Page2TeleType.setSelectedPosition(arg2);
	 * openListAdapter_Page2TeleType.notifyDataSetInvalidated(); //
	 * openListAdapter.notifyDataSetChanged();
	 * 
	 * editWindow.dismiss(); } }); }
	 */

	private void createOpenPackageTypeList() {

		openListAdapter_PackageType = new OpenListAdapter(this, openPackageTypeListData);

		openListAdapter_PackageType.setSelectedPosition(0);
		openPackageTypeList.setAdapter(openListAdapter_PackageType);

		openPackageTypeList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openPackageTypeList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page2PackageTypeValue.setText(title);

				openListAdapter_PackageType.setSelectedPosition(arg2);
				openListAdapter_PackageType.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});

	}

	private void createOpenMonthConsumptionList() {

		openListAdapter_MonthConsumption = new OpenListAdapter(this, openMonthConsumptionListData);

		openListAdapter_MonthConsumption.setSelectedPosition(0);
		openMonthConsumptionList.setAdapter(openListAdapter_MonthConsumption);

		openMonthConsumptionList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openMonthConsumptionList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page2MonthConsumptionValue.setText(title);

				openListAdapter_MonthConsumption.setSelectedPosition(arg2);
				openListAdapter_MonthConsumption.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});

	}

	private void createOpenMonthCallList() {

		openListAdapter_MonthCall = new OpenListAdapter(this, openMonthCallListData);

		openListAdapter_MonthCall.setSelectedPosition(0);
		openMonthCallList.setAdapter(openListAdapter_MonthCall);

		openMonthCallList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openMonthCallList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page2MonthCallValue.setText(title);

				openListAdapter_MonthCall.setSelectedPosition(arg2);
				openListAdapter_MonthCall.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});

	}

	private void createOpenMonthNetList() {

		openListAdapter_MonthNet = new OpenListAdapter(this, openMonthNetListData);

		openListAdapter_MonthNet.setSelectedPosition(0);
		openMonthNetList.setAdapter(openListAdapter_MonthNet);

		openMonthNetList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openMonthNetList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page2MonthNetValue.setText(title);

				openListAdapter_MonthNet.setSelectedPosition(arg2);
				openListAdapter_MonthNet.notifyDataSetInvalidated();
				// openListAdapter.notifyDataSetChanged();

				editWindow.dismiss();
			}
		});

	}

	private void createQuerySelectPackageList() {

		// TODO

		openSelectPackageGridViewLists.clear();

		int heightItemPx = com.tydic.android.usp.util.AppUtil.dp2Px(this, Constants.VIEWPAGER_GRIDITEM_HEIGHT1);
		// int rows =(int) Math.floor(openSelectPackageViewPager.getHeight() /
		// heightItemPx);
		// other height: 25+40*4+25+25+40 : 115+40*4
		int rows = (int) Math.floor((openLaoutRightPages.getHeight() - AppUtil.dp2Px(this, 235)) / heightItemPx);

		final int pageSize = rows * 4;
		final int pageCount = (int) Math.ceil((openSelectPackageListData.size() / (float) pageSize));
		// showToastMsg("PageCount=" + String.valueOf(PageCount) + "pageSize=" +
		// String.valueOf(pageSize));

		Log.i(TAG, "heightItemPx: " + heightItemPx);
		Log.i(TAG, "openSelectPackageViewPager.getHeight(): " + openSelectPackageViewPager.getHeight());
		Log.i(TAG, "套餐每页行数: " + rows);
		Log.i(TAG, "套餐一共有: " + pageCount);

		for (int i = 0; i < pageCount; i++) {
			// GridView gv = new GridView(this);
			final GridView gv = (GridView) mLayoutInflater.inflate(R.layout.viewpager_gridview, null);
			final OpenSelectPackageListViewPagerAdapter adapter_gv = new OpenSelectPackageListViewPagerAdapter(this, openSelectPackageListData, i, pageSize);
			gv.setAdapter(adapter_gv);
			gv.setGravity(Gravity.CENTER);
			gv.setClickable(true);
			gv.setFocusable(true);
			gv.setNumColumns(4);
			gv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
					Map<String, Object> item = (HashMap<String, Object>) gv.getItemAtPosition(arg2);

					String ofrName = String.valueOf(item.get("ofr_name"));
					String ofrId = String.valueOf(item.get("ofr_id"));
					TextView title_show = (TextView) findViewById(R.id.open_layout_right_page2_selected_package);
					title_show.setText(ofrName);

					saleOpenOrderSubmitRequest.setOfrId(ofrId);
					saleOpenOrderSubmitRequest.setOfrInfo(ofrName);

					adapter_gv.setSelectedPosition(arg2);

					if (lastSelectedPackageItemAdapter == null) {
						lastSelectedPackageItemAdapter = adapter_gv;
					} else if (adapter_gv != lastSelectedPackageItemAdapter) {
						lastSelectedPackageItemAdapter.setSelectedPosition(-1);
						lastSelectedPackageItemAdapter.notifyDataSetInvalidated();
						lastSelectedPackageItemAdapter = adapter_gv;
					}

					adapter_gv.notifyDataSetInvalidated();

				}
			});

			openSelectPackageGridViewLists.add(gv);

		}

		openSelectPackageViewPagerAdapter = new ViewPagerAdapter(this, openSelectPackageGridViewLists);
		openSelectPackageViewPager.setAdapter(openSelectPackageViewPagerAdapter);

	}

	// 编辑窗口
	private void showEditWindow(View parent) {

		int scrWidth = mWindowManager.getDefaultDisplay().getWidth();
		int scrHeight = mWindowManager.getDefaultDisplay().getHeight();
		if (editWindow == null) {
			editWindow = new PopupWindow(editView, scrWidth / 2 + 10, scrHeight - 110);

			editWindow.setFocusable(true);
			editWindow.setOutsideTouchable(true);
			editWindow.setAnimationStyle(R.style.PopupAnimationDownup);
			editWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
				@Override
				public void onDismiss() {
					hidePopupMask();
				}
			});

			// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
			editWindow.setBackgroundDrawable(new BitmapDrawable());
		}

		showPopupMask();
		editWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
		editWindow.update();

	}

	private void showEditWindow(View parent, int ratio) {

		int scrWidth = mWindowManager.getDefaultDisplay().getWidth();
		int scrHeight = mWindowManager.getDefaultDisplay().getHeight();
		if (editWindow == null) {
			editWindow = new PopupWindow(editView, scrWidth / ratio, scrHeight / ratio);

			editWindow.setFocusable(true);
			editWindow.setOutsideTouchable(true);
			editWindow.setAnimationStyle(R.style.PopupAnimationDownup);
			editWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
				@Override
				public void onDismiss() {
					hidePopupMask();
				}
			});

			// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
			editWindow.setBackgroundDrawable(new BitmapDrawable());
		}

		showPopupMask();
		editWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
		editWindow.update();
	}

	// 编辑窗口
	private void showFinishWindow(View parent) {

		int scrWidth = mWindowManager.getDefaultDisplay().getWidth();
		int scrHeight = mWindowManager.getDefaultDisplay().getHeight();
		if (finishWindow == null) {
			// finishWindow = new PopupWindow(finishView, scrWidth -
			// AppUtil.dp2Px(this, 450), maskLayout.getHeight());
			finishWindow = new PopupWindow(finishView, scrWidth / 2 + 10, scrHeight - 110);
			finishWindow.setFocusable(true);
			// finishWindow.setOutsideTouchable(true);
			finishWindow.setAnimationStyle(R.style.PopupAnimationDownup);
			finishWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
				@Override
				public void onDismiss() {
					page5FinishStep.setSelected(false);
					hidePopupMask();
				}
			});

			// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
			// finishWindow.setBackgroundDrawable(new BitmapDrawable());
		}

		page5FinishStep.setSelected(true);
		showPopupMask();
		finishWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
		finishWindow.update();

	}

	private void setOpenLaoutRightPagesAnimLeftToRight() {
		openLaoutRightPages.setInAnimation(slideInLeft);
		openLaoutRightPages.setOutAnimation(slideOutRight);
	}

	private void setOpenLaoutRightPagesAnimRightToLeft() {
		openLaoutRightPages.setInAnimation(slideInRight);
		openLaoutRightPages.setOutAnimation(slideOutLeft);
	}

	private void showPopupMask() {
		maskLayout.startAnimation(fadeIn);
		maskLayout.setVisibility(View.VISIBLE);
	}

	private void hidePopupMask() {
		maskLayout.startAnimation(fadeOut);
		maskLayout.setVisibility(View.GONE);
	}

	// 拦截触摸事件
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {

			// 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
			View v = getCurrentFocus();
			if (v != null && v.getClass().getName() != "EditText") {
				hideSoftInput(v.getWindowToken());
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	private void hideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	// 添加菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	// 菜单点击事件处理
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help_menu:
			Toast.makeText(this, "你选中的是 ‘帮助’ 菜单！", Toast.LENGTH_SHORT).show();
			break;
		case R.id.open_menu:
			Toast.makeText(this, "你选中的是 ‘反馈’ 菜单！", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle(R.string.attention_title).setMessage(R.string.exit_prompt).setPositiveButton(R.string.OK_text, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			}).setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			}).show();
			break;

		default:
			break;
		}
		return false;
	}

	// business

	private boolean queryTeleTypeList() {

		openPageTeleTypeListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("tele_type") != null) {
			openPageTeleTypeListData = getDataFromCodeListData("tele_type");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("tele_type");
			if (getCodeListInfo(request)) {
				openPageTeleTypeListData = getDataFromCodeListData("tele_type");
				return true;
			}
			return false;
		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限1");
		 * openPageTeleTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "2G");
		 * openPageTeleTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "3G");
		 * openPageTeleTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "4G");
		 * openPageTeleTypeListData.add(map);
		 * 
		 * return true;
		 */
	}

	/**
	 * 从codelist中获取号段
	 * 
	 * @return
	 */
	private boolean queryNumberDefineList() {
		openNumberDefineListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("mob_section") != null) {
			openNumberDefineListData = getDataFromCodeListData("mob_section");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("mob_section");
			if (getCodeListInfo(request)) {
				openNumberDefineListData = getDataFromCodeListData("mob_section");
				return true;
			}
			return false;
		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限");
		 * openNumberDefineListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "186");
		 * openNumberDefineListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "185");
		 * openNumberDefineListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "156");
		 * openNumberDefineListData.add(map);
		 * 
		 * return true;
		 */
	}

	private boolean queryPrepaidList() {
		openPrePaidListData = new ArrayList<Map<String, Object>>();
		// openNumberDefineListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("price_range") != null) {
			openPrePaidListData = getDataFromCodeListData("price_range");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("price_range");
			if (getCodeListInfo(request)) {
				openPrePaidListData = getDataFromCodeListData("price_range");
				return true;
			}
			return false;
		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限");
		 * openPrePaidListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "0-50");
		 * openPrePaidListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "51-100");
		 * openPrePaidListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "101-300");
		 * openPrePaidListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "301-500");
		 * openPrePaidListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "500以上");
		 * openPrePaidListData.add(map);
		 * 
		 * return true;
		 */
	}

	private boolean queryNumberTypeList() {

		openNumberTypeListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("perrty_type") != null) {
			openNumberTypeListData = getDataFromCodeListData("perrty_type");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("perrty_type");
			if (getCodeListInfo(request)) {
				openNumberTypeListData = getDataFromCodeListData("perrty_type");
				return true;
			}
			return false;
		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限");
		 * openNumberTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "ABCDE");
		 * openNumberTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "AAAA");
		 * openNumberTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "ABCD");
		 * openNumberTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "AAA");
		 * openNumberTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "AA");
		 * openNumberTypeListData.add(map);
		 * 
		 * return true;
		 */
	}

	/**
	 * 
	 * @return
	 */
	private boolean getSelectNumberList() {
		DataTask dataTask = new DataTask() {
			@Override
			public void onPreExecute() {
				showProgressDialog();
			}

			@Override
			public boolean doInBackground() {
				boolean flag = querySelectNumberList(false);
				if (flag) {
					return true;
				}
				return false;
			}

			@Override
			public void showErr() {
				showToastMsg("从服务端获取号码失败");
			}

			@Override
			public void showData() {
				createQuetySelectNumberList();
			}

		};

		prepareData(true, dataTask);

		return true;

	}

	/**
	 * 
	 * @param isInit
	 *            判断是否为初始化号码页面
	 * @return
	 */
	private boolean querySelectNumberList(boolean isInit) {

		Log.i(TAG, "进入querySelectNumberList: isInit = " + isInit);

		String jsessionid = appCache.getAsString("jsessionid");

		if (isInit) {
			SaleSelectNumberRequest request = new SaleSelectNumberRequest();

			String teleType = Constants.SELECT_NUMBER_DEFAULT_TELE_TYPE;
			String mobSection = "";
			String priceRange = "";
			String perrtyType = "";

			String selectCount = new Integer(Constants.SELECT_NUMBER_SHOW_NUM).toString();

			request.setTeleType(teleType);
			request.setMobSection(mobSection);
			request.setPriceRange(priceRange);
			request.setPerrtyType(perrtyType);
			request.setSelectCount(selectCount);
			request.setJsessionid(jsessionid);

			boolean flag = getSaleSelectNumberInfo(request);
			if (flag) {
				return true;
			}
			return false;

		} else {
			SaleSelectNumberRequest request = new SaleSelectNumberRequest();

			String teleType = page1TeleTypeValue.getText().toString();
			String mobSection = page1NumberDefineValue.getText().toString();
			String priceRange = page1PrePaidValue.getText().toString();
			String perrtyType = page1NumberTypeValue.getText().toString();

			if (teleType.equals("不限"))
				teleType = "";
			if (mobSection.equals("不限"))
				mobSection = "";
			if (priceRange.equals("不限"))
				priceRange = "";
			if (perrtyType.equals("不限"))
				perrtyType = "";

			String selectCount = new Integer(Constants.SELECT_NUMBER_SHOW_NUM).toString();

			request.setTeleType(teleType);
			request.setMobSection(mobSection);
			request.setPriceRange(priceRange);
			request.setPerrtyType(perrtyType);
			request.setSelectCount(selectCount);
			request.setJsessionid(jsessionid);

			boolean flag = getSaleSelectNumberInfo(request);
			if (flag) {
				return true;
			}
			return false;
		}
	}

	private boolean getSaleSelectNumberInfo(SaleSelectNumberRequest request) {
		// 业务参数
		RestParameters params = ObjectToRestParamUtils.transSaleSelectNumberRequestToParam(request);

		client.apiPost(ApiUrls.SALE_SELECT_NUMBER, params, new RestApiListener() {
			@Override
			public void onComplete(JSONObject json) {
				// Log.e(TAG, json.toString());
				SaleSelectNumberResponse saleSelectNumberResponse = JsonToBeanUtils.parseToSaleSelectNumberResponse(json);
				openSelectNumberListData = saleSelectNumberResponse.getNumberList();

				Log.i(TAG, "openSelectNumberListData: " + openSelectNumberListData);
			}

			@Override
			public void onError(ApiError error) {
				Log.e(TAG, error.toString());
				if (!StringUtils.isBlank(error.getContent())) {
					showToastMsg(error.getContent());
				}
			}

			@Override
			public void onException(Exception e) {

			}
		}, false);

		return true;
	}

	private boolean queryPackageTypeList() {
		openPackageTypeListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("package_type") != null) {
			openPackageTypeListData = getDataFromCodeListData("package_type");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("package_type");
			if (getCodeListInfo(request)) {
				openPackageTypeListData = getDataFromCodeListData("package_type");
				return true;
			}
			return false;
		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限");
		 * openPackageTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "4G/3G一体化套餐");
		 * openPackageTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "自由组合套餐");
		 * openPackageTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "更多本地通话");
		 * openPackageTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "更多上网流量");
		 * openPackageTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "20元卡");
		 * openPackageTypeListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "本地语音及流量套餐");
		 * openPackageTypeListData.add(map);
		 * 
		 * return true;
		 */
	}

	private boolean queryMonthConsumptionList() {

		openMonthConsumptionListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("month_fee") != null) {
			openMonthConsumptionListData = getDataFromCodeListData("month_fee");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("month_fee");
			if (getCodeListInfo(request)) {
				openMonthConsumptionListData = getDataFromCodeListData("month_fee");
				return true;
			}
			return false;
		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限");
		 * openMonthConsumptionListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "50元以下");
		 * openMonthConsumptionListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "50元-99元");
		 * openMonthConsumptionListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "100元-199元");
		 * openMonthConsumptionListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "200元-299元");
		 * openMonthConsumptionListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "300元以上");
		 * openMonthConsumptionListData.add(map);
		 * 
		 * return true;
		 */
	}

	private boolean queryMonthCallList() {
		openMonthCallListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("month_call_duration") != null) {
			openMonthCallListData = getDataFromCodeListData("month_call_duration");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("month_call_duration");
			if (getCodeListInfo(request)) {
				openMonthCallListData = getDataFromCodeListData("month_call_duration");
				return true;
			}
			return false;
		}

		/*
		 * openMonthCallListData = new ArrayList<Map<String, Object>>();
		 * 
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限");
		 * openMonthCallListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "100分钟以下");
		 * openMonthCallListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "100分钟-199分钟");
		 * openMonthCallListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "200分钟-399分钟");
		 * openMonthCallListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "400分钟-599分钟");
		 * openMonthCallListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "600分钟以上");
		 * openMonthCallListData.add(map);
		 * 
		 * return true;
		 */
	}

	private boolean queryMonthNetList() {
		openMonthNetListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("month_net_duration") != null) {
			openMonthNetListData = getDataFromCodeListData("month_net_duration");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("month_net_duration");
			if (getCodeListInfo(request)) {
				openMonthNetListData = getDataFromCodeListData("month_net_duration");
				return true;
			}
			return false;
		}

		/*
		 * openMonthNetListData = new ArrayList<Map<String, Object>>();
		 * 
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("PIC", R.drawable.icon_activity); map.put("TITLE", "不限");
		 * openMonthNetListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "150MB以下");
		 * openMonthNetListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "150-299MB");
		 * openMonthNetListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "300-599MB");
		 * openMonthNetListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "600-999MB");
		 * openMonthNetListData.add(map);
		 * 
		 * map = new HashMap<String, Object>(); map.put("PIC",
		 * R.drawable.icon_activity); map.put("TITLE", "1GB以上");
		 * openMonthNetListData.add(map);
		 * 
		 * return true;
		 */
	}

	/**
	 * 
	 * @return
	 */
	private boolean getSelectPackageList() {
		DataTask dataTask = new DataTask() {
			@Override
			public void onPreExecute() {
				showProgressDialog();
			}

			@Override
			public boolean doInBackground() {
				boolean flag = querySelectPackageList(false);
				if (flag) {
					return true;
				}
				return false;
			}

			@Override
			public void showErr() {
				showToastMsg("从服务端获取套餐失败");
			}

			@Override
			public void showData() {
				createQuerySelectPackageList();
			}

		};

		prepareData(true, dataTask);

		return true;

	}

	/**
	 * 
	 * @param isInit
	 *            判断是否为初始化选套餐页面
	 * @return
	 */
	private boolean querySelectPackageList(boolean isInit) {
		// TODO

		Log.i(TAG, "进入querySelectPackageList: isInit = " + isInit);

		String jsessionid = appCache.getAsString("jsessionid");

		if (isInit) {
			SaleSelectedCodeRequest request = new SaleSelectedCodeRequest();

			String teleType = Constants.SELECT_NUMBER_DEFAULT_TELE_TYPE;
			String ofrSubType = "";
			String monthFee = "";
			String monthCallDuration = "";
			String monthNetDuration = "";
			String ofrName = "";

			request.setTeleType(teleType);
			request.setOfrSubType(ofrSubType);
			request.setMonthFee(monthFee);
			request.setMonthCallDuration(monthCallDuration);
			request.setMonthNetDuration(monthNetDuration);
			request.setOfrName(ofrName);
			// request.setDeviceNumber(deviceNumber);
			request.setDeviceNumber("");
			request.setJsessionid(jsessionid);

			boolean flag = getSaleSelectPackageInfo(request);
			if (flag) {
				return true;
			}
			return false;

		} else {
			SaleSelectedCodeRequest request = new SaleSelectedCodeRequest();

			String teleType = page1TeleTypeValue.getText().toString();
			String ofrSubType = page2PackageTypeValue.getText().toString();
			String monthFee = page2MonthConsumptionValue.getText().toString();
			String monthCallDuration = page2MonthCallValue.getText().toString();
			String monthNetDuration = page2MonthNetValue.getText().toString();
			String ofrName = page2SearchingOfrName.getText().toString();
			String deviceNumber = page1SelectedPhone.getText().toString();

			if (teleType.equals("不限"))
				teleType = "";
			if (ofrSubType.equals("不限"))
				ofrSubType = "";
			if (monthFee.equals("不限"))
				monthFee = "";
			if (monthCallDuration.equals("不限"))
				monthCallDuration = "";
			if (monthNetDuration.equals("不限"))
				monthNetDuration = "";
			if (ofrName.equals("不限"))
				ofrName = "";

			request.setTeleType(teleType);
			request.setOfrSubType(ofrSubType);
			request.setMonthFee(monthFee);
			request.setMonthCallDuration(monthCallDuration);
			request.setMonthNetDuration(monthNetDuration);
			request.setOfrName(ofrName);
			request.setDeviceNumber(deviceNumber);
			request.setJsessionid(jsessionid);

			boolean flag = getSaleSelectPackageInfo(request);
			if (flag) {
				return true;
			}
			return false;
		}

	}

	private boolean getSaleSelectPackageInfo(SaleSelectedCodeRequest request) {

		// 业务参数
		RestParameters params = ObjectToRestParamUtils.transSaleSelectedCodeRequestToParam(request);

		client.apiPost(ApiUrls.SALE_SELECT_CODE, params, new RestApiListener() {
			@Override
			public void onComplete(JSONObject json) {
				// Log.e(TAG, json.toString());
				SaleSelectedCodeResponse saleSelectedCodeResponse = JsonToBeanUtils.parseToSaleSelectedCodeResponse(json);
				openSelectPackageListData = saleSelectedCodeResponse.getSaleList();
			}

			@Override
			public void onError(ApiError error) {
				Log.e(TAG, error.toString());
				if (!StringUtils.isBlank(error.getContent())) {
					showToastMsg(error.getContent());
				}
			}

			@Override
			public void onException(Exception e) {

			}
		}, false);

		return true;
	}

	private List<Map<String, Object>> getDataFromCodeListData(String codeType) {
		// TODO 每次添加一个codelist，都需要修改此函数
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		if (codeType.equals("mob_section")) {
			List<Map<String, Object>> codeList = codeListData.get("mob_section");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			Map<String, Object> unLimitMap = new HashMap<String, Object>();
			unLimitMap.put("PIC", R.drawable.icon_activity);
			unLimitMap.put("TITLE", "不限");
			unLimitMap.put("ID", "-1");
			dataList.add(unLimitMap);
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("tele_type")) {
			List<Map<String, Object>> codeList = codeListData.get("tele_type");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			/*
			 * Map<String, Object> unLimitMap = new HashMap<String, Object>();
			 * unLimitMap.put("PIC", R.drawable.icon_activity);
			 * unLimitMap.put("TITLE", "不限"); unLimitMap.put("ID", "-1");
			 * dataList.add(unLimitMap);
			 */
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("price_range")) {
			List<Map<String, Object>> codeList = codeListData.get("price_range");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			Map<String, Object> unLimitMap = new HashMap<String, Object>();
			unLimitMap.put("PIC", R.drawable.icon_activity);
			unLimitMap.put("TITLE", "不限");
			unLimitMap.put("ID", "-1");
			dataList.add(unLimitMap);
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("perrty_type")) {
			List<Map<String, Object>> codeList = codeListData.get("perrty_type");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			Map<String, Object> unLimitMap = new HashMap<String, Object>();
			unLimitMap.put("PIC", R.drawable.icon_activity);
			unLimitMap.put("TITLE", "不限");
			unLimitMap.put("ID", "-1");
			dataList.add(unLimitMap);
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("package_type")) {
			List<Map<String, Object>> codeList = codeListData.get("package_type");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			Map<String, Object> unLimitMap = new HashMap<String, Object>();
			unLimitMap.put("PIC", R.drawable.icon_activity);
			unLimitMap.put("TITLE", "不限");
			unLimitMap.put("ID", "-1");
			dataList.add(unLimitMap);
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("month_fee")) {
			List<Map<String, Object>> codeList = codeListData.get("month_fee");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			Map<String, Object> unLimitMap = new HashMap<String, Object>();
			unLimitMap.put("PIC", R.drawable.icon_activity);
			unLimitMap.put("TITLE", "不限");
			unLimitMap.put("ID", "-1");
			dataList.add(unLimitMap);
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("month_call_duration")) {
			List<Map<String, Object>> codeList = codeListData.get("month_call_duration");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			Map<String, Object> unLimitMap = new HashMap<String, Object>();
			unLimitMap.put("PIC", R.drawable.icon_activity);
			unLimitMap.put("TITLE", "不限");
			unLimitMap.put("ID", "-1");
			dataList.add(unLimitMap);
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("month_net_duration")) {
			List<Map<String, Object>> codeList = codeListData.get("month_net_duration");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			Map<String, Object> unLimitMap = new HashMap<String, Object>();
			unLimitMap.put("PIC", R.drawable.icon_activity);
			unLimitMap.put("TITLE", "不限");
			unLimitMap.put("ID", "-1");
			dataList.add(unLimitMap);
			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("id_type")) {
			List<Map<String, Object>> codeList = codeListData.get("id_type");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("payment_type")) {
			List<Map<String, Object>> codeList = codeListData.get("payment_type");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}
		if (codeType.equals("cust_sex")) {
			List<Map<String, Object>> codeList = codeListData.get("cust_sex");
			if (codeList == null || codeList.size() == 0)
				return dataList;

			for (Map<String, Object> map : codeList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("PIC", R.drawable.icon_activity);
				dataMap.put("TITLE", map.get("code_name"));
				dataMap.put("ID", map.get("code_id"));
				dataList.add(dataMap);
			}
		}

		// Log.i(TAG, "openNumberDefineListData: " + openNumberDefineListData);
		return dataList;
	}

	/**
	 * 获取codeList的方法
	 * 
	 * @param request
	 * @return
	 */
	private boolean getCodeListInfo(CodeListRequest request) {

		if (StringUtils.isBlank(request.getCodeType()))
			return false;

		if (codeListData == null) {
			codeListData = new HashMap<String, List<Map<String, Object>>>();
		}

		RestParameters params = new RestParameters();
		final String codeType = request.getCodeType();
		params.addParam("code_type", codeType);

		client.apiGet(ApiUrls.GET_CODE_LIST, params, new RestApiListener() {

			@Override
			public void onComplete(JSONObject json) {
				// Log.e(TAG, json.toString());
				CodeListResponse codeListResponse = JsonToBeanUtils.parseToCodeListResponse(json);
				List<Map<String, Object>> codeList = codeListResponse.getCodeList();
				if (codeList != null) {
					codeListData.put(codeType, codeList);
				} else {
					showToastMsg("服务没有返回" + codeType + "的codelist数据！");
				}

			}

			@Override
			public void onError(ApiError error) {
				Log.e(TAG, error.toString());

			}

			@Override
			public void onException(Exception e) {

			}
		}, false);
		return true;

	}

	private boolean setActionSelectList() {

		actionSelectListData = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TITLE", "参加");
		map.put("flag", 1);
		actionSelectListData.add(map);

		map = new HashMap<String, Object>();
		map.put("TITLE", "不参加");
		map.put("flag", 0);
		actionSelectListData.add(map);

		return true;
	}

	/**
	 * 发起调用 客户资料校验 接口
	 * 
	 * @param checkCustInfoRequest
	 * @return
	 */
	private void startRestCheckCustInfo(CheckCustInfoRequest checkCustInfoRequest) {

		Log.i(TAG, "调用 客户资料校验 接口");

		customerVerifyResponse = null;
		// 业务参数
		RestParameters params = ObjectToRestParamUtils.transCheckCustInfoRequestToParam(checkCustInfoRequest);

		client.apiPost(ApiUrls.CHECK_CUST_INFO, params, new RestApiListener() {
			@Override
			public void onComplete(JSONObject json) {
				customerVerifyResponse = JsonToBeanUtils.parseToCustomerVerifyResponse(json);
				Log.i(TAG, "data return");
				customerVerifyResponse.print_cust_info();
				// 把返回信息输出到屏幕
				page4_cust_name.setText(customerVerifyResponse.getCustomerName());
				page4_cust_addr.setText(customerVerifyResponse.getCustomerAddr());
				page4_cust_sex.setText(customerVerifyResponse.getCustomerSex().equals("1") ? "男性" : "女性");
				page4_cust_cert_exp.setText(customerVerifyResponse.getCertExpireDate());
				page4_cust_zipcode.setText(customerVerifyResponse.getCustomerZip());
				page4_cust_email.setText(customerVerifyResponse.getCustomerEmail());
				page4_contact_name.setText(customerVerifyResponse.getContactPerson());
				page4_contact_tel.setText(customerVerifyResponse.getContactPhone());
				page4_contact_addr.setText(customerVerifyResponse.getContactAddr());
				saleOpenOrderSubmitRequest.setCustomerId(customerVerifyResponse.getCustomerId());
			}

			@Override
			public void onError(ApiError error) {
				showToastMsg("证件号码不存在");
				Log.i(TAG, error.toString());

				page4_cust_name.setText("");
				page4_cust_addr.setText("");
				page4_cust_sex.setText("");
				page4_cust_cert_exp.setText("");
				page4_cust_zipcode.setText("");
				page4_cust_email.setText("");
				page4_contact_name.setText("");
				page4_contact_tel.setText("");
				page4_contact_addr.setText("");
			}

			@Override
			public void onException(Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}, false);
	}

	/**
	 * 发起调用 订单提交 接口
	 * 
	 * @param saleOpenOrderSubmitRequest
	 */
	private Boolean startRestOrderSubmit(SaleOpenOrderSubmitRequest saleOpenOrderSubmitRequest) {

		restResult = false;

		Log.i(TAG, "调用 订单提交接口");
		// 业务参数
		RestParameters params = ObjectToRestParamUtils.transSaleOpenOrderSubmitRequestToParam(saleOpenOrderSubmitRequest);
		Map<String, String> map = params.getParams();
		Set<Entry<String, String>> set = map.entrySet();
		for (Entry<String, String> entry : set) {
			Log.i(TAG, entry.getKey() + "   =   " + entry.getValue());
		}

		client.apiPost(ApiUrls.SALE_OPEN_ORDER_SUBMIT, params, new RestApiListener() {
			@Override
			public void onComplete(JSONObject json) {
				restResult = true;
				SaleOpenResponse saleOpenResponse = JsonToBeanUtils.parseToSaleOpenResponse(json);
				String orderId = saleOpenResponse.getOrderId();
				openFinishMsg.setText("\n\n\n   订单提交成功，订单号 = [ " + orderId + " ]");
			}

			@Override
			public void onError(ApiError error) {
				Log.i(TAG, error.toString());

			}

			@Override
			public void onException(Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}, false);

		return restResult;
	}

	private void createIdTypeList() {
		openListAdapter_IdType = new OpenListAdapter(this, openIdTypeListData);
		Log.i("IdType", "size=" + openIdTypeListData.size());

		openListAdapter_IdType.setSelectedPosition(0);
		openIdTypeList.setAdapter(openListAdapter_IdType);

		openIdTypeList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openIdTypeList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page4CertType.setText(title);
				posIdTypeSelected = arg2;

				openListAdapter_IdType.setSelectedPosition(arg2);
				openListAdapter_IdType.notifyDataSetInvalidated();

				editWindow.dismiss();
			}
		});
	}

	private boolean queryIdTypeList() {

		// openIdTypeListData = new ArrayList<Map<String, Object>>();

		if (codeListData != null && codeListData.get("id_type") != null) {
			openIdTypeListData = getDataFromCodeListData("id_type");
			page4CertType.setText(openIdTypeListData.get(posIdTypeSelected).get("TITLE").toString());
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("id_type");
			if (getCodeListInfo(request)) {
				openIdTypeListData = getDataFromCodeListData("id_type");
				page4CertType.setText(openIdTypeListData.get(posIdTypeSelected).get("TITLE").toString());
				return true;
			}
			return false;
		}
	}

	private void createPayTypeList() {
		openListAdapter_PayType = new OpenListAdapter(this, openPayTypeListData);

		openListAdapter_PayType.setSelectedPosition(0);
		openPayTypeList.setAdapter(openListAdapter_PayType);

		openPayTypeList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openPayTypeList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page4PayType.setText(title);
				posPayTypeSelected = arg2;
				Log.i(TAG, "paytype=" + openPayTypeListData.get(posPayTypeSelected).get("ID").toString());

				openListAdapter_PayType.setSelectedPosition(arg2);
				openListAdapter_PayType.notifyDataSetInvalidated();

				editWindow.dismiss();
			}
		});
	}

	private boolean queryPayTypeList() {
		if (codeListData != null && codeListData.get("payment_type") != null) {
			openPayTypeListData = getDataFromCodeListData("payment_type");
			page4PayType.setText(openPayTypeListData.get(posPayTypeSelected).get("TITLE").toString());
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("payment_type");
			if (getCodeListInfo(request)) {
				openPayTypeListData = getDataFromCodeListData("payment_type");
				page4PayType.setText(openPayTypeListData.get(posPayTypeSelected).get("TITLE").toString());
				return true;
			}
			return false;
		}
	}

	private void createCustSexList() {
		openListAdapter_CustSex = new OpenListAdapter(this, openCustSexListData);

		openListAdapter_CustSex.setSelectedPosition(0);
		openCustSexList.setAdapter(openListAdapter_CustSex);

		openCustSexList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Log.i(getClass().getName(), "position=" + arg2 + " " + arg3);
				Map<String, Object> item = (HashMap<String, Object>) openCustSexList.getItemAtPosition(arg2);
				String title = String.valueOf(item.get("TITLE"));
				page4CustSex.setText(title);
				posCustSexSelected = arg2;
				Log.i(TAG, "custsex=" + openCustSexListData.get(posCustSexSelected).get("ID").toString());

				openListAdapter_CustSex.setSelectedPosition(arg2);
				openListAdapter_CustSex.notifyDataSetInvalidated();

				editWindow.dismiss();
			}
		});
	}

	private boolean queryCustSexList() {
		if (codeListData != null && codeListData.get("cust_sex") != null) {
			openCustSexListData = getDataFromCodeListData("cust_sex");
			return true;
		} else {
			CodeListRequest request = new CodeListRequest();
			request.setCodeType("cust_sex");
			if (getCodeListInfo(request)) {
				openCustSexListData = getDataFromCodeListData("cust_sex");
				return true;
			}
			return false;
		}
	}

	public String getPage1SelectedPhone() {
		return page1SelectedPhone.getText().toString();
	}

	/**
	 * 验证产品界面是否选择套餐
	 * 
	 * @return
	 */
	public Boolean verifyIsCompletedProductView() {
		String selectedPackage = page2SelectedPackage.getText().toString();
		if (StringUtils.isBlank(selectedPackage) || selectedPackage.equals("未选择套餐")) {
			return false;
		}
		return true;
	}

	/**
	 * 验证客户信息界面必填项是否完成
	 * 
	 * @return
	 */
	public Boolean verifyIsCompletedCustInfoView() {
		if (StringUtils.isBlank(page4CertNumberValue.getText().toString()) || StringUtils.isBlank(page4CertAddrValue.getText().toString()) || StringUtils.isBlank(page4CertExpDate.getText().toString()) || StringUtils.isBlank(page4UserNameValue.getText().toString()) || StringUtils.isBlank(page4_cust_sex.getText().toString()))
			return false;
		else
			return true;
	}

}
