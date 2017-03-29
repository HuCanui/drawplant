package com.hucanhui.finaldesign;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class WebActivity extends Activity {
	private WebView	webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		webView = new WebView(this);
		setContentView(webView);
		//����֧��JavaScript�ű�
		WebSettings webSettings = webView.getSettings();  
		webSettings.setJavaScriptEnabled(true);
		//���ÿ��Է����ļ�
		webSettings.setAllowFileAccess(true);
		//����֧������
		webSettings.setBuiltInZoomControls(true);
		try {
	    	String url = "http://www.baidu.com/";
	    	//�ж�����������ǲ�����ַ
	    	if ( URLUtil.isNetworkUrl(url) ) {  
	    		//װ����ַ
	    		webView.loadUrl(url);
			}
		}
		catch (Exception e) {
		}
		//����WebViewClient
		webView.setWebViewClient(new WebViewClient() {   		    
			public boolean shouldOverrideUrlLoading(WebView view, String url) {   
		        view.loadUrl(url);   
		        return true;   
		    }  
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
		});
		//����WebChromeClient
		webView.setWebChromeClient(new WebChromeClient(){		
			@Override
			//����javascript�е�alert
			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
				//����һ��Builder����ʾ��ҳ�еĶԻ���
				Builder builder = new Builder(WebActivity.this);
				builder.setTitle("��ʾ�Ի���");
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								//���ȷ����ť֮��,����ִ����ҳ�еĲ���
								result.confirm();
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};
			@Override
			//����javascript�е�confirm
			public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {				
				Builder builder = new Builder(WebActivity.this);
				builder.setTitle("��ѡ��ĶԻ���");
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {							
					public void onClick(DialogInterface dialog, int which) {
						result.confirm();
					}
				});
				builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {							
					public void onClick(DialogInterface dialog, int which) {	
						result.cancel();					}

				});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};
			@Override
			//����javascript�е�prompt
			//messageΪ��ҳ�жԻ������ʾ����
			//defaultValue��û������ʱ��Ĭ����ʾ������
			public boolean onJsPrompt(WebView view, String url, String message,
					String defaultValue, final JsPromptResult result) {
				//�Զ���һ��������ĶԻ�����TextView��EditText����
				final LayoutInflater factory = LayoutInflater.from(WebActivity.this);
				final View dialogview = factory.inflate(R.layout.web_dialog, null);
				//����TextView��Ӧ��ҳ�е���ʾ��Ϣ
				((TextView) dialogview.findViewById(R.id.textview_prom)).setText(message);
				//����EditText��Ӧ��ҳ�е������
				((EditText) dialogview.findViewById(R.id.edittext_prom)).setText(defaultValue);				
				Builder builder = new Builder(WebActivity.this);
				builder.setTitle("������ĶԻ���");
				builder.setView(dialogview);
				builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {						
					public void onClick(DialogInterface dialog, int which) {
						//���ȷ��֮��ȡ�������ֵ��������ҳ����
						String value = ((EditText) dialogview.findViewById(R.id.edittext_prom)).
						getText().toString();
						result.confirm(value);	
					}
				});
				builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {							
					public void onClick(DialogInterface dialog, int which) {
						result.cancel();
					}
				});
				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					public void onCancel(DialogInterface dialog) {
						result.cancel();
					}
				});
				builder.show();
				return true;
			};
			@Override
			//������ҳ���صĽ�����
			public void onProgressChanged(WebView view, int newProgress) {
				WebActivity.this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}
			@Override
			//����Ӧ�ó���ı���title
			public void onReceivedTitle(WebView view, String title) {
				WebActivity.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
	}	
}
