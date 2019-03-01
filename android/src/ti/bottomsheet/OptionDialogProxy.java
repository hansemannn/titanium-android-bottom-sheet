/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2017 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.bottomsheet;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiC;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

import java.util.Arrays;

import de.mrapp.android.bottomsheet.BottomSheet;

@Kroll.proxy(creatableInModule=TitaniumAndroidBottomSheetModule.class)
public class OptionDialogProxy extends KrollProxy
{
	// Standard Debugging variables
	private static final String LCAT = "OptionDialogProxy";
	private static final boolean DBG = TiConfig.LOGD;

	private String title;
	private String[] options;
	private boolean cancelable;

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict args)
	{
		super.handleCreationDict(args);

		title = args.getString("title");
		options = TiConvert.toStringArray((Object[]) args.get("options"));
		cancelable = args.optBoolean("cancelable", true);
	}

	// Methods

	@Kroll.method
	public void show() {
		BottomSheet.Builder builder = new BottomSheet.Builder(TiApplication.getAppCurrentActivity());
		builder.setTitle(title);
		builder.setCancelable(cancelable);

		// TODO: Export all styles
		builder.setStyle(BottomSheet.Style.LIST);

		int i = 0;

		for (String option : options) {
			// TODO: Support image options
			// TODO: Support named dividers
			if (option != null) {
				builder.addItem(i, option);
			} else {
				builder.addDivider();
			}
			i++;
		}

		builder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				KrollDict event = new KrollDict();
				event.put("index", position);
                event.put("cancel", false);
				fireEvent("click", event);
			}
		});

		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				KrollDict event = new KrollDict();
				event.put("index", -1);
				event.put("cancel", true);
				fireEvent("click", event);
			}
		});

		builder.create().show();
	}
}
